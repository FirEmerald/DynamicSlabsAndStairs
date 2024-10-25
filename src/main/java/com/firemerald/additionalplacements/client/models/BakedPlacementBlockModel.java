package com.firemerald.additionalplacements.client.models;

import java.util.List;
import java.util.Random;

import javax.annotation.Nonnull;

import com.firemerald.additionalplacements.block.AdditionalPlacementBlock;
import com.firemerald.additionalplacements.client.BlockModelUtils;

import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockDisplayReader;
import net.minecraftforge.client.model.BakedModelWrapper;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.client.model.data.ModelDataMap;

public class BakedPlacementBlockModel extends BakedModelWrapper<IBakedModel>
{
	public BakedPlacementBlockModel(IBakedModel model)
	{
		super(model);
	}

	@Override
	public List<BakedQuad> getQuads(BlockState state, Direction side, Random rand, IModelData extraData)
	{
		BlockState modelState = extraData.getData(BlockModelUtils.MODEL_STATE);
		if (modelState == null) modelState = BlockModelUtils.getModeledState(state);
		if (state.getBlock() instanceof AdditionalPlacementBlock) {
			AdditionalPlacementBlock<?> block = (AdditionalPlacementBlock<?>) state.getBlock();
			if (block.rotatesModel(state)) return BlockModelUtils.rotatedQuads(modelState, BlockModelUtils::getBakedModel, block.getRotation(state), block.rotatesTexture(state), side, rand, extraData);
		}
		return BlockModelUtils.retexturedQuads(state, modelState, BlockModelUtils::getBakedModel, originalModel, side, rand, extraData);
	}

    @Override
    public boolean isAmbientOcclusion(BlockState state)
    {
    	BlockState modelState = BlockModelUtils.getModeledState(state);
        return BlockModelUtils.getBakedModel(modelState).isAmbientOcclusion(modelState);
    }

	@Override
	public TextureAtlasSprite getParticleTexture(IModelData extraData)
	{
		BlockState modelState = extraData.getData(BlockModelUtils.MODEL_STATE);
		if (modelState != null) return BlockModelUtils.getBakedModel(modelState).getParticleTexture(BlockModelUtils.getModelData(modelState, extraData));
		else return getParticleIcon();
	}

	@Override
	public @Nonnull IModelData getModelData(@Nonnull IBlockDisplayReader level, @Nonnull BlockPos pos, @Nonnull BlockState state, @Nonnull IModelData modelData)
    {
		return new ModelDataMap.Builder().withInitial(BlockModelUtils.MODEL_STATE, BlockModelUtils.getModeledState(state)).build();
    }
	
	public IBakedModel originalModel() {
		return originalModel;
	}
}
