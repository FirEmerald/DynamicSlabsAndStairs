package com.firemerald.additionalplacements.mixin;

import com.firemerald.additionalplacements.block.interfaces.IFloorBlock;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Block.class)
public abstract class MixinBlock {
    @Inject(method = "getStateForPlacement", at = @At("RETURN"), cancellable = true)
    private void getStateForPlacement(BlockPlaceContext context, CallbackInfoReturnable<BlockState> ci) {
        if ((Object) this instanceof IFloorBlock floorBlock) {
            if (floorBlock.enablePlacement(context.getClickedPos(), context.getLevel(), context.getClickedFace(), context.getPlayer())) {
                ci.setReturnValue(floorBlock.getStateForPlacementImpl(context, ci.getReturnValue()));
            }
        }
    }
}
