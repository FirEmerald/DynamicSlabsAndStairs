package com.firemerald.additionalplacements.mixin;

import java.lang.reflect.Type;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.firemerald.additionalplacements.client.IBlockModelExtensions;
import com.firemerald.additionalplacements.client.models.PlacementBlockModelLoader;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.util.GsonHelper;

@Mixin(BlockModel.Deserializer.class)
public class MixinBlockModelDeserializer {
	@Inject(
			//TODO priority above porting-lib
			method = "deserialize(Lcom/google/gson/JsonElement;Ljava/lang/reflect/Type;Lcom/google/gson/JsonDeserializationContext;)Lnet/minecraft/client/renderer/block/model/BlockModel;", 
			at = @At("RETURN"), 
			cancellable = true)
	public void deserialize(JsonElement json, Type type, JsonDeserializationContext context, CallbackInfoReturnable<BlockModel> cli) {
        JsonObject jsonObject = json.getAsJsonObject();
        if (jsonObject.has("loader")) {
			String loader = GsonHelper.getAsString(jsonObject, "loader");
			if (loader.equals(PlacementBlockModelLoader.ID.toString())) {
				jsonObject.remove("loader"); //Prevent PortingLib from reading our loader
				((IBlockModelExtensions) cli.getReturnValue()).setPlacementModel(PlacementBlockModelLoader.read(jsonObject, context));
			}
        }
	}
}
