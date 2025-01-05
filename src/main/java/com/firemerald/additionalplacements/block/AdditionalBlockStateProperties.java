package com.firemerald.additionalplacements.block;

import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class AdditionalBlockStateProperties
{
	public static final DirectionProperty HORIZONTAL_OR_UP_PLACING = DirectionProperty.create("ap_placing", Direction.UP, Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST);
	public static final EnumProperty<Axis> HORIZONTAL_AXIS = EnumProperty.create("ap_axis", Direction.Axis.class, Direction.Axis.X, Direction.Axis.Z);
}