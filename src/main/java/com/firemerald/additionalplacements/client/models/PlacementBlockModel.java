package com.firemerald.additionalplacements.client.models;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.function.Function;

import com.mojang.datafixers.util.Pair;

import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.*;
import net.minecraft.resources.ResourceLocation;

public class PlacementBlockModel
{
	public final ResourceLocation model;

	public PlacementBlockModel(ResourceLocation model)
	{
		this.model = model;
	}

	public BakedModel bake(BlockModel owner, ModelBakery bakery, Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelTransform, ItemOverrides overrides, ResourceLocation modelLocation)
	{
		return new BakedPlacementBlockModel(bakery.getModel(model).bake(bakery, spriteGetter, modelTransform, modelLocation)); //TODO make better
	}

	public Collection<Material> getTextures(BlockModel owner, Function<ResourceLocation, UnbakedModel> modelGetter, Set<Pair<String, String>> missingTextureErrors)
	{
		UnbakedModel model = modelGetter.apply(this.model);
		if (model != null) return model.getMaterials(modelGetter, missingTextureErrors);
		else return Collections.emptyList();
	}
}
