package com.firemerald.additionalplacements.datagen;

import com.firemerald.additionalplacements.block.AdditionalBasePressurePlateBlock;
import com.firemerald.additionalplacements.client.models.definitions.PressurePlateModels;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.BasePressurePlateBlock;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;

public class PressurePlateModelsBuilder<T extends BasePressurePlateBlock, U extends AdditionalBasePressurePlateBlock<T>> extends ModelBuilder<T, U, PressurePlateModelsBuilder<T, U>>
{
	public PressurePlateModelsBuilder(BlockStateProvider stateProvider)
	{
		super(stateProvider, PressurePlateModels.MODELS);
	}

	public PressurePlateModelsBuilder<T, U> setPillar(ResourceLocation side, ResourceLocation top, ResourceLocation bottom)
	{
		return setBase(PressurePlateModels.BASE_MODEL_FOLDER).addAction((builder, model) ->
		builder
		.texture("side", side)
		.texture("top", top)
		.texture("bottom", bottom));
	}

	public PressurePlateModelsBuilder<T, U> setColumn(ResourceLocation side, ResourceLocation end)
	{
		return setBase(PressurePlateModels.COLUMN_MODEL_FOLDER).addAction((builder, model) ->
		builder
		.texture("side", side)
		.texture("end", end));
	}

	public PressurePlateModelsBuilder<T, U> setAllSides(ResourceLocation all)
	{
		return setBase(PressurePlateModels.SIDE_ALL_MODEL_FOLDER).addAction((builder, model) ->
		builder
		.texture("all", all));
	}
}