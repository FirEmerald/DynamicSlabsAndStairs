package com.firemerald.additionalplacements.client.models.dynamic;

import java.util.List;

import javax.annotation.Nonnull;

import com.firemerald.additionalplacements.block.AdditionalPlacementBlock;
import com.firemerald.additionalplacements.client.models.BlockModelUtils;
import com.firemerald.additionalplacements.client.models.PlacementModelWrapper;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.ChunkRenderTypeSet;
import net.minecraftforge.client.model.data.ModelData;
import net.minecraftforge.client.model.data.ModelProperty;

public class BakedDynamicModel extends PlacementModelWrapper
{
	public static final ModelProperty<BlockState> MODEL_STATE = new ModelProperty<>();
	
	public BakedDynamicModel(BakedModel ourModel)
	{
		super(ourModel);
	}
	
	private BlockState getModelState(AdditionalPlacementBlock<?> block, BlockState state, ModelData modelData) {
		if (modelData.has(MODEL_STATE)) return modelData.get(MODEL_STATE);
		else return block.getModelState(state);
	}

	@Override
	public List<BakedQuad> getQuads(BlockState state, Direction side, RandomSource rand, ModelData extraData, RenderType renderType)
	{
    	if (state.getBlock() instanceof AdditionalPlacementBlock<?> block) {
    		BlockState modelState = getModelState(block, state, extraData);
    		if (block.rotatesModel(state))
    			return BlockModelUtils.rotatedQuads(modelState, BlockModelUtils::getBakedModel, block.getRotation(state), block.rotatesTexture(state), side, rand, extraData, renderType);
    		else
    			return BlockModelUtils.retexturedQuads(state, modelState, BlockModelUtils::getBakedModel, originalModel, side, rand, extraData, renderType);
    	}
    	else return super.getQuads(state, side, rand);
	}

    @Override
    public boolean useAmbientOcclusion(BlockState state)
    {
    	if (state != null && state.getBlock() instanceof AdditionalPlacementBlock<?> placement) {
    		BlockState modelState = placement.getModelState(state);
    		return BlockModelUtils.getBakedModel(modelState).useAmbientOcclusion(modelState);
    	}
    	else return super.useAmbientOcclusion(state);
    }

    @Override
    public boolean useAmbientOcclusion(BlockState state, RenderType renderType)
    {
    	if (state != null && state.getBlock() instanceof AdditionalPlacementBlock<?> placement) {
    		BlockState modelState = placement.getModelState(state);
    		return BlockModelUtils.getBakedModel(modelState).useAmbientOcclusion(modelState, renderType);
    	}
    	else return super.useAmbientOcclusion(state, renderType);
    }

    @Nonnull
    @Override
    public ModelData getModelData(@Nonnull BlockAndTintGetter level, @Nonnull BlockPos pos, @Nonnull BlockState state, @Nonnull ModelData modelData)
    {
    	if (state.getBlock() instanceof AdditionalPlacementBlock<?> placement) {
    		BlockState modelState = placement.getModelState(state);
    		return BlockModelUtils.getModelData(modelState, modelData).derive().with(MODEL_STATE, modelState).build();
    	} else return modelData;
    }

    @Override
    public ChunkRenderTypeSet getRenderTypes(@Nonnull BlockState state, @Nonnull RandomSource rand, @Nonnull ModelData data)
    {
    	if (state.getBlock() instanceof AdditionalPlacementBlock<?> placement) {
    		BlockState modelState = getModelState(placement, state, data);
    		return BlockModelUtils.getBakedModel(modelState).getRenderTypes(modelState, rand, data);
    	}
    	else return super.getRenderTypes(state, rand, data);
    }

    @Override
    public TextureAtlasSprite getParticleIcon(@Nonnull ModelData data)
    {
    	if (data.has(MODEL_STATE)) return BlockModelUtils.getBakedModel(data.get(MODEL_STATE)).getParticleIcon(data);
    	else return originalModel.getParticleIcon(data);
    }
}
