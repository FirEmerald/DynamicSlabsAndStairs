package com.firemerald.additionalplacements.block.stairs.v2;

import com.firemerald.additionalplacements.util.ComplexFacing;

import net.minecraft.util.StringRepresentable;

public enum V2StairFacing implements StringRepresentable {
	SOUTH_UP_EAST("south_up_east", ComplexFacing.SOUTH_UP, ComplexFacing.UP_SOUTH, ComplexFacing.SOUTH_EAST),
	WEST_UP_SOUTH("west_up_south", ComplexFacing.WEST_UP, ComplexFacing.UP_WEST, ComplexFacing.WEST_SOUTH),
	NORTH_UP_WEST("north_up_west", ComplexFacing.NORTH_UP, ComplexFacing.UP_NORTH, ComplexFacing.NORTH_WEST),
	EAST_UP_NORTH("east_up_north", ComplexFacing.EAST_UP, ComplexFacing.UP_EAST, ComplexFacing.EAST_NORTH),
	SOUTH_DOWN_WEST("south_down_west", ComplexFacing.SOUTH_DOWN, ComplexFacing.DOWN_SOUTH, ComplexFacing.SOUTH_WEST),
	WEST_DOWN_NORTH("west_down_north", ComplexFacing.WEST_DOWN, ComplexFacing.DOWN_WEST, ComplexFacing.WEST_NORTH),
	NORTH_DOWN_EAST("north_down_east", ComplexFacing.NORTH_DOWN, ComplexFacing.DOWN_NORTH, ComplexFacing.NORTH_EAST),
	EAST_DOWN_SOUTH("east_down_south", ComplexFacing.EAST_DOWN, ComplexFacing.DOWN_EAST, ComplexFacing.EAST_SOUTH);
	
	public static V2StairFacing get(String name) {
		for (V2StairFacing placing : values()) if (placing.name.equals(name)) return placing;
		return null;
	}
	
	public final String name;
	public final ComplexFacing normal, flipped, vertical;
	
	V2StairFacing(String name, ComplexFacing normal, ComplexFacing flipped, ComplexFacing vertical) {
		this.name = name;
		this.normal = normal;
		this.flipped = flipped;
		this.vertical = vertical;
	}

	@Override
	public String getSerializedName() {
		return name;
	}
}
