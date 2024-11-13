package com.firemerald.additionalplacements.client.models.fixed;

import com.firemerald.additionalplacements.AdditionalPlacementsMod;
import com.firemerald.additionalplacements.block.AdditionalPlacementBlock;
import com.firemerald.additionalplacements.util.BlockRotation;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;

public class FixedModelLoader
{
	public static final ResourceLocation ID = new ResourceLocation(AdditionalPlacementsMod.MOD_ID, "fixed");

	public static UnbakedFixedModel read(JsonObject modelContents, JsonDeserializationContext deserializationContext)
	{
		return new UnbakedFixedModel(
				(AdditionalPlacementBlock<?>) Registry.BLOCK.get(new ResourceLocation(modelContents.get("block").getAsString())),
				new ResourceLocation(modelContents.get("ourModel").getAsString()),
				new ModelResourceLocation(new ResourceLocation(modelContents.get("theirBlock").getAsString()), modelContents.get("theirState").getAsString()),
				BlockRotation.valueOf(modelContents.get("modelRotation").getAsString())
				);
	}

}
