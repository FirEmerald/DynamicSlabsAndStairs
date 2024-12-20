package com.firemerald.additionalplacements.block.interfaces;

import java.util.function.Consumer;

import net.minecraft.block.Block;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.state.Property;

public interface IStateFixer {
	public CompoundNBT fix(CompoundNBT properties, Consumer<Block> changeBlock);
	
	public static <T extends Comparable<T>> void setProperty(CompoundNBT tag, Property<T> property, T value) {
		tag.put(property.getName(), StringNBT.valueOf(property.getName(value)));
	}
	
	public static <T extends Comparable<T>> T getProperty(CompoundNBT tag, Property<T> property) {
		return property.getValue(tag.getString(property.getName())).orElse(null);
	}
}
