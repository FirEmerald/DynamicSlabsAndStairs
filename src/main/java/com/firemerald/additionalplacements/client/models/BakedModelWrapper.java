package com.firemerald.additionalplacements.client.models;

import java.util.List;
import java.util.Random;

import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;

public abstract class BakedModelWrapper implements BakedModel
{
	public final BakedModel originalModel;
	
	protected BakedModelWrapper(BakedModel originalModel)
	{
		this.originalModel = originalModel;
	}

	@Override
	public List<BakedQuad> getQuads(BlockState blockState, Direction face, Random rand) {
		return originalModel.getQuads(blockState, face, rand);
	}

	@Override
	public boolean useAmbientOcclusion() {
		return originalModel.useAmbientOcclusion();
	}

	@Override
	public boolean isGui3d() {
		return originalModel.isGui3d();
	}

	@Override
	public boolean isCustomRenderer() {
		return originalModel.isCustomRenderer();
	}

	@Override
	public TextureAtlasSprite getParticleIcon() {
		return originalModel.getParticleIcon();
	}

	@Override
	public boolean usesBlockLight() {
		return originalModel.usesBlockLight();
	}

	@Override
	public ItemTransforms getTransforms() {
		return originalModel.getTransforms();
	}

	@Override
	public ItemOverrides getOverrides() {
		return originalModel.getOverrides();
	}
}
