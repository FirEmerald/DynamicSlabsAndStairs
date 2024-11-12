package com.firemerald.additionalplacements.client.models;

import java.util.List;
import java.util.Random;

import com.firemerald.additionalplacements.block.AdditionalPlacementBlock;
import com.firemerald.additionalplacements.client.BlockModelUtils;
import com.firemerald.additionalplacements.util.BlockRotation;

import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;

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
	public List<BakedQuad> getQuads(BlockState state, Direction side, Random rand)
	{
		BlockState modelState = block.getModelState(state);
		if (block.rotatesModel(state))
			return BlockModelUtils.rotatedQuads(modelState, unused -> wrapped, modelRotation, block.rotatesTexture(state), side, rand);
		else
			return BlockModelUtils.retexturedQuads(state, modelState, unused -> wrapped, ourModel, side, rand);
	}
}
