package com.firemerald.additionalplacements.client.models;

import java.util.function.Function;

import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.*;
import net.minecraft.resources.ResourceLocation;

public interface IUnbakedGeometry<T extends IUnbakedGeometry<T>> {
    BakedModel bake(BlockModel context, ModelBaker baker, Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelState, ItemOverrides overrides, ResourceLocation modelLocation);

    void resolveParents(Function<ResourceLocation, UnbakedModel> modelGetter, BlockModel context);
}
