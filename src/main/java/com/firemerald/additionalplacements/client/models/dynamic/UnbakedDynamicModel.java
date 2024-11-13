package com.firemerald.additionalplacements.client.models.dynamic;

import java.util.*;
import java.util.function.Function;

import com.firemerald.additionalplacements.client.models.BlockModelCache;
import com.firemerald.additionalplacements.client.models.IModelGeometry;
import com.mojang.datafixers.util.Pair;

import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.*;
import net.minecraft.resources.ResourceLocation;

public class UnbakedDynamicModel implements IModelGeometry<UnbakedDynamicModel>
{
	public final ResourceLocation ourModelLocation;
	private UnbakedModel ourModel;

	public UnbakedDynamicModel(ResourceLocation ourModelLocation)
	{
		this.ourModelLocation = ourModelLocation;
	}

	@Override
	public Collection<Material> getMaterials(BlockModel owner, Function<ResourceLocation, UnbakedModel> modelGetter, Set<Pair<String, String>> missingTextureErrors)
	{
		ourModel = modelGetter.apply(ourModelLocation);
		return Collections.emptyList();
	}

	@Override
	public BakedModel bake(BlockModel owner, ModelBakery bakery, Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelTransform, ItemOverrides overrides, ResourceLocation modelLocation)
	{
		return new BakedDynamicModel(BlockModelCache.bake(this.ourModel, bakery, spriteGetter, modelTransform, ourModelLocation));
	}
}
