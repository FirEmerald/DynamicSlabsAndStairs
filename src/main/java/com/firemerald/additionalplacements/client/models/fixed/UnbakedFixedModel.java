package com.firemerald.additionalplacements.client.models.fixed;

import java.util.List;
import java.util.function.Function;

import com.firemerald.additionalplacements.block.AdditionalPlacementBlock;
import com.firemerald.additionalplacements.client.IModelBakerExtensions;
import com.firemerald.additionalplacements.client.models.BlockModelCache;
import com.firemerald.additionalplacements.client.models.IUnbakedGeometry;
import com.firemerald.additionalplacements.util.BlockRotation;

import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.renderer.block.model.ItemOverride;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.*;
import net.minecraft.client.resources.model.UnbakedModel.Resolver;
import net.minecraft.resources.ResourceLocation;

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
	public void resolveDependencies(Resolver modelGetter, BlockModel context)
	{
		ourModel = modelGetter.resolve(ourModelLocation);
	}

	@Override
	public BakedModel bake(
			BlockModel context, ModelBaker bakery, Function<Material, TextureAtlasSprite> spriteGetter,
			ModelState modelTransform, List<ItemOverride> overrides)
	{
		UnbakedModel theirModel = ((IModelBakerExtensions) bakery).apGetTopLevelModel(theirModelLocation);
		return BlockModelCache.bake(block, 
				BlockModelCache.bake(this.ourModel, bakery, spriteGetter, modelTransform), 
				BlockModelCache.bake(theirModel, bakery, spriteGetter),
				modelRotation);
	}
}
