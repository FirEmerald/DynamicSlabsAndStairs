package com.firemerald.additionalplacements.block.stairs.complex;

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

public class AdditionalComplexStairBlock extends AdditionalStairBlockBase
{
	public static final StairPropertyVersion PROPERTY_VERSION = StairPropertyVersion.COMPLEX_V1;
	public static final EnumProperty<StairPropertyVersion> PROPERTY_VERSION_PROP = EnumProperty.create(PROPERTY_VERSION_NAME, StairPropertyVersion.class, PROPERTY_VERSION);
	
	public static final EnumProperty<ComplexStairFacing> FACING = EnumProperty.create("facing", ComplexStairFacing.class);
	public static final EnumProperty<ComplexStairShape> SHAPE = EnumProperty.create("shape", ComplexStairShape.class);

	public static AdditionalComplexStairBlock of(StairBlock stairs)
	{
		AdditionalComplexStairBlock ret = new AdditionalComplexStairBlock(stairs);
		return ret;
	}
	
	private AdditionalComplexStairBlock(StairBlock stairs)
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
		return defaultState.setValue(FACING, ComplexStairFacing.NORTH_EAST).setValue(SHAPE, ComplexStairShape.STRAIGHT).setValue(PROPERTY_VERSION_PROP, PROPERTY_VERSION);
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
		if (commonShapeState.complexFlipped()) {
			AdditionalPlacementsMod.LOGGER.warn(this.toString() + " attempted to set to flipped stair shape state \"" + commonShapeState.toString() + "\", unflipping", new Exception());
			return getBlockState(commonShapeState.flipped(), currentState);
		} else {
			ComplexStairShapeState shapeState = commonShapeState.complex();
			return getDefaultAdditionalState(currentState)
					.setValue(FACING, shapeState.facing)
					.setValue(SHAPE, shapeState.shape);
		}
	}

	@Override
	public void applyStateInternal(CommonStairShapeState commonShapeState, CompoundTag properties, Consumer<Block> changeBlock) {
		if (commonShapeState.complexFlipped()) {
			AdditionalPlacementsMod.LOGGER.warn(this.toString() + " attempted to set to flipped stair shape state \"" + commonShapeState.toString() + "\", unflipping", new Exception());
			applyState(commonShapeState.flipped(), properties, changeBlock);
		} else {
			ComplexStairShapeState shapeState = commonShapeState.complex();
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
		return ComplexStairShapeState.toCommon(blockState.getValue(FACING), blockState.getValue(SHAPE));
	}

	@Override
	public boolean allowVerticalConnections() {
		return true;
	}

	@Override
	public boolean allowMixedConnections() {
		return true;
	}

	@Override
	public boolean allowFlippedFacings() {
		return false;
	}

    @Override
	public String connectionsType() {
    	return "all_connections";
	}
}
