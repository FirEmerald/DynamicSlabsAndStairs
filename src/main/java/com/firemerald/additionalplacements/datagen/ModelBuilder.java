package com.firemerald.additionalplacements.datagen;

import java.util.function.BiConsumer;

import com.firemerald.additionalplacements.block.AdditionalPlacementBlock;
import com.firemerald.additionalplacements.block.interfaces.IPlacementBlock;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;

public class ModelBuilder<T extends Block, U extends AdditionalPlacementBlock<T>, V extends ModelBuilder<T, U, V>>
{
	private final BlockStateProvider stateProvider;
	private final ModelType<U> modelType;
	private BiConsumer<BlockModelBuilder, String> actions = null;
	private boolean uvLock = false;

	public ModelBuilder(BlockStateProvider stateProvider, String... models)
	{
		this.stateProvider = stateProvider;
		this.modelType = new ModelType<>(models);
	}
	
	@SuppressWarnings("unchecked")
	public V me() {
		return (V) this;
	}

	public V reset()
	{
		modelType.clear();
		actions = null;
		uvLock = false;
		return me();
	}

	public V addAction(BiConsumer<BlockModelBuilder, String> action)
	{
		actions = actions == null ? action : actions.andThen(action);
		return me();
	}

	public V setTextures(String side, String top, String bottom)
	{
		return addAction((builder, model) ->
		builder
		.texture("side", side)
		.texture("top", top)
		.texture("bottom", bottom));
	}

	public V setBase(ResourceLocation parentFolder)
	{
		if (modelType.has) modelType.setParent(parentFolder);
		return me();
	}

	public V set(U block, String outputFolder, ResourceLocation parentFolder)
	{
		modelType.set(block, outputFolder, parentFolder);
		return me();
	}

	public V set(U block, String outputFolder)
	{
		return set(block, outputFolder, null);
	}

	@SuppressWarnings("unchecked")
	public V set(T baseBlock, String outputFolder, ResourceLocation parentFolder)
	{
		modelType.set(((IPlacementBlock<U>) baseBlock).getOtherBlock(), outputFolder, parentFolder);
		return me();
	}

	public V set(T baseBlock, String outputFolder)
	{
		return set(baseBlock, outputFolder, null);
	}

	public V set(String outputFolder, ResourceLocation parentFolder)
	{
		return set((U) null, outputFolder, parentFolder);
	}

	public V set(String outputFolder)
	{
		return set(outputFolder, null);
	}

	public V set(ResourceLocation outputFolder, ResourceLocation parentFolder)
	{
		return set((U) null, outputFolder.getPath(), parentFolder);
	}

	public V set(ResourceLocation outputFolder)
	{
		return set(outputFolder, null);
	}

	public V clear()
	{
		modelType.clear();
		return me();
	}

	public V setUVLock(boolean uvLock)
	{
		this.uvLock = uvLock;
		return me();
	}

	public V clearActions()
	{
		this.actions = null;
		return me();
	}

	public V build()
	{
		modelType.build(stateProvider, actions, uvLock);
		return me();
	}

	public V buildAndClear()
	{
		return build().clear();
	}

	public V compile()
	{
		return buildAndClear().clearActions();
	}
}