package com.firemerald.additionalplacements.client.resources;

import java.io.InputStream;

import com.firemerald.additionalplacements.block.AdditionalPlacementBlock;
import com.firemerald.additionalplacements.client.models.fixed.FixedModelLoader;
import com.google.gson.JsonObject;

import net.minecraft.client.renderer.block.BlockModelShaper;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;

public class BlockModelJsonSupplier {
	public static InputStream of(AdditionalPlacementBlock<?> block, ResourceLocation blockId, BlockState state) {
		JsonObject root = new JsonObject();
		root.addProperty("loader", FixedModelLoader.ID.toString());
		root.addProperty("block", blockId.toString());
		root.addProperty("ourModel", block.getModelDefinition(state).location(block.getBaseModelPrefix()).toString());
		ModelResourceLocation theirModel = BlockModelShaper.stateToModelLocation(block.getModelState(state));
		root.addProperty("theirBlock", theirModel.getNamespace() + ":" + theirModel.getPath());
		root.addProperty("theirState", theirModel.getVariant());
		root.addProperty("modelRotation", block.getRotation(state).name());
		return JsonInputUtil.get(root);
	}
}
