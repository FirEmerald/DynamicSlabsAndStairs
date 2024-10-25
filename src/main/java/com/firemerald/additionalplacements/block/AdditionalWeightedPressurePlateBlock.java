package com.firemerald.additionalplacements.block;

import com.firemerald.additionalplacements.AdditionalPlacementsMod;
import com.firemerald.additionalplacements.block.interfaces.IWeightedPressurePlateBlock;

import net.minecraft.block.WeightedPressurePlateBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class AdditionalWeightedPressurePlateBlock extends AdditionalBasePressurePlateBlock<WeightedPressurePlateBlock> implements IWeightedPressurePlateBlock<WeightedPressurePlateBlock>
{
	static final ResourceLocation WEIGHTED_PRESSURE_PLATE_BLOCKSTATES = new ResourceLocation(AdditionalPlacementsMod.MOD_ID, "blockstate_templates/weighted_pressure_plate.json");
	
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
		AxisAlignedBB aabb = TOUCH_AABBS[level.getBlockState(pos).getValue(AdditionalBasePressurePlateBlock.PLACING).ordinal() - 1].move(pos);
		int i = Math.min(level.getEntitiesOfClass(Entity.class, aabb).size(), parentBlock.maxWeight);
		if (i > 0) return MathHelper.ceil(15 * (float) Math.min(parentBlock.maxWeight, i) / parentBlock.maxWeight);
		else return 0;
	}

	@Override
	public ResourceLocation getDynamicBlockstateJson() {
		return WEIGHTED_PRESSURE_PLATE_BLOCKSTATES;
	}
}