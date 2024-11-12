package com.firemerald.additionalplacements.client.models;

import java.util.List;
import java.util.Random;

import javax.annotation.Nonnull;

import com.firemerald.additionalplacements.block.AdditionalPlacementBlock;
import com.firemerald.additionalplacements.client.BlockModelUtils;
import com.firemerald.additionalplacements.util.BlockRotation;

import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.data.IModelData;

public class BakedPlacementBlockModel extends PlacementModelWrapper
{
	private final AdditionalPlacementBlock<?> block;
	private final BakedModel ourModel;
	private final BlockRotation modelRotation;
	
	public BakedPlacementBlockModel(AdditionalPlacementBlock<?> block, BakedModel ourModel, BakedModel theirModel, BlockRotation modelRotation)
	{
		super(theirModel);
		this.block = block;
		this.ourModel = ourModel;
		this.modelRotation = modelRotation;
	}

	@Override
	public List<BakedQuad> getQuads(BlockState state, Direction side, Random rand, IModelData extraData)
	{
		BlockState modelState = block.getModelState(state);
		if (block.rotatesModel(state))
			return BlockModelUtils.rotatedQuads(modelState, unused -> originalModel, modelRotation, block.rotatesTexture(state), side, rand, extraData);
		else
			return BlockModelUtils.retexturedQuads(state, modelState, unused -> originalModel, ourModel, side, rand, extraData);
	}

    @Override
    public boolean useAmbientOcclusion(BlockState state)
    {
    	return originalModel.useAmbientOcclusion(state == null ? null : block.getModelState(state));
    }

	@Override
    public IModelData getModelData(@Nonnull BlockAndTintGetter level, @Nonnull BlockPos pos, @Nonnull BlockState state, @Nonnull IModelData modelData)
    {
		return originalModel.getModelData(level, pos, block.getModelState(state), modelData);
    }
}
