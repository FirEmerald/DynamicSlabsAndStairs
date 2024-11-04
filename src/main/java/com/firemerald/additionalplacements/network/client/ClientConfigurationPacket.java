package com.firemerald.additionalplacements.network.client;

import com.firemerald.additionalplacements.network.APPacket;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientConfigurationNetworking.Context;
import net.minecraft.network.FriendlyByteBuf;

public abstract class ClientConfigurationPacket extends APPacket<FriendlyByteBuf> {
	@Environment(EnvType.CLIENT)
	public abstract void handleClient(Context context);
}
