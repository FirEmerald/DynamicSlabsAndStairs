package com.firemerald.additionalplacements.client.models;

import com.firemerald.additionalplacements.AdditionalPlacementsMod;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;

import net.minecraft.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelLoader;

public class PlacementBlockModelLoader implements IModelLoader<PlacementBlockModel>
{
	public static final ResourceLocation ID = new ResourceLocation(AdditionalPlacementsMod.MOD_ID, "placement_block_loader");

	@Override
	public void onResourceManagerReload(IResourceManager pResourceManager) {}

	@Override
	public PlacementBlockModel read(JsonDeserializationContext deserializationContext, JsonObject modelContents)
	{
		return new PlacementBlockModel(new ResourceLocation(modelContents.get("model").getAsString()));
	}

}
