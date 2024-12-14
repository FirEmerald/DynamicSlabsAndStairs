package com.firemerald.additionalplacements.block.stairs.extended;

import java.util.function.Consumer;

import com.firemerald.additionalplacements.AdditionalPlacementsMod;
import com.firemerald.additionalplacements.block.interfaces.IStateFixer;
import com.firemerald.additionalplacements.block.stairs.AdditionalStairBlockBase;
import com.firemerald.additionalplacements.block.stairs.StairPropertyVersion;
import com.firemerald.additionalplacements.block.stairs.common.CommonStairShapeState;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;

public class AdditionalExtendedStairBlock extends AdditionalStairBlockBase
{
	public static final StairPropertyVersion PROPERTY_VERSION = StairPropertyVersion.EXTENDED_V1;
	public static final EnumProperty<StairPropertyVersion> PROPERTY_VERSION_PROP = EnumProperty.create(PROPERTY_VERSION_NAME, StairPropertyVersion.class, PROPERTY_VERSION);
	
	public static final EnumProperty<ExtendedStairFacing> FACING = EnumProperty.create("facing", ExtendedStairFacing.class);
	public static final EnumProperty<ExtendedStairShape> SHAPE = EnumProperty.create("shape", ExtendedStairShape.class);

	public static AdditionalExtendedStairBlock of(StairBlock stairs)
	{
		AdditionalExtendedStairBlock ret = new AdditionalExtendedStairBlock(stairs);
		return ret;
	}
	
	private AdditionalExtendedStairBlock(StairBlock stairs)
	{
		super(stairs);
	}

	@Override
	public boolean isValidProperty(Property<?> prop) {
		return prop != StairBlock.FACING && prop != StairBlock.HALF && prop != StairBlock.SHAPE;
	}
	
	@Override
	public BlockState setDefaults(BlockState defaultState)
	{
		return defaultState.setValue(FACING, ExtendedStairFacing.SOUTH_EAST_UP).setValue(SHAPE, ExtendedStairShape.VERTICAL_STRAIGHT).setValue(PROPERTY_VERSION_PROP, PROPERTY_VERSION);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
	{
		builder.add(FACING, SHAPE, PROPERTY_VERSION_PROP);
		super.createBlockStateDefinition(builder);
	}

	@Override
	public BlockState getBlockStateInternal(CommonStairShapeState commonShapeState, BlockState currentState)
	{
		ExtendedStairShapeState shapeState = commonShapeState.extended();
		if (shapeState == null) {
			AdditionalPlacementsMod.LOGGER.warn(this.toString() + " attempted to set to invalid stair shape state \"" + commonShapeState.toString() + "\", using closest match instead", new Exception());
			return getBlockState(commonShapeState.closestVanillaShape, currentState);
		} else {
			return getDefaultAdditionalState(currentState)
					.setValue(FACING, shapeState.facing)
					.setValue(SHAPE, shapeState.shape);
		}
	}

	@Override
	public void applyStateInternal(CommonStairShapeState commonShapeState, CompoundTag properties, Consumer<Block> changeBlock) {
		ExtendedStairShapeState shapeState = commonShapeState.extended();
		if (shapeState == null) {
			AdditionalPlacementsMod.LOGGER.warn(this.toString() + " attempted to set to invalid stair shape state \"" + commonShapeState.toString() + "\", using closest match instead", new Exception());
			applyState(commonShapeState.closestVanillaShape, properties, changeBlock);
		} else {
			IStateFixer.setProperty(properties, PROPERTY_VERSION_PROP, PROPERTY_VERSION);
			IStateFixer.setProperty(properties, FACING, shapeState.facing);
			IStateFixer.setProperty(properties, SHAPE, shapeState.shape);
		}
	}

	@Override
	public StairPropertyVersion getPropertyVersion() {
		return PROPERTY_VERSION;
	}

	@Override
	public CommonStairShapeState getShapeState(BlockState blockState) {
		return ExtendedStairShapeState.toCommon(blockState.getValue(FACING), blockState.getValue(SHAPE));
	}

	@Override
	public boolean allowVerticalConnections() {
		return true;
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
    	return "no_mixed_connections";
	}
}
