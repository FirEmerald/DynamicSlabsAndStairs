package com.firemerald.additionalplacements.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import com.firemerald.additionalplacements.client.IModelBakerExtensions;

import net.minecraft.client.resources.model.*;

@Mixin(targets = "net.minecraft.client.resources.model.ModelBakery$ModelBakerImpl")
public abstract class MixinModelBakerImpl implements IModelBakerExtensions {
	@Shadow
	private ModelBakery field_40571;

	@Override
    public UnbakedModel apGetTopLevelModel(ModelResourceLocation location) {
		return field_40571.topModels.get(location);
    }
}
