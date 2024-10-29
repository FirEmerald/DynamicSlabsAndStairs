package com.firemerald.additionalplacements.network.server;

import com.firemerald.additionalplacements.network.APNetwork;
import com.firemerald.additionalplacements.network.APPacket;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;

public abstract class ServerPlayPacket extends APPacket {
	public abstract void handleServer(MinecraftServer server, ServerPlayer player, ServerGamePacketListenerImpl handler, PacketSender responseSender);
	
    public void sendToServer()
    {
    	APNetwork.sendToServer(this);
    }
}
