package com.firemerald.additionalplacements.client.models.fixed;

import java.util.*;
import java.util.function.Function;

import com.firemerald.additionalplacements.block.AdditionalPlacementBlock;
import com.firemerald.additionalplacements.client.models.BlockModelCache;
import com.firemerald.additionalplacements.util.BlockRotation;
import com.mojang.datafixers.util.Pair;

import net.minecraft.client.renderer.model.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelConfiguration;
import net.minecraftforge.client.model.geometry.IModelGeometry;

public class UnbakedFixedModel implements IModelGeometry<UnbakedFixedModel>
{
	public final AdditionalPlacementBlock<?> block;
	public final ResourceLocation ourModelLocation;
	public final ModelResourceLocation theirModelLocation;
	public final BlockRotation modelRotation;
	private IUnbakedModel ourModel, theirModel;

	public UnbakedFixedModel(AdditionalPlacementBlock<?> block, ResourceLocation ourModelLocation, ModelResourceLocation theirModelLocation, BlockRotation modelRotation)
	{
		this.block = block;
		this.ourModelLocation = ourModelLocation;
		this.theirModelLocation = theirModelLocation;
		this.modelRotation = modelRotation;
	}

	@Override
	public IBakedModel bake(IModelConfiguration owner, ModelBakery bakery, Function<RenderMaterial, TextureAtlasSprite> spriteGetter, IModelTransform modelTransform, ItemOverrideList overrides, ResourceLocation modelLocation)
	{
		return BlockModelCache.bake(block, 
				BlockModelCache.bake(this.ourModel, bakery, spriteGetter, modelTransform, ourModelLocation), 
				BlockModelCache.bake(this.theirModel, bakery, spriteGetter, theirModelLocation),
				modelRotation);
	}

	@Override
	public Collection<RenderMaterial> getTextures(IModelConfiguration owner, Function<ResourceLocation, IUnbakedModel> modelGetter, Set<Pair<String, String>> missingTextureErrors)
	{
		ourModel = modelGetter.apply(ourModelLocation);
		theirModel = modelGetter.apply(theirModelLocation);
		Collection<RenderMaterial> ourTextures = ourModel.getMaterials(modelGetter, missingTextureErrors);
		Collection<RenderMaterial> theirTextures = theirModel.getMaterials(modelGetter, missingTextureErrors);
		List<RenderMaterial> textures = new ArrayList<>(ourTextures.size() + theirTextures.size());
		textures.addAll(ourTextures);
		textures.addAll(theirTextures);
		return textures;
	}
}
