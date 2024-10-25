package com.firemerald.additionalplacements.client.models;

import java.util.*;

import org.apache.commons.lang3.tuple.Triple;

import com.firemerald.additionalplacements.client.BlockModelUtils;
import com.firemerald.additionalplacements.util.BlockRotation;

import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.util.Direction;
import net.minecraftforge.client.model.BakedModelWrapper;
import net.minecraftforge.client.model.data.IModelData;

public class BakedRotatedBlockModel extends BakedModelWrapper<IBakedModel>
{
	private static final Map<Triple<IBakedModel, BlockRotation, Boolean>, BakedRotatedBlockModel> CACHE = new HashMap<>();

	public synchronized static BakedRotatedBlockModel of(IBakedModel model, BlockRotation rotation, boolean rotateTex)
	{
		return CACHE.computeIfAbsent(Triple.of(model, rotation, rotateTex), k -> new BakedRotatedBlockModel(model, rotation, rotateTex));
	}

	public static void clearCache()
	{
		CACHE.clear();
	}
	
	private final BlockRotation rotation;
	private final boolean rotateTex;

	private BakedRotatedBlockModel(IBakedModel model, BlockRotation rotation, boolean rotateTex)
	{
		super(model);
		this.rotation = rotation;
		this.rotateTex = rotateTex;
	}

	@Override
	public List<BakedQuad> getQuads(BlockState state, Direction side, Random rand, IModelData extraData)
	{
		return BlockModelUtils.rotatedQuads(BlockModelUtils.getModeledState(state), unused -> originalModel, rotation, rotateTex, side, rand, extraData);
	}
}
