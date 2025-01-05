package com.firemerald.additionalplacements.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import com.firemerald.additionalplacements.client.IModelBakerExtensions;

import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelState;
import net.minecraft.client.resources.model.UnbakedModel;

@Mixin(targets = "net.minecraft.client.resources.model.ModelBakery$ModelBakerImpl")
public abstract class MixinModelBakerImpl implements IModelBakerExtensions {
	@Shadow
	public abstract BakedModel bakeUncached(UnbakedModel model, ModelState state);

	@Override
	public BakedModel apBakeUncached(UnbakedModel model, ModelState state) {
		return bakeUncached(model, state);
	}
}
