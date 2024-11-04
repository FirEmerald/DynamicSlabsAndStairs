package com.firemerald.additionalplacements.client.models;

import com.firemerald.additionalplacements.AdditionalPlacementsMod;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;

import net.minecraft.resources.ResourceLocation;

public class PlacementBlockModelLoader
{
	public static final ResourceLocation ID = ResourceLocation.tryBuild(AdditionalPlacementsMod.MOD_ID, "placement_block_loader");

	public static PlacementBlockModel read(JsonObject modelContents, JsonDeserializationContext deserializationContext)
	{
		return new PlacementBlockModel(ResourceLocation.parse(modelContents.get("model").getAsString()));
	}

}
