package com.firemerald.additionalplacements.client.models;

import java.util.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.util.Direction;
import net.minecraftforge.client.model.BakedModelWrapper;
import net.minecraftforge.client.model.data.IModelData;

public abstract class PlacementModelWrapper extends BakedModelWrapper<IBakedModel>
{
	public PlacementModelWrapper(IBakedModel originalModel)
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
