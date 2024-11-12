package com.firemerald.additionalplacements.client.models;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.BakedModelWrapper;
import net.neoforged.neoforge.client.model.data.ModelData;

public abstract class PlacementModelWrapper extends BakedModelWrapper<BakedModel> {

    public PlacementModelWrapper(BakedModel originalModel)
    {
    	super(originalModel);
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, RandomSource rand)
    {
    	return this.getQuads(state, side, rand, null, null);
    }

    @NotNull
    @Override
    public abstract List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @NotNull RandomSource rand, @Nullable ModelData extraData, @Nullable RenderType renderType);
}
