package com.firemerald.additionalplacements.network;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public abstract class APPacket<T extends FriendlyByteBuf> implements CustomPacketPayload
{
	public abstract void write(T buf);

	public void send(PacketSender sender) {
		APNetwork.send(this, sender);
	}
}