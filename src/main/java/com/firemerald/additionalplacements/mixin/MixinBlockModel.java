package com.firemerald.additionalplacements.mixin;

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
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.*;
import net.minecraft.resources.ResourceLocation;

@Mixin(BlockModel.class)
public class MixinBlockModel implements IBlockModelExtensions {
	@Unique
	private PlacementBlockModel placementModel = null;
	@Shadow
	private ItemOverrides getItemOverrides(ModelBaker baker, BlockModel model) {
		return null;
	}
	
	@Inject(
			//TODO priority above porting-lib
			method = "bake(Lnet/minecraft/client/resources/model/ModelBaker;Lnet/minecraft/client/renderer/block/model/BlockModel;Ljava/util/function/Function;Lnet/minecraft/client/resources/model/ModelState;Lnet/minecraft/resources/ResourceLocation;Z)Lnet/minecraft/client/resources/model/BakedModel;",
			at = @At("HEAD"),
			cancellable = true
	)
	private void bake(ModelBaker baker, BlockModel model, Function<Material, TextureAtlasSprite> spriteGetter, ModelState state, ResourceLocation location, boolean guiLight3d, CallbackInfoReturnable<BakedModel> cli) {
		if (placementModel != null)
			cli.setReturnValue(placementModel.bake(model, baker, spriteGetter, state, getItemOverrides(baker, model), location, guiLight3d));
	}
	
	@Inject(
			//TODO priority above porting-lib
			method = "resolveParents",
			at = @At("HEAD")
			)
	private void resolveParents(Function<ResourceLocation, UnbakedModel> function, CallbackInfo ci) {
		if (placementModel != null) placementModel.resolveParents(function, (BlockModel) (Object) this);
	}

	@Override
	public void setPlacementModel(PlacementBlockModel placementModel) {
		this.placementModel = placementModel;
	}
}
