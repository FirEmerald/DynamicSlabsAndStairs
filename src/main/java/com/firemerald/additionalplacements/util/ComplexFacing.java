package com.firemerald.additionalplacements.util;

import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;

public enum ComplexFacing implements IStringSerializable {
	//vanilla facing
	SOUTH_UP("south_up", Direction.SOUTH, Direction.UP, Direction.EAST),
	WEST_UP("west_up", Direction.WEST, Direction.UP, Direction.SOUTH),
	NORTH_UP("north_up", Direction.NORTH, Direction.UP, Direction.WEST),
	EAST_UP("east_up", Direction.EAST, Direction.UP, Direction.NORTH),

	SOUTH_DOWN("south_down", Direction.SOUTH, Direction.DOWN, Direction.WEST),
	WEST_DOWN("west_down", Direction.WEST, Direction.DOWN, Direction.NORTH),
	NORTH_DOWN("north_down", Direction.NORTH, Direction.DOWN, Direction.EAST),
	EAST_DOWN("east_down", Direction.EAST, Direction.DOWN, Direction.SOUTH),

	//rotate X -90 (270)
	DOWN_SOUTH("down_south", Direction.DOWN, Direction.SOUTH, Direction.EAST),
	WEST_SOUTH("west_south", Direction.WEST, Direction.SOUTH, Direction.DOWN),
	UP_SOUTH("up_south", Direction.UP, Direction.SOUTH, Direction.WEST),
	EAST_SOUTH("east_south", Direction.EAST, Direction.SOUTH, Direction.UP),

	DOWN_NORTH("down_north", Direction.DOWN, Direction.NORTH, Direction.WEST),
	WEST_NORTH("west_north", Direction.WEST, Direction.NORTH, Direction.UP),
	UP_NORTH("up_north", Direction.UP, Direction.NORTH, Direction.EAST),
	EAST_NORTH("east_north", Direction.EAST, Direction.NORTH, Direction.DOWN),
	
	//rotate X-90 (270) then Y-90 (270)
	DOWN_EAST("down_east", Direction.DOWN, Direction.EAST, Direction.NORTH),
	SOUTH_EAST("south_east", Direction.SOUTH, Direction.EAST, Direction.DOWN),
	UP_EAST("up_east", Direction.UP, Direction.EAST, Direction.SOUTH),
	NORTH_EAST("north_east", Direction.NORTH, Direction.EAST, Direction.UP),
	
	DOWN_WEST("down_west", Direction.DOWN, Direction.WEST, Direction.SOUTH),
	SOUTH_WEST("south_west", Direction.SOUTH, Direction.WEST, Direction.UP),
	UP_WEST("up_west", Direction.UP, Direction.WEST, Direction.NORTH),
	NORTH_WEST("north_west", Direction.NORTH, Direction.WEST, Direction.DOWN);
	
	public static final ComplexFacing[] ALL_FACING = values();
	
	private static final ComplexFacing[][] FACING = new ComplexFacing[6][6];
	
	static {
		for (ComplexFacing facing : values()) FACING[facing.forward.get3DDataValue()][facing.up.get3DDataValue()] = facing;
		for (ComplexFacing facing : values()) facing.flipped = forFacing(facing.up, facing.forward);
	}
	
	public static ComplexFacing forFacing(Direction forward, Direction up) {
		return FACING[forward.get3DDataValue()][up.get3DDataValue()];
	}
	
	public static ComplexFacing get(String name) {
		for (ComplexFacing facing : values()) if (facing.name.equals(name)) return facing;
		return null;
	}
	
	public final String name;
	public final Direction forward, up, left, backward, down, right;
	private ComplexFacing flipped;
	
	ComplexFacing(String name, Direction forward, Direction up, Direction left) {
		this.name = name;
		this.backward = (this.forward = forward).getOpposite();
		this.down = (this.up = up).getOpposite();
		this.right = (this.left = left).getOpposite();
	}
	
	public ComplexFacing flipped() {
		return flipped;
	}

	@Override
	public String getSerializedName() {
		return name;
	}
}
