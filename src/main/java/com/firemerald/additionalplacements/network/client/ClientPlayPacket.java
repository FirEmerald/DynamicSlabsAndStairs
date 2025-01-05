package com.firemerald.additionalplacements.network.client;

import com.firemerald.additionalplacements.network.APNetwork;
import com.firemerald.additionalplacements.network.APPacket;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking.Context;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;

public abstract class ClientPlayPacket extends APPacket<RegistryFriendlyByteBuf> {
	@Environment(EnvType.CLIENT)
	public abstract void handleClient(Context context);

    public void sendToClient(ServerPlayer player) {
    	APNetwork.sendToClient(this, player);
    }
}
