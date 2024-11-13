package com.firemerald.additionalplacements.client.models.fixed;

import java.util.function.Function;

import com.firemerald.additionalplacements.block.AdditionalPlacementBlock;
import com.firemerald.additionalplacements.client.models.BlockModelCache;
import com.firemerald.additionalplacements.util.BlockRotation;

import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.*;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.geometry.IGeometryBakingContext;
import net.neoforged.neoforge.client.model.geometry.IUnbakedGeometry;

public class UnbakedFixedModel implements IUnbakedGeometry<UnbakedFixedModel>
{
	public final AdditionalPlacementBlock<?> block;
	public final ResourceLocation ourModelLocation;
	public final ModelResourceLocation theirModelLocation;
	public final BlockRotation modelRotation;
	private UnbakedModel ourModel;

	public UnbakedFixedModel(AdditionalPlacementBlock<?> block, ResourceLocation ourModelLocation, ModelResourceLocation theirModelLocation, BlockRotation modelRotation)
	{
		this.block = block;
		this.ourModelLocation = ourModelLocation;
		this.theirModelLocation = theirModelLocation;
		this.modelRotation = modelRotation;
	}

	@Override
	public void resolveParents(Function<ResourceLocation, UnbakedModel> modelGetter, IGeometryBakingContext context)
    {
		ourModel = modelGetter.apply(ourModelLocation);
    }

	@Override
	public BakedModel bake(IGeometryBakingContext context, ModelBaker bakery, Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelTransform, ItemOverrides overrides)
	{
		UnbakedModel theirModel = bakery.getTopLevelModel(theirModelLocation);		
		return BlockModelCache.bake(block, 
				BlockModelCache.bake(this.ourModel, bakery, spriteGetter, modelTransform), 
				BlockModelCache.bake(theirModel, bakery, spriteGetter),
				modelRotation);
	}
}
