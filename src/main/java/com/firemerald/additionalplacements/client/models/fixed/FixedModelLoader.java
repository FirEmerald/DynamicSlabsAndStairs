package com.firemerald.additionalplacements.client.models.fixed;

import com.firemerald.additionalplacements.AdditionalPlacementsMod;
import com.firemerald.additionalplacements.block.AdditionalPlacementBlock;
import com.firemerald.additionalplacements.client.models.BlockModelCache;
import com.firemerald.additionalplacements.util.BlockRotation;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;

import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelLoader;
import net.minecraftforge.registries.ForgeRegistries;

public class FixedModelLoader implements IModelLoader<UnbakedFixedModel>
{
	public static final ResourceLocation ID = new ResourceLocation(AdditionalPlacementsMod.MOD_ID, "fixed");

	@Override
	public void onResourceManagerReload(IResourceManager pResourceManager) {
		BlockModelCache.clearCache();
	}

	@Override
	public UnbakedFixedModel read(JsonDeserializationContext deserializationContext, JsonObject modelContents)
	{
		return new UnbakedFixedModel(
				(AdditionalPlacementBlock<?>) ForgeRegistries.BLOCKS.getValue(new ResourceLocation(modelContents.get("block").getAsString())),
				new ResourceLocation(modelContents.get("ourModel").getAsString()),
				new ModelResourceLocation(new ResourceLocation(modelContents.get("theirBlock").getAsString()), modelContents.get("theirState").getAsString()),
				BlockRotation.valueOf(modelContents.get("modelRotation").getAsString())
				);
	}

}
