package com.firemerald.additionalplacements.block.stairs.complex;

import com.firemerald.additionalplacements.block.stairs.common.CommonStairShape;

import net.minecraft.util.StringRepresentable;

public enum ComplexStairShape implements StringRepresentable {
	//vanilla
	STRAIGHT("straight", ComplexStairShapeFace.NORMAL, CommonStairShape.STRAIGHT),

	INNER_FRONT_LEFT("inner_front_left", ComplexStairShapeFace.NORMAL, CommonStairShape.INNER_FRONT_LEFT),
	INNER_FRONT_RIGHT("inner_front_right", ComplexStairShapeFace.NORMAL, CommonStairShape.INNER_FRONT_RIGHT),
	INNER_TOP_LEFT("inner_top_left", ComplexStairShapeFace.NORMAL, CommonStairShape.INNER_TOP_LEFT),
	INNER_TOP_RIGHT("inner_top_right", ComplexStairShapeFace.NORMAL, CommonStairShape.INNER_TOP_RIGHT),
	INNER_BOTH_LEFT("inner_both_left", ComplexStairShapeFace.NORMAL, CommonStairShape.INNER_BOTH_LEFT),
	INNER_BOTH_RIGHT("inner_both_right", ComplexStairShapeFace.NORMAL, CommonStairShape.INNER_BOTH_RIGHT),
	
	OUTER_BACK_LEFT("outer_back_left", ComplexStairShapeFace.NORMAL, CommonStairShape.OUTER_BACK_LEFT),
	OUTER_BACK_RIGHT("outer_back_right", ComplexStairShapeFace.NORMAL, CommonStairShape.OUTER_BACK_RIGHT),
	OUTER_BOTTOM_LEFT("outer_bottom_left", ComplexStairShapeFace.NORMAL, CommonStairShape.OUTER_BOTTOM_LEFT),
	OUTER_BOTTOM_RIGHT("outer_bottom_right", ComplexStairShapeFace.NORMAL, CommonStairShape.OUTER_BOTTOM_RIGHT),
	OUTER_BOTH_LEFT("outer_both_left", ComplexStairShapeFace.NORMAL, CommonStairShape.OUTER_BOTH_LEFT),
	OUTER_BOTH_RIGHT("outer_both_right", ComplexStairShapeFace.NORMAL, CommonStairShape.OUTER_BOTH_RIGHT),

	OUTER_BACK_LEFT_BOTTOM_RIGHT("outer_back_left_bottom_right", ComplexStairShapeFace.NORMAL, CommonStairShape.OUTER_BACK_LEFT_BOTTOM_RIGHT),
	OUTER_BACK_RIGHT_BOTTOM_LEFT("outer_back_right_bottom_left", ComplexStairShapeFace.NORMAL, CommonStairShape.OUTER_BACK_RIGHT_BOTTOM_LEFT),
	
	//upFront
	UP_FRONT_INNER_TOP_RIGHT("up_front_inner_top_right", ComplexStairShapeFace.UP_FRONT, CommonStairShape.INNER_TOP_RIGHT),
	UP_FRONT_INNER_BOTH_RIGHT("up_front_inner_both_right", ComplexStairShapeFace.UP_FRONT, CommonStairShape.INNER_BOTH_RIGHT),
	
	UP_FRONT_OUTER_BOTTOM_RIGHT("up_front_outer_bottom_right", ComplexStairShapeFace.UP_FRONT, CommonStairShape.OUTER_BOTTOM_RIGHT),
	UP_FRONT_OUTER_BOTH_RIGHT("up_front_outer_both_right", ComplexStairShapeFace.UP_FRONT, CommonStairShape.OUTER_BOTH_RIGHT),

	UP_FRONT_OUTER_BACK_LEFT_BOTTOM_RIGHT("up_front_outer_back_left_bottom_right", ComplexStairShapeFace.UP_FRONT, CommonStairShape.OUTER_BACK_RIGHT_BOTTOM_LEFT),
	
	//upTop
	UP_TOP_INNER_TOP_LEFT("up_top_inner_top_left", ComplexStairShapeFace.UP_TOP, CommonStairShape.INNER_TOP_LEFT),
	UP_TOP_INNER_BOTH_LEFT("up_top_inner_both_left", ComplexStairShapeFace.UP_TOP, CommonStairShape.INNER_BOTH_LEFT),
	
	UP_TOP_OUTER_BOTTOM_LEFT("up_top_outer_bottom_left", ComplexStairShapeFace.UP_TOP, CommonStairShape.OUTER_BOTTOM_LEFT),
	UP_TOP_OUTER_BOTH_LEFT("up_top_outer_both_left", ComplexStairShapeFace.UP_TOP, CommonStairShape.OUTER_BOTH_LEFT),

	UP_TOP_OUTER_BACK_RIGHT_BOTTOM_LEFT("up_top_outer_back_right_bottom_left", ComplexStairShapeFace.UP_TOP, CommonStairShape.OUTER_BACK_LEFT_BOTTOM_RIGHT),
	
	//downFront
	DOWN_FRONT_INNER_TOP_LEFT("down_front_inner_top_left", ComplexStairShapeFace.DOWN_FRONT, CommonStairShape.INNER_TOP_LEFT),
	DOWN_FRONT_INNER_BOTH_LEFT("down_front_inner_both_left", ComplexStairShapeFace.DOWN_FRONT, CommonStairShape.INNER_BOTH_LEFT),
	
	DOWN_FRONT_OUTER_BOTTOM_LEFT("down_front_outer_bottom_left", ComplexStairShapeFace.DOWN_FRONT, CommonStairShape.OUTER_BOTTOM_LEFT),
	DOWN_FRONT_OUTER_BOTH_LEFT("down_front_outer_both_left", ComplexStairShapeFace.DOWN_FRONT, CommonStairShape.OUTER_BOTH_LEFT),

	DOWN_FRONT_OUTER_BACK_RIGHT_BOTTOM_LEFT("down_front_outer_back_right_bottom_left", ComplexStairShapeFace.DOWN_FRONT, CommonStairShape.OUTER_BACK_LEFT_BOTTOM_RIGHT),
	
	//downTop
	DOWN_TOP_INNER_TOP_RIGHT("down_top_inner_top_right", ComplexStairShapeFace.DOWN_TOP, CommonStairShape.INNER_TOP_RIGHT),
	DOWN_TOP_INNER_BOTH_RIGHT("down_top_inner_both_right", ComplexStairShapeFace.DOWN_TOP, CommonStairShape.INNER_BOTH_RIGHT),
	
	DOWN_TOP_OUTER_BOTTOM_RIGHT("down_top_outer_bottom_right", ComplexStairShapeFace.DOWN_TOP, CommonStairShape.OUTER_BOTTOM_RIGHT),
	DOWN_TOP_OUTER_BOTH_RIGHT("down_top_outer_both_right", ComplexStairShapeFace.DOWN_TOP, CommonStairShape.OUTER_BOTH_RIGHT),

	DOWN_TOP_OUTER_BACK_LEFT_BOTTOM_RIGHT("down_top_outer_back_left_bottom_right", ComplexStairShapeFace.DOWN_TOP, CommonStairShape.OUTER_BACK_RIGHT_BOTTOM_LEFT);
	
	public final String name;
	public final ComplexStairShapeFace face;
	public final CommonStairShape shape;
	
	ComplexStairShape(String name, ComplexStairShapeFace face, CommonStairShape shape) {
		this.name = name;
		this.face = face;
		this.shape = shape;
	}

	@Override
	public String getSerializedName() {
		return name;
	}
}
