package com.firemerald.additionalplacements.network;

import java.util.function.Function;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;

public class APStreamCodec<T extends FriendlyByteBuf, U extends APPacket<T>> implements StreamCodec<T, U> {
	public final Function<T, U> constructor;
	
	public APStreamCodec(Function<T, U> constructor) {
		this.constructor = constructor;
	}
	
	@Override
	public U decode(T buf) {
		return constructor.apply(buf);
	}

	@Override
	public void encode(T buf, U packet) {
		packet.write(buf);
	}
}
