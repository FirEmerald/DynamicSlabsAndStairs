package com.firemerald.additionalplacements.client.models;

import java.util.List;
import java.util.function.Function;

import net.minecraft.client.renderer.block.model.ItemOverride;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBaker;
import net.minecraft.client.resources.model.ModelState;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.geometry.IGeometryBakingContext;
import net.neoforged.neoforge.client.model.geometry.IUnbakedGeometry;

public class PlacementBlockModel implements IUnbakedGeometry<PlacementBlockModel>
{
	public final ResourceLocation model;

	public PlacementBlockModel(ResourceLocation model)
	{
		this.model = model;
	}

	@Override
	public BakedModel bake(IGeometryBakingContext context, ModelBaker baker, Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelState, List<ItemOverride> overrides) {
		return new BakedPlacementBlockModel(baker.bake(model, modelState, spriteGetter)); //TODO make better
	}
}
