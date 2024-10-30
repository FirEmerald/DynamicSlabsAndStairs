package com.firemerald.additionalplacements.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Desc;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.firemerald.additionalplacements.AdditionalPlacementsMod;
import com.firemerald.additionalplacements.generation.Registration;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;
import net.minecraftforge.registries.IForgeRegistry;

@Mixin(value = ForgeRegistry.class, remap = false)
public class MixinForgeRegistry
{
	@SuppressWarnings("unchecked")
	@Inject(target = @Desc(value = "register", args = {ResourceLocation.class, Object.class}), at = @At("RETURN"), remap = false)
	private void register(ResourceLocation key, Object value, CallbackInfo ci)
    {
		if (this == ForgeRegistries.BLOCKS && AdditionalPlacementsMod.dynamicRegistration)
		{
			Registration.tryApply((Block) value, key, ((IForgeRegistry<Block>) this)::register);
		}
    }
}
