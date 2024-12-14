package com.firemerald.additionalplacements.block.stairs.extended;

import com.firemerald.additionalplacements.util.ComplexFacing;

import net.minecraft.util.StringRepresentable;

public enum ExtendedStairFacing implements StringRepresentable {
	SOUTH_EAST_UP("south_east_up", ComplexFacing.SOUTH_UP, ComplexFacing.UP_SOUTH, ComplexFacing.SOUTH_EAST),
	WEST_SOUTH_UP("west_south_up", ComplexFacing.WEST_UP, ComplexFacing.UP_WEST, ComplexFacing.WEST_SOUTH),
	NORTH_WEST_UP("north_west_up", ComplexFacing.NORTH_UP, ComplexFacing.UP_NORTH, ComplexFacing.NORTH_WEST),
	EAST_NORTH_UP("east_north_up", ComplexFacing.EAST_UP, ComplexFacing.UP_EAST, ComplexFacing.EAST_NORTH),
	SOUTH_WEST_DOWN("south_west_down", ComplexFacing.SOUTH_DOWN, ComplexFacing.DOWN_SOUTH, ComplexFacing.SOUTH_WEST),
	WEST_NORTH_DOWN("west_north_down", ComplexFacing.WEST_DOWN, ComplexFacing.DOWN_WEST, ComplexFacing.WEST_NORTH),
	NORTH_EAST_DOWN("north_east_down", ComplexFacing.NORTH_DOWN, ComplexFacing.DOWN_NORTH, ComplexFacing.NORTH_EAST),
	EAST_SOUTH_DOWN("east_south_down", ComplexFacing.EAST_DOWN, ComplexFacing.DOWN_EAST, ComplexFacing.EAST_SOUTH);
	
	public static ExtendedStairFacing get(String name) {
		for (ExtendedStairFacing placing : values()) if (placing.name.equals(name)) return placing;
		return null;
	}
	
	public final String name;
	public final ComplexFacing vanilla, vanillaFlipped, vertical;
	
	ExtendedStairFacing(String name, ComplexFacing normal, ComplexFacing flipped, ComplexFacing vertical) {
		this.name = name;
		this.vanilla = normal;
		this.vanillaFlipped = flipped;
		this.vertical = vertical;
	}

	@Override
	public String getSerializedName() {
		return name;
	}
}
