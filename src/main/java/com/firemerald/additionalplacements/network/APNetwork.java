package com.firemerald.additionalplacements.network;

import java.util.function.Function;

import com.firemerald.additionalplacements.AdditionalPlacementsMod;
import com.firemerald.additionalplacements.network.client.CheckDataClientPacket;
import com.firemerald.additionalplacements.network.client.ClientPlayPacket;
import com.firemerald.additionalplacements.network.client.ConfigurationCheckFailedPacket;
import com.firemerald.additionalplacements.network.server.CheckDataServerPacket;
import com.firemerald.additionalplacements.network.server.ServerPlayPacket;
import com.firemerald.additionalplacements.network.server.SetPlacementTogglePacket;

import net.minecraft.network.Connection;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.network.CustomPayloadEvent;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.PacketDistributor.PacketTarget;
import net.minecraftforge.network.SimpleChannel;
import net.minecraftforge.network.SimpleChannel.MessageBuilder;

public class APNetwork
{
    public static SimpleChannel INSTANCE;

    private static int packetId = 0;
    private static int id()
    {
        return packetId++;
    }

    public static void register()
    {
        INSTANCE = ChannelBuilder
            .named(new ResourceLocation(AdditionalPlacementsMod.MOD_ID, "network"))
            .networkProtocolVersion(1)
            .simpleChannel();
        registerServerPlayPacket(SetPlacementTogglePacket.class, SetPlacementTogglePacket::new);
        registerClientPlayPacketAsync(CheckDataClientPacket.class, CheckDataClientPacket::new);
        registerServerPlayPacketAsync(CheckDataServerPacket.class, CheckDataServerPacket::new);
        registerClientPlayPacketAsync(ConfigurationCheckFailedPacket.class, ConfigurationCheckFailedPacket::new);
    }

    public static <T extends ClientPlayPacket> void registerClientPlayPacket(Class<T> clazz, Function<FriendlyByteBuf, T> decoder)
    {
    	registerPlayPacket(clazz, decoder, NetworkDirection.PLAY_TO_CLIENT);
    }

    public static <T extends ServerPlayPacket> void registerServerPlayPacket(Class<T> clazz, Function<FriendlyByteBuf, T> decoder)
    {
    	registerPlayPacket(clazz, decoder, NetworkDirection.PLAY_TO_SERVER);
    }

    public static <T extends APPacket> void registerPlayPacket(Class<T> clazz, Function<FriendlyByteBuf, T> decoder, NetworkDirection direction)
    {
    	registerPlayPacket(INSTANCE.messageBuilder(clazz, id(), direction), decoder);
    }

    public static <T extends APPacket> void registerPlayPacket(MessageBuilder<T> builder, Function<FriendlyByteBuf, T> decoder)
    {
    	builder
    	.decoder(decoder)
    	.encoder(APPacket::write)
    	.consumerMainThread(APPacket::handle)
    	.add();
    }

    public static <T extends ClientPlayPacket> void registerClientPlayPacketAsync(Class<T> clazz, Function<FriendlyByteBuf, T> decoder)
    {
    	registerPlayPacketAsync(clazz, decoder, NetworkDirection.PLAY_TO_CLIENT);
    }

    public static <T extends ServerPlayPacket> void registerServerPlayPacketAsync(Class<T> clazz, Function<FriendlyByteBuf, T> decoder)
    {
    	registerPlayPacketAsync(clazz, decoder, NetworkDirection.PLAY_TO_SERVER);
    }

    public static <T extends APPacket> void registerPlayPacketAsync(Class<T> clazz, Function<FriendlyByteBuf, T> decoder, NetworkDirection direction)
    {
    	registerPlayPacketAsync(INSTANCE.messageBuilder(clazz, id(), direction), decoder);
    }

    public static <T extends APPacket> void registerPlayPacketAsync(MessageBuilder<T> builder, Function<FriendlyByteBuf, T> decoder)
    {
    	builder
    	.decoder(decoder)
    	.encoder(APPacket::write)
    	.consumerNetworkThread(APPacket::handle)
    	.add();
    }

    public static void sendToServer(Object packet)
    {
    	sendTo(packet, PacketDistributor.SERVER.noArg());
    }

    public static void sendToClient(Object packet, ServerPlayer player)
    {
    	sendTo(packet, PacketDistributor.PLAYER.with(player));
    }

    public static void sendToAllClients(Object packet)
    {
    	sendTo(packet, PacketDistributor.ALL.noArg());
    }

    public static void sendTo(Object packet, PacketTarget target)
    {
    	INSTANCE.send(packet, target);
    }

    public static void send(Object packet, Connection connection)
    {
    	INSTANCE.send(packet, connection);
    }

    public static void reply(Object packet, CustomPayloadEvent.Context context)
    {
    	INSTANCE.reply(packet, context);
    }
}