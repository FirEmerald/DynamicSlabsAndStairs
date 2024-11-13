package com.firemerald.additionalplacements.client.models.fixed;

import com.firemerald.additionalplacements.AdditionalPlacementsMod;
import com.firemerald.additionalplacements.block.AdditionalPlacementBlock;
import com.firemerald.additionalplacements.util.BlockRotation;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

public class FixedModelLoader
{
	public static final ResourceLocation ID = ResourceLocation.tryBuild(AdditionalPlacementsMod.MOD_ID, "placement_block_loader");

	public static UnbakedFixedModel read(JsonObject modelContents, JsonDeserializationContext deserializationContext)
	{
		return new UnbakedFixedModel(
				(AdditionalPlacementBlock<?>) BuiltInRegistries.BLOCK.get(ResourceLocation.tryParse(modelContents.get("block").getAsString())),
				ResourceLocation.tryParse(modelContents.get("ourModel").getAsString()),
				new ModelResourceLocation(ResourceLocation.tryParse(modelContents.get("theirBlock").getAsString()), modelContents.get("theirState").getAsString()),
				BlockRotation.valueOf(modelContents.get("modelRotation").getAsString())
				);
	}

}
