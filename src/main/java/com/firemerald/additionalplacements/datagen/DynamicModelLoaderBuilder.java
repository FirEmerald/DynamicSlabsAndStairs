package com.firemerald.additionalplacements.datagen;

import javax.annotation.Nonnull;

import com.firemerald.additionalplacements.client.models.dynamic.DynamicModelLoader;
import com.google.common.base.Preconditions;
import com.google.gson.JsonObject;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.CustomLoaderBuilder;
import net.minecraftforge.client.model.generators.ModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;

public class DynamicModelLoaderBuilder<T extends ModelBuilder<T>> extends CustomLoaderBuilder<T>
{
	public DynamicModelLoaderBuilder(T parent, ExistingFileHelper existingFileHelper)
	{
		super(DynamicModelLoader.ID, parent, existingFileHelper);
	}

    private ResourceLocation ourModel = null;

    public DynamicModelLoaderBuilder<T> setModel(@Nonnull ResourceLocation ourModel)
    {
    	this.ourModel = ourModel;
    	return this;
    }

    @Override
    public JsonObject toJson(JsonObject json)
    {
        json = super.toJson(json);
        Preconditions.checkNotNull(ourModel, "ourModel must not be null");
        json.addProperty("ourModel", ourModel.toString());
        return json;
    }
}