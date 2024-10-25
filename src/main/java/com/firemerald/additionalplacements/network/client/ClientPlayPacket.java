package com.firemerald.additionalplacements.network.client;

import com.firemerald.additionalplacements.network.APNetwork;
import com.firemerald.additionalplacements.network.APPacket;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

public abstract class ClientPlayPacket extends APPacket {
	@Override
	public NetworkDirection getDirection() {
		return NetworkDirection.PLAY_TO_CLIENT;
	}
	
    @Override
	public void handle(NetworkEvent.Context context) {
    	handleClient(context);
    }
	
	@OnlyIn(Dist.CLIENT)
	protected abstract void handleClient(NetworkEvent.Context context);
	
    public void sendToClient(ServerPlayerEntity player) {
    	APNetwork.sendToClient(this, player);
    }

    public void sendToAllClients() {
    	APNetwork.sendToAllClients(this);
    }
}
