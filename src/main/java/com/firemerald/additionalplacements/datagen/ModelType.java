package com.firemerald.additionalplacements.datagen;

import java.util.function.BiConsumer;
import java.util.function.Function;

import org.apache.http.util.Asserts;

import com.firemerald.additionalplacements.block.AdditionalPlacementBlock;
import com.firemerald.additionalplacements.client.models.definitions.StateModelDefinition;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.BlockModelBuilder;
import net.neoforged.neoforge.client.model.generators.BlockModelProvider;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;

public class ModelType<T extends AdditionalPlacementBlock<?>>
{
	final String[] models;
	boolean has = false;
	T block = null;
	String folder = null;
	ResourceLocation parentFolder = null;

	public ModelType(String... models)
	{
		this.models = models;
	}

	public void set(T block, String folder, ResourceLocation parentFolder)
	{
		has = true;
		this.block = block;
		this.folder = folder;
		this.parentFolder = parentFolder;
	}

	public void set(T block, String folder)
	{
		set(block, folder, null);
	}

	public void set(String folder)
	{
		set(null, folder, null);
	}

	public void setParent(ResourceLocation parentFolder)
	{
		this.parentFolder = parentFolder;
	}

	public void build(BlockStateProvider stateProvider, BiConsumer<BlockModelBuilder, String> actions, boolean uvLock)
	{
		if (has)
		{
			Asserts.notNull(folder, "folder");
			BlockModelProvider modelProvider = stateProvider.models();
			Function<String, BlockModelBuilder> startModel;
			if (parentFolder == null) startModel = model -> modelProvider.getBuilder(folder + model);
			else startModel = model -> modelProvider.withExistingParent(folder + model, parentFolder.withSuffix(model));
			if (actions == null) actions = (builder, model) -> {};
			for (String model : models) actions.accept(startModel.apply(model), model);
			if (block != null)
			{
				stateProvider.getVariantBuilder(block).forAllStatesExcept(state -> {
					StateModelDefinition modelDef = block.getModelDefinition(state);
					return ConfiguredModel.builder()
					.modelFile(modelProvider.getExistingFile(modelProvider.modLoc(folder + modelDef.model())))
					.rotationX(modelDef.xRotation())
					.rotationY(modelDef.yRotation())
					.uvLock(uvLock)
					.build();
				}, block.getCopyProps());
			}
		}
	}

	public void clear()
	{
		has = false;
		this.block = null;
		this.folder = null;
		this.parentFolder = null;
	}
}