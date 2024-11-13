package com.firemerald.additionalplacements.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import com.firemerald.additionalplacements.client.IModelBakerExtensions;

import net.minecraft.client.resources.model.*;

@Mixin(targets = "net.minecraft.client.resources.model.ModelBakery$ModelBakerImpl")
public abstract class MixinModelBakerImpl implements IModelBakerExtensions {
	@Shadow
	private ModelBakery field_40571;
	
	@Shadow
	public abstract BakedModel bakeUncached(UnbakedModel model, ModelState state);

	@Override
    public UnbakedModel apGetTopLevelModel(ModelResourceLocation location) {
		return field_40571.topLevelModels.get(location);
    }

	@Override
	public BakedModel apBakeUncached(UnbakedModel model, ModelState state) {
		return bakeUncached(model, state);
	}
}
