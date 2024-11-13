package com.firemerald.additionalplacements.client.models.dynamic;

import java.util.*;
import java.util.function.Function;

import com.firemerald.additionalplacements.client.models.BlockModelCache;
import com.mojang.datafixers.util.Pair;

import net.minecraft.client.renderer.model.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelConfiguration;
import net.minecraftforge.client.model.geometry.IModelGeometry;

public class UnbakedDynamicModel implements IModelGeometry<UnbakedDynamicModel>
{
	public final ResourceLocation ourModelLocation;
	private IUnbakedModel ourModel;

	public UnbakedDynamicModel(ResourceLocation ourModelLocation)
	{
		this.ourModelLocation = ourModelLocation;
	}

	@Override
	public Collection<RenderMaterial> getTextures(IModelConfiguration owner, Function<ResourceLocation, IUnbakedModel> modelGetter, Set<Pair<String, String>> missingTextureErrors)
	{
		ourModel = modelGetter.apply(ourModelLocation);
		return Collections.emptyList();
	}

	@Override
	public IBakedModel bake(IModelConfiguration owner, ModelBakery bakery, Function<RenderMaterial, TextureAtlasSprite> spriteGetter, IModelTransform modelTransform, ItemOverrideList overrides, ResourceLocation modelLocation)
	{
		return new BakedDynamicModel(BlockModelCache.bake(this.ourModel, bakery, spriteGetter, modelTransform, ourModelLocation));
	}
}
