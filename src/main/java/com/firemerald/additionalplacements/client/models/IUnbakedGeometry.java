package com.firemerald.additionalplacements.client.models;

import java.util.List;
import java.util.function.Function;

import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.renderer.block.model.ItemOverride;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.*;
import net.minecraft.client.resources.model.UnbakedModel.Resolver;

public interface IUnbakedGeometry<T extends IUnbakedGeometry<T>> {
    BakedModel bake(BlockModel context, ModelBaker baker, Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelState, List<ItemOverride> overrides);

	void resolveDependencies(Resolver modelGetter, BlockModel context);
}
