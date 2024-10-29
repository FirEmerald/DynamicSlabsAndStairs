package com.firemerald.additionalplacements.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.firemerald.additionalplacements.block.AdditionalPlacementBlock;

import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.DataMapHooks;

@Mixin(DataMapHooks.class)
public class MixinDataMapHooks {
	
	@Inject(method = "getNextOxidizedStage", at = @At("HEAD"))
    private static void getNextOxidizedStage(Block block, CallbackInfoReturnable<Block> cli) {
		if (block instanceof AdditionalPlacementBlock apBlock) cli.setReturnValue(DataMapHooks.getNextOxidizedStage(apBlock.getOtherBlock()));
    }

	@Inject(method = "getPreviousOxidizedStage", at = @At("HEAD"))
	private static void getPreviousOxidizedStage(Block block, CallbackInfoReturnable<Block> cli) {
		if (block instanceof AdditionalPlacementBlock apBlock) cli.setReturnValue(DataMapHooks.getPreviousOxidizedStage(apBlock.getOtherBlock()));
    }

	@Inject(method = "getBlockWaxed", at = @At("HEAD"))
	private static void getBlockWaxed(Block block, CallbackInfoReturnable<Block> cli) {
		if (block instanceof AdditionalPlacementBlock apBlock) cli.setReturnValue(DataMapHooks.getBlockWaxed(apBlock.getOtherBlock()));
    }

	@Inject(method = "getBlockUnwaxed", at = @At("HEAD"))
	private static void getBlockUnwaxed(Block block, CallbackInfoReturnable<Block> cli) {
		if (block instanceof AdditionalPlacementBlock apBlock) cli.setReturnValue(DataMapHooks.getBlockUnwaxed(apBlock.getOtherBlock()));
    }
}
