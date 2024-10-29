package com.firemerald.additionalplacements.network.client;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import org.jetbrains.annotations.Nullable;

import com.firemerald.additionalplacements.network.APPacket;

import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientHandshakePacketListenerImpl;
import net.minecraft.network.FriendlyByteBuf;

public abstract class ClientLoginPacket extends APPacket {
	/**
	 * Handles an incoming query request from a server.
	 *
	 * <p>This method is executed on {@linkplain io.netty.channel.EventLoop netty's event loops}.
	 * Modification to the game should be {@linkplain net.minecraft.util.thread.BlockableEventLoop#submit(Runnable) scheduled} using the provided Minecraft client instance.
	 *
	 * <p>The return value of this method is a completable future that may be used to delay the login process to the server until a task {@link CompletableFuture#isDone() is done}.
	 * The future should complete in reasonably time to prevent disconnection by the server.
	 * If your request processes instantly, you may use {@link CompletableFuture#completedFuture(Object)} to wrap your response for immediate sending.
	 *
	 * @param client the client
	 * @param handler the network handler that received this packet
	 * @param listenerAdder listeners to be called when the response packet is sent to the server
	 * @return a completable future which contains the payload to respond to the server with.
	 * If the future contains {@code null}, then the server will be notified that the client did not understand the query.
	 */
	@Environment(EnvType.CLIENT)
	public abstract CompletableFuture<@Nullable FriendlyByteBuf> handleClient(Minecraft client, ClientHandshakePacketListenerImpl handler, Consumer<GenericFutureListener<? extends Future<? super Void>>> listenerAdder);
}
