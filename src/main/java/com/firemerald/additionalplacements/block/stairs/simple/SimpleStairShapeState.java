package com.firemerald.additionalplacements.block.stairs.simple;

import com.firemerald.additionalplacements.block.stairs.SpecificStairShapeStateBase;
import com.firemerald.additionalplacements.block.stairs.StairShapeStateMapper;
import com.firemerald.additionalplacements.block.stairs.common.CommonStairShapeState;

import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.StairsShape;

public class SimpleStairShapeState extends SpecificStairShapeStateBase {
	public static final int COUNT = 2 * 4 * 2 * 5;
	private static final SimpleStairShapeState[] VALUES = new SimpleStairShapeState[COUNT];
	
	static {
		for (Direction facing : Direction.values())
			if (facing.getAxis() != Axis.Y)
				for (Half half : Half.values())
					for (StairsShape shape : StairsShape.values()) {
						VALUES[ordinal(Axis.X, facing, half, shape)] = new SimpleStairShapeState(Axis.X, facing, half, shape);
						VALUES[ordinal(Axis.Z, facing, half, shape)] = new SimpleStairShapeState(Axis.Z, facing, half, shape);
					}
		StairShapeStateMapper.run();
	}
	
	public static int ordinal(Axis axis, Direction facing, Half half, StairsShape shape) {
		return (((axis == Axis.Z ? 1 : 0) * 4 + facing.get2DDataValue()) * 2 + half.ordinal()) * 5 + shape.ordinal();
	}
	
	public static SimpleStairShapeState of(Axis axis, Direction facing, Half half, StairsShape shape) {
		return axis == null || axis == Axis.Y || facing == null || facing.getAxis() == Axis.Y || half == null || shape == null ? null : VALUES[ordinal(axis, facing, half, shape)];
	}
	
	public static CommonStairShapeState toCommon(Axis axis, Direction facing, Half half, StairsShape shape) {
		return axis == null || axis == Axis.Y || facing == null || facing.getAxis() == Axis.Y || half == null || shape == null ? null : VALUES[ordinal(axis, facing, half, shape)].common;
	}
	
	public static SimpleStairShapeState[] values() {
		return VALUES.clone();
	}

	public final Axis axis;
	public final Direction facing;
	public final Half half;
	public final StairsShape shape;
	
	private SimpleStairShapeState(Axis axis, Direction facing, Half half, StairsShape shape) {
		super(ordinal(axis, facing, half, shape));
		this.axis = axis;
		this.facing = facing;
		this.half = half;
		this.shape = shape;
	}
	
	@Override
	public String toString() {
		return "SimpleStairShapeState{axis=" + axis.getSerializedName() + ",facing=" + facing.getSerializedName() + ",half=" + half.getSerializedName() + ",shape=" + shape.getSerializedName() + "}";
	}
}
