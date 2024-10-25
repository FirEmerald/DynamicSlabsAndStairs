package com.firemerald.additionalplacements.network.server;

import com.firemerald.additionalplacements.network.APNetwork;
import com.firemerald.additionalplacements.network.APPacket;

import net.minecraftforge.event.network.CustomPayloadEvent;
import net.minecraftforge.network.NetworkDirection;

public abstract class ServerPlayPacket extends APPacket {
	@Override
	public NetworkDirection getDirection() {
		return NetworkDirection.PLAY_TO_SERVER;
	}
	
	public abstract void handleServer(CustomPayloadEvent.Context context);
	
	@Override
	public void handleInternal(CustomPayloadEvent.Context context)  {
		handleServer(context);
	}
	
    public void sendToServer() {
    	APNetwork.sendToServer(this);
    }
}
