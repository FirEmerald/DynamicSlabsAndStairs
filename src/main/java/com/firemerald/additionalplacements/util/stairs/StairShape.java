package com.firemerald.additionalplacements.util.stairs;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import com.firemerald.additionalplacements.util.ComplexFacing;
import com.firemerald.additionalplacements.util.VoxelShapes;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.StairsShape;
import net.minecraft.world.phys.shapes.VoxelShape;

public enum StairShape implements StringRepresentable {
	STRAIGHT("straight", VoxelShapes::getStraightStairs, StairsShape.STRAIGHT, StairsShape.STRAIGHT, false, false, true),

	INNER_FRONT_LEFT("inner_front_left", VoxelShapes::getLeftInnerStairs, StairsShape.INNER_RIGHT, StairsShape.INNER_LEFT, false, false, true),
	INNER_FRONT_RIGHT("inner_front_right", VoxelShapes::getRightInnerStairs, StairsShape.INNER_LEFT, StairsShape.INNER_RIGHT, false, false, true),
	INNER_TOP_LEFT("inner_top_left", VoxelShapes::getLeftInnerStairs, StairsShape.INNER_RIGHT, StairsShape.INNER_LEFT, true, false, false),
	INNER_TOP_RIGHT("inner_top_right", VoxelShapes::getRightInnerStairs, StairsShape.INNER_LEFT, StairsShape.INNER_RIGHT, true, false, false),
	INNER_BOTH_LEFT("inner_both_left", VoxelShapes::getLeftInnerStairs, StairsShape.INNER_RIGHT, StairsShape.INNER_LEFT, true, true, false),
	INNER_BOTH_RIGHT("inner_both_right", VoxelShapes::getRightInnerStairs, StairsShape.INNER_LEFT, StairsShape.INNER_RIGHT, true, true, false),
	
	OUTER_BACK_LEFT("outer_back_left", VoxelShapes::getLeftOuterBackFlatStairs, StairsShape.OUTER_RIGHT, StairsShape.OUTER_LEFT, false, false, true),
	OUTER_BACK_RIGHT("outer_back_right", VoxelShapes::getRightOuterBackFlatStairs, StairsShape.OUTER_LEFT, StairsShape.OUTER_RIGHT, false, false, true),
	OUTER_BOTTOM_LEFT("outer_bottom_left", VoxelShapes::getLeftOuterBottomFlatStairs, StairsShape.OUTER_RIGHT, StairsShape.OUTER_LEFT, true, false, false),
	OUTER_BOTTOM_RIGHT("outer_bottom_right", VoxelShapes::getRightOuterBottomFlatStairs, StairsShape.OUTER_RIGHT, StairsShape.OUTER_LEFT, true, false, false),
	OUTER_BOTH_LEFT("outer_both_left", VoxelShapes::getLeftOuterStairs, StairsShape.OUTER_RIGHT, StairsShape.OUTER_LEFT, true, true, false),
	OUTER_BOTH_RIGHT("outer_both_right", VoxelShapes::getRightOuterStairs, StairsShape.OUTER_RIGHT, StairsShape.OUTER_LEFT, true, true, false),

	OUTER_BACK_LEFT_BOTTOM_RIGHT("outer_back_left_bottom_right", VoxelShapes::getCounterClockwiseTwistStairs, StairsShape.STRAIGHT, StairsShape.STRAIGHT, true, true, false),
	OUTER_BACK_RIGHT_BOTTOM_LEFT("outer_back_right_bottom_left", VoxelShapes::getClockwiseTwistStairs, StairsShape.STRAIGHT, StairsShape.STRAIGHT, true, true, false);
	
	public static final StairShape[] ALL_SHAPES = values();
	public static final StairShape[] NO_VERTICAL_CONNECTIONS;
	public static final StairShape[] NO_MIXED_CONNECTIONS;

	private static final StairShape[] FROM_BASIC_TOP = new StairShape[5];
	private static final StairShape[] FROM_BASIC_BOTTOM = new StairShape[5];
	
	static {
		STRAIGHT.flipped = STRAIGHT;
		
		setFlipped(INNER_FRONT_LEFT, INNER_TOP_RIGHT);
		setFlipped(INNER_TOP_LEFT, INNER_FRONT_RIGHT);
		setFlipped(INNER_BOTH_LEFT, INNER_BOTH_RIGHT);
		
		setFlipped(OUTER_BACK_LEFT, OUTER_BOTTOM_RIGHT);
		setFlipped(OUTER_BOTTOM_LEFT, OUTER_BACK_RIGHT);
		setFlipped(OUTER_BOTH_LEFT, OUTER_BOTH_RIGHT);

		OUTER_BACK_LEFT_BOTTOM_RIGHT.flipped = OUTER_BACK_LEFT_BOTTOM_RIGHT;
		OUTER_BACK_RIGHT_BOTTOM_LEFT.flipped = OUTER_BACK_RIGHT_BOTTOM_LEFT;
		
		List<StairShape> noVerticalConnections = new ArrayList<>();
		List<StairShape> noMixedConnections = new ArrayList<>();
		for (StairShape shape : ALL_SHAPES) {
			if (!shape.isVerticalConnection) noVerticalConnections.add(shape);
			if (!shape.isMixedConnection) noMixedConnections.add(shape);
			if (shape.vanillaTopShape != null) FROM_BASIC_TOP[shape.vanillaTopShape.ordinal()] = shape;
			if (shape.vanillaBottomShape != null) FROM_BASIC_BOTTOM[shape.vanillaBottomShape.ordinal()] = shape;
		}
		NO_VERTICAL_CONNECTIONS = noVerticalConnections.toArray(StairShape[]::new);
		NO_MIXED_CONNECTIONS = noMixedConnections.toArray(StairShape[]::new);
	}
	
	private static void setFlipped(StairShape a, StairShape b) {
		a.flipped = b;
		b.flipped = a;
	}
	
	public static StairShape getShape(StairsShape basicShape, Half half) {
		return (half == Half.TOP ? FROM_BASIC_TOP : FROM_BASIC_BOTTOM)[basicShape.ordinal()];
	}
	
	public static StairShape get(String name) {
		for (StairShape shape : ALL_SHAPES) if (shape.name.equals(name)) return shape;
		return null;
	}
	
	public final String name;
	public final Function<ComplexFacing, VoxelShape> getShape;
	public final StairsShape vanillaTopShape, vanillaBottomShape;
	public final boolean isVerticalConnection, isMixedConnection, isRotatedModel;
	private StairShape flipped;
	
	StairShape(String name, Function<ComplexFacing, VoxelShape> getShape, StairsShape vanillaTopShape, StairsShape vanillaBottomShape, boolean isVerticalConnection, boolean isMixedConnection, boolean isRotatedModel) {
		this.name = name;
		this.getShape = getShape;
		this.vanillaTopShape = vanillaTopShape;
		this.vanillaBottomShape = vanillaBottomShape;
		this.isVerticalConnection = isVerticalConnection;
		this.isMixedConnection = isMixedConnection;
		this.isRotatedModel = isRotatedModel;
	}
	
	public VoxelShape getVoxelShape(ComplexFacing facing) {
		return getShape.apply(facing);
	}
	
	public StairsShape getVanillaShape(Half half) {
		return half == Half.TOP ? vanillaTopShape : vanillaBottomShape;
	}
	
	public StairsShape getVanillaShape(ComplexFacing facing) {
		return isRotatedModel ? getVanillaShape(facing.vanillaStairsHalf) : null;
	}
	
	public StairShape flipped() { 
		return flipped;
	}

	@Override
	public String getSerializedName() {
		return name;
	}
}