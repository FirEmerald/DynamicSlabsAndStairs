package com.firemerald.additionalplacements.block.stairs.v2;

import com.firemerald.additionalplacements.block.stairs.common.CommonStairShape;

import net.minecraft.util.StringRepresentable;

public enum V2StairShape implements StringRepresentable {
	//NORMAL_STRAIGHT("normal_straight", V1StairPlacingType.NORMAL, CommonStairShape.STRAIGHT),

	//NORMAL_INNER_FRONT_LEFT("normal_inner_front_left", V1StairPlacingType.NORMAL, CommonStairShape.INNER_FRONT_LEFT),
	//NORMAL_INNER_FRONT_RIGHT("normal_inner_front_right", V1StairPlacingType.NORMAL, CommonStairShape.INNER_FRONT_RIGHT),
	NORMAL_INNER_TOP_LEFT("normal_inner_top_left", V2StairFacingType.NORMAL, CommonStairShape.INNER_TOP_LEFT),
	NORMAL_INNER_TOP_RIGHT("normal_inner_top_right", V2StairFacingType.NORMAL, CommonStairShape.INNER_TOP_RIGHT),
	NORMAL_INNER_BOTH_LEFT("normal_inner_both_left", V2StairFacingType.NORMAL, CommonStairShape.INNER_BOTH_LEFT),
	NORMAL_INNER_BOTH_RIGHT("normal_inner_both_right", V2StairFacingType.NORMAL, CommonStairShape.INNER_BOTH_RIGHT),

	//NORMAL_OUTER_BACK_LEFT("normal_outer_back_left", V1StairPlacingType.NORMAL, CommonStairShape.OUTER_BACK_LEFT),
	//NORMAL_OUTER_BACK_RIGHT("normal_outer_back_right", V1StairPlacingType.NORMAL, CommonStairShape.OUTER_BACK_RIGHT),
	NORMAL_OUTER_BOTTOM_LEFT("normal_outer_bottom_left", V2StairFacingType.NORMAL, CommonStairShape.OUTER_BOTTOM_LEFT),
	NORMAL_OUTER_BOTTOM_RIGHT("normal_outer_bottom_right", V2StairFacingType.NORMAL, CommonStairShape.OUTER_BOTTOM_RIGHT),
	NORMAL_OUTER_BOTH_LEFT("normal_outer_both_left", V2StairFacingType.NORMAL, CommonStairShape.OUTER_BOTH_LEFT),
	NORMAL_OUTER_BOTH_RIGHT("normal_outer_both_right", V2StairFacingType.NORMAL, CommonStairShape.OUTER_BOTH_RIGHT),

	NORMAL_OUTER_BACK_LEFT_BOTTOM_RIGHT("normal_outer_back_left_bottom_right", V2StairFacingType.NORMAL, CommonStairShape.OUTER_BACK_LEFT_BOTTOM_RIGHT),
	NORMAL_OUTER_BACK_RIGHT_BOTTOM_LEFT("normal_outer_back_right_bottom_left", V2StairFacingType.NORMAL, CommonStairShape.OUTER_BACK_RIGHT_BOTTOM_LEFT),


	FLIPPED_STRAIGHT("flipped_straight", V2StairFacingType.FLIPPED, CommonStairShape.STRAIGHT),

	FLIPPED_INNER_FRONT_LEFT("flipped_inner_front_left", V2StairFacingType.FLIPPED, CommonStairShape.INNER_FRONT_LEFT),
	FLIPPED_INNER_FRONT_RIGHT("flipped_inner_front_right", V2StairFacingType.FLIPPED, CommonStairShape.INNER_FRONT_RIGHT),
	FLIPPED_INNER_TOP_LEFT("flipped_inner_top_left", V2StairFacingType.FLIPPED, CommonStairShape.INNER_TOP_LEFT),
	FLIPPED_INNER_TOP_RIGHT("flipped_inner_top_right", V2StairFacingType.FLIPPED, CommonStairShape.INNER_TOP_RIGHT),
	FLIPPED_INNER_BOTH_LEFT("flipped_inner_both_left", V2StairFacingType.FLIPPED, CommonStairShape.INNER_BOTH_LEFT),
	FLIPPED_INNER_BOTH_RIGHT("flipped_inner_both_right", V2StairFacingType.FLIPPED, CommonStairShape.INNER_BOTH_RIGHT),

	FLIPPED_OUTER_BACK_LEFT("flipped_outer_back_left", V2StairFacingType.FLIPPED, CommonStairShape.OUTER_BACK_LEFT),
	FLIPPED_OUTER_BACK_RIGHT("flipped_outer_back_right", V2StairFacingType.FLIPPED, CommonStairShape.OUTER_BACK_RIGHT),
	FLIPPED_OUTER_BOTTOM_LEFT("flipped_outer_bottom_left", V2StairFacingType.FLIPPED, CommonStairShape.OUTER_BOTTOM_LEFT),
	FLIPPED_OUTER_BOTTOM_RIGHT("flipped_outer_bottom_right", V2StairFacingType.FLIPPED, CommonStairShape.OUTER_BOTTOM_RIGHT),
	FLIPPED_OUTER_BOTH_LEFT("flipped_outer_both_left", V2StairFacingType.FLIPPED, CommonStairShape.OUTER_BOTH_LEFT),
	FLIPPED_OUTER_BOTH_RIGHT("flipped_outer_both_right", V2StairFacingType.FLIPPED, CommonStairShape.OUTER_BOTH_RIGHT),

	FLIPPED_OUTER_BACK_LEFT_BOTTOM_RIGHT("flipped_outer_back_left_bottom_right", V2StairFacingType.FLIPPED, CommonStairShape.OUTER_BACK_LEFT_BOTTOM_RIGHT),
	FLIPPED_OUTER_BACK_RIGHT_BOTTOM_LEFT("flipped_outer_back_right_bottom_left", V2StairFacingType.FLIPPED, CommonStairShape.OUTER_BACK_RIGHT_BOTTOM_LEFT),


	VERTICAL_STRAIGHT("vertical_straight", V2StairFacingType.VERTICAL, CommonStairShape.STRAIGHT),

	VERTICAL_INNER_FRONT_LEFT("vertical_inner_front_left", V2StairFacingType.VERTICAL, CommonStairShape.INNER_FRONT_LEFT),
	VERTICAL_INNER_FRONT_RIGHT("vertical_inner_front_right", V2StairFacingType.VERTICAL, CommonStairShape.INNER_FRONT_RIGHT),
	VERTICAL_INNER_TOP_LEFT("vertical_inner_top_left", V2StairFacingType.VERTICAL, CommonStairShape.INNER_TOP_LEFT),
	VERTICAL_INNER_TOP_RIGHT("vertical_inner_top_right", V2StairFacingType.VERTICAL, CommonStairShape.INNER_TOP_RIGHT),
	VERTICAL_INNER_BOTH_LEFT("vertical_inner_both_left", V2StairFacingType.VERTICAL, CommonStairShape.INNER_BOTH_LEFT),
	VERTICAL_INNER_BOTH_RIGHT("vertical_inner_both_right", V2StairFacingType.VERTICAL, CommonStairShape.INNER_BOTH_RIGHT),

	VERTICAL_OUTER_BACK_LEFT("vertical_outer_back_left", V2StairFacingType.VERTICAL, CommonStairShape.OUTER_BACK_LEFT),
	VERTICAL_OUTER_BACK_RIGHT("vertical_outer_back_right", V2StairFacingType.VERTICAL, CommonStairShape.OUTER_BACK_RIGHT),
	VERTICAL_OUTER_BOTTOM_LEFT("vertical_outer_bottom_left", V2StairFacingType.VERTICAL, CommonStairShape.OUTER_BOTTOM_LEFT),
	VERTICAL_OUTER_BOTTOM_RIGHT("vertical_outer_bottom_right", V2StairFacingType.VERTICAL, CommonStairShape.OUTER_BOTTOM_RIGHT),
	VERTICAL_OUTER_BOTH_LEFT("vertical_outer_both_left", V2StairFacingType.VERTICAL, CommonStairShape.OUTER_BOTH_LEFT),
	VERTICAL_OUTER_BOTH_RIGHT("vertical_outer_both_right", V2StairFacingType.VERTICAL, CommonStairShape.OUTER_BOTH_RIGHT),

	VERTICAL_OUTER_BACK_LEFT_BOTTOM_RIGHT("vertical_outer_back_left_bottom_right", V2StairFacingType.VERTICAL, CommonStairShape.OUTER_BACK_LEFT_BOTTOM_RIGHT),
	VERTICAL_OUTER_BACK_RIGHT_BOTTOM_LEFT("vertical_outer_back_right_bottom_left", V2StairFacingType.VERTICAL, CommonStairShape.OUTER_BACK_RIGHT_BOTTOM_LEFT);

	public static V2StairShape get(String name) {
		for (V2StairShape shape : values()) if (shape.name.equals(name)) return shape;
		return null;
	}

	public final String name;
	public final V2StairFacingType facingType;
	public final CommonStairShape shape;

	V2StairShape(String name, V2StairFacingType facingType, CommonStairShape shape) {
		this.name = name;
		this.facingType = facingType;
		this.shape = shape;
	}

	@Override
	public String getSerializedName() {
		return name;
	}
}
