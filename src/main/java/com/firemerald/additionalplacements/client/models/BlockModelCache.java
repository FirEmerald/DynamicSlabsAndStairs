package com.firemerald.additionalplacements.client.models;

import java.util.*;
import java.util.function.Function;

import com.firemerald.additionalplacements.block.AdditionalPlacementBlock;
import com.firemerald.additionalplacements.client.models.fixed.BakedFixedModel;
import com.firemerald.additionalplacements.util.BlockRotation;

import net.minecraft.client.renderer.model.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.TransformationMatrix;

public class BlockModelCache {
	private static final List<Function<IBakedModel, IBakedModel>> UNWRAPPERS = new ArrayList<>();
	
	public static void registerUnwrapper(Function<IBakedModel, IBakedModel> unwrapper) {
		UNWRAPPERS.add(unwrapper);
	}
	
	public static IBakedModel unwrap(IBakedModel model) {
		Optional<IBakedModel> next;
		while ((next = unwrapSingle(model)).isPresent()) model = next.get();
		return model;
	}
	
	private static Optional<IBakedModel> unwrapSingle(IBakedModel model) {
		return UNWRAPPERS.stream().map(uw -> uw.apply(model)).filter(bm -> bm != null).findFirst();
	}
	
	private static class OurModelKey {
		final IUnbakedModel model;
		final TransformationMatrix rotation;
		final boolean uvLocked;
		final ResourceLocation modelLocation;
		
		OurModelKey(IUnbakedModel model, TransformationMatrix rotation, boolean uvLocked, ResourceLocation modelLocation) {
			this.model = model;
			this.rotation = rotation;
			this.uvLocked = uvLocked;
			this.modelLocation = modelLocation;
		}
		
		@Override
		public int hashCode() {
			return rotation.hashCode() ^ modelLocation.hashCode() ^ (uvLocked ? 0 : -2147483648);
		}
		
		@Override
		public boolean equals(Object o) {
			if (o == this) return true;
			else if (o == null || o.getClass() != this.getClass()) return false;
			else {
				OurModelKey key = (OurModelKey) o;
				return Objects.equals(key.model, model) && 
						Objects.equals(key.rotation, rotation) && 
						Objects.equals(key.modelLocation, modelLocation) && 
						key.uvLocked == uvLocked;
			}
		}
	}
	
	private static final Map<OurModelKey, IBakedModel> OUR_MODEL_CACHE = new HashMap<>();
	
	public static IBakedModel bake(IUnbakedModel model, ModelBakery baker, Function<RenderMaterial, TextureAtlasSprite> spriteGetter, IModelTransform modelTransform, ResourceLocation modelLocation) {
		return OUR_MODEL_CACHE.computeIfAbsent(new OurModelKey(model, modelTransform.getRotation(), modelTransform.isUvLocked(), modelLocation), key -> unwrap(model.bake(baker, spriteGetter, modelTransform, modelLocation)));	}
	
	private static class TheirModelKey {
		final IUnbakedModel model;
		final ResourceLocation modelLocation;
		
		TheirModelKey(IUnbakedModel model, ResourceLocation modelLocation) {
			this.model = model;
			this.modelLocation = modelLocation;
		}
		
		@Override
		public int hashCode() {
			return modelLocation.hashCode();
		}
		
		@Override
		public boolean equals(Object o) {
			if (o == this) return true;
			else if (o == null || o.getClass() != this.getClass()) return false;
			else {
				TheirModelKey key = (TheirModelKey) o;
				return Objects.equals(key.model, model) && 
						Objects.equals(key.modelLocation, modelLocation);
			}
		}
	}
	
	private static final Map<TheirModelKey, IBakedModel> THEIR_MODEL_CACHE = new HashMap<>();
	
	public static IBakedModel bake(IUnbakedModel model, ModelBakery baker, Function<RenderMaterial, TextureAtlasSprite> spriteGetter, ResourceLocation modelLocation) {
		return THEIR_MODEL_CACHE.computeIfAbsent(new TheirModelKey(model, modelLocation), key -> unwrap(model.bake(baker, spriteGetter, ModelRotation.X0_Y0, modelLocation)));
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
	
	public static BakedFixedModel bake(AdditionalPlacementBlock<?> block, IBakedModel ourModel, IBakedModel theirModel, BlockRotation modelRotation) {
		return MODEL_CACHE.computeIfAbsent(new ModelKey(block, ourModel, theirModel, modelRotation), key -> new BakedFixedModel(block, ourModel, theirModel, modelRotation));
	}
	
	public static void clearCache() {
		OUR_MODEL_CACHE.clear();
		THEIR_MODEL_CACHE.clear();
		MODEL_CACHE.clear();
	}
}
