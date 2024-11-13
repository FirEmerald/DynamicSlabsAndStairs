package com.firemerald.additionalplacements.client.models.dynamic;

import com.firemerald.additionalplacements.AdditionalPlacementsMod;
import com.firemerald.additionalplacements.client.models.BlockModelCache;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraftforge.client.model.IModelLoader;

public class DynamicModelLoader implements IModelLoader<UnbakedDynamicModel>
{
	public static final ResourceLocation ID = new ResourceLocation(AdditionalPlacementsMod.MOD_ID, "dynamic");

	@Override
	public void onResourceManagerReload(ResourceManager pResourceManager) {
		BlockModelCache.clearCache();
	}

	@Override
	public UnbakedDynamicModel read(JsonDeserializationContext deserializationContext, JsonObject modelContents)
	{
		return new UnbakedDynamicModel(new ResourceLocation(modelContents.get("ourModel").getAsString()));
	}

}
