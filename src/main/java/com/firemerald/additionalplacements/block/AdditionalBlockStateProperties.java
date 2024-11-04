package com.firemerald.additionalplacements.block;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class AdditionalBlockStateProperties
{
	   public static final EnumProperty<Direction> HORIZONTAL_OR_UP_FACING = EnumProperty.create("facing", Direction.class, Direction.UP, Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST);
}