package com.firemerald.additionalplacements.datagen;

import com.firemerald.additionalplacements.client.models.definitions.CarpetModels;
import com.firemerald.additionalplacements.client.models.definitions.PressurePlateModels;
import com.firemerald.additionalplacements.client.models.definitions.SlabModels;
import com.firemerald.additionalplacements.client.models.definitions.StairModels;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModelGenerator extends BlockStateProvider
{
	public ModelGenerator(PackOutput output, String modid, ExistingFileHelper exFileHelper)
	{
		super(output, modid, exFileHelper);
	}

	@Override
	protected void registerStatesAndModels()
	{
		new SlabModelsBuilder(this)
		.set(SlabModels.SIDE_ALL_MODEL_FOLDER, SlabModels.BASE_MODEL_FOLDER).addAction((builder, model) -> builder.texture("side", "#all").texture("top", "#all").texture("bottom", "#all")).compile() //build side_all
		.set(SlabModels.COLUMN_MODEL_FOLDER, SlabModels.BASE_MODEL_FOLDER).addAction((builder, model) -> builder.texture("top", "#end").texture("bottom", "#end")).compile(); //build pillar
		new StairModelsBuilder(this)
		.set(StairModels.SIDE_ALL_MODEL_FOLDER, StairModels.BASE_MODEL_FOLDER).addAction((builder, model) -> builder.texture("side", "#all").texture("top", "#all").texture("bottom", "#all")).compile() //build side_all
		.set(StairModels.COLUMN_MODEL_FOLDER, StairModels.BASE_MODEL_FOLDER).addAction((builder, model) -> builder.texture("top", "#end").texture("bottom", "#end")).compile(); //build pillar
		new CarpetModelsBuilder(this)
		.set(CarpetModels.SIDE_ALL_MODEL_FOLDER, CarpetModels.BASE_MODEL_FOLDER).addAction((builder, model) -> builder.texture("side", "#all").texture("top", "#all").texture("bottom", "#all")).compile() //build side_all
		.set(CarpetModels.COLUMN_MODEL_FOLDER, CarpetModels.BASE_MODEL_FOLDER).addAction((builder, model) -> builder.texture("top", "#end").texture("bottom", "#end")).compile(); //build pillar
		new PressurePlateModelsBuilder<>(this)
		.set(PressurePlateModels.SIDE_ALL_MODEL_FOLDER, PressurePlateModels.BASE_MODEL_FOLDER).addAction((builder, model) -> builder.texture("side", "#all").texture("top", "#all").texture("bottom", "#all")).compile() //build side_all
		.set(PressurePlateModels.COLUMN_MODEL_FOLDER, PressurePlateModels.BASE_MODEL_FOLDER).addAction((builder, model) -> builder.texture("top", "#end").texture("bottom", "#end")).compile(); //build pillar
	}
}