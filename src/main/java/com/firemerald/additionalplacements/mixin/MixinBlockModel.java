package com.firemerald.additionalplacements.mixin;

import java.util.Collection;
import java.util.Set;
import java.util.function.Function;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.firemerald.additionalplacements.client.IBlockModelExtensions;
import com.firemerald.additionalplacements.client.models.IModelGeometry;
import com.mojang.datafixers.util.Pair;

import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.*;
import net.minecraft.resources.ResourceLocation;

@Mixin(value = BlockModel.class, priority = 900)
public abstract class MixinBlockModel implements IBlockModelExtensions {
	@Unique
	private IModelGeometry<?> placementModel = null;
	@Shadow
	protected abstract ItemOverrides getItemOverrides(ModelBakery baker, BlockModel model);
	
	@Inject(method = "bake(Lnet/minecraft/client/resources/model/ModelBakery;Lnet/minecraft/client/renderer/block/model/BlockModel;Ljava/util/function/Function;Lnet/minecraft/client/resources/model/ModelState;Lnet/minecraft/resources/ResourceLocation;Z)Lnet/minecraft/client/resources/model/BakedModel;",
			at = @At("HEAD"), cancellable = true)
	private void bake(ModelBakery baker, BlockModel model, Function<Material, TextureAtlasSprite> spriteGetter, ModelState state, ResourceLocation location, boolean guiLight3d, CallbackInfoReturnable<BakedModel> cli) {
		if (placementModel != null)
			cli.setReturnValue(placementModel.bake(model, baker, spriteGetter, state, getItemOverrides(baker, model), location));
	}
	
	@Inject(method = "getMaterials", at = @At("RETURN"))
	public void getMaterials(Function<ResourceLocation, UnbakedModel> modelGetter, Set<Pair<String, String>> missingTextureErrors, CallbackInfoReturnable<Collection<Material>> cli) {
		if (placementModel != null) cli.getReturnValue().addAll(placementModel.getTextures((BlockModel) (Object) this, modelGetter, missingTextureErrors));
	}

	@Override
	public void setPlacementModel(IModelGeometry<?> placementModel) {
		this.placementModel = placementModel;
	}
}
