package com.firemerald.additionalplacements.block.stairs;

import com.firemerald.additionalplacements.block.stairs.common.CommonStairShape;
import com.firemerald.additionalplacements.block.stairs.common.CommonStairShapeState;
import com.firemerald.additionalplacements.block.stairs.v1.V1StairShapeState;
import com.firemerald.additionalplacements.block.stairs.v2.V2StairShapeState;
import com.firemerald.additionalplacements.block.stairs.vanilla.VanillaStairShapeState;
import com.firemerald.additionalplacements.util.BlockRotation;
import com.firemerald.additionalplacements.util.ComplexFacing;

import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.StairsShape;

public class StairShapeStateMapper {
	private static boolean lock = false;
	
	public static void run() {
		if (!lock) {
			lock = true;
			setMapping(StairsShape.STRAIGHT   , StairsShape.STRAIGHT   , CommonStairShape.STRAIGHT         , CommonStairShape.OUTER_BACK_LEFT_BOTTOM_RIGHT, CommonStairShape.OUTER_BACK_RIGHT_BOTTOM_LEFT);
			setMapping(StairsShape.INNER_LEFT , StairsShape.INNER_RIGHT, CommonStairShape.INNER_FRONT_LEFT , CommonStairShape.INNER_TOP_LEFT              , CommonStairShape.INNER_BOTH_LEFT             );
			setMapping(StairsShape.INNER_RIGHT, StairsShape.INNER_LEFT , CommonStairShape.INNER_FRONT_RIGHT, CommonStairShape.INNER_TOP_RIGHT             , CommonStairShape.INNER_BOTH_RIGHT            );
			setMapping(StairsShape.OUTER_LEFT , StairsShape.OUTER_RIGHT, CommonStairShape.OUTER_BACK_LEFT  , CommonStairShape.OUTER_BOTTOM_LEFT           , CommonStairShape.OUTER_BOTH_LEFT             );
			setMapping(StairsShape.OUTER_RIGHT, StairsShape.OUTER_LEFT , CommonStairShape.OUTER_BACK_RIGHT , CommonStairShape.OUTER_BOTTOM_RIGHT          , CommonStairShape.OUTER_BOTH_RIGHT            );
			//V1
			for (V1StairShapeState v1 : V1StairShapeState.values()) {
				CommonStairShapeState common = CommonStairShapeState.of(v1.placing.equivalent(v1.shape), v1.shape.equivalent);
				v1.setCommon(common);
			}
			//V2
			for (V2StairShapeState v2 : V2StairShapeState.values()) {
				CommonStairShapeState common = CommonStairShapeState.of(v2.shape.facingType.fromCompressedFacing(v2.facing), v2.shape.shape);
				v2.setCommon(common);
			}
		}
	}
	
	private static void setMapping(StairsShape vanillaBottomShape, StairsShape vanillaTopShape, CommonStairShape commonShape, CommonStairShape... extraShapes) {
		setMapping(vanillaBottomShape, vanillaTopShape, commonShape, true);
		for (CommonStairShape extraShape : extraShapes) setMapping(vanillaBottomShape, vanillaTopShape, extraShape, false);
	}
	
	private static void setMapping(StairsShape vanillaBottomShape, StairsShape vanillaTopShape, CommonStairShape commonShape, boolean shapesEqual) {
		for (Direction facing : Direction.values()) if (facing.getAxis() != Axis.Y) {
			setMapping(Axis.X, BlockRotation.X_270_Y_270, facing, Half.BOTTOM, vanillaBottomShape, Direction.EAST , commonShape, shapesEqual);
			setMapping(Axis.X, BlockRotation.X_270_Y_270, facing, Half.TOP,    vanillaTopShape   , Direction.WEST , commonShape, shapesEqual);
			setMapping(                                   facing, Half.BOTTOM, vanillaBottomShape, Direction.UP   , commonShape, shapesEqual);
			setMapping(                                   facing, Half.TOP,    vanillaTopShape   , Direction.DOWN , commonShape, shapesEqual);
			setMapping(Axis.Z, BlockRotation.X_270      , facing, Half.BOTTOM, vanillaBottomShape, Direction.SOUTH, commonShape, shapesEqual);
			setMapping(Axis.Z, BlockRotation.X_270      , facing, Half.TOP,    vanillaTopShape   , Direction.NORTH, commonShape, shapesEqual);
		}
	}
	
	private static void setMapping(Direction vanillaFacing, Half vanillaHalf, StairsShape vanillaShape, Direction commonUp, CommonStairShape commonShape, boolean shapesEqual) {
		VanillaStairShapeState vanilla = VanillaStairShapeState.of(vanillaFacing, vanillaHalf, vanillaShape);
		CommonStairShapeState common = CommonStairShapeState.of(ComplexFacing.forFacing(vanillaFacing.getOpposite(), commonUp), commonShape);
		common.setModel(vanilla, BlockRotation.IDENTITY, shapesEqual);
		if (shapesEqual) {
			common.setVanilla(vanilla);
			vanilla.setCommon(common);
		}
	}
	
	private static void setMapping(Axis axis, BlockRotation rotation, Direction vanillaFacing, Half vanillaHalf, StairsShape vanillaShape, Direction commonUp, CommonStairShape commonShape, boolean shapesEqual) {
		VanillaStairShapeState vanilla = VanillaStairShapeState.of(vanillaFacing, vanillaHalf, vanillaShape);
		CommonStairShapeState common = CommonStairShapeState.of(ComplexFacing.forFacing(rotation.apply(vanillaFacing.getOpposite()), commonUp), commonShape);
		common.setModel(vanilla, rotation, shapesEqual);
	}
}
