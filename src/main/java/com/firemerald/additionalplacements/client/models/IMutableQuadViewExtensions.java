package com.firemerald.additionalplacements.client.models;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;

public interface IMutableQuadViewExtensions {
	public TextureAtlasSprite sprite();
	
	public void sprite(TextureAtlasSprite sprite);
}
