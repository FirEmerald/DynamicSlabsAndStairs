package com.firemerald.additionalplacements.client.models;

import java.util.*;
import java.util.function.Function;

import javax.annotation.Nonnull;

import com.firemerald.additionalplacements.block.AdditionalPlacementBlock;
import com.firemerald.additionalplacements.util.BlockRotation;

import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.model.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockDisplayReader;
import net.minecraftforge.client.model.data.IModelData;

public class BakedPlacementModel extends PlacementModelWrapper
{
	private static class ModelKey {
		final AdditionalPlacementBlock<?> block;
		final IBakedModel ourModel;
		final IBakedModel theirModel;
		final BlockRotation modelRotation;
		
		private ModelKey(AdditionalPlacementBlock<?> block, IBakedModel ourModel, IBakedModel theirModel, BlockRotation modelRotation) {
			this.block = block;
			this.ourModel = ourModel;
			this.theirModel = theirModel;
			this.modelRotation = modelRotation;
		}
		
		@Override
		public int hashCode() {
			return block.hashCode() ^ ourModel.hashCode() ^ theirModel.hashCode() ^ modelRotation.ordinal();
		}
		
		@Override
		public boolean equals(Object o) {
			if (o == this) return true;
			else if (o == null || o.getClass() != this.getClass()) return false;
			else {
				ModelKey key = (ModelKey) o;
				return key.block == block && 
						Objects.equals(key.ourModel, ourModel) &&
						Objects.equals(key.theirModel, theirModel) &&
						Objects.equals(key.modelRotation, modelRotation);
						
			}
		}
	}
	
	private static final Map<ModelKey, BakedPlacementModel> MODEL_CACHE = new HashMap<>();
	
	public static BakedPlacementModel of(ModelBakery bakery, IModelTransform modelTransform, AdditionalPlacementBlock<?> block, ResourceLocation ourModelLocation, ResourceLocation theirModelLocation, BlockRotation modelRotation, Function<RenderMaterial, TextureAtlasSprite> sprites) {
		return of(block, 
				Unwrapper.unwrap(bakery.getBakedModel(ourModelLocation, modelTransform, sprites)), 
				Unwrapper.unwrap(bakery.getBakedModel(theirModelLocation, ModelRotation.X0_Y0, sprites)),
				modelRotation);
	}
	
	public static BakedPlacementModel of(AdditionalPlacementBlock<?> block, IBakedModel ourModel, IBakedModel theirModel, BlockRotation modelRotation) {
		return MODEL_CACHE.computeIfAbsent(new ModelKey(block, ourModel, theirModel, modelRotation), BakedPlacementModel::new);
	}
	
	public static void clearCache() {
		MODEL_CACHE.clear();
	}
	
	private final AdditionalPlacementBlock<?> block;
	private final IBakedModel ourModel;
	private final BlockRotation modelRotation;

	private BakedPlacementModel(ModelKey key)
	{
		super(key.theirModel);
		this.block = key.block;
		this.ourModel = key.ourModel;
		this.modelRotation = key.modelRotation;
	}

	@Override
	public List<BakedQuad> getQuads(BlockState state, Direction side, Random rand, IModelData extraData) {
		BlockState modelState = block.getModelState(state);
		if (block.rotatesModel(state))
			return BlockModelUtils.rotatedQuads(modelState, unused -> originalModel, modelRotation, block.rotatesTexture(state), side, rand, extraData);
		else
			return BlockModelUtils.retexturedQuads(state, modelState, unused -> originalModel, ourModel, side, rand, extraData);
	}

    @Override
    public boolean isAmbientOcclusion(BlockState state) {
        return originalModel.isAmbientOcclusion(state == null ? null : block.getModelState(state));
    }

    @Nonnull
    @Override
    public IModelData getModelData(@Nonnull IBlockDisplayReader level, @Nonnull BlockPos pos, @Nonnull BlockState state, @Nonnull IModelData modelData)
    {
        return originalModel.getModelData(level, pos, block.getModelState(state), modelData);
    }
}
