package com.firemerald.additionalplacements.mixin;

import com.firemerald.additionalplacements.block.interfaces.IFloorBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockBehaviour.class)
public abstract class MixinBlockBehaviour {

    @Inject(method = "rotate", at = @At("HEAD"), cancellable = true)
    private void rotate(BlockState blockState, Rotation rotation, CallbackInfoReturnable<BlockState> ci) {
        if ((Object) this instanceof IFloorBlock floorBlock && floorBlock.hasAdditionalStates()) {
            ci.setReturnValue(floorBlock.rotateImpl(blockState, rotation));
        }
    }

    @Inject(method = "mirror", at = @At("HEAD"), cancellable = true)
    private void mirror(BlockState blockState, Mirror mirror, CallbackInfoReturnable<BlockState> ci) {
        if ((Object) this instanceof IFloorBlock floorBlock && floorBlock.hasAdditionalStates()) {
            ci.setReturnValue(floorBlock.mirrorImpl(blockState, mirror));
        }
    }
}