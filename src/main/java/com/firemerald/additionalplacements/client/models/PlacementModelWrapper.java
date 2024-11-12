package com.firemerald.additionalplacements.client.models;

import java.util.List;
import java.util.Random;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.BakedModelWrapper;
import net.minecraftforge.client.model.data.IModelData;

public abstract class PlacementModelWrapper extends BakedModelWrapper<BakedModel>
{
	public PlacementModelWrapper(BakedModel originalModel)
	{
		super(originalModel);
	}

    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, Random rand)
    {
        return this.getQuads(state, side, rand, null);
    }

    @Nonnull
    @Override
    public abstract List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @Nonnull Random rand, @Nullable IModelData extraData);
}
