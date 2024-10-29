package com.firemerald.additionalplacements.network.server;

import com.firemerald.additionalplacements.network.APPacket;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerConfigurationNetworking;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerConfigurationPacketListenerImpl;
import net.minecraft.util.thread.BlockableEventLoop;

public abstract class ServerConfigurationPacket extends APPacket {
	/**
	 * Handles an incoming packet.
	 *
	 * <p>This method is executed on {@linkplain io.netty.channel.EventLoop netty's event loops}.
	 * Modification to the game should be {@linkplain BlockableEventLoop#submit(Runnable) scheduled} using the server instance from {@link ServerConfigurationNetworking#getServer(ServerConfigurationPacketListenerImpl)}.
	 *
	 * <p>An example usage of this is:
	 * <pre>{@code
	 * ServerConfigurationNetworking.registerReceiver(new Identifier("mymod", "boom"), (server, handler, buf, responseSender) -> {
	 * 	boolean fire = buf.readBoolean();
	 *
	 * 	// All operations on the server must be executed on the server thread
	 * 	server.execute(() -> {
	 *
	 * 	});
	 * });
	 * }</pre>
	 * @param server the server
	 * @param handler the network handler that received this packet, representing the client who sent the packet
	 * @param responseSender the packet sender
	 */
	public abstract void handleServer(MinecraftServer server, ServerConfigurationPacketListenerImpl handler, PacketSender responseSender);
}
