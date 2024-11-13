package com.firemerald.additionalplacements.client.models.fixed;

import com.firemerald.additionalplacements.AdditionalPlacementsMod;
import com.firemerald.additionalplacements.block.AdditionalPlacementBlock;
import com.firemerald.additionalplacements.util.BlockRotation;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.geometry.IGeometryLoader;
import net.minecraftforge.registries.ForgeRegistries;

public class FixedModelLoader implements IGeometryLoader<UnbakedFixedModel>
{
	public static final ResourceLocation ID = new ResourceLocation(AdditionalPlacementsMod.MOD_ID, "fixed");

	@Override
	public UnbakedFixedModel read(JsonObject modelContents, JsonDeserializationContext deserializationContext)
	{
		return new UnbakedFixedModel(
				(AdditionalPlacementBlock<?>) ForgeRegistries.BLOCKS.getValue(new ResourceLocation(modelContents.get("block").getAsString())),
				new ResourceLocation(modelContents.get("ourModel").getAsString()),
				new ModelResourceLocation(new ResourceLocation(modelContents.get("theirBlock").getAsString()), modelContents.get("theirState").getAsString()),
				BlockRotation.valueOf(modelContents.get("modelRotation").getAsString())
				);
	}

}
