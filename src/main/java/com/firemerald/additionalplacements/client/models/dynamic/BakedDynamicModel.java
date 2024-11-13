package com.firemerald.additionalplacements.client.models.dynamic;

import java.util.List;
import java.util.Random;

import javax.annotation.Nonnull;

import com.firemerald.additionalplacements.block.AdditionalPlacementBlock;
import com.firemerald.additionalplacements.client.models.BlockModelUtils;
import com.firemerald.additionalplacements.client.models.PlacementModelWrapper;

import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.client.model.data.ModelProperty;

public class BakedDynamicModel extends PlacementModelWrapper
{
	public static final ModelProperty<BlockState> MODEL_STATE = new ModelProperty<>();
	
	public BakedDynamicModel(BakedModel ourModel)
	{
		super(ourModel);
	}
	
	private BlockState getModelState(AdditionalPlacementBlock<?> block, BlockState state, IModelData modelData) {
		if (modelData.hasProperty(MODEL_STATE)) return modelData.getData(MODEL_STATE);
		else return block.getModelState(state);
	}

	@Override
	public List<BakedQuad> getQuads(BlockState state, Direction side, Random rand, IModelData extraData)
	{
    	if (state.getBlock() instanceof AdditionalPlacementBlock<?> block) {
    		BlockState modelState = getModelState(block, state, extraData);
    		if (block.rotatesModel(state))
    			return BlockModelUtils.rotatedQuads(modelState, BlockModelUtils::getBakedModel, block.getRotation(state), block.rotatesTexture(state), side, rand, extraData);
    		else
    			return BlockModelUtils.retexturedQuads(state, modelState, BlockModelUtils::getBakedModel, originalModel, side, rand, extraData);
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
	public @Nonnull IModelData getModelData(@Nonnull BlockAndTintGetter level, @Nonnull BlockPos pos, @Nonnull BlockState state, @Nonnull IModelData modelData)
    {
    	if (state.getBlock() instanceof AdditionalPlacementBlock<?> placement) {
    		BlockState modelState = placement.getModelState(state);
    		modelData = BlockModelUtils.getModelData(modelState, modelData);
    		modelData.setData(MODEL_STATE, modelState);
    	}
    	return modelData;
    }

    @Override
    public TextureAtlasSprite getParticleIcon(@Nonnull IModelData data)
    {
    	if (data.hasProperty(MODEL_STATE)) return BlockModelUtils.getBakedModel(data.getData(MODEL_STATE)).getParticleIcon(data);
    	else return originalModel.getParticleIcon(data);
    }
}
