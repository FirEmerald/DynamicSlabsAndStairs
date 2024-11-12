package com.firemerald.additionalplacements.datagen;

import com.firemerald.additionalplacements.block.AdditionalCarpetBlock;
import com.firemerald.additionalplacements.client.models.definitions.CarpetModels;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;

public class CarpetModelsBuilder extends ModelBuilder<CarpetBlock, AdditionalCarpetBlock, CarpetModelsBuilder>
{
	public CarpetModelsBuilder(BlockStateProvider stateProvider)
	{
		super(stateProvider, CarpetModels.MODELS);
	}

	public CarpetModelsBuilder setPillar(ResourceLocation side, ResourceLocation top, ResourceLocation bottom)
	{
		return setBase(CarpetModels.BASE_MODEL_FOLDER).addAction((builder, model) ->
		builder
		.texture("side", side)
		.texture("top", top)
		.texture("bottom", bottom));
	}

	public CarpetModelsBuilder setColumn(ResourceLocation side, ResourceLocation end)
	{
		return setBase(CarpetModels.COLUMN_MODEL_FOLDER).addAction((builder, model) ->
		builder
		.texture("side", side)
		.texture("end", end));
	}

	public CarpetModelsBuilder setAllSides(ResourceLocation all)
	{
		return setBase(CarpetModels.SIDE_ALL_MODEL_FOLDER).addAction((builder, model) ->
		builder
		.texture("all", all));
	}
}