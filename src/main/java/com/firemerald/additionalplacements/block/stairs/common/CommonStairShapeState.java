package com.firemerald.additionalplacements.block.stairs.common;

import com.firemerald.additionalplacements.block.stairs.StairShapeStateBase;
import com.firemerald.additionalplacements.block.stairs.complex.ComplexStairShapeState;
import com.firemerald.additionalplacements.block.stairs.extended.ExtendedStairShapeState;
import com.firemerald.additionalplacements.block.stairs.simple.SimpleStairShapeState;
import com.firemerald.additionalplacements.block.stairs.vanilla.VanillaStairShapeState;
import com.firemerald.additionalplacements.util.BlockRotation;
import com.firemerald.additionalplacements.util.ComplexFacing;

public class CommonStairShapeState extends StairShapeStateBase {
	public static final int COUNT = 24 * 15;
	private static final CommonStairShapeState[] VALUES = new CommonStairShapeState[COUNT];
	
	static {
		for (ComplexFacing facing : ComplexFacing.values())
			for (CommonStairShape shape : CommonStairShape.values())
				VALUES[ordinal(facing, shape)] = new CommonStairShapeState(facing, shape);
		for (ComplexFacing facing : ComplexFacing.values())
			for (CommonStairShape shape : CommonStairShape.values())
				VALUES[ordinal(facing, shape)].flipped = VALUES[ordinal(facing.flipped(), shape.flipped())];
	}
	
	public static int ordinal(ComplexFacing facing, CommonStairShape shape) {
		return (facing.ordinal() * 15) + shape.ordinal();
	}
	
	public static CommonStairShapeState of(ComplexFacing facing, CommonStairShape shape) {
		return facing == null || shape == null ? null : VALUES[ordinal(facing, shape)];
	}
	
	public static ExtendedStairShapeState toExtended(ComplexFacing facing, CommonStairShape shape) {
		return facing == null || shape == null ? null : VALUES[ordinal(facing, shape)].extended;
	}

	public final ComplexFacing facing;
	public final CommonStairShape shape;
	public final CommonStairShapeState closestVanillaShape;
	private CommonStairShapeState flipped;
	private VanillaStairShapeState model;
	private BlockRotation modelRotation;
	private boolean isRotatedModel;
	private VanillaStairShapeState vanilla;
	private SimpleStairShapeState simple;
	private ExtendedStairShapeState extended;
	private ComplexStairShapeState complex;
	private boolean complexFlipped;
	
	private CommonStairShapeState(ComplexFacing facing, CommonStairShape shape) {
		super(ordinal(facing, shape));
		this.facing = facing;
		this.shape = shape;
		this.closestVanillaShape = of(facing, shape.closestVanillaMatch);
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
	
	public void setSimple(SimpleStairShapeState simple) {
		this.simple = simple;
	}
	
	public SimpleStairShapeState simple() {
		return simple;
	}
	
	public void setExtended(ExtendedStairShapeState extended) {
		this.extended = extended;
	}
	
	public ExtendedStairShapeState extended() {
		return extended;
	}
	
	public void setComplex(ComplexStairShapeState complex, boolean complexFlipped) {
		this.complex = complex;
		this.complexFlipped = complexFlipped;
	}
	
	public ComplexStairShapeState complex() {
		return complex;
	}
	
	public boolean complexFlipped() {
		return complexFlipped;
	}
	
	@Override
	public String toString() {
		return "CommonStairShapeState{facing=" + facing.getSerializedName() + ",shape=" + shape.getSerializedName() + "}";
	}
}
