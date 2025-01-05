package com.firemerald.additionalplacements.network;

import java.util.function.Consumer;

import com.firemerald.additionalplacements.AdditionalPlacementsMod;
import com.firemerald.additionalplacements.network.client.CheckDataClientPacket;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.protocol.Packet;
import net.minecraft.server.network.ConfigurationTask;

public class CheckDataConfigurationTask implements ConfigurationTask {
	public static final Type TYPE = new Type(AdditionalPlacementsMod.MOD_ID + ":configuration_checks");

	private final PacketSender packetSender;

	protected CheckDataConfigurationTask(PacketSender packetSender) {
		this.packetSender = packetSender;
	}

    @Override
    public void start(Consumer<Packet<?>> send) {
    	new CheckDataClientPacket().send(packetSender);
    }

	@Override
	public Type type() {
		return TYPE;
	}
}
