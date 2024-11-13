package com.firemerald.additionalplacements.client.models.fixed;

import java.util.List;
import java.util.Random;

import javax.annotation.Nonnull;

import com.firemerald.additionalplacements.block.AdditionalPlacementBlock;
import com.firemerald.additionalplacements.client.models.BlockModelUtils;
import com.firemerald.additionalplacements.client.models.PlacementModelWrapper;
import com.firemerald.additionalplacements.util.BlockRotation;

import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockDisplayReader;
import net.minecraftforge.client.model.data.IModelData;

public class BakedFixedModel extends PlacementModelWrapper
{
	private final AdditionalPlacementBlock<?> block;
	private final IBakedModel ourModel;
	private final BlockRotation modelRotation;
	
	public BakedFixedModel(AdditionalPlacementBlock<?> block, IBakedModel ourModel, IBakedModel theirModel, BlockRotation modelRotation)
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
    public boolean isAmbientOcclusion(BlockState state)
    {
    	return originalModel.isAmbientOcclusion(state == null ? null : block.getModelState(state));
    }

	@Override
	public @Nonnull IModelData getModelData(@Nonnull IBlockDisplayReader level, @Nonnull BlockPos pos, @Nonnull BlockState state, @Nonnull IModelData modelData)
    {
		return originalModel.getModelData(level, pos, block.getModelState(state), modelData);
    }
}
