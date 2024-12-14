package com.firemerald.additionalplacements.util;

import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.StateHolder;
import net.minecraft.world.level.block.state.properties.Property;

public interface IStateDefinitionBuilderExtension<O, S extends StateHolder<O, S>> {
	public StateDefinition.Builder<O, S> addSingular(Property<?>... pProperties);
}
