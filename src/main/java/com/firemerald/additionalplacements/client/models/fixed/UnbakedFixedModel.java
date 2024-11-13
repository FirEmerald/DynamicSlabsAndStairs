package com.firemerald.additionalplacements.client.models.fixed;

import java.util.*;
import java.util.function.Function;

import com.firemerald.additionalplacements.block.AdditionalPlacementBlock;
import com.firemerald.additionalplacements.client.models.BlockModelCache;
import com.firemerald.additionalplacements.client.models.IModelGeometry;
import com.firemerald.additionalplacements.util.BlockRotation;
import com.mojang.datafixers.util.Pair;

import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.*;
import net.minecraft.resources.ResourceLocation;

public class UnbakedFixedModel implements IModelGeometry<UnbakedFixedModel>
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
	public BakedModel bake(BlockModel owner, ModelBakery bakery, Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelTransform, ItemOverrides overrides, ResourceLocation modelLocation)
	{
		return BlockModelCache.bake(block, 
				BlockModelCache.bake(this.ourModel, bakery, spriteGetter, modelTransform, ourModelLocation), 
				BlockModelCache.bake(this.theirModel, bakery, spriteGetter, theirModelLocation),
				modelRotation);
	}

	@Override
	public Collection<Material> getMaterials(BlockModel owner, Function<ResourceLocation, UnbakedModel> modelGetter, Set<Pair<String, String>> missingTextureErrors)
	{
		ourModel = modelGetter.apply(ourModelLocation);
		theirModel = modelGetter.apply(theirModelLocation);
		Collection<Material> ourTextures = ourModel.getMaterials(modelGetter, missingTextureErrors);
		Collection<Material> theirTextures = theirModel.getMaterials(modelGetter, missingTextureErrors);
		List<Material> textures = new ArrayList<>(ourTextures.size() + theirTextures.size());
		textures.addAll(ourTextures);
		textures.addAll(theirTextures);
		return textures;
	}
}
