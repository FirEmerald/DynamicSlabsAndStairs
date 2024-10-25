package com.firemerald.additionalplacements.client.models;

import java.util.*;

import org.apache.commons.lang3.tuple.Pair;

import com.firemerald.additionalplacements.client.BlockModelUtils;

import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.util.Direction;
import net.minecraftforge.client.model.BakedModelWrapper;
import net.minecraftforge.client.model.data.IModelData;

public class BakedRetexturedBlockModel extends BakedModelWrapper<IBakedModel>
{
	private static final Map<Pair<IBakedModel, IBakedModel>, BakedRetexturedBlockModel> CACHE = new HashMap<>();

	public synchronized static BakedRetexturedBlockModel of(IBakedModel originalModel, IBakedModel newModel)
	{
		return CACHE.computeIfAbsent(Pair.of(originalModel, newModel), k -> new BakedRetexturedBlockModel(originalModel, newModel));
	}

	public static void clearCache()
	{
		CACHE.clear();
	}
	
	private final IBakedModel newModel;

	private BakedRetexturedBlockModel(IBakedModel originalModel, IBakedModel newModel)
	{
		super(originalModel);
		this.newModel = newModel;
	}

	@Override
	public List<BakedQuad> getQuads(BlockState state, Direction side, Random rand, IModelData extraData)
	{
		return BlockModelUtils.retexturedQuads(state, BlockModelUtils.getModeledState(state), (unused) -> originalModel, newModel, side, rand, extraData);
	}
}
