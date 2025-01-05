package com.firemerald.additionalplacements.network.client;

import com.firemerald.additionalplacements.network.AbstractLoginPacket;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

public abstract class ClientLoginPacket extends AbstractLoginPacket {
	@Override
	public NetworkDirection getDirection() {
		return NetworkDirection.LOGIN_TO_CLIENT;
	}

    @Override
	public void handle(NetworkEvent.Context context) {
    	handleClient(context);
    }

	@OnlyIn(Dist.CLIENT)
	protected abstract void handleClient(NetworkEvent.Context context);
}
