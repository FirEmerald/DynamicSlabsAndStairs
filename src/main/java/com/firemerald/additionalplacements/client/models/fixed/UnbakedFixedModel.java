package com.firemerald.additionalplacements.client.models.fixed;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.firemerald.additionalplacements.block.AdditionalPlacementBlock;
import com.firemerald.additionalplacements.client.models.Unwrapper;
import com.firemerald.additionalplacements.util.BlockRotation;
import com.google.common.collect.Streams;
import com.mojang.datafixers.util.Pair;

import net.minecraft.client.renderer.model.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelConfiguration;
import net.minecraftforge.client.model.geometry.IModelGeometry;

public class UnbakedFixedModel implements IModelGeometry<UnbakedFixedModel>
{
	public final AdditionalPlacementBlock<?> block;
	public final ResourceLocation ourModelLocation;
	public final ModelResourceLocation theirModelLocation;
	public final BlockRotation modelRotation;

	public UnbakedFixedModel(AdditionalPlacementBlock<?> block, ResourceLocation ourModelLocation, ModelResourceLocation theirModelLocation, BlockRotation modelRotation)
	{
		this.block = block;
		this.ourModelLocation = ourModelLocation;
		this.theirModelLocation = theirModelLocation;
		this.modelRotation = modelRotation;
	}

	@Override
	public IBakedModel bake(IModelConfiguration owner, ModelBakery bakery, Function<RenderMaterial, TextureAtlasSprite> spriteGetter, IModelTransform modelTransform, ItemOverrideList overrides, ResourceLocation modelLocation)
	{
		
		return get(block, 
				Unwrapper.unwrap(bakery.getBakedModel(ourModelLocation, modelTransform, spriteGetter)), 
				Unwrapper.unwrap(bakery.getBakedModel(theirModelLocation, ModelRotation.X0_Y0, spriteGetter)),
				modelRotation);
	}

	@Override
	public Collection<RenderMaterial> getTextures(IModelConfiguration owner, Function<ResourceLocation, IUnbakedModel> modelGetter, Set<Pair<String, String>> missingTextureErrors)
	{
		return Streams.concat(
				modelGetter.apply(ourModelLocation).getMaterials(modelGetter, missingTextureErrors).stream(),
				modelGetter.apply(theirModelLocation).getMaterials(modelGetter, missingTextureErrors).stream()
				).collect(Collectors.toSet());
	}
	
	private static class ModelKey {
		final AdditionalPlacementBlock<?> block;
		final IBakedModel ourModel;
		final IBakedModel theirModel;
		final BlockRotation modelRotation;
		
		ModelKey(AdditionalPlacementBlock<?> block, IBakedModel ourModel, IBakedModel theirModel, BlockRotation modelRotation) {
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
						key.modelRotation == modelRotation;
						
			}
		}
	}
	
	private static final Map<ModelKey, BakedFixedModel> MODEL_CACHE = new HashMap<>();
	
	public static BakedFixedModel get(AdditionalPlacementBlock<?> block, IBakedModel ourModel, IBakedModel theirModel, BlockRotation modelRotation) {
		return MODEL_CACHE.computeIfAbsent(new ModelKey(block, ourModel, theirModel, modelRotation), key -> new BakedFixedModel(block, ourModel, theirModel, modelRotation));
	}
	
	public static void clearCache() {
		MODEL_CACHE.clear();
	}
}
