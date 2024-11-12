package com.firemerald.additionalplacements.mixin;

import org.spongepowered.asm.mixin.Mixin;

import com.firemerald.additionalplacements.client.IModelBakerExtensions;

import net.minecraft.client.resources.model.ModelBaker;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.client.resources.model.UnbakedModel;

@Mixin(ModelBaker.class)
public interface MixinModelBaker extends IModelBakerExtensions {
	public default UnbakedModel apGetTopLevelModel(ModelResourceLocation location) {
		throw new IllegalStateException("ModelBaker not implementing IModelBakerExtensions found. Generally caused by calling PlacementBlockModel.bake with a ModelBaker that isn't an instance of ModelBakery.ModelBakerImpl");
	}
}
