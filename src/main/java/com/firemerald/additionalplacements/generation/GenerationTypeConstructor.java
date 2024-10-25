package com.firemerald.additionalplacements.generation;

import net.minecraft.util.ResourceLocation;

@FunctionalInterface
public interface GenerationTypeConstructor<T extends GenerationType<?, ?>> {
	public T construct(Object protectionKey, ResourceLocation name, String description);
}
