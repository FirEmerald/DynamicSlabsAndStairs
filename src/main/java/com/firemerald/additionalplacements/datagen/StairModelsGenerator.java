package com.firemerald.additionalplacements.datagen;

import java.util.stream.Collectors;

import com.firemerald.additionalplacements.block.stairs.AdditionalStairBlock;
import com.firemerald.additionalplacements.block.stairs.StairConnectionsType;
import com.firemerald.additionalplacements.client.models.definitions.*;

import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.StairBlock;

public class StairModelsGenerator extends BlockModelGenerator<StairBlock, AdditionalStairBlock, StairModelsGenerator> {
	@Override
	public String[] modelPaths() {
		return StairModels.MODELS;
	}

	@Override
	public String[] modelPaths(AdditionalStairBlock block) {
		return block.connectionsType.getPossibleValues().stream().map(StairModels::getModelDefinition).map(StateModelDefinition::model).collect(Collectors.toSet()).toArray(String[]::new);
	}
	
	public PropertyDispatch dispatch(StairConnectionsType connectionType, ResourceLocation modelPrefix) {
		return PropertyDispatch.property(connectionType).generate(shape -> variantOf(StairModels.getModelDefinition(shape), modelPrefix));
	}
	
	@Override
	public MultiVariantGenerator generator(AdditionalStairBlock block, ResourceLocation modelPrefix) {
		return MultiVariantGenerator.multiVariant(block).with(dispatch(block.connectionsType, modelPrefix));
	}
}
