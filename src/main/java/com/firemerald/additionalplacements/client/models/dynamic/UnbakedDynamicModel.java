package com.firemerald.additionalplacements.client.models.dynamic;

import java.util.*;
import java.util.function.Function;

import com.firemerald.additionalplacements.client.models.Unwrapper;
import com.mojang.datafixers.util.Pair;

import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.IModelConfiguration;
import net.minecraftforge.client.model.geometry.IModelGeometry;

public class UnbakedDynamicModel implements IModelGeometry<UnbakedDynamicModel>
{
	public final ResourceLocation ourModelLocation;

	public UnbakedDynamicModel(ResourceLocation ourModelLocation)
	{
		this.ourModelLocation = ourModelLocation;
	}

	@Override
	public Collection<Material> getTextures(IModelConfiguration owner, Function<ResourceLocation, UnbakedModel> modelGetter, Set<Pair<String, String>> missingTextureErrors)
	{
		return modelGetter.apply(ourModelLocation).getMaterials(modelGetter, missingTextureErrors);
	}

	@Override
	public BakedModel bake(IModelConfiguration owner, ModelBakery bakery, Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelTransform, ItemOverrides overrides, ResourceLocation modelLocation)
	{
		return new BakedDynamicModel(Unwrapper.unwrap(bakery.bake(ourModelLocation, modelTransform, spriteGetter)));
	}
}
