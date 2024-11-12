package com.firemerald.additionalplacements.client.resources;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.firemerald.additionalplacements.AdditionalPlacementsMod;
import com.firemerald.additionalplacements.block.AdditionalPlacementBlock;
import com.firemerald.additionalplacements.client.models.definitions.StateModelDefinition;
import com.google.gson.JsonObject;

import net.minecraft.block.BlockState;
import net.minecraft.state.Property;

public class BlockStateJsonSupplier {	
	public static InputStream of(AdditionalPlacementBlock<?> block, String blockName) {
		JsonObject root = new JsonObject();
		JsonObject variants = new JsonObject();
		root.add("variants", variants);
		//TODO what if no blockstate properties?
		BlockState state = block.defaultBlockState();
		parseBlockstates(block, variants, state, new ArrayList<>(state.getProperties()), 0, "", "block/" + blockName + "/");
		return JsonInputUtil.get(root);
	}
	
	private static <T extends Comparable<T>> void parseBlockstates(AdditionalPlacementBlock<?> block, JsonObject variants, BlockState state, List<Property<?>> props, int index, String currentStateDef, String currentStateDir) {
		if (index >= props.size()) {
			JsonObject variant = new JsonObject();
			variants.add(currentStateDef.substring(0, currentStateDef.length() - 1), variant);
			variant.addProperty("model", AdditionalPlacementsMod.MOD_ID + ":" + currentStateDir + "model");
			StateModelDefinition modelDef = block.getModelDefinition(state);
			if (modelDef.xRotation != 0) variant.addProperty("x", modelDef.xRotation);
			if (modelDef.yRotation != 0) variant.addProperty("y", modelDef.yRotation);
			variant.addProperty("uvlock", true);
		}
		else {
			@SuppressWarnings("unchecked")
			Property<T> prop = (Property<T>) props.get(index);
			String name = prop.getName();
			String stateDef2 = currentStateDef + name + "=";
			prop.getAllValues().forEach(val -> {
				String valName = prop.getName(val.value());
				parseBlockstates(block, variants, state.setValue(prop, val.value()), props, index + 1, stateDef2 + valName + ",", currentStateDir + valName + "/");
			});
		}
	}
}
