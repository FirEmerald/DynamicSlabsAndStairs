package com.firemerald.additionalplacements.block;

import java.util.Arrays;
import java.util.stream.Stream;

import com.firemerald.additionalplacements.block.interfaces.IAdditionalBeaconBeamBlock;
import com.firemerald.additionalplacements.block.interfaces.ICarpetBlock;
import com.firemerald.additionalplacements.client.models.definitions.CarpetModels;
import com.firemerald.additionalplacements.client.models.definitions.StateModelDefinition;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BeaconBeamBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class AdditionalCarpetBlock extends AdditionalFloorBlock<CarpetBlock> implements ICarpetBlock<CarpetBlock>
{
	public static final VoxelShape[] SHAPES = {
			Block.box(0, 15, 0, 16, 16, 16),
			Block.box(0, 0, 0, 16, 16, 1),
			Block.box(0, 0, 15, 16, 16, 16),
			Block.box(0, 0, 0, 1, 16, 16),
			Block.box(15, 0, 0, 16, 16, 16)
	};

	public static AdditionalCarpetBlock of(CarpetBlock carpet)
	{
		return carpet instanceof BeaconBeamBlock ? new AdditionalBeaconBeamCarpetBlock(carpet) : new AdditionalCarpetBlock(carpet);
	}

	private static class AdditionalBeaconBeamCarpetBlock extends AdditionalCarpetBlock implements IAdditionalBeaconBeamBlock<CarpetBlock>
	{
		AdditionalBeaconBeamCarpetBlock(CarpetBlock carpet)
		{
			super(carpet);
		}
	}

	private AdditionalCarpetBlock(CarpetBlock carpet)
	{
		super(carpet);
		this.registerDefaultState(copyProperties(getOtherBlockState(), this.stateDefinition.any()).setValue(PLACING, Direction.NORTH));
		((IVanillaCarpetBlock) carpet).setOtherBlock(this);
	}

	@Override
	public VoxelShape getShapeInternal(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context)
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
	public BlockState updateShapeImpl(BlockState thisState, Direction updatedDirection, BlockState otherState, LevelAccessor level, BlockPos thisPos, BlockPos otherPos)
	{
		return !thisState.canSurvive(level, thisPos) ? Blocks.AIR.defaultBlockState() : thisState;
	}

	@Override
	@Deprecated
	public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos)
	{
		return !level.isEmptyBlock(pos.relative(state.getValue(PLACING)));
	}

	@Override
	@Environment(EnvType.CLIENT)
	public ResourceLocation getBaseModelPrefix() {
		return CarpetModels.BASE_MODEL_FOLDER;
	}

	@Override
	@Environment(EnvType.CLIENT)
	public StateModelDefinition getModelDefinition(BlockState state) {
		return CarpetModels.getModel(state);
	}

	@Override
	@Environment(EnvType.CLIENT)
	public Stream<StateModelDefinition> allModelDefinitions() {
		return Arrays.stream(CarpetModels.MODEL_DEFINITIONS);
	}
}
