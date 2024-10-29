package com.firemerald.additionalplacements.client.models;

import java.util.*;

import org.apache.commons.lang3.tuple.Triple;

import com.firemerald.additionalplacements.client.BlockModelUtils;
import com.firemerald.additionalplacements.util.BlockRotation;

import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;

public class BakedRotatedBlockModel extends BakedModelWrapper
{
	private static final Map<Triple<BakedModel, BlockRotation, Boolean>, BakedRotatedBlockModel> CACHE = new HashMap<>();

	public synchronized static BakedRotatedBlockModel of(BakedModel model, BlockRotation rotation, boolean rotateTex)
	{
		return CACHE.computeIfAbsent(Triple.of(model, rotation, rotateTex), k -> new BakedRotatedBlockModel(model, rotation, rotateTex));
	}

	public static void clearCache()
	{
		CACHE.clear();
	}
	
	private final BlockRotation rotation;
	private final boolean rotateTex;

	private BakedRotatedBlockModel(BakedModel model, BlockRotation rotation, boolean rotateTex)
	{
		super(model);
		this.rotation = rotation;
		this.rotateTex = rotateTex;
	}

	@Override
	public List<BakedQuad> getQuads(BlockState state, Direction side, RandomSource rand)
	{
		return BlockModelUtils.rotatedQuads(BlockModelUtils.getModeledState(state), unused -> wrapped, rotation, rotateTex, side, rand);
	}

	/* Too much information about the quad is hidden, making re-ordering vertices (to fix an annoying AO bug) impossible, so we will have to use vanilla model code
	@Override
	public void emitBlockQuads(BlockAndTintGetter blockView, BlockState state, BlockPos pos, Supplier<Random> randomSupplier, RenderContext context) {
		BlockModelUtils.emitRotatedQuads(rotation, rotateTex, context, (ctx) -> ((FabricBakedModel) originalModel).emitBlockQuads(blockView, state, pos, randomSupplier, ctx));
	}

	@Override
	public void emitItemQuads(ItemStack stack, Supplier<Random> randomSupplier, RenderContext context) {
		BlockModelUtils.emitRotatedQuads(rotation, rotateTex, context, (ctx) -> ((FabricBakedModel) originalModel).emitItemQuads(stack, randomSupplier, ctx));
	}
	*/
}
