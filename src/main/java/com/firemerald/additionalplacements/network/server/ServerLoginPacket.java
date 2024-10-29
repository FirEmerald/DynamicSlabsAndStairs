package com.firemerald.additionalplacements.network.server;

import java.util.concurrent.Future;

import com.firemerald.additionalplacements.network.APPacket;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerLoginNetworking.LoginSynchronizer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerLoginPacketListenerImpl;

public abstract class ServerLoginPacket extends APPacket {
	/**
	 * Handles an incoming query response from a client.
	 *
	 * <p>This method is executed on {@linkplain io.netty.channel.EventLoop netty's event loops}.
	 * Modification to the game should be {@linkplain net.minecraft.util.thread.BlockableEventLoop#submit(Runnable) scheduled} using the provided Minecraft client instance.
	 *
	 * <p><b>Whether the client understood the query should be checked before reading from the payload of the packet.</b>
	 * @param server the server
	 * @param handler the network handler that received this packet, representing the player/client who sent the response
	 * @param understood whether the client understood the packet
	 * @param synchronizer the synchronizer which may be used to delay log-in till a {@link Future} is completed.
	 * @param responseSender the packet sender
	 */
	public abstract void handleServer(MinecraftServer server, ServerLoginPacketListenerImpl handler, boolean understood, LoginSynchronizer synchronizer, PacketSender responseSender);
}
