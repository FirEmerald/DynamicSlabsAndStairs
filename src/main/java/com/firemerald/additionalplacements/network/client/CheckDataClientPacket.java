package com.firemerald.additionalplacements.network.client;

import java.util.*;

import com.firemerald.additionalplacements.generation.Registration;
import com.firemerald.additionalplacements.network.server.CheckDataServerPacket;
import com.firemerald.additionalplacements.util.BufferUtils;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkEvent;

public class CheckDataClientPacket extends ClientLoginPacket
{
	private final Map<ResourceLocation, CompoundNBT> data;
	
	public CheckDataClientPacket() {
		data = new HashMap<>();
		Registration.forEach((id, type) -> {
			CompoundNBT tag = type.getServerCheckData();
			if (tag != null) data.put(id, tag);
		});
	}

	public CheckDataClientPacket(PacketBuffer buf)
	{
		data = BufferUtils.readMap(buf, PacketBuffer::readResourceLocation, PacketBuffer::readNbt);
	}

	@Override
	public void write(PacketBuffer buf)
	{
		BufferUtils.writeMap(buf, data, PacketBuffer::writeResourceLocation, PacketBuffer::writeNbt);
	}

	@Override
	public void handle(NetworkEvent.Context context) {
		context.setPacketHandled(true);
		new CheckDataServerPacket(data).reply(context);
	}
}