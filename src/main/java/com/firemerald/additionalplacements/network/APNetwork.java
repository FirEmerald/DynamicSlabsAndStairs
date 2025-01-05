package com.firemerald.additionalplacements.network;

import java.util.function.Function;

import com.firemerald.additionalplacements.AdditionalPlacementsMod;
import com.firemerald.additionalplacements.network.client.CheckDataClientPacket;
import com.firemerald.additionalplacements.network.client.ClientPacket;
import com.firemerald.additionalplacements.network.client.ConfigurationCheckFailedPacket;
import com.firemerald.additionalplacements.network.server.CheckDataServerPacket;
import com.firemerald.additionalplacements.network.server.ServerPacket;
import com.firemerald.additionalplacements.network.server.SetPlacementTogglePacket;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload.Type;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class APNetwork
{
	public static void register(RegisterPayloadHandlersEvent event)
	{
		PayloadRegistrar registrar = event.registrar(AdditionalPlacementsMod.MOD_ID);
		playToServer(registrar, SetPlacementTogglePacket.TYPE, SetPlacementTogglePacket::new);
		configurationToClient(registrar, CheckDataClientPacket.TYPE, CheckDataClientPacket::new);
		configurationToServer(registrar, CheckDataServerPacket.TYPE, CheckDataServerPacket::new);
		configurationToClient(registrar, ConfigurationCheckFailedPacket.TYPE, ConfigurationCheckFailedPacket::new);
	}

	public static <T extends ServerPacket<RegistryFriendlyByteBuf>> void playToServer(PayloadRegistrar registrar, Type<T> type, Function<RegistryFriendlyByteBuf, T> constructor) {
		registrar.playToServer(type, new APStreamCodec<>(constructor), APPacket::handle);
	}

	public static <T extends ClientPacket<RegistryFriendlyByteBuf>> void playToClient(PayloadRegistrar registrar, Type<T> type, Function<RegistryFriendlyByteBuf, T> constructor) {
		registrar.playToClient(type, new APStreamCodec<>(constructor), APPacket::handle);
	}

	public static <T extends ServerPacket<FriendlyByteBuf>> void configurationToServer(PayloadRegistrar registrar, Type<T> type, Function<FriendlyByteBuf, T> constructor) {
		registrar.configurationToServer(type, new APStreamCodec<>(constructor), APPacket::handle);
	}

	public static <T extends ClientPacket<FriendlyByteBuf>> void configurationToClient(PayloadRegistrar registrar, Type<T> type, Function<FriendlyByteBuf, T> constructor) {
		registrar.configurationToClient(type, new APStreamCodec<>(constructor), APPacket::handle);
	}

    public static void sendToServer(CustomPacketPayload packet)
    {
    	PacketDistributor.sendToServer(packet);
    }

    public static void sendToClient(CustomPacketPayload packet, ServerPlayer player)
    {
    	PacketDistributor.sendToPlayer(player, packet);
    }

    public static void sendToAllClients(CustomPacketPayload packet)
    {
    	PacketDistributor.sendToAllPlayers(packet);
    }
}