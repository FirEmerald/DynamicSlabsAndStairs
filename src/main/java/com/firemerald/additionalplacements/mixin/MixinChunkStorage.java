package com.firemerald.additionalplacements.mixin;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.firemerald.additionalplacements.block.interfaces.IStateFixer;
import com.mojang.serialization.Codec;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.chunk.storage.ChunkStorage;
import net.minecraft.world.level.storage.DimensionDataStorage;
import net.minecraftforge.registries.ForgeRegistries;

@Mixin(ChunkStorage.class)
public class MixinChunkStorage {
	@Inject(method = "upgradeChunkTag", at = @At("RETURN"), cancellable = true)
	public <T extends Block> void upgradeChunkTag(ResourceKey<Level> pLevelKey, Supplier<DimensionDataStorage> pStorage, CompoundTag pChunkData, Optional<ResourceKey<Codec<? extends ChunkGenerator>>> pChunkGeneratorKey, CallbackInfoReturnable<CompoundTag> cli) {
		CompoundTag chunkData = cli.getReturnValue();
		if (chunkData != null) {
			ifListNotEmpty(chunkData, "sections", Tag.TAG_COMPOUND, sections -> {
				sections.forEach(section -> {
					ifCompoundNotEmpty((CompoundTag) section, "block_states", blockStates -> {
						ifListNotEmpty(blockStates, "palette", Tag.TAG_COMPOUND, palette -> {
							palette.forEach(blockTag -> {
								CompoundTag block = (CompoundTag) blockTag;
								if (block.contains("Name", Tag.TAG_STRING)) {
									String name = block.getString("Name");
									@SuppressWarnings("unchecked")
									T theBlock = (T) ForgeRegistries.BLOCKS.getValue(new ResourceLocation(name));
									if (theBlock instanceof IStateFixer fixer) {
										CompoundTag original = block.getCompound("Properties");
										CompoundTag fixed = fixer.fix(original, newBlock -> {
											block.put("Name", StringTag.valueOf(ForgeRegistries.BLOCKS.getResourceKey(newBlock).get().location().toString()));
										});
										if (original != fixed) {
											if (fixed == null) block.remove("Properties");
											else block.put("Properties", fixed);
										}
									}
								}
							});
						});
					});
				});
			});
		}
	}
	
	private static void ifCompoundNotEmpty(CompoundTag tag, String key, Consumer<CompoundTag> action) {
		if (tag.contains(key, Tag.TAG_COMPOUND)) {
			CompoundTag tag2 = tag.getCompound(key);
			if (!tag2.isEmpty()) action.accept(tag2);
		}
	}
	
	private static void ifListNotEmpty(CompoundTag tag, String key, int listTagType, Consumer<ListTag> action) {
		if (tag.contains(key, Tag.TAG_LIST)) {
			ListTag list = tag.getList(key, listTagType);
			if (!list.isEmpty()) action.accept(list);
		}
	}
}
