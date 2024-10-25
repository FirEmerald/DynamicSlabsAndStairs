package com.firemerald.additionalplacements.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.firemerald.additionalplacements.block.AdditionalPlacementBlock;
import com.firemerald.additionalplacements.client.models.BakedRotatedBlockModel;

import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.BlockModelShapes;
import net.minecraft.client.renderer.model.IBakedModel;

import com.firemerald.additionalplacements.client.models.BakedRetexturedBlockModel;
import com.firemerald.additionalplacements.client.models.BakedPlacementBlockModel;

@Mixin(BlockModelShapes.class)
public class MixinBlockModelShapes
{
	@Inject(method = "getBlockModel", at = @At("RETURN"), cancellable = true)
    public void onGetBlockModel(BlockState state, CallbackInfoReturnable<IBakedModel> ci)
    {
		if (state.getBlock() instanceof AdditionalPlacementBlock && ci.getReturnValue() instanceof BakedPlacementBlockModel)
		{
			AdditionalPlacementBlock<?> block = (AdditionalPlacementBlock<?>) state.getBlock();
			boolean rotatesModel = block.rotatesModel(state);
			IBakedModel modelModel = ((BlockModelShapes) (Object) this).getBlockModel(rotatesModel ? block.getUnrotatedModelState(state) : block.getModelState(state));
			ci.setReturnValue(rotatesModel ? 
					BakedRotatedBlockModel.of(modelModel, block.getRotation(state), block.rotatesTexture(state)) : 
						BakedRetexturedBlockModel.of(modelModel, ((BakedPlacementBlockModel) ci.getReturnValue()).originalModel()));
		}
    }
}