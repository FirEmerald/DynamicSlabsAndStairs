package com.firemerald.additionalplacements.block;

import com.firemerald.additionalplacements.block.interfaces.ISimpleRotationBlock;
import com.firemerald.additionalplacements.block.interfaces.IStairBlock;
import com.firemerald.additionalplacements.client.models.definitions.StairModels;
import com.firemerald.additionalplacements.client.models.definitions.StateModelDefinition;
import com.firemerald.additionalplacements.util.*;
import com.firemerald.additionalplacements.util.stairs.CompressedStairFacing;
import com.firemerald.additionalplacements.util.stairs.CompressedStairShape;
import com.firemerald.additionalplacements.util.stairs.StairConnections;
import com.firemerald.additionalplacements.util.stairs.StairShape;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.Property;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.Half;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class VerticalStairBlock extends AdditionalPlacementLiquidBlock<StairsBlock> implements IStairBlock<StairsBlock>, ISimpleRotationBlock
{
	public static final EnumProperty<CompressedStairFacing> FACING = EnumProperty.create("facing", CompressedStairFacing.class, CompressedStairFacing.ALL_FACINGS);

	private static StairConnections staticAllowedConnections;
	
	public static VerticalStairBlock of(StairsBlock stairs, StairConnections allowedConnections)
	{
		staticAllowedConnections = allowedConnections;
		VerticalStairBlock ret = new VerticalStairBlock(stairs, allowedConnections);
		staticAllowedConnections = null;
		return ret;
	}
	
	public final StairConnections allowedConnections;
	public boolean rotateLogic = false, rotateModel = false, rotateTex = false;

	private VerticalStairBlock(StairsBlock stairs, StairConnections allowedConnections)
	{
		super(stairs);
		this.registerDefaultState(copyProperties(getOtherBlockState(), this.stateDefinition.any()).setValue(FACING, CompressedStairFacing.SOUTH_UP_EAST).setValue(allowedConnections.shapeProperty, CompressedStairShape.VERTICAL_STRAIGHT));
		((IVanillaStairBlock) stairs).setOtherBlock(this);
		this.allowedConnections = allowedConnections;
	}

	@Override
	public EnumProperty<CompressedStairShape> shapeProperty() {
		return allowedConnections.shapeProperty;
	}

	@Override
	public boolean isValidProperty(Property<?> prop) {
		return prop != StairsBlock.FACING && prop != StairsBlock.HALF && prop != StairsBlock.SHAPE;
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(FACING, staticAllowedConnections.shapeProperty);
		super.createBlockStateDefinition(builder);
	}

	@Override
	public VoxelShape getShapeInternal(BlockState state, IBlockReader level, BlockPos pos, ISelectionContext context)
	{
		return state.getValue(allowedConnections.shapeProperty).getVoxelShape(state.getValue(FACING));
	}

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
		CompressedStairFacing compressedFacing = worldState.getValue(FACING);
		CompressedStairShape compressedShape = worldState.getValue(allowedConnections.shapeProperty);
		ComplexFacing facing = compressedShape.facingType.fromCompressedFacing(compressedFacing);
		StairShape shape = compressedShape.shape;
		return modelState
				.setValue(StairsBlock.FACING, facing.vanillaStairsFacing)
				.setValue(StairsBlock.HALF, facing.vanillaStairsHalf)
				.setValue(StairsBlock.SHAPE, facing.vanillaStairsHalf == Half.TOP ? shape.vanillaTopShape : shape.vanillaBottomShape);
	}
	
	public boolean canRotate(BlockState state) {
		return state.getValue(allowedConnections.shapeProperty).shape.isRotatedModel;
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
	public BlockRotation getRotation(BlockState state) {
		CompressedStairFacing compressedFacing = state.getValue(FACING);
		CompressedStairShape compressedShape = state.getValue(allowedConnections.shapeProperty);
		return compressedShape.facingType.fromCompressedFacing(compressedFacing).stairsModelRotation;
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
	public StairConnections allowedConnections() {
		return this.allowedConnections;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public ResourceLocation getBaseModelPrefix() {
		return StairModels.BASE_MODEL_FOLDER;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public StateModelDefinition getModelDefinition(BlockState state) {
		return StairModels.getModelDefinition(state, allowedConnections);
	}
}
