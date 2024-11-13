package com.firemerald.additionalplacements.mixin;

import java.lang.reflect.Type;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.firemerald.additionalplacements.client.IBlockModelExtensions;
import com.firemerald.additionalplacements.client.models.dynamic.DynamicModelLoader;
import com.firemerald.additionalplacements.client.models.fixed.FixedModelLoader;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.util.GsonHelper;

@Mixin(value = BlockModel.Deserializer.class, priority = 900)
public class MixinBlockModelDeserializer {
	@Inject(method = "deserialize(Lcom/google/gson/JsonElement;Ljava/lang/reflect/Type;Lcom/google/gson/JsonDeserializationContext;)Lnet/minecraft/client/renderer/block/model/BlockModel;", 
			at = @At("RETURN"), cancellable = true)
	public void deserialize(JsonElement json, Type type, JsonDeserializationContext context, CallbackInfoReturnable<BlockModel> cli) {
        JsonObject jsonObject = json.getAsJsonObject();
        if (jsonObject.has("loader")) {
			String loader = GsonHelper.getAsString(jsonObject, "loader");
			if (loader.equals(FixedModelLoader.ID.toString())) {
				jsonObject.remove("loader"); //Prevent PortingLib from reading our loader
				((IBlockModelExtensions) cli.getReturnValue()).setPlacementModel(FixedModelLoader.read(jsonObject, context));
			}
			else if (loader.equals(DynamicModelLoader.ID.toString())) {
				jsonObject.remove("loader"); //Prevent PortingLib from reading our loader
				((IBlockModelExtensions) cli.getReturnValue()).setPlacementModel(DynamicModelLoader.read(jsonObject, context));
			}
        }
	}
}
