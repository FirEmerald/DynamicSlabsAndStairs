package com.firemerald.additionalplacements.client.models.definitions;

import net.minecraft.resources.ResourceLocation;

public record StateModelDefinition(String model, int xRotation, int yRotation)
{
	public StateModelDefinition(String model, int yRotation)
	{
		this(model, 0, yRotation);
	}

	public StateModelDefinition(String model)
	{
		this(model, 0, 0);
	}

	public ResourceLocation location(ResourceLocation prefix) {
		return new ResourceLocation(prefix.getNamespace(), prefix.getPath() + model);
	}
}