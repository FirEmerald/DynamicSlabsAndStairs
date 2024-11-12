package com.firemerald.additionalplacements.client.models;

import com.firemerald.additionalplacements.AdditionalPlacementsMod;
import com.firemerald.additionalplacements.block.AdditionalPlacementBlock;
import com.firemerald.additionalplacements.util.BlockRotation;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraftforge.client.model.IModelLoader;
import net.minecraftforge.registries.ForgeRegistries;

public class PlacementBlockModelLoader implements IModelLoader<PlacementBlockModel>
{
	public static final ResourceLocation ID = new ResourceLocation(AdditionalPlacementsMod.MOD_ID, "placement_block_loader");

	@Override
	public void onResourceManagerReload(ResourceManager pResourceManager) {
		PlacementBlockModel.clearCache();
	}

	@Override
	public PlacementBlockModel read(JsonDeserializationContext deserializationContext, JsonObject modelContents)
	{
		return new PlacementBlockModel(
				(AdditionalPlacementBlock<?>) ForgeRegistries.BLOCKS.getValue(new ResourceLocation(modelContents.get("block").getAsString())),
				new ResourceLocation(modelContents.get("ourModel").getAsString()),
				new ModelResourceLocation(new ResourceLocation(modelContents.get("theirBlock").getAsString()), modelContents.get("theirState").getAsString()),
				BlockRotation.valueOf(modelContents.get("modelRotation").getAsString())
				);
	}

}
