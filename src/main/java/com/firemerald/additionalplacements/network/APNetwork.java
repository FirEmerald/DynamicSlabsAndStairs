package com.firemerald.additionalplacements.network;

import java.util.function.Function;

import com.firemerald.additionalplacements.network.client.CheckDataClientPacket;
import com.firemerald.additionalplacements.network.client.ClientConfigurationPacket;
import com.firemerald.additionalplacements.network.client.ClientPlayPacket;
import com.firemerald.additionalplacements.network.client.ConfigurationCheckFailedPacket;
import com.firemerald.additionalplacements.network.server.CheckDataServerPacket;
import com.firemerald.additionalplacements.network.server.ServerConfigurationPacket;
import com.firemerald.additionalplacements.network.server.ServerPlayPacket;
import com.firemerald.additionalplacements.network.server.SetPlacementTogglePacket;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientConfigurationNetworking;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.fabric.api.networking.v1.*;
import net.fabricmc.fabric.impl.networking.server.ServerConfigurationNetworkAddon;
import net.fabricmc.fabric.impl.networking.server.ServerNetworkingImpl;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientConfigurationPacketListenerImpl;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerConfigurationPacketListenerImpl;
import net.minecraft.server.network.ServerGamePacketListenerImpl;

public class APNetwork
{
    public static void register()
    {
        registerServerPlayPacket(SetPlacementTogglePacket.ID, SetPlacementTogglePacket::new);
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) registerClientConfigurationPacket(CheckDataClientPacket.ID, CheckDataClientPacket::new);
        registerServerConfigurationPacket(CheckDataServerPacket.ID, CheckDataServerPacket::new);
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) registerClientConfigurationPacket(ConfigurationCheckFailedPacket.ID, ConfigurationCheckFailedPacket::new);
        
		ServerConfigurationConnectionEvents.CONFIGURE.register((handler, server) -> {
			final ServerConfigurationNetworkAddon addon = ServerNetworkingImpl.getAddon(handler);
			handler.addTask(new CheckDataConfigurationTask(addon));
		});
    }

    public static <T extends ClientPlayPacket> void registerClientPlayPacket(ResourceLocation id, Function<FriendlyByteBuf, T> fromBuffer)
    {
    	ClientPlayNetworking.registerGlobalReceiver(id, (Minecraft client, ClientPacketListener handler, FriendlyByteBuf buf, PacketSender responseSender) -> fromBuffer.apply(buf).handleClient(client, handler, responseSender));
    }

    public static <T extends ServerPlayPacket> void registerServerPlayPacket(ResourceLocation id, Function<FriendlyByteBuf, T> fromBuffer)
    {
    	ServerPlayNetworking.registerGlobalReceiver(id, (MinecraftServer server, ServerPlayer player, ServerGamePacketListenerImpl handler, FriendlyByteBuf buf, PacketSender responseSender) -> fromBuffer.apply(buf).handleServer(server, player, handler, responseSender));
    }

    @Environment(EnvType.CLIENT)
    public static <T extends ClientConfigurationPacket> void registerClientConfigurationPacket(ResourceLocation id, Function<FriendlyByteBuf, T> fromBuffer)
    {
    	ClientConfigurationNetworking.registerGlobalReceiver(id, (Minecraft client, ClientConfigurationPacketListenerImpl handler, FriendlyByteBuf buf, PacketSender responseSender) -> fromBuffer.apply(buf).handleClient(client, handler, responseSender));
    }

    public static <T extends ServerConfigurationPacket> void registerServerConfigurationPacket(ResourceLocation id, Function<FriendlyByteBuf, T> fromBuffer)
    {
    	ServerConfigurationNetworking.registerGlobalReceiver(id, (MinecraftServer server, ServerConfigurationPacketListenerImpl handler, FriendlyByteBuf buf, PacketSender responseSender) -> fromBuffer.apply(buf).handleServer(server, handler, responseSender));
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
}