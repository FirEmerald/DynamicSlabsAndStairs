package com.firemerald.additionalplacements.block.stairs.simple;

import java.util.function.Consumer;

import com.firemerald.additionalplacements.AdditionalPlacementsMod;
import com.firemerald.additionalplacements.block.interfaces.IStateFixer;
import com.firemerald.additionalplacements.block.stairs.AdditionalStairBlockBase;
import com.firemerald.additionalplacements.block.stairs.StairPropertyVersion;
import com.firemerald.additionalplacements.block.stairs.common.CommonStairShapeState;
import com.firemerald.additionalplacements.util.BlockRotation;

import net.minecraft.core.Direction.Axis;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;

public class AdditionalSimpleStairBlock extends AdditionalStairBlockBase
{
	public static final StairPropertyVersion PROPERTY_VERSION = StairPropertyVersion.SIMPLE_V1;
	public static final EnumProperty<StairPropertyVersion> PROPERTY_VERSION_PROP = EnumProperty.create(PROPERTY_VERSION_NAME, StairPropertyVersion.class, PROPERTY_VERSION);
	
	public static final EnumProperty<Axis> AXIS = BlockStateProperties.HORIZONTAL_AXIS;

	public static AdditionalSimpleStairBlock of(StairBlock stairs)
	{
		AdditionalSimpleStairBlock ret = new AdditionalSimpleStairBlock(stairs);
		return ret;
	}
	
	public boolean rotateLogic = false, rotateModel = false, rotateTex = false;

	private AdditionalSimpleStairBlock(StairBlock stairs)
	{
		super(stairs);
	}
	
	@Override
	public BlockState setDefaults(BlockState defaultState)
	{
		return defaultState.setValue(AXIS, Axis.Z).setValue(PROPERTY_VERSION_PROP, PROPERTY_VERSION);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
	{
		builder.add(AXIS, PROPERTY_VERSION_PROP);
		super.createBlockStateDefinition(builder);
	}

	@Override
	public BlockState withUnrotatedPlacement(BlockState worldState, BlockState modelState) {
		return modelState; //no changes needed
	}

	@Override
	public boolean canRotate(BlockState state) {
		return true;
	}

	@Override
	public BlockRotation getRotation(BlockState state) {
		return state.getValue(AXIS) == Axis.X ? BlockRotation.X_270_Y_270 : BlockRotation.X_270;
	}

	@Override
	public BlockState getBlockStateInternal(CommonStairShapeState commonShapeState, BlockState currentState)
	{
		SimpleStairShapeState shapeState = commonShapeState.simple();
		if (shapeState == null) {
			AdditionalPlacementsMod.LOGGER.warn(this.toString() + " attempted to set to invalid stair shape state \"" + commonShapeState.toString() + "\", using equivalent straight instead", new Exception());
			return getBlockState(commonShapeState.closestVanillaShape, currentState);
		} else {
			return getDefaultAdditionalState(currentState)
					.setValue(AXIS, shapeState.axis)
					.setValue(StairBlock.FACING, shapeState.facing)
					.setValue(StairBlock.HALF, shapeState.half)
					.setValue(StairBlock.SHAPE, shapeState.shape);
		}
	}

	@Override
	public void applyStateInternal(CommonStairShapeState commonShapeState, CompoundTag properties, Consumer<Block> changeBlock) {
		SimpleStairShapeState shapeState = commonShapeState.simple();
		if (shapeState == null) {
			AdditionalPlacementsMod.LOGGER.warn(this.toString() + " attempted to set to invalid stair shape state \"" + commonShapeState.toString() + "\", using equivalent straight instead", new Exception());
			applyState(commonShapeState.closestVanillaShape, properties, changeBlock);
		} else {
			IStateFixer.setProperty(properties, PROPERTY_VERSION_PROP, PROPERTY_VERSION);
			IStateFixer.setProperty(properties, AXIS, shapeState.axis);
			IStateFixer.setProperty(properties, StairBlock.FACING, shapeState.facing);
			IStateFixer.setProperty(properties, StairBlock.HALF, shapeState.half);
			IStateFixer.setProperty(properties, StairBlock.SHAPE, shapeState.shape);
		}
	}

	@Override
	public StairPropertyVersion getPropertyVersion() {
		return PROPERTY_VERSION;
	}

	@Override
	public CommonStairShapeState getShapeState(BlockState blockState) {
		return SimpleStairShapeState.toCommon(blockState.getValue(AXIS), blockState.getValue(StairBlock.FACING), blockState.getValue(StairBlock.HALF), blockState.getValue(StairBlock.SHAPE));
	}

	@Override
	public boolean allowVerticalConnections() {
		return false;
	}

	@Override
	public boolean allowMixedConnections() {
		return false;
	}

	@Override
	public boolean allowFlippedFacings() {
		return true;
	}

    @Override
	public String connectionsType() {
    	return "no_vertical_connections";
	}
}
