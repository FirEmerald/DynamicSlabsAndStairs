package com.firemerald.additionalplacements.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import com.firemerald.additionalplacements.client.models.IFabricForwardingModel;

import net.fabricmc.fabric.api.renderer.v1.model.ForwardingBakedModel;
import net.minecraft.client.resources.model.BakedModel;

@Mixin(ForwardingBakedModel.class)
public class MixinForwardingBakedModel implements IFabricForwardingModel {
	@Shadow
	protected BakedModel wrapped;

	@Override
	public BakedModel apGetWrapped() {
		return wrapped;
	}
}
