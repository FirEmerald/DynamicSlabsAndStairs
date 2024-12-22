package com.firemerald.additionalplacements.block.interfaces;

import java.util.function.Consumer;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.Property;

public interface IStateFixer {
	public CompoundTag fix(CompoundTag properties, Consumer<Block> changeBlock);
	
	public static <T extends Comparable<T>> void setProperty(CompoundTag tag, Property<T> property, T value) {
		tag.put(property.getName(), StringTag.valueOf(property.getName(value)));
	}
	
	public static <T extends Comparable<T>> T getProperty(CompoundTag tag, Property<T> property) {
		return property.getValue(tag.getString(property.getName())).orElse(null);
	}
}
