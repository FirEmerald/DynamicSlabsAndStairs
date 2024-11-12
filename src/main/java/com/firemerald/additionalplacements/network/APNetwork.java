package com.firemerald.additionalplacements.network;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;

import com.firemerald.additionalplacements.network.client.CheckDataClientPacket;
import com.firemerald.additionalplacements.network.client.ClientLoginPacket;
import com.firemerald.additionalplacements.network.client.ClientPlayPacket;
import com.firemerald.additionalplacements.network.client.ConfigurationCheckFailedPacket;
import com.firemerald.additionalplacements.network.server.CheckDataServerPacket;
import com.firemerald.additionalplacements.network.server.ServerLoginPacket;
import com.firemerald.additionalplacements.network.server.ServerPlayPacket;
import com.firemerald.additionalplacements.network.server.SetPlacementTogglePacket;

import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.client.networking.v1.ClientLoginNetworking;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerLoginConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerLoginNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.fabric.api.networking.v1.ServerLoginNetworking.LoginSynchronizer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientHandshakePacketListenerImpl;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.server.network.ServerLoginPacketListenerImpl;

public class APNetwork
{
    public static void register()
    {
        registerServerPlayPacket(SetPlacementTogglePacket.ID, SetPlacementTogglePacket::new);
        registerLoginResponsePackets(CheckDataClientPacket.ID, CheckDataClientPacket::new, CheckDataServerPacket::new);
        registerClientLoginPacket(ConfigurationCheckFailedPacket.ID, ConfigurationCheckFailedPacket::new);
        ServerLoginConnectionEvents.QUERY_START.register(APNetwork::onLoginQuery);
    }

    public static <T extends ClientPlayPacket> void registerClientPlayPacket(ResourceLocation id, Function<FriendlyByteBuf, T> fromBuffer)
    {
    	ClientPlayNetworking.registerGlobalReceiver(id, (Minecraft client, ClientPacketListener handler, FriendlyByteBuf buf, PacketSender responseSender) -> fromBuffer.apply(buf).handleClient(client, handler, responseSender));
    }

    public static <T extends ServerPlayPacket> void registerServerPlayPacket(ResourceLocation id, Function<FriendlyByteBuf, T> fromBuffer)
    {
    	ServerPlayNetworking.registerGlobalReceiver(id, (MinecraftServer server, ServerPlayer player, ServerGamePacketListenerImpl handler, FriendlyByteBuf buf, PacketSender responseSender) -> fromBuffer.apply(buf).handleServer(server, player, handler, responseSender));
    }

    public static <T extends ClientLoginPacket> void registerClientLoginPacket(ResourceLocation id, Function<FriendlyByteBuf, T> fromBuffer)
    {
    	ClientLoginNetworking.registerGlobalReceiver(id, (Minecraft client, ClientHandshakePacketListenerImpl handler, FriendlyByteBuf buf, Consumer<GenericFutureListener<? extends Future<? super Void>>> listenerAdder) -> fromBuffer.apply(buf).handleClient(client, handler, listenerAdder));
    }

    public static <T extends ServerLoginPacket> void registerServerLoginPacket(ResourceLocation id, Function<FriendlyByteBuf, T> fromBuffer)
    {
    	ServerLoginNetworking.registerGlobalReceiver(id, (MinecraftServer server, ServerLoginPacketListenerImpl handler, boolean understood, FriendlyByteBuf buf, LoginSynchronizer synchronizer, PacketSender responseSender) -> fromBuffer.apply(buf).handleServer(server, handler, understood, synchronizer, responseSender));
    }

    public static <T extends ClientLoginPacket, U extends ServerLoginPacket> void registerLoginResponsePackets(ResourceLocation id, Function<FriendlyByteBuf, T> clientFromBuffer, Function<FriendlyByteBuf, U> serverFromBuffer)
    {
    	if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) registerClientLoginPacket(id, clientFromBuffer);
    	registerServerLoginPacket(id, serverFromBuffer);
    }

    public static <T extends APPacket> void send(T packet, PacketSender sender)
    {
    	sender.sendPacket(packet.getID(), packet.getBuf());
    }

    public static <T extends ServerPlayPacket> void sendToServer(T packet)
    {
    	ClientPlayNetworking.send(packet.getID(), packet.getBuf());
    }

    public static <T extends ClientPlayPacket> void sendToClient(T packet, ServerPlayer player)
    {
    	ServerPlayNetworking.send(player, packet.getID(), packet.getBuf());
    }
    
    public static CompletableFuture<Void> dataCheckWaiter;
    
    public static void onLoginQuery(ServerLoginPacketListenerImpl handler, MinecraftServer server, PacketSender sender, ServerLoginNetworking.LoginSynchronizer synchronizer) {
    	dataCheckWaiter = new CompletableFuture<>();
    	new CheckDataClientPacket().send(sender);
    	synchronizer.waitFor(dataCheckWaiter);
    }
}