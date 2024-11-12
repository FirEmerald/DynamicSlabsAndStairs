package com.firemerald.additionalplacements.generation;

import com.firemerald.additionalplacements.block.AdditionalPlacementBlock;

import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;

public class CreatedBlockEntry<T extends Block, U extends AdditionalPlacementBlock<T>> {
	public final ResourceLocation originalId;
	public final T originalBlock;
	public final ResourceLocation newId;
	public final U newBlock;
	
	public CreatedBlockEntry(ResourceLocation originalId, T originalBlock, ResourceLocation newId, U newBlock) {
		this.originalId = originalId;
		this.originalBlock = originalBlock;
		this.newId = newId;
		this.newBlock = newBlock;
	}
}
