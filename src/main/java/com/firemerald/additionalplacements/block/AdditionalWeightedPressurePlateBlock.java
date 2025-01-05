package com.firemerald.additionalplacements.block;

import com.firemerald.additionalplacements.block.interfaces.IWeightedPressurePlateBlock;
import com.firemerald.additionalplacements.client.models.definitions.PressurePlateModels;
import com.firemerald.additionalplacements.client.models.definitions.StateModelDefinition;

import net.minecraft.block.BlockState;
import net.minecraft.block.WeightedPressurePlateBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class AdditionalWeightedPressurePlateBlock extends AdditionalBasePressurePlateBlock<WeightedPressurePlateBlock> implements IWeightedPressurePlateBlock<WeightedPressurePlateBlock>
{
	public static AdditionalWeightedPressurePlateBlock of(WeightedPressurePlateBlock plate)
	{
		return new AdditionalWeightedPressurePlateBlock(plate);
	}

	private AdditionalWeightedPressurePlateBlock(WeightedPressurePlateBlock plate)
	{
		super(plate);
	}

	@Override
	protected int getSignalStrength(World level, BlockPos pos)
	{
		AxisAlignedBB aabb = TOUCH_AABBS[level.getBlockState(pos).getValue(AdditionalFloorBlock.PLACING).ordinal() - 1].move(pos);
		int i = Math.min(level.getEntitiesOfClass(Entity.class, aabb).size(), parentBlock.maxWeight);
		if (i > 0) return MathHelper.ceil(15 * (float) Math.min(parentBlock.maxWeight, i) / parentBlock.maxWeight);
		else return 0;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public StateModelDefinition getModelDefinition(BlockState state) {
		return PressurePlateModels.getWeightedPressurePlateModel(state);
	}
}