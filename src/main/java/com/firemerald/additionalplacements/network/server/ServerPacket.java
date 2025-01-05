package com.firemerald.additionalplacements.network.server;

import com.firemerald.additionalplacements.network.APNetwork;
import com.firemerald.additionalplacements.network.APPacket;

import net.minecraft.network.protocol.PacketFlow;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public abstract class ServerPacket<T extends IPayloadContext> extends APPacket<T>
{
	@Override
	public PacketFlow getDirection() {
		return PacketFlow.SERVERBOUND;
	}

	public abstract void handleServer(T context);

	@Override
	public void handleInternal(T context)
	{
		handleServer(context);
	}

    public void sendToServer()
    {
    	APNetwork.sendToServer(this);
    }
}
