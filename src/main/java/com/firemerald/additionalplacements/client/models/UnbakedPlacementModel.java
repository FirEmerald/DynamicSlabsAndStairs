package com.firemerald.additionalplacements.client.models;

import java.util.*;
import java.util.function.Function;

import com.firemerald.additionalplacements.block.AdditionalPlacementBlock;
import com.firemerald.additionalplacements.util.BlockRotation;
import com.mojang.datafixers.util.Pair;

import net.minecraft.client.renderer.model.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;

public class UnbakedPlacementModel implements IUnbakedModel
{
	private static class ModelKey {
		final AdditionalPlacementBlock<?> block;
		final ResourceLocation ourModelLocation;
		final IModelTransform ourModelRotation;
		final IUnbakedModel theirModel;
		final BlockRotation theirModelRotation;
		
		private ModelKey(AdditionalPlacementBlock<?> block, ResourceLocation ourModelLocation, IModelTransform ourModelRotation, IUnbakedModel theirModel, BlockRotation theirModelRotation) {
			this.block = block;
			this.ourModelLocation = ourModelLocation;
			this.ourModelRotation = ourModelRotation;
			this.theirModel = theirModel;
			this.theirModelRotation = theirModelRotation;
		}
		
		@Override
		public int hashCode() {
			return block.hashCode() ^ ourModelLocation.hashCode() ^ ourModelRotation.hashCode() ^ theirModel.hashCode() ^ theirModelRotation.ordinal();
		}
		
		@Override
		public boolean equals(Object o) {
			if (o == this) return true;
			else if (o == null || o.getClass() != this.getClass()) return false;
			else {
				ModelKey key = (ModelKey) o;
				return key.block == block && 
						Objects.equals(key.ourModelLocation, ourModelLocation) &&
						Objects.equals(key.ourModelRotation, ourModelRotation) &&
						Objects.equals(key.theirModel, theirModel) &&
						Objects.equals(key.theirModelRotation, theirModelRotation);
						
			}
		}
	}
	
	private static final Map<ModelKey, UnbakedPlacementModel> MODEL_CACHE = new HashMap<>();
	
	public static UnbakedPlacementModel of(AdditionalPlacementBlock<?> block, ResourceLocation ourModelLocation, IModelTransform ourModelRotation, ResourceLocation theirModelLocation, IUnbakedModel theirModel, BlockRotation theirModelRotation) {
		return MODEL_CACHE.computeIfAbsent(
				new ModelKey(block, ourModelLocation, ourModelRotation, theirModel, theirModelRotation), 
				key -> new UnbakedPlacementModel(key, theirModelLocation));
	}
	
	public static void clearCache() {
		MODEL_CACHE.clear();
	}
	
	public final AdditionalPlacementBlock<?> block;
	public final ResourceLocation ourModelLocation;
	public final IModelTransform ourModelRotation;
	public final ResourceLocation theirModelLocation;
	public final BlockRotation theirModelRotation;

	private UnbakedPlacementModel(ModelKey key, ResourceLocation theirModelLocation)
	{
		this.block = key.block;
		this.ourModelLocation = key.ourModelLocation;
		this.ourModelRotation = key.ourModelRotation;
		this.theirModelLocation = theirModelLocation;
		this.theirModelRotation = key.theirModelRotation;
	}

	@Override
	public Collection<ResourceLocation> getDependencies() {
		return Collections.singleton(ourModelLocation);
	}

	@Override
	public Collection<RenderMaterial> getMaterials(Function<ResourceLocation, IUnbakedModel> pModelGetter, Set<Pair<String, String>> pMissingTextureErrors) {
		return pModelGetter.apply(ourModelLocation).getMaterials(pModelGetter, pMissingTextureErrors);
	}

	@Override
	public IBakedModel bake(ModelBakery baker, Function<RenderMaterial, TextureAtlasSprite> spriteGetter, IModelTransform state, ResourceLocation modelLocation) {
		return BakedPlacementModel.of(baker, ourModelRotation, block, ourModelLocation, theirModelLocation, theirModelRotation, spriteGetter);
	}
}
