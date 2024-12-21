package com.firemerald.additionalplacements.datagen;

import com.firemerald.additionalplacements.block.stairs.AdditionalStairBlock;
import com.firemerald.additionalplacements.client.models.definitions.StairModels;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.StairBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;

public class StairModelsBuilder extends ModelBuilder<StairBlock, AdditionalStairBlock, StairModelsBuilder>
{
	public StairModelsBuilder(BlockStateProvider stateProvider)
	{
		super(stateProvider, StairModels.MODELS);
	}

	public StairModelsBuilder setPillar(ResourceLocation side, ResourceLocation top, ResourceLocation bottom)
	{
		return setBase(StairModels.BASE_MODEL_FOLDER).addAction((builder, model) ->
		builder
		.texture("side", side)
		.texture("top", top)
		.texture("bottom", bottom));
	}

	public StairModelsBuilder setColumn(ResourceLocation side, ResourceLocation end)
	{
		return setBase(StairModels.COLUMN_MODEL_FOLDER).addAction((builder, model) ->
		builder
		.texture("side", side)
		.texture("end", end));
	}

	public StairModelsBuilder setAllSides(ResourceLocation all)
	{
		return setBase(StairModels.SIDE_ALL_MODEL_FOLDER).addAction((builder, model) ->
		builder
		.texture("all", all));
	}
}