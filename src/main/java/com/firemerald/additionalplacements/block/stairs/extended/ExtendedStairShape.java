package com.firemerald.additionalplacements.block.stairs.extended;

import com.firemerald.additionalplacements.block.stairs.common.CommonStairShape;

import net.minecraft.util.StringRepresentable;

public enum ExtendedStairShape implements StringRepresentable {
	//VANILLA_STRAIGHT("vanilla_straight", ExtendedStairShapeFace.VANILLA, CommonStairShape.STRAIGHT),

	//VANILLA_INNER_FRONT_LEFT("vanilla_inner_front_left", ExtendedStairShapeFace.VANILLA, CommonStairShape.INNER_FRONT_LEFT),
	//VANILLA_INNER_FRONT_RIGHT("vanilla_inner_front_right", ExtendedStairShapeFace.VANILLA, CommonStairShape.INNER_FRONT_RIGHT),
	VANILLA_INNER_TOP_LEFT("vanilla_inner_top_left", ExtendedStairShapeFace.VANILLA, CommonStairShape.INNER_TOP_LEFT),
	VANILLA_INNER_TOP_RIGHT("vanilla_inner_top_right", ExtendedStairShapeFace.VANILLA, CommonStairShape.INNER_TOP_RIGHT),
	
	//VANILLA_OUTER_BACK_LEFT("vanilla_outer_back_left", ExtendedStairShapeFace.VANILLA, CommonStairShape.OUTER_BACK_LEFT),
	//VANILLA_OUTER_BACK_RIGHT("vanilla_outer_back_right", ExtendedStairShapeFace.VANILLA, CommonStairShape.OUTER_BACK_RIGHT),
	VANILLA_OUTER_BOTTOM_LEFT("vanilla_outer_bottom_left", ExtendedStairShapeFace.VANILLA, CommonStairShape.OUTER_BOTTOM_LEFT),
	VANILLA_OUTER_BOTTOM_RIGHT("vanilla_outer_bottom_right", ExtendedStairShapeFace.VANILLA, CommonStairShape.OUTER_BOTTOM_RIGHT),

	
	FLIPPED_STRAIGHT("flipped_straight", ExtendedStairShapeFace.VANILLA_FLIPPED, CommonStairShape.STRAIGHT),

	FLIPPED_INNER_FRONT_LEFT("flipped_inner_front_left", ExtendedStairShapeFace.VANILLA_FLIPPED, CommonStairShape.INNER_FRONT_LEFT),
	FLIPPED_INNER_FRONT_RIGHT("flipped_inner_front_right", ExtendedStairShapeFace.VANILLA_FLIPPED, CommonStairShape.INNER_FRONT_RIGHT),
	FLIPPED_INNER_TOP_LEFT("flipped_inner_top_left", ExtendedStairShapeFace.VANILLA_FLIPPED, CommonStairShape.INNER_TOP_LEFT),
	FLIPPED_INNER_TOP_RIGHT("flipped_inner_top_right", ExtendedStairShapeFace.VANILLA_FLIPPED, CommonStairShape.INNER_TOP_RIGHT),
	
	FLIPPED_OUTER_BACK_LEFT("flipped_outer_back_left", ExtendedStairShapeFace.VANILLA_FLIPPED, CommonStairShape.OUTER_BACK_LEFT),
	FLIPPED_OUTER_BACK_RIGHT("flipped_outer_back_right", ExtendedStairShapeFace.VANILLA_FLIPPED, CommonStairShape.OUTER_BACK_RIGHT),
	FLIPPED_OUTER_BOTTOM_LEFT("flipped_outer_bottom_left", ExtendedStairShapeFace.VANILLA_FLIPPED, CommonStairShape.OUTER_BOTTOM_LEFT),
	FLIPPED_OUTER_BOTTOM_RIGHT("flipped_outer_bottom_right", ExtendedStairShapeFace.VANILLA_FLIPPED, CommonStairShape.OUTER_BOTTOM_RIGHT),


	VERTICAL_STRAIGHT("vertical_straight", ExtendedStairShapeFace.VERTICAL, CommonStairShape.STRAIGHT),

	VERTICAL_INNER_FRONT_LEFT("vertical_inner_front_left", ExtendedStairShapeFace.VERTICAL, CommonStairShape.INNER_FRONT_LEFT),
	VERTICAL_INNER_FRONT_RIGHT("vertical_inner_front_right", ExtendedStairShapeFace.VERTICAL, CommonStairShape.INNER_FRONT_RIGHT),
	VERTICAL_INNER_TOP_LEFT("vertical_inner_top_left", ExtendedStairShapeFace.VERTICAL, CommonStairShape.INNER_TOP_LEFT),
	VERTICAL_INNER_TOP_RIGHT("vertical_inner_top_right", ExtendedStairShapeFace.VERTICAL, CommonStairShape.INNER_TOP_RIGHT),
	
	VERTICAL_OUTER_BACK_LEFT("vertical_outer_back_left", ExtendedStairShapeFace.VERTICAL, CommonStairShape.OUTER_BACK_LEFT),
	VERTICAL_OUTER_BACK_RIGHT("vertical_outer_back_right", ExtendedStairShapeFace.VERTICAL, CommonStairShape.OUTER_BACK_RIGHT),
	VERTICAL_OUTER_BOTTOM_LEFT("vertical_outer_bottom_left", ExtendedStairShapeFace.VERTICAL, CommonStairShape.OUTER_BOTTOM_LEFT),
	VERTICAL_OUTER_BOTTOM_RIGHT("vertical_outer_bottom_right", ExtendedStairShapeFace.VERTICAL, CommonStairShape.OUTER_BOTTOM_RIGHT);
	
	public static ExtendedStairShape get(String name) {
		for (ExtendedStairShape shape : values()) if (shape.name.equals(name)) return shape;
		return null;
	}
	
	public final String name;
	public final ExtendedStairShapeFace face;
	public final CommonStairShape shape;
	
	ExtendedStairShape(String name, ExtendedStairShapeFace face, CommonStairShape shape) {
		this.name = name;
		this.face = face;
		this.shape = shape;
	}

	@Override
	public String getSerializedName() {
		return name;
	}
}
