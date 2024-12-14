package com.firemerald.additionalplacements.block.stairs.complex;

import com.firemerald.additionalplacements.block.stairs.SpecificStairShapeStateBase;
import com.firemerald.additionalplacements.block.stairs.StairShapeStateMapper;
import com.firemerald.additionalplacements.block.stairs.common.CommonStairShapeState;

public class ComplexStairShapeState extends SpecificStairShapeStateBase {
	public static final int COUNT = 4 * 35;
	private static final ComplexStairShapeState[] VALUES = new ComplexStairShapeState[COUNT];
	
	static {
		for (ComplexStairFacing facing : ComplexStairFacing.values())
			for (ComplexStairShape shape : ComplexStairShape.values())
				VALUES[ordinal(facing, shape)] = new ComplexStairShapeState(facing, shape);
		StairShapeStateMapper.run();
	}
	
	public static int ordinal(ComplexStairFacing facing, ComplexStairShape shape) {
		return (facing.ordinal() * 35) + shape.ordinal();
	}
	
	public static ComplexStairShapeState of(ComplexStairFacing facing, ComplexStairShape shape) {
		return facing == null || shape == null ? null : VALUES[ordinal(facing, shape)];
	}
	
	public static CommonStairShapeState toCommon(ComplexStairFacing facing, ComplexStairShape shape) {
		return facing == null || shape == null ? null : VALUES[ordinal(facing, shape)].common;
	}
	
	public static ComplexStairShapeState[] values() {
		return VALUES.clone();
	}

	public final ComplexStairFacing facing;
	public final ComplexStairShape shape;
	
	private ComplexStairShapeState(ComplexStairFacing facing, ComplexStairShape shape) {
		super(ordinal(facing, shape));
		this.facing = facing;
		this.shape = shape;
	}
	
	@Override
	public String toString() {
		return "ComplexStairShapeState{facing=" + facing.getSerializedName() + ",shape=" + shape.getSerializedName() + "}";
	}
}
