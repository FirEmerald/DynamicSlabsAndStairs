package com.firemerald.additionalplacements.block;

import com.firemerald.additionalplacements.AdditionalPlacementsMod;
import com.firemerald.additionalplacements.block.interfaces.ICarpetBlock;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CarpetBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;

public class AdditionalCarpetBlock extends AdditionalFloorBlock<CarpetBlock> implements ICarpetBlock<CarpetBlock>
{
	static final ResourceLocation CARPET_BLOCKSTATES = new ResourceLocation(AdditionalPlacementsMod.MOD_ID, "blockstate_templates/carpet.json");
	public static final VoxelShape[] SHAPES = {
			Block.box(0, 15, 0, 16, 16, 16),
			Block.box(0, 0, 0, 16, 16, 1),
			Block.box(0, 0, 15, 16, 16, 16),
			Block.box(0, 0, 0, 1, 16, 16),
			Block.box(15, 0, 0, 16, 16, 16)
	};

	public static AdditionalCarpetBlock of(CarpetBlock carpet)
	{
		return new AdditionalCarpetBlock(carpet);
	}

	@SuppressWarnings("deprecation")
	private AdditionalCarpetBlock(CarpetBlock carpet)
	{
		super(carpet);
		this.registerDefaultState(copyProperties(getModelState(), this.stateDefinition.any()).setValue(PLACING, Direction.NORTH));
		((IVanillaCarpetBlock) carpet).setOtherBlock(this);
	}

	@Override
	public VoxelShape getShapeInternal(BlockState state, IBlockReader level, BlockPos pos, ISelectionContext context)
	{
		return SHAPES[state.getValue(PLACING).ordinal() - 1];
	}

	@Override
	public String getTagTypeName()
	{
		return "carpet";
	}

	@Override
	public String getTagTypeNamePlural()
	{
		return "carpets";
	}

	@Override
	public BlockState updateShapeImpl(BlockState thisState, Direction updatedDirection, BlockState otherState, IWorld level, BlockPos thisPos, BlockPos otherPos)
	{
		return !thisState.canSurvive(level, thisPos) ? Blocks.AIR.defaultBlockState() : thisState;
	}

	@Override
	@Deprecated
	public boolean canSurvive(BlockState state, IWorldReader level, BlockPos pos)
	{
		return !level.isEmptyBlock(pos.relative(state.getValue(PLACING)));
	}

	@Override
	public ResourceLocation getDynamicBlockstateJson() {
		return CARPET_BLOCKSTATES;
	}
}
