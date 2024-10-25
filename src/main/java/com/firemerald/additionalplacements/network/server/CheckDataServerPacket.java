package com.firemerald.additionalplacements.network.server;

import java.util.*;
import java.util.function.Consumer;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

import com.firemerald.additionalplacements.generation.Registration;
import com.firemerald.additionalplacements.network.client.ConfigurationCheckFailedPacket;
import com.firemerald.additionalplacements.util.BufferUtils;
import com.firemerald.additionalplacements.util.MessageTree;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.network.NetworkEvent;

public class CheckDataServerPacket extends ServerLoginPacket
{
	private final Map<ResourceLocation, Pair<CompoundNBT, List<MessageTree>>> serverData;
	
	@SuppressWarnings("unchecked")
	public CheckDataServerPacket(Map<ResourceLocation, CompoundNBT> serverData) {
		this.serverData = new HashMap<>();
		Registration.forEach((id, type) -> {
			CompoundNBT clientTag = type.getClientCheckData();
			List<MessageTree> clientErrors = new ArrayList<>();
			CompoundNBT serverTag = serverData.get(id);
			type.checkServerData(serverTag, (Consumer<MessageTree>) clientErrors::add);
			if (clientTag != null || !clientErrors.isEmpty()) this.serverData.put(id, Pair.of(clientTag, clientErrors));
		});
	}

	public CheckDataServerPacket(PacketBuffer buf)
	{
		serverData = BufferUtils.readMap(buf, PacketBuffer::readResourceLocation, buf2 -> {
			CompoundNBT clientTag = buf2.readNbt();
			List<MessageTree> clientErrors = BufferUtils.readList(buf2, MessageTree::new);
			return Pair.of(clientTag, clientErrors);
		});
	}

	@Override
	public void write(PacketBuffer buf)
	{
		BufferUtils.writeMap(buf, serverData, PacketBuffer::writeResourceLocation, (buf2, data) -> {
			buf2.writeNbt(data.getLeft());
			BufferUtils.writeCollection(buf2, data.getRight(), MessageTree::write);
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public void handleServer(NetworkEvent.Context context) {
		context.setPacketHandled(true);
		List<Triple<ResourceLocation, List<MessageTree>, List<MessageTree>>> compiledErrors = new ArrayList<>();
		Registration.forEach((id, type) -> {
			Pair<CompoundNBT, List<MessageTree>> clientData = this.serverData.get(id);
			CompoundNBT clientTag;
			List<MessageTree> clientErrors;
			if (clientData != null) {
				clientTag = clientData.getLeft();
				clientErrors = clientData.getRight();
			} else {
				clientTag = null;
				clientErrors = Collections.emptyList();
			}
			List<MessageTree> serverErrors = new ArrayList<>();
			type.checkClientData(clientTag, (Consumer<MessageTree>) serverErrors::add);
			if (!clientErrors.isEmpty() || !serverErrors.isEmpty()) compiledErrors.add(Triple.of(id, clientErrors, serverErrors));
		});
		if (!compiledErrors.isEmpty()) {
			new ConfigurationCheckFailedPacket(compiledErrors).reply(context);
			//if it turns out this CAN prevent the above packet from being sent, move disconnect to client-side. It did not prevent it in testing.
			context.getNetworkManager().disconnect(new TranslationTextComponent("msg.additionalplacements.disconnected"));
		}
	}
}