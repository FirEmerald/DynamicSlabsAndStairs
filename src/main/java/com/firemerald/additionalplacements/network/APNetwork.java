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

import net.fabricmc.fabric.api.client.networking.v1.ClientConfigurationNetworking;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.*;
import net.fabricmc.fabric.impl.networking.server.ServerConfigurationNetworkAddon;
import net.fabricmc.fabric.impl.networking.server.ServerNetworkingImpl;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload.Type;
import net.minecraft.server.level.ServerPlayer;

public class APNetwork
{
    public static void register()
    {
        registerServerPlayPacket(SetPlacementTogglePacket.TYPE, SetPlacementTogglePacket::new);
        registerClientConfigurationPacket(CheckDataClientPacket.TYPE, CheckDataClientPacket::new);
        registerServerConfigurationPacket(CheckDataServerPacket.TYPE, CheckDataServerPacket::new);
        registerClientConfigurationPacket(ConfigurationCheckFailedPacket.TYPE, ConfigurationCheckFailedPacket::new);
        
		ServerConfigurationConnectionEvents.CONFIGURE.register((handler, server) -> {
			final ServerConfigurationNetworkAddon addon = ServerNetworkingImpl.getAddon(handler);
			handler.addTask(new CheckDataConfigurationTask(addon));
		});
    }

    public static <T extends ClientPlayPacket> void registerClientPlayPacket(Type<T> type, Function<RegistryFriendlyByteBuf, T> fromBuffer)
    {
    	PayloadTypeRegistry.playS2C().register(type, new APStreamCodec<>(fromBuffer));
    	ClientPlayNetworking.registerGlobalReceiver(type, ClientPlayPacket::handleClient);
    }

    public static <T extends ServerPlayPacket> void registerServerPlayPacket(Type<T> type, Function<RegistryFriendlyByteBuf, T> fromBuffer)
    {
    	PayloadTypeRegistry.playC2S().register(type, new APStreamCodec<>(fromBuffer));
    	ServerPlayNetworking.registerGlobalReceiver(type, ServerPlayPacket::handleServer);
    }

    public static <T extends ClientConfigurationPacket> void registerClientConfigurationPacket(Type<T> type, Function<FriendlyByteBuf, T> fromBuffer)
    {
    	PayloadTypeRegistry.configurationS2C().register(type, new APStreamCodec<>(fromBuffer));
    	ClientConfigurationNetworking.registerGlobalReceiver(type, ClientConfigurationPacket::handleClient);
    }

    public static <T extends ServerConfigurationPacket> void registerServerConfigurationPacket(Type<T> type, Function<FriendlyByteBuf, T> fromBuffer)
    {
    	PayloadTypeRegistry.configurationC2S().register(type, new APStreamCodec<>(fromBuffer));
    	ServerConfigurationNetworking.registerGlobalReceiver(type, ServerConfigurationPacket::handleServer);
    }

    public static <T extends APPacket<?>> void send(T packet, PacketSender sender)
    {
    	sender.sendPacket(packet);
    }

    public static <T extends ServerPlayPacket> void sendToServer(T packet)
    {
    	ClientPlayNetworking.send(packet);
    }

    public static <T extends ClientPlayPacket> void sendToClient(T packet, ServerPlayer player)
    {
    	ServerPlayNetworking.send(player, packet);
    }
}