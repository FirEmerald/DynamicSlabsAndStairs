package com.firemerald.additionalplacements.network.server;

import com.firemerald.additionalplacements.network.APNetwork;
import com.firemerald.additionalplacements.network.APPacket;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking.Context;
import net.minecraft.network.RegistryFriendlyByteBuf;

public abstract class ServerPlayPacket extends APPacket<RegistryFriendlyByteBuf> {
	public abstract void handleServer(Context context);

    public void sendToServer()
    {
    	APNetwork.sendToServer(this);
    }
}
