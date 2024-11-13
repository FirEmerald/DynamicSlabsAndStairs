package com.firemerald.additionalplacements.client.models.dynamic;

import java.util.List;

import com.firemerald.additionalplacements.block.AdditionalPlacementBlock;
import com.firemerald.additionalplacements.client.models.BlockModelUtils;
import com.firemerald.additionalplacements.client.models.PlacementModelWrapper;

import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;

public class BakedDynamicModel extends PlacementModelWrapper
{
	public BakedDynamicModel(BakedModel ourModel)
	{
		super(ourModel);
	}

	@Override
	public List<BakedQuad> getQuads(BlockState state, Direction side, RandomSource rand)
	{
    	if (state.getBlock() instanceof AdditionalPlacementBlock<?> block) {
    		BlockState modelState = block.getModelState(state);
    		if (block.rotatesModel(state))
    			return BlockModelUtils.rotatedQuads(modelState, BlockModelUtils::getBakedModel, block.getRotation(state), block.rotatesTexture(state), side, rand);
    		else
    			return BlockModelUtils.retexturedQuads(state, modelState, BlockModelUtils::getBakedModel, wrapped, side, rand);
    	}
    	else return super.getQuads(state, side, rand);
	}
}
