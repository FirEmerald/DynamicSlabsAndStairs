package com.firemerald.additionalplacements.block.stairs.v2;

import com.firemerald.additionalplacements.block.stairs.SpecificStairShapeStateBase;
import com.firemerald.additionalplacements.block.stairs.StairShapeStateMapper;
import com.firemerald.additionalplacements.block.stairs.common.CommonStairShapeState;

public class V2StairShapeState extends SpecificStairShapeStateBase {
	public static final int COUNT = 8 * 40;
	private static final V2StairShapeState[] VALUES = new V2StairShapeState[COUNT];
	
	static {
		for (V2StairFacing facing : V2StairFacing.values())
			for (V2StairShape shape : V2StairShape.values())
				VALUES[ordinal(facing, shape)] = new V2StairShapeState(facing, shape);
		StairShapeStateMapper.run();
	}
	
	public static int ordinal(V2StairFacing facing, V2StairShape shape) {
		return (facing.ordinal() * 40) + shape.ordinal();
	}
	
	public static V2StairShapeState of(V2StairFacing facing, V2StairShape shape) {
		return facing == null || shape == null ? null : VALUES[ordinal(facing, shape)];
	}
	
	public static CommonStairShapeState toCommon(V2StairFacing facing, V2StairShape shape) {
		return facing == null || shape == null ? null : VALUES[ordinal(facing, shape)].common;
	}
	
	public static V2StairShapeState[] values() {
		return VALUES.clone();
	}

	public final V2StairFacing facing;
	public final V2StairShape shape;
	
	private V2StairShapeState(V2StairFacing facing, V2StairShape shape) {
		super(ordinal(facing, shape));
		this.facing = facing;
		this.shape = shape;
	}
	
	@Override
	public String toString() {
		return "V2StairShapeState{facing=" + facing.getSerializedName() + ",shape=" + shape.getSerializedName() + "}";
	}
}
