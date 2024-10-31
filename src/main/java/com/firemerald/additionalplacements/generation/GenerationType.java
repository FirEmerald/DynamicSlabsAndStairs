package com.firemerald.additionalplacements.generation;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.apache.commons.lang3.tuple.Pair;

import com.firemerald.additionalplacements.block.AdditionalPlacementBlock;
import com.firemerald.additionalplacements.config.GenerationBlacklist;
import com.firemerald.additionalplacements.util.MessageTree;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.BooleanValue;

public abstract class GenerationType<T extends Block, U extends AdditionalPlacementBlock<T>> {
	
	protected abstract static class BuilderBase<T extends Block, U extends AdditionalPlacementBlock<T>, V extends GenerationType<T, U>, W extends BuilderBase<T, U, V, W>> {
		protected GenerationBlacklist blacklist = new GenerationBlacklist.Builder().build();
		protected boolean placementEnabled = true;
		
		@SuppressWarnings("unchecked")
		protected W me() {
			return (W) this;
		}
		
		public W blacklist(GenerationBlacklist blacklist) {
			this.blacklist = blacklist;
			return me();
		}
		
		public W placementEnabled() {
			placementEnabled = true;
			return me();
		}
		
		public W placementDisabled() {
			placementEnabled = false;
			return me();
		}
		
		public abstract V construct(ResourceLocation name, String description);
	}
	
	public final ResourceLocation name;
	public final String description;
	private final GenerationBlacklist blacklist;
	private final boolean defaultPlacementEnabled;
	private BooleanValue placementEnabled;
	private List<Pair<ResourceLocation, U>> created = new ArrayList<>();

	protected GenerationType(ResourceLocation name, String description, BuilderBase<T, U, ?, ?> builder) {
		this.name = name;
		this.description = description;
		this.blacklist = builder.blacklist;
		this.defaultPlacementEnabled = builder.placementEnabled;
	}
	
	public boolean placementEnabled() {
		return placementEnabled.get();
	}
	
	//The following method is for the "startup" config, a custom config that loads before block registration and doesn't support re-loading changed values in-game.
	//They should be used for options that affect the dynamic generation of additional placement blocks.
	public void buildStartupConfig(ModConfigSpec.Builder builder) {
		builder
		.comment("Options for controlling which blocks (that are valid for this type) will generate variants of this type")
		.push("enabled");
		blacklist.addToConfig(builder);
		builder.pop();
	}
	
	public void onBootupConfigLoaded() {
		blacklist.loadListsFromConfig();
	}
	
	public void buildCommonConfig(ModConfigSpec.Builder builder) {
		placementEnabled = builder
				.comment("Whether or not to allow for manual placement of the additional placement variants of this block type.")
				.define("enable_placement", defaultPlacementEnabled); //TODO make this also a blacklist
	}
	
	public void onCommonConfigLoaded() {}
	
	public void onCommonConfigReloaded() {
		onCommonConfigLoaded();
	}
	
	public void buildClientConfig(ModConfigSpec.Builder builder) {}
	
	public void onClientConfigLoaded() {}
	
	public void onClientConfigReloaded() {
		onClientConfigLoaded();
	}
	
	public void buildServerConfig(ModConfigSpec.Builder builder) {}
	
	public void onServerConfigLoaded() {}
	
	public void onServerConfigReloaded() {
		onServerConfigLoaded();
	}
	
	/**
	 * Data to check ON SERVER
	 * 
	 * @return
	 */
	public CompoundTag getClientCheckData() {
		return null;
	}

	/**
	 * Check data FROM CLIENT
	 * 
	 * @return
	 */
	public void checkClientData(CompoundTag tag, Consumer<MessageTree> logError) {}

	/**
	 * Data to check ON CLIENT
	 * 
	 * @return
	 */
	public CompoundTag getServerCheckData() {
		return null;
	}

	/**
	 * Check data FROM SERVER
	 * 
	 * @return
	 */
	public void checkServerData(CompoundTag tag, Consumer<MessageTree> logError) {}
	
	public final boolean enabledForBlock(T block, ResourceLocation blockId) {
		return blacklist.test(blockId);
	}
	
	public final void apply(T block, ResourceLocation blockId, BiConsumer<ResourceLocation, U> action) {
		if (enabledForBlock(block, blockId)) {
			U created = construct(block, blockId);
			this.created.add(Pair.of(blockId, created));
			action.accept(new ResourceLocation(name.getNamespace(), blockId.getNamespace() + "." + blockId.getPath()), created);
		}
	}
	
	public abstract U construct(T block, ResourceLocation blockId);
	
	protected void forEachCreated(BiConsumer<? super ResourceLocation, ? super U> action) {
		created.forEach(p -> action.accept(p.getLeft(), p.getRight()));
	}
}
