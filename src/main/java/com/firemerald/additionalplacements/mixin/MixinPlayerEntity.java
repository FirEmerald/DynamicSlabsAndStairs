package com.firemerald.additionalplacements.mixin;

import org.spongepowered.asm.mixin.Mixin;

import com.firemerald.additionalplacements.common.IAPPlayer;
import com.firemerald.additionalplacements.config.APConfigs;

import net.minecraft.entity.player.PlayerEntity;

@Mixin(PlayerEntity.class)
public class MixinPlayerEntity implements IAPPlayer
{
	@Override
	public boolean isPlacementEnabled()
	{
		return APConfigs.SERVER.fakePlayerPlacement.get();
	}
}