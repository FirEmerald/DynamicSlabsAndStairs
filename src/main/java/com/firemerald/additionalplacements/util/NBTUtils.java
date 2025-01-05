package com.firemerald.additionalplacements.util;

import java.util.function.Consumer;

import com.google.gson.*;

import net.minecraft.nbt.*;

public class NBTUtils {
	public static JsonElement toJson(INBT nbt) {
		if (nbt instanceof CompoundNBT) return toJson((CompoundNBT) nbt);
		if (nbt instanceof CollectionNBT) return toJson((CollectionNBT<?>) nbt);
		if (nbt instanceof NumberNBT) return toJson((NumberNBT) nbt);
		if (nbt instanceof StringNBT) return toJson((StringNBT) nbt);
		return JsonNull.INSTANCE; //TODO shouldn't happen but if it does throw error?
	}

	public static JsonObject toJson(CompoundNBT compound) {
		JsonObject obj = new JsonObject();
		compound.getAllKeys().forEach(key -> obj.add(key, toJson(compound.get(key))));
		return obj;
	}

	public static JsonArray toJson(CollectionNBT<?> list) {
		JsonArray array = new JsonArray();
		for (int i = 0; i < list.size(); ++i) array.add(toJson(list.get(i)));
		return array;
	}

	public static JsonPrimitive toJson(NumberNBT number) {
		return new JsonPrimitive(number.getAsNumber());
	}

	public static JsonPrimitive toJson(StringNBT string) {
		return new JsonPrimitive(string.getAsString());
	}

	public static void ifCompoundNotEmpty(CompoundNBT tag, String key, Consumer<CompoundNBT> action) {
		if (tag.contains(key, TagIds.TAG_COMPOUND)) {
			CompoundNBT tag2 = tag.getCompound(key);
			if (!tag2.isEmpty()) action.accept(tag2);
		}
	}

	public static void ifListNotEmpty(CompoundNBT tag, String key, int listTagType, Consumer<ListNBT> action) {
		if (tag.contains(key, TagIds.TAG_LIST)) {
			ListNBT list = tag.getList(key, listTagType);
			if (!list.isEmpty()) action.accept(list);
		}
	}
}
