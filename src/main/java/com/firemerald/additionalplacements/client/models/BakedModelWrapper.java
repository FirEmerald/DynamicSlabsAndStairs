package com.firemerald.additionalplacements.client.models;

import java.util.function.Supplier;

import net.fabricmc.fabric.api.renderer.v1.model.ForwardingBakedModel;
import net.fabricmc.fabric.api.renderer.v1.render.RenderContext;
import net.fabricmc.fabric.impl.renderer.VanillaModelEncoder;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;

public abstract class BakedModelWrapper extends ForwardingBakedModel
{
	protected BakedModelWrapper(BakedModel originalModel)
	{
		this.wrapped = originalModel;
	}

	@Override
	public void emitBlockQuads(BlockAndTintGetter blockView, BlockState state, BlockPos pos, Supplier<RandomSource> randomSupplier, RenderContext context) {
		VanillaModelEncoder.emitBlockQuads(this, state, randomSupplier, context, context.getEmitter());
	}

	@Override
	public boolean isVanillaAdapter() {
		return true;
	}

	@Override
	public void emitItemQuads(ItemStack stack, Supplier<RandomSource> randomSupplier, RenderContext context) {
		VanillaModelEncoder.emitItemQuads(this, null, randomSupplier, context);
	}
}
