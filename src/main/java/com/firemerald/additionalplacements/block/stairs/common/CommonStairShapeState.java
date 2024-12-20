package com.firemerald.additionalplacements.block.stairs.common;

import java.util.HashMap;
import java.util.Map;

import com.firemerald.additionalplacements.block.stairs.StairShapeStateBase;
import com.firemerald.additionalplacements.block.stairs.StairShapeStateMapper;
import com.firemerald.additionalplacements.block.stairs.vanilla.VanillaStairShapeState;
import com.firemerald.additionalplacements.util.BlockRotation;
import com.firemerald.additionalplacements.util.ComplexFacing;

import net.minecraft.util.Direction;
import net.minecraft.util.Direction.Axis;
import net.minecraft.util.IStringSerializable;

public class CommonStairShapeState extends StairShapeStateBase implements IStringSerializable, Comparable<CommonStairShapeState> {
	public static final int COUNT = 24 * 15;
	private static final CommonStairShapeState[] VALUES = new CommonStairShapeState[COUNT];
	private static final Map<String, CommonStairShapeState> MASTER_MAPPING = new HashMap<>(COUNT);
	
	static {
		for (ComplexFacing facing : ComplexFacing.values())
			for (CommonStairShape shape : CommonStairShape.values())
				VALUES[ordinal(facing, shape)] = new CommonStairShapeState(facing, shape);
		for (ComplexFacing facing : ComplexFacing.values())
			for (CommonStairShape shape : CommonStairShape.values())
				VALUES[ordinal(facing, shape)].flipped = VALUES[ordinal(facing.flipped(), shape.flipped())];
		StairShapeStateMapper.run();
	}
	
	public static int ordinal(ComplexFacing facing, CommonStairShape shape) {
		return (facing.ordinal() * 15) + shape.ordinal();
	}
	
	public static CommonStairShapeState of(ComplexFacing facing, CommonStairShape shape) {
		return facing == null || shape == null ? null : VALUES[ordinal(facing, shape)];
	}
	
	public static CommonStairShapeState[] values() {
		return VALUES.clone();
	}
	
	public static CommonStairShapeState get(String name) {
		return MASTER_MAPPING.get(name);
	}

	public final ComplexFacing facing;
	public final CommonStairShape shape;
	public final CommonStairShapeState closestVanillaShape;
	private CommonStairShapeState flipped;
	private VanillaStairShapeState model;
	private BlockRotation modelRotation;
	private boolean isRotatedModel;
	private VanillaStairShapeState vanilla;
	public final boolean isComplexFlipped;
	private final String name;
	
	private CommonStairShapeState(ComplexFacing facing, CommonStairShape shape) {
		super(ordinal(facing, shape));
		this.facing = facing;
		this.shape = shape;
		this.closestVanillaShape = of(facing, shape.closestVanillaMatch);
		this.isComplexFlipped = facing.forward.getAxis() == Axis.Y || facing.left == Direction.DOWN;
		this.name = facing.getSerializedName() + "_" + shape.getSerializedName();
		MASTER_MAPPING.put(name, this);
	}
	
	public CommonStairShapeState flipped() {
		return flipped;
	}
	
	public void setModel(VanillaStairShapeState model, BlockRotation modelRotation, boolean isRotatedModel) {
		this.model = model;
		this.modelRotation = modelRotation;
		this.isRotatedModel = isRotatedModel;
	}
	
	public VanillaStairShapeState model() {
		return model;
	}
	
	public BlockRotation modelRotation() {
		return modelRotation;
	}
	
	public boolean isRotatedModel() {
		return isRotatedModel;
	}
	
	public void setVanilla(VanillaStairShapeState vanilla) {
		this.vanilla = vanilla;
	}
	
	public VanillaStairShapeState vanilla() {
		return vanilla;
	}
	
	@Override
	public String toString() {
		return "CommonStairShapeState{facing=" + facing.getSerializedName() + ",shape=" + shape.getSerializedName() + "}";
	}

	@Override
	public String getSerializedName() {
		return name;
	}

	@Override
	public int compareTo(CommonStairShapeState o) {
		return ordinal - o.ordinal;
	}
}
