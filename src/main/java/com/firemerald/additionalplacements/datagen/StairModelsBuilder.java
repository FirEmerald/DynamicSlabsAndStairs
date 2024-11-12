package com.firemerald.additionalplacements.datagen;

import com.firemerald.additionalplacements.block.VerticalStairBlock;
import com.firemerald.additionalplacements.client.models.definitions.StairModels;

import io.github.fabricators_of_create.porting_lib.models.generators.block.BlockStateProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.StairBlock;

public class StairModelsBuilder extends ModelBuilder<StairBlock, VerticalStairBlock, StairModelsBuilder>
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