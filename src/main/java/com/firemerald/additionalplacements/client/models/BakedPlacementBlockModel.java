package com.firemerald.additionalplacements.client.models;

import java.util.List;

import com.firemerald.additionalplacements.block.AdditionalPlacementBlock;
import com.firemerald.additionalplacements.client.BlockModelUtils;

import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;

public class BakedPlacementBlockModel extends BakedModelWrapper
{
	public BakedPlacementBlockModel(BakedModel model)
	{
		super(model);
	}

	@Override
	public List<BakedQuad> getQuads(BlockState state, Direction side, RandomSource rand)
	{
		BlockState modelState = BlockModelUtils.getModeledState(state);
		if (state.getBlock() instanceof AdditionalPlacementBlock) {
			AdditionalPlacementBlock<?> block = (AdditionalPlacementBlock<?>) state.getBlock();
			if (block.rotatesModel(state)) return BlockModelUtils.rotatedQuads(modelState, BlockModelUtils::getBakedModel, block.getRotation(state), block.rotatesTexture(state), side, rand);
		}
		return BlockModelUtils.retexturedQuads(state, modelState, BlockModelUtils::getBakedModel, wrapped, side, rand);
	}
	
	public BakedModel originalModel() {
		return wrapped;
	}

	/* Too much information about the quad is hidden, making re-ordering vertices (to fix an annoying AO bug) impossible, so we will have to use vanilla model code
	@Override
	public void emitBlockQuads(BlockAndTintGetter blockView, BlockState state, BlockPos pos, Supplier<Random> randomSupplier, RenderContext context) {
		BlockState modelState = BlockModelUtils.getModeledState(state);
		if (state.getBlock() instanceof AdditionalPlacementBlock) {
			AdditionalPlacementBlock<?> block = (AdditionalPlacementBlock<?>) state.getBlock();
			if (block.rotatesModel(state)) {
				BlockModelUtils.emitRotatedQuads(block.getRotation(state), block.rotatesTexture(state), context, (ctx) -> ((FabricBakedModel) BlockModelUtils.getBakedModel(modelState)).emitBlockQuads(blockView, state, pos, randomSupplier, ctx));
				return;
			}
		}
		BlockModelUtils.emitRetexturedQuads(BlockModelUtils.getModeledState(state), BlockModelUtils.getBakedModel(modelState), ((FabricBakedModel) originalModel), blockView, state, pos, randomSupplier, context);
	}

	@Override
	public void emitItemQuads(ItemStack stack, Supplier<Random> randomSupplier, RenderContext context) {
		((FabricBakedModel) originalModel).emitItemQuads(stack, randomSupplier, context);
	}
	*/
}
