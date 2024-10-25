package com.firemerald.additionalplacements.network.client;

import com.firemerald.additionalplacements.network.AbstractLoginPacket;

import net.minecraftforge.fml.network.NetworkDirection;

public abstract class ClientLoginPacket extends AbstractLoginPacket {
	@Override
	public NetworkDirection getDirection() {
		return NetworkDirection.LOGIN_TO_CLIENT;
	}
}
