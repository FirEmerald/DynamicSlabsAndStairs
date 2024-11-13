package com.firemerald.additionalplacements.client.resources;

import com.firemerald.additionalplacements.block.AdditionalPlacementBlock;
import com.firemerald.additionalplacements.client.models.fixed.FixedModelLoader;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.minecraft.client.renderer.block.BlockModelShaper;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;

public class BlockModelJsonSupplier implements IJsonInputSupplier {
	private final AdditionalPlacementBlock<?> block;
	private final ResourceLocation blockId;
	private final BlockState state;
	
	public BlockModelJsonSupplier(AdditionalPlacementBlock<?> block, ResourceLocation blockId, BlockState state) {
		this.block = block;
		this.blockId = blockId;
		this.state = state;
	}
	
	@Override
	public JsonElement getJson() {
		JsonObject root = new JsonObject();
		root.addProperty("loader", FixedModelLoader.ID.toString());
		root.addProperty("block", blockId.toString());
		root.addProperty("ourModel", block.getModelDefinition(state).location(block.getBaseModelPrefix()).toString());
		ModelResourceLocation theirModel = BlockModelShaper.stateToModelLocation(block.getModelState(state));
		root.addProperty("theirBlock", theirModel.id().toString());
		root.addProperty("theirState", theirModel.getVariant());
		root.addProperty("modelRotation", block.getRotation(state).name());
		return root;
	}
}
