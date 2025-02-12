package com.firemerald.additionalplacements.datagen;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonObject;

import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

//TextureMapping is poopoo and doesn't let you map a texture slot to another texture slot so here's an ACTUALLY USEFUL mapping class.
public class BetterTextureMapping {
	public static final BetterTextureMapping SIDE_ALL_TO_COMPLETE = new BetterTextureMapping()
			.put(TextureSlot.SIDE, TextureSlot.ALL)
			.put(TextureSlot.TOP, TextureSlot.ALL)
			.put(TextureSlot.BOTTOM, TextureSlot.ALL)
			.immutable();
	public static final BetterTextureMapping PILLAR_TO_COMPLETE = new BetterTextureMapping()
			.put(TextureSlot.TOP, TextureSlot.END)
			.put(TextureSlot.BOTTOM, TextureSlot.END)
			.immutable();
	
	public static BetterTextureMapping sideAll(ResourceLocation texture) {
		return new BetterTextureMapping()
				.put(TextureSlot.ALL, texture);
	}

	public static BetterTextureMapping sideAll(Block block) {
		return sideAll(TextureMapping.getBlockTexture(block));
	}

	public static BetterTextureMapping sideAll(Block block, String suffix) {
		return sideAll(TextureMapping.getBlockTexture(block, suffix));
	}
	
	public static BetterTextureMapping pillar(ResourceLocation side, ResourceLocation end) {
		return new BetterTextureMapping()
				.put(TextureSlot.SIDE, side)
				.put(TextureSlot.END, end);
	}

	public static BetterTextureMapping pillar(Block block) {
		return pillar(block, "");
	}

	public static BetterTextureMapping pillar(Block block, String suffix) {
		return pillar(TextureMapping.getBlockTexture(block, suffix + "_side"), TextureMapping.getBlockTexture(block, suffix + "_end"));
	}
	
	public static BetterTextureMapping complete(ResourceLocation side, ResourceLocation top, ResourceLocation bottom) {
		return new BetterTextureMapping()
				.put(TextureSlot.SIDE, side)
				.put(TextureSlot.TOP, top)
				.put(TextureSlot.BOTTOM, bottom);
	}

	public static BetterTextureMapping complete(Block block) {
		return complete(block, "");
	}

	public static BetterTextureMapping complete(Block block, String suffix) {
		return complete(TextureMapping.getBlockTexture(block, suffix + "_side"), TextureMapping.getBlockTexture(block, suffix + "top"), TextureMapping.getBlockTexture(block, suffix + "_bottom"));
	}
	
	private final Map<String, String> textures;
	
	public BetterTextureMapping(Map<String, String> textures) {
		this.textures = textures;
	}
	
	public BetterTextureMapping() {
		this(new HashMap<>());
	}
	
	public BetterTextureMapping put(String slot, String texture) {
		textures.put(slot, texture);
		return this;
	}
	
	public BetterTextureMapping put(String slot, ResourceLocation texture) {
		return put(slot, texture.toString());
	}
	
	public BetterTextureMapping put(String slot, TextureSlot mapTo) {
		return put(slot, "#" + mapTo.getId());
	}
	
	public BetterTextureMapping put(TextureSlot slot, String texture) {
		return put(slot.getId(), texture);
	}
	
	public BetterTextureMapping put(TextureSlot slot, ResourceLocation texture) {
		return put(slot.getId(), texture);
	}
	
	public BetterTextureMapping put(TextureSlot slot, TextureSlot mapTo) {
		return put(slot.getId(), mapTo);
	}
	
	public JsonObject toJson() {
		JsonObject json = new JsonObject();
		textures.forEach(json::addProperty);
		return json;
	}
	
	public BetterTextureMapping immutable() {
		Map<String, String> copied = Map.copyOf(textures);
		return copied == textures ? this : new BetterTextureMapping(copied);
	}
}
