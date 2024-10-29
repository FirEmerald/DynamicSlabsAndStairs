package com.firemerald.additionalplacements.client.models;

import com.firemerald.additionalplacements.AdditionalPlacementsMod;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;

import io.github.fabricators_of_create.porting_lib.model.IModelLoader;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;

public class PlacementBlockModelLoader implements IModelLoader<PlacementBlockModel>
{
	public static final ResourceLocation ID = new ResourceLocation(AdditionalPlacementsMod.MOD_ID, "placement_block_loader");

	@Override
	public void onResourceManagerReload(ResourceManager pResourceManager) {}

	@Override
	public PlacementBlockModel read(JsonDeserializationContext deserializationContext, JsonObject modelContents)
	{
		return new PlacementBlockModel(new ResourceLocation(modelContents.get("model").getAsString()));
	}

}
