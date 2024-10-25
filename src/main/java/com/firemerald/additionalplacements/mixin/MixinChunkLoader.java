package com.firemerald.additionalplacements.mixin;

import java.util.function.Function;
import java.util.function.Supplier;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.firemerald.additionalplacements.util.StateFixer;
import com.firemerald.additionalplacements.util.TagIds;

import net.minecraft.block.Block;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.chunk.storage.ChunkLoader;
import net.minecraft.world.storage.DimensionSavedDataManager;
import net.minecraftforge.registries.ForgeRegistries;

@Mixin(ChunkLoader.class)
public class MixinChunkLoader {
	
	@Inject(method = "upgradeChunkTag", at = @At("RETURN"), cancellable = true)
	public void upgradeChunkTag(RegistryKey<World> pLevelKey, Supplier<DimensionSavedDataManager> pStorage, CompoundNBT pChunkData, CallbackInfoReturnable<CompoundNBT> cli) {
		CompoundNBT chunkData = cli.getReturnValue();
		if (chunkData.contains("sections", TagIds.TAG_LIST)) {
			ListNBT sections = chunkData.getList("sections", TagIds.TAG_COMPOUND);
			sections.forEach(sectionTag -> {
				CompoundNBT section = (CompoundNBT) sectionTag;
				if (section.contains("block_states", TagIds.TAG_COMPOUND)) {
					CompoundNBT blockStates = section.getCompound("block_states");
					if (blockStates.contains("palette", TagIds.TAG_LIST)) {
						ListNBT palette = blockStates.getList("palette", TagIds.TAG_COMPOUND);
						palette.forEach(blockTag -> {
							CompoundNBT block = (CompoundNBT) blockTag;
							if (block.contains("Name", TagIds.TAG_STRING)) {
								String name = block.getString("Name");
								Block theBlock = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(name));
								if (theBlock != null) {
									Function<CompoundNBT, CompoundNBT> fixer = StateFixer.getFixer(theBlock.getClass());
									if (fixer != null) {
										CompoundNBT original = block.getCompound("Properties");
										CompoundNBT fixed = fixer.apply(original);
										if (original != fixed) {
											if (fixed == null) block.remove("Properties");
											else block.put("Properties", fixed);
										}
									}
								}
							}
						});
					}
				}
			});
		}
		
	}
}
