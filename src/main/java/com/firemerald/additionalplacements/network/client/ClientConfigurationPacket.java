package com.firemerald.additionalplacements.network.client;

import com.firemerald.additionalplacements.network.APPacket;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientConfigurationPacketListenerImpl;

public abstract class ClientConfigurationPacket extends APPacket {
	/**
	 * Handles an incoming packet.
	 *
	 * <p>This method is executed on {@linkplain io.netty.channel.EventLoop netty's event loops}.
	 * Modification to the game should be {@linkplain net.minecraft.util.thread.BlockableEventLoop#submit(Runnable) scheduled} using the provided Minecraft client instance.
	 *
	 * <p>An example usage of this is to display an overlay message:
	 * <pre>{@code
	 * ClientConfigurationNetworking.registerReceiver(new Identifier("mymod", "overlay"), (client, handler, buf, responseSender) -> {
	 * 	String message = buf.readString(32767);
	 *
	 * 	// All operations on the server or world must be executed on the server thread
	 * 	client.execute(() -> {
	 * 		client.inGameHud.setOverlayMessage(message, true);
	 * 	});
	 * });
	 * }</pre>
	 *  @param client the client
	 * @param handler the network handler that received this packet
	 * @param responseSender the packet sender
	 */
	@Environment(EnvType.CLIENT)
	public abstract void handleClient(Minecraft client, ClientConfigurationPacketListenerImpl handler, PacketSender responseSender);
}
