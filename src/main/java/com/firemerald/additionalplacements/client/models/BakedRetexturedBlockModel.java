package com.firemerald.additionalplacements.client.models;

import java.util.*;

import org.apache.commons.lang3.tuple.Pair;

import com.firemerald.additionalplacements.client.BlockModelUtils;

import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;

public class BakedRetexturedBlockModel extends BakedModelWrapper
{
	private static final Map<Pair<BakedModel, BakedModel>, BakedRetexturedBlockModel> CACHE = new HashMap<>();

	public synchronized static BakedRetexturedBlockModel of(BakedModel originalModel, BakedModel newModel)
	{
		return CACHE.computeIfAbsent(Pair.of(originalModel, newModel), k -> new BakedRetexturedBlockModel(originalModel, newModel));
	}

	public static void clearCache()
	{
		CACHE.clear();
	}

	private final BakedModel newModel;

	private BakedRetexturedBlockModel(BakedModel originalModel, BakedModel newModel)
	{
		super(originalModel);
		this.newModel = newModel;
	}

	@Override
	public List<BakedQuad> getQuads(BlockState state, Direction side, Random rand)
	{
		return BlockModelUtils.retexturedQuads(state, BlockModelUtils.getModeledState(state), (unused) -> originalModel, newModel, side, rand);
	}

	/* Too much information about the quad is hidden, making re-ordering vertices (to fix an annoying AO bug) impossible, so we will have to use vanilla model code
	@Override
	public void emitBlockQuads(BlockAndTintGetter blockView, BlockState state, BlockPos pos, Supplier<Random> randomSupplier, RenderContext context) {
		BlockModelUtils.emitRetexturedQuads(BlockModelUtils.getModeledState(state), originalModel, newModel, blockView, state, pos, randomSupplier, context);
	}

	@Override
	public void emitItemQuads(ItemStack stack, Supplier<Random> randomSupplier, RenderContext context) {
		newModel.emitItemQuads(stack, randomSupplier, context);
	}
	*/
}
