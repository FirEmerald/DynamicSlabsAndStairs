package com.firemerald.additionalplacements.block.stairs.complex;

import com.firemerald.additionalplacements.util.ComplexFacing;

import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;

public enum ComplexStairFacing implements StringRepresentable {
	//all of these have "UP" as their left direction
	NORTH_EAST("north_east", Direction.NORTH, Direction.EAST),
	EAST_SOUTH("east_south", Direction.EAST, Direction.SOUTH),
	SOUTH_WEST("south_west", Direction.SOUTH, Direction.WEST),
	WEST_NORTH("west_north", Direction.WEST, Direction.NORTH);
	
	public final String name;
	public final ComplexFacing normal, upFront, upTop, downFront, downTop;
	
	ComplexStairFacing(String name, Direction front, Direction top) {
		this.name = name;
		this.normal = ComplexFacing.forFacing(front, top);
		this.upFront = ComplexFacing.forFacing(front, Direction.UP);
		this.upTop = ComplexFacing.forFacing(top, Direction.UP);
		this.downFront = ComplexFacing.forFacing(front, Direction.DOWN);
		this.downTop = ComplexFacing.forFacing(top, Direction.DOWN);
	}
	
	@Override
	public String getSerializedName() {
		return name;
	}
}
