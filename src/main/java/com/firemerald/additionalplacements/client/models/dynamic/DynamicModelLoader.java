package com.firemerald.additionalplacements.client.models.dynamic;

import com.firemerald.additionalplacements.AdditionalPlacementsMod;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.geometry.IGeometryLoader;

public class DynamicModelLoader implements IGeometryLoader<UnbakedDynamicModel>
{
	public static final ResourceLocation ID = ResourceLocation.tryBuild(AdditionalPlacementsMod.MOD_ID, "fixed");

	@Override
	public UnbakedDynamicModel read(JsonObject modelContents, JsonDeserializationContext deserializationContext)
	{
		return new UnbakedDynamicModel(ResourceLocation.parse(modelContents.get("ourModel").getAsString()));
	}

}
