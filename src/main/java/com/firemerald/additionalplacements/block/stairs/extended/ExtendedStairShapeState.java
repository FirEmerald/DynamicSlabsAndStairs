package com.firemerald.additionalplacements.block.stairs.extended;

import com.firemerald.additionalplacements.block.stairs.SpecificStairShapeStateBase;
import com.firemerald.additionalplacements.block.stairs.StairShapeStateMapper;
import com.firemerald.additionalplacements.block.stairs.common.CommonStairShapeState;

public class ExtendedStairShapeState extends SpecificStairShapeStateBase {
	public static final int COUNT = 8 * 22;
	private static final ExtendedStairShapeState[] VALUES = new ExtendedStairShapeState[COUNT];
	
	static {
		for (ExtendedStairFacing facing : ExtendedStairFacing.values())
			for (ExtendedStairShape shape : ExtendedStairShape.values())
				VALUES[ordinal(facing, shape)] = new ExtendedStairShapeState(facing, shape);
		StairShapeStateMapper.run();
	}
	
	public static int ordinal(ExtendedStairFacing facing, ExtendedStairShape shape) {
		return (facing.ordinal() * 22) + shape.ordinal();
	}
	
	public static ExtendedStairShapeState of(ExtendedStairFacing facing, ExtendedStairShape shape) {
		return facing == null || shape == null ? null : VALUES[ordinal(facing, shape)];
	}
	
	public static CommonStairShapeState toCommon(ExtendedStairFacing facing, ExtendedStairShape shape) {
		return facing == null || shape == null ? null : VALUES[ordinal(facing, shape)].common;
	}
	
	public static ExtendedStairShapeState[] values() {
		return VALUES.clone();
	}

	public final ExtendedStairFacing facing;
	public final ExtendedStairShape shape;
	
	private ExtendedStairShapeState(ExtendedStairFacing facing, ExtendedStairShape shape) {
		super(ordinal(facing, shape));
		this.facing = facing;
		this.shape = shape;
	}
	
	@Override
	public String toString() {
		return "ExtendedStairShapeState{facing=" + facing.getSerializedName() + ",shape=" + shape.getSerializedName() + "}";
	}
}
