package com.firemerald.additionalplacements.network.client;

import com.firemerald.additionalplacements.network.APNetwork;
import com.firemerald.additionalplacements.network.APPacket;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.server.level.ServerPlayer;

public abstract class ClientPlayPacket extends APPacket {
	@Environment(EnvType.CLIENT)
	public abstract void handleClient(Minecraft client, ClientPacketListener handler, PacketSender responseSender);

    public void sendToClient(ServerPlayer player) {
    	APNetwork.sendToClient(this, player);
    }
}
