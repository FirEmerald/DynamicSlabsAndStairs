package com.firemerald.additionalplacements.client.models.dynamic;

import java.util.function.Function;

import com.firemerald.additionalplacements.client.models.Unwrapper;

import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.*;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.geometry.IGeometryBakingContext;
import net.neoforged.neoforge.client.model.geometry.IUnbakedGeometry;

public class UnbakedDynamicModel implements IUnbakedGeometry<UnbakedDynamicModel>
{
	public final ResourceLocation ourModelLocation;

	public UnbakedDynamicModel(ResourceLocation ourModelLocation)
	{
		this.ourModelLocation = ourModelLocation;
	}

	@Override
	public void resolveParents(Function<ResourceLocation, UnbakedModel> modelGetter, IGeometryBakingContext context)
    {
		modelGetter.apply(ourModelLocation);
    }

	@Override
	public BakedModel bake(IGeometryBakingContext context, ModelBaker bakery, Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelTransform, ItemOverrides overrides)
	{
		return new BakedDynamicModel(Unwrapper.unwrap(bakery.bake(ourModelLocation, modelTransform, spriteGetter)));
	}
}
