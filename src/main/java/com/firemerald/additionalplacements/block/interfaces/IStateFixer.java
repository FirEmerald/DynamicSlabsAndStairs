package com.firemerald.additionalplacements.block.interfaces;

import java.util.function.Consumer;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.Property;

public interface IStateFixer {
	public CompoundTag fix(CompoundTag properties, Consumer<Block> changeBlock);
	
	public static boolean contains(CompoundTag tag, Property<?> property) {
		return tag.contains(property.getName());
	}
	
	public static <T extends Comparable<T>> void setProperty(CompoundTag tag, Property<T> property, T value) {
		tag.put(property.getName(), StringTag.valueOf(property.getName(value)));
	}
	
	public static <T extends Comparable<T>> T getProperty(CompoundTag tag, Property<T> property) {
		return property.getValue(getPropertyString(tag, property)).orElse(null);
	}
	
	public static String getPropertyString(CompoundTag tag, Property<?> property) {
		return tag.getString(property.getName());
	}
	
	public static void renameProperty(CompoundTag tag, Property<?> from, Property<?> to) {
		renameProperty(tag, from.getName(), to);
	}
	
	public static void renameProperty(CompoundTag tag, String from, Property<?> to) {
		tag.put(to.getName(), tag.get(from));
		tag.remove(from);
	}
	
	public static void remove(CompoundTag tag, Property<?> property) {
		tag.remove(property.getName());
	}
}
