package com.firemerald.additionalplacements.block;

import com.firemerald.additionalplacements.block.interfaces.IFloorBlock;
import com.firemerald.additionalplacements.block.interfaces.ISimpleRotationBlock;
import com.firemerald.additionalplacements.util.BlockRotation;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;

public abstract class AdditionalFloorBlock<T extends Block> extends AdditionalPlacementBlock<T> implements IFloorBlock<T>, ISimpleRotationBlock
{
	private boolean rotateLogic = true, rotateTex = true, rotateModel = true;
	public static final DirectionProperty PLACING = AdditionalBlockStateProperties.HORIZONTAL_OR_UP_FACING;

	public AdditionalFloorBlock(T block)
	{
		super(block);
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(PLACING);
		super.createBlockStateDefinition(builder);
	}

	@Override
	public Direction getPlacing(BlockState blockState)
	{
		return blockState.getValue(PLACING);
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
	public BlockState withUnrotatedPlacement(BlockState worldState, BlockState modelState) {
		return modelState;
	}

	@Override
	public boolean rotatesLogic(BlockState state) {
		return rotateLogic;
	}

	@Override
	public boolean rotatesTexture(BlockState state) {
		return rotateTex;
	}

	@Override
	public boolean rotatesModel(BlockState state) {
		return rotateModel;
	}

	@Override
	public BlockRotation getRotation(BlockState state) {
		switch (state.getValue(PLACING)) {
		case NORTH: return BlockRotation.X_270;
		case EAST: return BlockRotation.X_270_Y_90;
		case SOUTH: return BlockRotation.X_270_Y_180;
		case WEST: return BlockRotation.X_270_Y_270;
		case UP: return BlockRotation.X_180;
		default: return BlockRotation.IDENTITY;
		}
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
}
