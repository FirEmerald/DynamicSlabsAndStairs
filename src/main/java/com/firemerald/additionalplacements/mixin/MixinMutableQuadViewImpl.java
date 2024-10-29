package com.firemerald.additionalplacements.mixin;

/* Too much information about the quad is hidden, making re-ordering vertices (to fix an annoying AO bug) impossible, so we will have to use vanilla model code
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Desc;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.firemerald.additionalplacements.client.models.IMutableQuadViewExtensions;

import net.fabricmc.fabric.api.renderer.v1.material.RenderMaterial;
import net.fabricmc.fabric.impl.client.indigo.renderer.mesh.MutableQuadViewImpl;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.Direction;

@Mixin(MutableQuadViewImpl.class)
public class MixinMutableQuadViewImpl implements IMutableQuadViewExtensions {
	TextureAtlasSprite sprite;

	@Inject(method = "clear", at = @At("RETURN"), cancellable = true)
	public void clear(CallbackInfo cli) {
		sprite = null;
	}

	@Inject(target = @Desc(
			value = "fromVanilla", 
			owner = MutableQuadViewImpl.class, 
			ret = MutableQuadViewImpl.class, 
			args = {BakedQuad.class, RenderMaterial.class, Direction.class}), 
			at = @At("RETURN"), cancellable = true)
	public void fromVanilla(BakedQuad quad, RenderMaterial material, Direction cullFace, CallbackInfoReturnable<MutableQuadViewImpl> cli) {
		sprite = quad.getSprite();
	}

	@Inject(
			method = "Lnet/fabricmc/fabric/impl/client/indigo/renderer/mesh/MutableQuadViewImpl;spriteBake(ILnet/minecraft/client/renderer/texture/TextureAtlasSprite;I)Lnet/fabricmc/fabric/impl/client/indigo/renderer/mesh/MutableQuadViewImpl;", 
			at = @At("RETURN"), cancellable = true)
	public void spriteBake(int spriteIndex, TextureAtlasSprite sprite, int bakeFlags, CallbackInfoReturnable<MutableQuadViewImpl> cli) {
		this.sprite = sprite;
	}
	
	@Override
	public TextureAtlasSprite sprite() {
		return sprite;
	}
	
	@Override
	public void sprite(TextureAtlasSprite sprite) {
		this.sprite = sprite;
	}
}
*/
