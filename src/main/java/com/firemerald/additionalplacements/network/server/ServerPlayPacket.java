package com.firemerald.additionalplacements.network.server;

import com.firemerald.additionalplacements.network.APNetwork;
import com.firemerald.additionalplacements.network.APPacket;

import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

public abstract class ServerPlayPacket extends APPacket {
	@Override
	public NetworkDirection getDirection() {
		return NetworkDirection.PLAY_TO_SERVER;
	}

    @Override
	public void handle(NetworkEvent.Context context) {
    	handleServer(context);
    }

	protected abstract void handleServer(NetworkEvent.Context context);

    public void sendToServer()
    {
    	APNetwork.sendToServer(this);
    }
}
