package com.firemerald.additionalplacements.network.client;

import java.util.HashMap;
import java.util.Map;

import com.firemerald.additionalplacements.generation.Registration;
import com.firemerald.additionalplacements.network.server.CheckDataServerPacket;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.network.CustomPayloadEvent;

public class CheckDataClientPacket extends ClientPlayPacket
{
	private final Map<ResourceLocation, CompoundTag> data;

	public CheckDataClientPacket() {
		data = new HashMap<>();
		Registration.forEach((id, type) -> {
			CompoundTag tag = type.getServerCheckData();
			if (tag != null) data.put(id, tag);
		});
	}

	public CheckDataClientPacket(FriendlyByteBuf buf)
	{
		data = buf.readMap(FriendlyByteBuf::readResourceLocation, FriendlyByteBuf::readNbt);
	}

	@Override
	public void write(FriendlyByteBuf buf)
	{
		buf.writeMap(data, FriendlyByteBuf::writeResourceLocation, FriendlyByteBuf::writeNbt);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void handleClient(CustomPayloadEvent.Context context)
	{
		new CheckDataServerPacket(data).reply(context);
	}
}