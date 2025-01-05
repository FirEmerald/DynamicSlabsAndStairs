package com.firemerald.additionalplacements.block.stairs.vanilla;

import com.firemerald.additionalplacements.block.stairs.SpecificStairShapeStateBase;
import com.firemerald.additionalplacements.block.stairs.StairShapeStateMapper;
import com.firemerald.additionalplacements.block.stairs.common.CommonStairShapeState;

import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.StairsShape;

public class VanillaStairShapeState extends SpecificStairShapeStateBase {
	public static final int COUNT = 4 * 2 * 5;
	private static final VanillaStairShapeState[] VALUES = new VanillaStairShapeState[COUNT];

	static {
		for (Direction facing : Direction.values())
			if (facing.getAxis() != Axis.Y)
				for (Half half : Half.values())
					for (StairsShape shape : StairsShape.values())
						VALUES[ordinal(facing, half, shape)] = new VanillaStairShapeState(facing, half, shape);
		StairShapeStateMapper.run();
	}

	public static int ordinal(Direction facing, Half half, StairsShape shape) {
		return (((facing.get2DDataValue() * 2) + half.ordinal()) * 5) + shape.ordinal();
	}

	public static VanillaStairShapeState of(Direction facing, Half half, StairsShape shape) {
		return facing == null || facing.getAxis() == Axis.Y || half == null || shape == null ? null : VALUES[ordinal(facing, half, shape)];
	}

	public static CommonStairShapeState toCommon(Direction facing, Half half, StairsShape shape) {
		return facing == null || facing.getAxis() == Axis.Y || half == null || shape == null ? null : VALUES[ordinal(facing, half, shape)].common;
	}

	public static VanillaStairShapeState[] values() {
		return VALUES.clone();
	}

	public final Direction facing;
	public final Half half;
	public final StairsShape shape;

	private VanillaStairShapeState(Direction facing, Half half, StairsShape shape) {
		super(ordinal(facing, half, shape));
		this.facing = facing;
		this.half = half;
		this.shape = shape;
	}

	@Override
	public String toString() {
		return "VanillaStairShapeState{facing=" + facing.getSerializedName() + ",half=" + half.getSerializedName() + ",shape=" + shape.getSerializedName() + "}";
	}
}
