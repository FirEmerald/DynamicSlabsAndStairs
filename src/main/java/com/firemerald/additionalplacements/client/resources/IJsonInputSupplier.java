package com.firemerald.additionalplacements.client.resources;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import net.minecraft.server.packs.resources.IoSupplier;

public interface IJsonInputSupplier extends IoSupplier<InputStream> {
	static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

	@Override
	public default InputStream get() throws IOException {
		return new ByteArrayInputStream(GSON.toJson(getJson()).getBytes(StandardCharsets.UTF_8));
	}

	public JsonElement getJson();
}
