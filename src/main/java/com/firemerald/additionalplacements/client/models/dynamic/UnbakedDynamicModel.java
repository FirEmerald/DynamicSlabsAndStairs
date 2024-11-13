package com.firemerald.additionalplacements.client.models.dynamic;

import java.util.function.Function;

import com.firemerald.additionalplacements.client.models.Unwrapper;
import com.firemerald.additionalplacements.client.models.IUnbakedGeometry;

import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.*;
import net.minecraft.resources.ResourceLocation;

public class UnbakedDynamicModel implements IUnbakedGeometry<UnbakedDynamicModel>
{
	public final ResourceLocation ourModelLocation;

	public UnbakedDynamicModel(ResourceLocation ourModelLocation)
	{
		this.ourModelLocation = ourModelLocation;
	}

	@Override
	public void resolveParents(Function<ResourceLocation, UnbakedModel> modelGetter, BlockModel context)
    {
		modelGetter.apply(ourModelLocation);
    }

	@Override
	public BakedModel bake(BlockModel context, ModelBaker bakery, Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelTransform, ItemOverrides overrides, ResourceLocation modelLocation)
	{
		return new BakedDynamicModel(Unwrapper.unwrap(bakery.bake(ourModelLocation, modelTransform)));
	}
}
