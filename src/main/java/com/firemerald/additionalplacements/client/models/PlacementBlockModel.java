package com.firemerald.additionalplacements.client.models;

import java.util.*;
import java.util.function.Function;

import com.firemerald.additionalplacements.block.AdditionalPlacementBlock;
import com.firemerald.additionalplacements.util.BlockRotation;
import com.mojang.math.Transformation;

import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.*;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.geometry.IGeometryBakingContext;
import net.neoforged.neoforge.client.model.geometry.IUnbakedGeometry;

public class PlacementBlockModel implements IUnbakedGeometry<PlacementBlockModel>
{
	private static final List<Function<BakedModel, BakedModel>> UNWRAPPERS = new ArrayList<>();
	
	public static void registerUnwrapper(Function<BakedModel, BakedModel> unwrapper) {
		UNWRAPPERS.add(unwrapper);
	}
	
	private static BakedModel unwrap(BakedModel model) {
		Optional<BakedModel> next;
		while ((next = unwrapSingle(model)).isPresent()) model = next.get();
		return model;
	}
	
	private static Optional<BakedModel> unwrapSingle(BakedModel model) {
		return UNWRAPPERS.stream().map(uw -> uw.apply(model)).filter(bm -> bm != null).findFirst();
	}
	
	public final AdditionalPlacementBlock<?> block;
	public final ResourceLocation ourModelLocation;
	public final ModelResourceLocation theirModelLocation;
	public final BlockRotation modelRotation;
	private UnbakedModel ourModel, theirModel;

	public PlacementBlockModel(AdditionalPlacementBlock<?> block, ResourceLocation ourModelLocation, ModelResourceLocation theirModelLocation, BlockRotation modelRotation)
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
		theirModel = modelGetter.apply(theirModelLocation);
    }

	@Override
	public BakedModel bake(IGeometryBakingContext context, ModelBaker bakery, Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelTransform, ItemOverrides overrides, ResourceLocation modelLocation)
	{
		return bake(block, 
				bake(this.ourModel, bakery, spriteGetter, modelTransform, ourModelLocation), 
				bake(this.theirModel, bakery, spriteGetter, theirModelLocation),
				modelRotation);
	}
	
	private static record OurModelKey(UnbakedModel model, Transformation rotation, boolean uvLocked, ResourceLocation modelLocation) {}
	
	private static final Map<OurModelKey, BakedModel> OUR_MODEL_CACHE = new HashMap<>();
	
	private static BakedModel bake(UnbakedModel model, ModelBaker baker, Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelTransform, ResourceLocation modelLocation) {
		return OUR_MODEL_CACHE.computeIfAbsent(new OurModelKey(model, modelTransform.getRotation(), modelTransform.isUvLocked(), modelLocation), key -> unwrap(model.bake(baker, spriteGetter, modelTransform, modelLocation)));	}
	
	private static record TheirModelKey(UnbakedModel model, ResourceLocation modelLocation) {}
	
	private static final Map<TheirModelKey, BakedModel> THEIR_MODEL_CACHE = new HashMap<>();
	
	private static BakedModel bake(UnbakedModel model, ModelBaker baker, Function<Material, TextureAtlasSprite> spriteGetter, ResourceLocation modelLocation) {
		return THEIR_MODEL_CACHE.computeIfAbsent(new TheirModelKey(model, modelLocation), key -> unwrap(model.bake(baker, spriteGetter, BlockModelRotation.X0_Y0, modelLocation)));
	}
	
	private static record ModelKey(AdditionalPlacementBlock<?> block, BakedModel ourModel, BakedModel theirModel, BlockRotation modelRotation) {}
	
	private static final Map<ModelKey, BakedPlacementBlockModel> MODEL_CACHE = new HashMap<>();
	
	private static BakedPlacementBlockModel bake(AdditionalPlacementBlock<?> block, BakedModel ourModel, BakedModel theirModel, BlockRotation modelRotation) {
		return MODEL_CACHE.computeIfAbsent(new ModelKey(block, ourModel, theirModel, modelRotation), key -> new BakedPlacementBlockModel(block, ourModel, theirModel, modelRotation));
	}
	
	public static void clearCache() {
		OUR_MODEL_CACHE.clear();
		THEIR_MODEL_CACHE.clear();
		MODEL_CACHE.clear();
	}
}
