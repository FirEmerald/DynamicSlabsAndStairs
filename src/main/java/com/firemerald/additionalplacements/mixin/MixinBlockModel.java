package com.firemerald.additionalplacements.mixin;

import java.util.List;
import java.util.function.Function;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.firemerald.additionalplacements.client.IBlockModelExtensions;
import com.firemerald.additionalplacements.client.models.PlacementBlockModel;

import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.renderer.block.model.ItemOverride;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.*;
import net.minecraft.client.resources.model.UnbakedModel.Resolver;

@Mixin(value = BlockModel.class, priority = 900)
public abstract class MixinBlockModel implements IBlockModelExtensions {
	@Unique
	private PlacementBlockModel placementModel = null;
	@Shadow
	private List<ItemOverride> overrides;
	
	@Inject(method = "bake(Lnet/minecraft/client/resources/model/ModelBaker;Ljava/util/function/Function;Lnet/minecraft/client/resources/model/ModelState;)Lnet/minecraft/client/resources/model/BakedModel;",
			at = @At("HEAD"), cancellable = true)
	private void bake(ModelBaker baker, Function<Material, TextureAtlasSprite> spriteGetter, ModelState state, CallbackInfoReturnable<BakedModel> cli) {
		if (placementModel != null)
			cli.setReturnValue(placementModel.bake((BlockModel) (Object) this, baker, spriteGetter, state, overrides));
	}
	
	@Inject(method = "resolveDependencies", at = @At("HEAD"))
	private void resolveDependencies(Resolver resolver, CallbackInfo ci) {
		if (placementModel != null) placementModel.resolveDependencies(resolver, (BlockModel) (Object) this);
	}

	@Override
	public void setPlacementModel(PlacementBlockModel placementModel) {
		this.placementModel = placementModel;
	}
}
