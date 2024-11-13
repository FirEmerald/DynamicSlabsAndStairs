package com.firemerald.additionalplacements.client.models.fixed;

import java.util.function.Function;

import com.firemerald.additionalplacements.block.AdditionalPlacementBlock;
import com.firemerald.additionalplacements.client.models.BlockModelCache;
import com.firemerald.additionalplacements.client.models.IUnbakedGeometry;
import com.firemerald.additionalplacements.util.BlockRotation;

import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.*;
import net.minecraft.resources.ResourceLocation;

public class UnbakedFixedModel implements IUnbakedGeometry<UnbakedFixedModel>
{
	public final AdditionalPlacementBlock<?> block;
	public final ResourceLocation ourModelLocation;
	public final ModelResourceLocation theirModelLocation;
	public final BlockRotation modelRotation;
	private UnbakedModel ourModel, theirModel;

	public UnbakedFixedModel(AdditionalPlacementBlock<?> block, ResourceLocation ourModelLocation, ModelResourceLocation theirModelLocation, BlockRotation modelRotation)
	{
		this.block = block;
		this.ourModelLocation = ourModelLocation;
		this.theirModelLocation = theirModelLocation;
		this.modelRotation = modelRotation;
	}

	@Override
	public void resolveParents(Function<ResourceLocation, UnbakedModel> modelGetter, BlockModel context)
	{
		ourModel = modelGetter.apply(ourModelLocation);
		theirModel = modelGetter.apply(theirModelLocation);
	}

	@Override
	public BakedModel bake(
			BlockModel context, ModelBaker bakery, Function<Material, TextureAtlasSprite> spriteGetter,
			ModelState modelTransform, ItemOverrides overrides, ResourceLocation modelLocation)
	{
		return BlockModelCache.bake(block, 
				BlockModelCache.bake(this.ourModel, bakery, spriteGetter, modelTransform, ourModelLocation), 
				BlockModelCache.bake(this.theirModel, bakery, spriteGetter, theirModelLocation),
				modelRotation);
	}
}
