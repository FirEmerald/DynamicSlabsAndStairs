package com.firemerald.additionalplacements.datagen;

import com.firemerald.additionalplacements.block.VerticalSlabBlock;
import com.firemerald.additionalplacements.client.models.definitions.SlabModels;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;

public class SlabModelsBuilder extends ModelBuilder<SlabBlock, VerticalSlabBlock, SlabModelsBuilder>
{
	public SlabModelsBuilder(BlockStateProvider stateProvider)
	{
		super(stateProvider, SlabModels.MODELS);
	}

	public SlabModelsBuilder setPillar(ResourceLocation side, ResourceLocation top, ResourceLocation bottom)
	{
		return setBase(SlabModels.BASE_MODEL_FOLDER).addAction((builder, model) ->
		builder
		.texture("side", side)
		.texture("top", top)
		.texture("bottom", bottom));
	}

	public SlabModelsBuilder setColumn(ResourceLocation side, ResourceLocation end)
	{
		return setBase(SlabModels.COLUMN_MODEL_FOLDER).addAction((builder, model) ->
		builder
		.texture("side", side)
		.texture("end", end));
	}

	public SlabModelsBuilder setAllSides(ResourceLocation all)
	{
		return setBase(SlabModels.SIDE_ALL_MODEL_FOLDER).addAction((builder, model) ->
		builder
		.texture("all", all));
	}
}