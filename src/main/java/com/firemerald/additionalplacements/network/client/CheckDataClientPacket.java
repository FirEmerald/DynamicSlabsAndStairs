package com.firemerald.additionalplacements.network.client;

import java.util.*;

import com.firemerald.additionalplacements.AdditionalPlacementsMod;
import com.firemerald.additionalplacements.generation.Registration;
import com.firemerald.additionalplacements.network.server.CheckDataServerPacket;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientConfigurationPacketListenerImpl;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

public class CheckDataClientPacket extends ClientConfigurationPacket
{
	public static final ResourceLocation ID = new ResourceLocation(AdditionalPlacementsMod.MOD_ID, "check_data_client");
	
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
	public ResourceLocation getID()
	{
		return ID;
	}

	@Override
	public void write(FriendlyByteBuf buf)
	{
		buf.writeMap(data, FriendlyByteBuf::writeResourceLocation, FriendlyByteBuf::writeNbt);
	}

	@Override
	@Environment(EnvType.CLIENT)
	public void handleClient(Minecraft client, ClientConfigurationPacketListenerImpl handler, PacketSender responseSender) {
		new CheckDataServerPacket(data).send(responseSender);
	}
}