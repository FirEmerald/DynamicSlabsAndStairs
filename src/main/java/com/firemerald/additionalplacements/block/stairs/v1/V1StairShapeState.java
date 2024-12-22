package com.firemerald.additionalplacements.block.stairs.v1;

import com.firemerald.additionalplacements.block.stairs.SpecificStairShapeStateBase;
import com.firemerald.additionalplacements.block.stairs.StairShapeStateMapper;
import com.firemerald.additionalplacements.block.stairs.common.CommonStairShapeState;

public class V1StairShapeState extends SpecificStairShapeStateBase {
	public static final int COUNT = 4 * 23;
	private static final V1StairShapeState[] VALUES = new V1StairShapeState[COUNT];
	
	static {
		for (V1StairPlacing placing : V1StairPlacing.values())
			for (V1StairShape shape : V1StairShape.values())
				VALUES[ordinal(placing, shape)] = new V1StairShapeState(placing, shape);
		StairShapeStateMapper.run();
	}
	
	public static int ordinal(V1StairPlacing placing, V1StairShape shape) {
		return (placing.ordinal() * 23) + shape.ordinal();
	}
	
	public static V1StairShapeState of(V1StairPlacing placing, V1StairShape shape) {
		return placing == null || shape == null ? null : VALUES[ordinal(placing, shape)];
	}
	
	public static CommonStairShapeState toCommon(V1StairPlacing placing, V1StairShape shape) {
		return placing == null || shape == null ? null : VALUES[ordinal(placing, shape)].common;
	}
	
	public static V1StairShapeState[] values() {
		return VALUES.clone();
	}

	public final V1StairPlacing placing;
	public final V1StairShape shape;
	
	private V1StairShapeState(V1StairPlacing placing, V1StairShape shape) {
		super(ordinal(placing, shape));
		this.placing = placing;
		this.shape = shape;
	}
	
	@Override
	public String toString() {
		return "V1StairShapeState{placing=" + placing.getSerializedName() + ",shape=" + shape.getSerializedName() + "}";
	}
}
