package com.firemerald.additionalplacements.network.server;

import com.firemerald.additionalplacements.network.AbstractLoginPacket;

import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

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
