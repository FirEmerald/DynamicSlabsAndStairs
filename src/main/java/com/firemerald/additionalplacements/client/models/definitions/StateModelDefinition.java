package com.firemerald.additionalplacements.client.models.definitions;

import net.minecraft.client.data.models.blockstates.VariantProperties;
import net.minecraft.resources.ResourceLocation;

public record StateModelDefinition(String model, VariantProperties.Rotation xRotation, VariantProperties.Rotation yRotation)
{
	public StateModelDefinition(String model, VariantProperties.Rotation yRotation)
	{
		this(model, VariantProperties.Rotation.R0, yRotation);
	}

	public StateModelDefinition(String model)
	{
		this(model, VariantProperties.Rotation.R0, VariantProperties.Rotation.R0);
	}

	public ResourceLocation location(ResourceLocation prefix) {
		return prefix.withSuffix(model);
	}
}