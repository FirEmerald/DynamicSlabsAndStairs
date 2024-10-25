package com.firemerald.additionalplacements.network.server;

import com.firemerald.additionalplacements.network.AbstractLoginPacket;

import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

public abstract class ServerLoginPacket extends AbstractLoginPacket {
	@Override
	public NetworkDirection getDirection() {
		return NetworkDirection.LOGIN_TO_CLIENT;
	}
	
    @Override
	public void handle(NetworkEvent.Context context) {
    	handleServer(context);
    }
	
	protected abstract void handleServer(NetworkEvent.Context context);
}
