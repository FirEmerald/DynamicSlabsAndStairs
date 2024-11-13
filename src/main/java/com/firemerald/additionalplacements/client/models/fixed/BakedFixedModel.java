package com.firemerald.additionalplacements.client.models.fixed;

import java.util.List;

import javax.annotation.Nonnull;

import com.firemerald.additionalplacements.block.AdditionalPlacementBlock;
import com.firemerald.additionalplacements.client.models.BlockModelUtils;
import com.firemerald.additionalplacements.client.models.PlacementModelWrapper;
import com.firemerald.additionalplacements.util.BlockRotation;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.ChunkRenderTypeSet;
import net.minecraftforge.client.model.data.ModelData;

public class BakedFixedModel extends PlacementModelWrapper
{
	private final AdditionalPlacementBlock<?> block;
	private final BakedModel ourModel;
	private final BlockRotation modelRotation;
	
	public BakedFixedModel(AdditionalPlacementBlock<?> block, BakedModel ourModel, BakedModel theirModel, BlockRotation modelRotation)
	{
		super(theirModel);
		this.block = block;
		this.ourModel = ourModel;
		this.modelRotation = modelRotation;
	}

	@Override
	public List<BakedQuad> getQuads(BlockState state, Direction side, RandomSource rand, ModelData extraData, RenderType renderType)
	{
		BlockState modelState = block.getModelState(state);
		if (block.rotatesModel(state))
			return BlockModelUtils.rotatedQuads(modelState, unused -> originalModel, modelRotation, block.rotatesTexture(state), side, rand, extraData, renderType);
		else
			return BlockModelUtils.retexturedQuads(state, modelState, unused -> originalModel, ourModel, side, rand, extraData, renderType);
	}

    @Override
    public boolean useAmbientOcclusion(BlockState state)
    {
    	return originalModel.useAmbientOcclusion(state == null ? null : block.getModelState(state));
    }

    @Override
    public boolean useAmbientOcclusion(BlockState state, RenderType renderType)
    {
    	return originalModel.useAmbientOcclusion(state == null ? null : block.getModelState(state), renderType);
    }

	@Override
	public @Nonnull ModelData getModelData(@Nonnull BlockAndTintGetter level, @Nonnull BlockPos pos, @Nonnull BlockState state, @Nonnull ModelData modelData)
    {
		return originalModel.getModelData(level, pos, block.getModelState(state), modelData);
    }

	@Override
	public ChunkRenderTypeSet getRenderTypes(BlockState state, RandomSource rand, ModelData extraData)
	{
		return originalModel.getRenderTypes(state == null ? null : block.getModelState(state), rand, extraData);
	}
}
