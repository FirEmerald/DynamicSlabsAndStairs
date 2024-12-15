package com.firemerald.additionalplacements.block.stairs;

import java.util.stream.Stream;

import com.firemerald.additionalplacements.block.stairs.common.CommonStairShape;
import com.firemerald.additionalplacements.block.stairs.common.CommonStairShapeState;
import com.firemerald.additionalplacements.util.ComplexFacing;

public enum StairConnectionsType {
	SIMPLE("tooltip.additionalplacements.stair_connections.no_vertical_connections", false, false, true, ComplexFacing.UP_SOUTH, Stream.of(CommonStairShapeState.values())
			.filter(state -> state.vanilla() == null && !state.shape.isVertical)
			.toArray(CommonStairShapeState[]::new)),
	EXTENDED("tooltip.additionalplacements.stair_connections.no_mixed_connections", true, false, true, ComplexFacing.UP_SOUTH, Stream.of(CommonStairShapeState.values())
			.filter(state -> state.vanilla() == null && !state.shape.isMixed)
			.toArray(CommonStairShapeState[]::new)),
	COMPLEX("tooltip.additionalplacements.stair_connections.all_connections", true, true, false, ComplexFacing.NORTH_EAST, Stream.of(CommonStairShapeState.values())
			.filter(state -> state.vanilla() == null && !state.complexFlipped)
			.toArray(CommonStairShapeState[]::new));

	public final String tooltip;
	public final boolean allowVertical, allowMixed, allowFlipped;
	public final CommonStairShapeState defaultShapeState;
	public final CommonStairShapeState[] allowedShapeStates;
	public final StairShapeStateProperty stateProperty;
	
	StairConnectionsType(String tooltip, boolean allowVertical, boolean allowMixed, boolean allowFlipped, ComplexFacing defaultFacing, CommonStairShapeState... allowedShapeStates) {
		this.tooltip = tooltip;
		this.allowVertical = allowVertical;
		this.allowMixed = allowMixed;
		this.allowFlipped = allowFlipped;
		this.defaultShapeState = CommonStairShapeState.of(defaultFacing, CommonStairShape.STRAIGHT);
		this.allowedShapeStates = allowedShapeStates;
		stateProperty = new StairShapeStateProperty("front_top_shape", allowedShapeStates);
	}
}
