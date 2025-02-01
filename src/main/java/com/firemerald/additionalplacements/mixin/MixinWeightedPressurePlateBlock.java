package com.firemerald.additionalplacements.mixin;

import com.firemerald.additionalplacements.block.AdditionalWeightedPressurePlateBlock;
import com.firemerald.additionalplacements.block.interfaces.IWeightedPressurePlateBlock.IVanillaWeightedPressurePlateBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.WeightedPressurePlateBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(WeightedPressurePlateBlock.class)
public abstract class MixinWeightedPressurePlateBlock extends Block implements IVanillaWeightedPressurePlateBlock
{
	private MixinWeightedPressurePlateBlock(Properties properties)
	{
		super(properties);
	}

	public AdditionalWeightedPressurePlateBlock plate;

	public WeightedPressurePlateBlock asPlate()
	{
		return (WeightedPressurePlateBlock) (Object) this;
	}

	@Override
	public void setOtherBlock(AdditionalWeightedPressurePlateBlock plate)
	{
		this.plate = plate;
	}

	@Override
	public AdditionalWeightedPressurePlateBlock getOtherBlock()
	{
		return plate;
	}

	@Override
	public boolean hasAdditionalStates()
	{
		return plate != null;
	}

	@Override
	public Direction getPlacing(BlockState blockState)
	{
		return Direction.DOWN;
	}

	@Override
	public boolean isThis(BlockState blockState)
	{
		return blockState.is(asPlate()) || blockState.is(plate);
	}

	@Override
	public BlockState getDefaultVanillaState(BlockState currentState)
	{
		return currentState.is(asPlate()) ? currentState : plate.copyProperties(currentState, asPlate().defaultBlockState());
	}

	@Override
	public BlockState getDefaultAdditionalState(BlockState currentState)
	{
		return currentState.is(plate) ? currentState : plate.copyProperties(currentState, plate.defaultBlockState());
	}

	@Override
	@Unique(silent = true)
	public BlockState getStateForPlacement(BlockPlaceContext context)
	{
		BlockState superRet = super.getStateForPlacement(context);
		if (this.hasAdditionalStates() && enablePlacement(context.getClickedPos(), context.getLevel(), context.getClickedFace(), context.getPlayer())) return getStateForPlacementImpl(context, superRet);
		else return superRet;
	}

	@Override
	@Unique(silent = true)
	@SuppressWarnings("deprecation")
	public BlockState rotate(BlockState blockState, Rotation rotation)
	{
		if (this.hasAdditionalStates()) return rotateImpl(blockState, rotation);
		else return super.rotate(blockState, rotation);
	}

	@Override
	@Unique(silent = true)
	@SuppressWarnings("deprecation")
	public BlockState mirror(BlockState blockState, Mirror mirror)
	{
		if (this.hasAdditionalStates()) return mirrorImpl(blockState, mirror);
		else return super.mirror(blockState, mirror);
	}

	@SuppressWarnings("deprecation")
	@Override
	public BlockState updateShapeImpl(BlockState state, Direction direction, BlockState otherState, LevelAccessor level, BlockPos pos, BlockPos otherPos)
	{
		return super.updateShape(state, direction, otherState, level, pos, otherPos);
	}
}