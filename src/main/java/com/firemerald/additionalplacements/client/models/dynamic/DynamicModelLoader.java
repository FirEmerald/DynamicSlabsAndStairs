package com.firemerald.additionalplacements.client.models.dynamic;

import com.firemerald.additionalplacements.AdditionalPlacementsMod;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.geometry.IGeometryLoader;

public class DynamicModelLoader implements IGeometryLoader<UnbakedDynamicModel>
{
	public static final ResourceLocation ID = new ResourceLocation(AdditionalPlacementsMod.MOD_ID, "dynamic");

	@Override
	public UnbakedDynamicModel read(JsonObject modelContents, JsonDeserializationContext deserializationContext)
	{
		return new UnbakedDynamicModel(new ResourceLocation(modelContents.get("ourModel").getAsString()));
	}
}
