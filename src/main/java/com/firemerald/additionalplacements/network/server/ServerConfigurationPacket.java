package com.firemerald.additionalplacements.network.server;

import com.firemerald.additionalplacements.network.APPacket;

import net.fabricmc.fabric.api.networking.v1.ServerConfigurationNetworking.Context;
import net.minecraft.network.FriendlyByteBuf;

public abstract class ServerConfigurationPacket extends APPacket<FriendlyByteBuf> {
	public abstract void handleServer(Context context);
}
