package com.firemerald.additionalplacements.generation;

import com.firemerald.additionalplacements.block.AdditionalPlacementBlock;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

public record CreatedBlockEntry<T extends Block, U extends AdditionalPlacementBlock<T>>(ResourceLocation originalId, T originalBlock, ResourceLocation newId, U newBlock) {}
