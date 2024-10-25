package com.firemerald.additionalplacements.network.client;

import com.firemerald.additionalplacements.network.APNetwork;
import com.firemerald.additionalplacements.network.APPacket;

import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.network.CustomPayloadEvent;
import net.minecraftforge.network.NetworkDirection;

public abstract class ClientPlayPacket extends APPacket {
	@Override
	public NetworkDirection getDirection() {
		return NetworkDirection.PLAY_TO_CLIENT;
	}
	
	@OnlyIn(Dist.CLIENT)
	public abstract void handleClient(CustomPayloadEvent.Context context);
	
	@Override
	public void handleInternal(CustomPayloadEvent.Context context) {
		handleClient(context);
	}
	
    public void sendToClient(ServerPlayer player) {
    	APNetwork.sendToClient(this, player);
    }

    public void sendToAllClients() {
    	APNetwork.sendToAllClients(this);
    }
}
