package com.firemerald.additionalplacements.client.resources;

import java.util.function.BiFunction;

import com.firemerald.additionalplacements.AdditionalPlacementsMod;
import com.firemerald.additionalplacements.block.AdditionalPlacementBlock;
import com.firemerald.additionalplacements.client.models.definitions.StateModelDefinition;
import com.firemerald.additionalplacements.config.APConfigs;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;

public class BlockStateJsonSupplier implements IJsonInputSupplier {
	private final AdditionalPlacementBlock<?> block;
	private final String blockName;
	
	public BlockStateJsonSupplier(AdditionalPlacementBlock<?> block, String blockName) {
		this.block = block;
		this.blockName = blockName;
	}
	
	@Override
	public JsonElement getJson() {
		JsonObject root = new JsonObject();
		JsonObject variants = new JsonObject();
		root.add("variants", variants);
		//TODO what if no blockstate properties?
		BlockState state = block.defaultBlockState();
		Property<?>[] props;
		BiFunction<BlockState, String, ResourceLocation> getModel;
		if (APConfigs.client().useDynamicModels.get()) {
			props = block.dynamicModelProperties();
			getModel = (modelState, stateDir) -> block.getModelDefinition(modelState).location(block.getDynamicModelPrefix());
		}
		else {
			props = state.getProperties().toArray(Property[]::new);
			getModel = (modelState, stateDir) -> new ResourceLocation(AdditionalPlacementsMod.MOD_ID, stateDir + "model");
		}
		parseBlockstates(variants, state, props, 0, "", "block/" + blockName + "/", getModel);
		return root;
	}
	
	private <T extends Comparable<T>> void parseBlockstates(JsonObject variants, BlockState state, Property<?>[] props, int index, String currentStateDef, String currentStateDir, BiFunction<BlockState, String, ResourceLocation> getModel) {
		if (index >= props.length) {
			JsonObject variant = new JsonObject();
			variants.add(currentStateDef.substring(0, currentStateDef.length() - 1), variant);
			variant.addProperty("model", getModel.apply(state, currentStateDir).toString());
			StateModelDefinition modelDef = block.getModelDefinition(state);
			if (modelDef.xRotation() != 0) variant.addProperty("x", modelDef.xRotation());
			if (modelDef.yRotation() != 0) variant.addProperty("y", modelDef.yRotation());
			variant.addProperty("uvlock", true);
		}
		else {
			@SuppressWarnings("unchecked")
			Property<T> prop = (Property<T>) props[index];
			String name = prop.getName();
			String stateDef2 = currentStateDef + name + "=";
			prop.getAllValues().forEach(val -> {
				String valName = prop.getName(val.value());
				parseBlockstates(variants, state.setValue(prop, val.value()), props, index + 1, stateDef2 + valName + ",", currentStateDir + valName + "/", getModel);
			});
		}
	}
}
