package com.firemerald.additionalplacements.client.models.fixed;

import com.firemerald.additionalplacements.AdditionalPlacementsMod;
import com.firemerald.additionalplacements.block.AdditionalPlacementBlock;
import com.firemerald.additionalplacements.util.BlockRotation;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.geometry.IGeometryLoader;

public class FixedModelLoader implements IGeometryLoader<UnbakedFixedModel>
{
	public static final ResourceLocation ID = ResourceLocation.tryBuild(AdditionalPlacementsMod.MOD_ID, "fixed");

	@Override
	public UnbakedFixedModel read(JsonObject modelContents, JsonDeserializationContext deserializationContext)
	{
		return new UnbakedFixedModel(
				(AdditionalPlacementBlock<?>) BuiltInRegistries.BLOCK.get(ResourceLocation.parse(modelContents.get("block").getAsString())),
				ResourceLocation.parse(modelContents.get("ourModel").getAsString()),
				new ModelResourceLocation(ResourceLocation.parse(modelContents.get("theirBlock").getAsString()), modelContents.get("theirState").getAsString()),
				BlockRotation.valueOf(modelContents.get("modelRotation").getAsString())
				);
	}

}
