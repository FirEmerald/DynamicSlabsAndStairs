package com.firemerald.additionalplacements.block.stairs;

import java.util.function.Consumer;

import com.firemerald.additionalplacements.AdditionalPlacementsMod;
import com.firemerald.additionalplacements.block.AdditionalPlacementLiquidBlock;
import com.firemerald.additionalplacements.block.interfaces.ISimpleRotationBlock;
import com.firemerald.additionalplacements.block.interfaces.IStairBlock;
import com.firemerald.additionalplacements.block.interfaces.IStateFixer;
import com.firemerald.additionalplacements.block.stairs.common.CommonStairShapeState;
import com.firemerald.additionalplacements.block.stairs.vanilla.VanillaStairShapeState;
import com.firemerald.additionalplacements.client.models.definitions.StairModels;
import com.firemerald.additionalplacements.client.models.definitions.StateModelDefinition;
import com.firemerald.additionalplacements.config.APConfigs;
import com.firemerald.additionalplacements.util.BlockRotation;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public abstract class AdditionalStairBlockBase extends AdditionalPlacementLiquidBlock<StairBlock> implements IStairBlock<StairBlock>, ISimpleRotationBlock, IStateFixer
{
	public static final String PROPERTY_VERSION_NAME = "ap_property_version";
	
	public boolean rotateLogic = false, rotateModel = false, rotateTex = false;

	protected AdditionalStairBlockBase(StairBlock stairs)
	{
		super(stairs);
		this.registerDefaultState(setDefaults(copyProperties(getOtherBlockState(), this.stateDefinition.any())));
		((IVanillaStairBlock) stairs).setOtherBlock(this);
	}
	
	public abstract BlockState setDefaults(BlockState defaultState);

	@Override
	public BlockState getDefaultVanillaState(BlockState currentState)
	{
		return currentState.is(parentBlock) ? currentState : copyProperties(currentState, parentBlock.defaultBlockState());
	}

	@Override
	public BlockState getDefaultAdditionalState(BlockState currentState)
	{
		return currentState.is(this) ? currentState : copyProperties(currentState, this.defaultBlockState());
	}

	@Override
	public String getTagTypeName()
	{
		return "stair";
	}

	@Override
	public String getTagTypeNamePlural()
	{
		return "stairs";
	}

	@Override
	public BlockState withUnrotatedPlacement(BlockState worldState, BlockState modelState) {
		VanillaStairShapeState modelShapeState = getShapeState(worldState).model();
		return modelState
				.setValue(StairBlock.FACING, modelShapeState.facing)
				.setValue(StairBlock.HALF, modelShapeState.half)
				.setValue(StairBlock.SHAPE, modelShapeState.shape);
	}

	public boolean canRotate(BlockState state) {
		return this.getShapeState(state).isRotatedModel();
	}

	@Override
	public BlockRotation getRotation(BlockState state) {
		return this.getShapeState(state).modelRotation();
	}

	@Override
	public boolean rotatesLogic(BlockState state) {
		return rotateLogic && canRotate(state);
	}

	@Override
	public boolean rotatesTexture(BlockState state) {
		return rotateTex && canRotate(state);
	}

	@Override
	public boolean rotatesModel(BlockState state) {
		return rotateModel && canRotate(state);
	}

	@Override
	public void setLogicRotation(boolean useLogicRotation) {
		this.rotateLogic = useLogicRotation;
	}

	@Override
	public void setModelRotation(boolean useTexRotation, boolean useModelRotation) {
		this.rotateTex = useTexRotation;
		this.rotateModel = useModelRotation;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public ResourceLocation getBaseModelPrefix() {
		return StairModels.BASE_MODEL_FOLDER;
	}

	@Override
	public VoxelShape getShapeInternal(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context)
	{
		CommonStairShapeState shapeState = this.getShapeState(state);
		return shapeState.shape.getVoxelShape(shapeState.facing);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public StateModelDefinition getModelDefinition(BlockState state) {
		return StairModels.getModelDefinition(getShapeState(state));
	}

	@Override
	public CompoundTag fix(CompoundTag properties, Consumer<Block> changeBlock) {
		StairPropertyVersion propertyVersion = getMismatchedPropertyVersion(properties);
		if (propertyVersion != null) {
			CommonStairShapeState commonShape = propertyVersion.apply(properties);
			if (commonShape != null) applyState(commonShape, properties, changeBlock);
		}
		return properties;
	}
	
	public StairPropertyVersion getMismatchedPropertyVersion(CompoundTag properties) {
		if (properties.contains(PROPERTY_VERSION_NAME)) {
			String name = properties.getString(PROPERTY_VERSION_NAME);
			properties.remove(PROPERTY_VERSION_NAME);
			StairPropertyVersion version = StairPropertyVersion.get(name);
			if (version != null) {
				if (version == getPropertyVersion()) return null;
				else return version;
			} else {
				AdditionalPlacementsMod.LOGGER.warn(this + " has unknown property version " + name + " and cannot have it's state potentially corrected. This may lead to missing blocks.");
			}
		}
		if (APConfigs.common().fixOldStates.get()) {
			if (properties.contains("shape")) {
				if (properties.contains("facing")) { //potentially V2
					return StairPropertyVersion.OLD_V2;
				} else if (properties.contains("placing")) { //potentially V1
					return StairPropertyVersion.OLD_V1;
				}
			}
		}
		return null;
	}
	
	public void applyState(CommonStairShapeState complexShapeState, CompoundTag properties, Consumer<Block> changeBlock) {
		VanillaStairShapeState vanillaShapeState = complexShapeState.vanilla();
		if (vanillaShapeState != null) { //make vanilla
			changeBlock.accept(getOtherBlock());
			IStateFixer.setProperty(properties, StairBlock.FACING, vanillaShapeState.facing);
			IStateFixer.setProperty(properties, StairBlock.HALF, vanillaShapeState.half);
			IStateFixer.setProperty(properties, StairBlock.SHAPE, vanillaShapeState.shape);
		} else applyStateInternal(complexShapeState, properties, changeBlock);
	}
	
	public abstract void applyStateInternal(CommonStairShapeState complexShapeState, CompoundTag properties, Consumer<Block> changeBlock);
	
	public abstract StairPropertyVersion getPropertyVersion();
}
