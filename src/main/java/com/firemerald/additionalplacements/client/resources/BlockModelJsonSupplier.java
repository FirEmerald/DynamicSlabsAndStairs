package com.firemerald.additionalplacements.client.resources;

import java.io.InputStream;

import com.firemerald.additionalplacements.block.AdditionalPlacementBlock;
import com.firemerald.additionalplacements.client.models.PlacementBlockModelLoader;
import com.google.gson.JsonObject;

import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.BlockModelShapes;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;

public class BlockModelJsonSupplier {
	public static InputStream of(AdditionalPlacementBlock<?> block, ResourceLocation blockId, BlockState state) {
		JsonObject root = new JsonObject();
		root.addProperty("loader", PlacementBlockModelLoader.ID.toString());
		root.addProperty("block", blockId.toString());
		root.addProperty("ourModel", block.getModelDefinition(state).location(block.getModelPrefix()).toString());
		ModelResourceLocation theirModel = BlockModelShapes.stateToModelLocation(block.getModelState(state));
		root.addProperty("theirBlock", theirModel.getNamespace() + ":" + theirModel.getPath());
		root.addProperty("theirState", theirModel.getVariant());
		root.addProperty("modelRotation", block.getRotation(state).name());
		return JsonInputUtil.get(root);
	}
}
