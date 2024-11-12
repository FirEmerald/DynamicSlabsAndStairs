package com.firemerald.additionalplacements.client.resources;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

public class JsonInputUtil {
	static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

	public static InputStream get(JsonElement json) {
		return new ByteArrayInputStream(GSON.toJson(json).getBytes(StandardCharsets.UTF_8));
	}
}
