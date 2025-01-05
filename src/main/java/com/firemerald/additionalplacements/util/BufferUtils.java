package com.firemerald.additionalplacements.util;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;

import net.minecraft.network.PacketBuffer;

public class BufferUtils {
	public static <T> List<T> readList(PacketBuffer buffer, Function<PacketBuffer, T> itemReader) {
		int size = buffer.readVarInt();
		List<T> list = new ArrayList<>(size);
		for (int i = 0; i < size; ++i) list.add(itemReader.apply(buffer));
		return list;
	}

	public static <T> void writeCollection(PacketBuffer buffer, Collection<T> items, BiConsumer<PacketBuffer, T> itemWriter) {
		buffer.writeVarInt(items.size());
		items.forEach(item -> itemWriter.accept(buffer, item));
	}

	public static <T, U> Map<T, U> readMap(PacketBuffer buffer, Function<PacketBuffer, T> keyReader, Function<PacketBuffer, U> itemReader) {
		int size = buffer.readVarInt();
		Map<T, U> map = new HashMap<>(size);
		for (int i = 0; i < size; ++i) map.put(keyReader.apply(buffer), itemReader.apply(buffer));
		return map;
	}

	public static <T, U> void writeMap(PacketBuffer buffer, Map<T, U> map, BiConsumer<PacketBuffer, T> keyWriter, BiConsumer<PacketBuffer, U> itemWriter) {
		buffer.writeVarInt(map.size());
		map.forEach((key, item) -> {
			keyWriter.accept(buffer, key);
			itemWriter.accept(buffer, item);
		});
	}
}
