package com.firemerald.additionalplacements.client.models.fixed;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.firemerald.additionalplacements.block.AdditionalPlacementBlock;
import com.firemerald.additionalplacements.client.models.Unwrapper;
import com.firemerald.additionalplacements.client.models.IModelGeometry;
import com.firemerald.additionalplacements.util.BlockRotation;
import com.google.common.collect.Streams;
import com.mojang.datafixers.util.Pair;

import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.*;
import net.minecraft.resources.ResourceLocation;

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
	public BakedModel bake(BlockModel owner, ModelBakery bakery, Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelTransform, ItemOverrides overrides, ResourceLocation modelLocation)
	{
		return get(block, 
				Unwrapper.unwrap(bakery.bake(ourModelLocation, modelTransform)), 
				Unwrapper.unwrap(bakery.bake(theirModelLocation, BlockModelRotation.X0_Y0)),
				modelRotation);
	}

	@Override
	public Collection<Material> getTextures(BlockModel owner, Function<ResourceLocation, UnbakedModel> modelGetter, Set<Pair<String, String>> missingTextureErrors)
	{
		return Streams.concat(
				modelGetter.apply(ourModelLocation).getMaterials(modelGetter, missingTextureErrors).stream(),
				modelGetter.apply(theirModelLocation).getMaterials(modelGetter, missingTextureErrors).stream()
				).collect(Collectors.toSet());
	}
	
	private static record ModelKey(AdditionalPlacementBlock<?> block, BakedModel ourModel, BakedModel theirModel, BlockRotation modelRotation) {}
	
	private static final Map<ModelKey, BakedFixedModel> MODEL_CACHE = new HashMap<>();
	
	public static BakedFixedModel get(AdditionalPlacementBlock<?> block, BakedModel ourModel, BakedModel theirModel, BlockRotation modelRotation) {
		return MODEL_CACHE.computeIfAbsent(new ModelKey(block, ourModel, theirModel, modelRotation), key -> new BakedFixedModel(block, ourModel, theirModel, modelRotation));
	}
	
	public static void clearCache() {
		MODEL_CACHE.clear();
	}
}
