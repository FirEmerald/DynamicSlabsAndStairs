package com.firemerald.additionalplacements.generation;

import com.firemerald.additionalplacements.block.AdditionalPlacementBlock;
import com.firemerald.additionalplacements.block.interfaces.ISimpleRotationBlock;
import com.firemerald.additionalplacements.config.APConfigs;
import com.firemerald.additionalplacements.config.BlockBlacklist;

import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;

public class SimpleRotatableGenerationType<T extends Block, U extends AdditionalPlacementBlock<T> & ISimpleRotationBlock> extends SimpleGenerationType<T, U> {
	protected abstract static class BuilderBase<T extends Block, U extends AdditionalPlacementBlock<T> & ISimpleRotationBlock, V extends SimpleRotatableGenerationType<T, U>, W extends BuilderBase<T, U, V, W>> extends SimpleGenerationType.BuilderBase<T, U, V, W> {
		protected BlockBlacklist 
		logicRotationBlacklist = new BlockBlacklist.Builder().build(),
		textureRotationBlacklist = new BlockBlacklist.Builder().build(),
		modelRotationBlacklist = new BlockBlacklist.Builder().build();
		
		public W blacklistLogicRotation(BlockBlacklist blacklist) {
			this.logicRotationBlacklist = blacklist;
			return me();
		}
		
		public W blacklistTextureRotation(BlockBlacklist blacklist) {
			this.textureRotationBlacklist = blacklist;
			return me();
		}
		
		public W blacklistModelRotation(BlockBlacklist blacklist) {
			this.modelRotationBlacklist = blacklist;
			return me();
		}
	}
	
	public static class Builder<T extends Block, U extends AdditionalPlacementBlock<T> & ISimpleRotationBlock> extends BuilderBase<T, U, SimpleRotatableGenerationType<T, U>, Builder<T, U>> {
		@Override
		public SimpleRotatableGenerationType<T, U> construct(ResourceLocation name, String description) {
			return new SimpleRotatableGenerationType<>(name, description, this);
		}
	}
	
	private final BlockBlacklist logicRotationBlackist, textureRotationBlacklist, modelRotationBlacklist;
	
	protected SimpleRotatableGenerationType(ResourceLocation name, String description, BuilderBase<T, U, ?, ?> builder) {
		super(name, description, builder);
		this.logicRotationBlackist = builder.logicRotationBlacklist;
		this.textureRotationBlacklist = builder.textureRotationBlacklist;
		this.modelRotationBlacklist = builder.modelRotationBlacklist;
	}

	@Override
	public void buildServerConfig(ForgeConfigSpec.Builder builder) {
		super.buildServerConfig(builder);
		builder
		.comment("Options to control which blocks will use \"rotated logic\" of their original blocks. Mainly affects bounding boxes.")
		.push("rotated_logic");
		logicRotationBlackist.addToConfig(builder);
		builder.pop();
	}

	@Override
	public void onServerConfigLoaded() {
		super.onServerConfigLoaded();
		logicRotationBlackist.loadListsFromConfig();
		updateLogicSettings();
	}

	@Override
	public void buildClientConfig(ForgeConfigSpec.Builder builder) {
		super.buildClientConfig(builder);
		builder
		.comment("Options to control which blocks will rotate the textures of their original blocks.")
		.push("rotated_textures");
		textureRotationBlacklist.addToConfig(builder);
		builder.pop();
		builder
		.comment("Options to control which blocks will use \"rotated models\" of their original blocks.")
		.push("rotated_models");
		modelRotationBlacklist.addToConfig(builder);
		builder.pop();
	}

	@Override
	public void onClientConfigLoaded() {
		super.onClientConfigLoaded();
		textureRotationBlacklist.loadListsFromConfig();
		modelRotationBlacklist.loadListsFromConfig();
		updateModelSettings();
	}

	@Override
	public void onTagsUpdated() {
		if (APConfigs.clientLoaded()) updateModelSettings();
		if (APConfigs.serverLoaded()) updateLogicSettings();
	}
	
	public void updateModelSettings() {
		forEachCreated((id, block) -> block.setModelRotation(textureRotationBlacklist.test(id, block), modelRotationBlacklist.test(id, block)));
	}
	
	public void updateLogicSettings() {
		forEachCreated((id, block) -> block.setLogicRotation(logicRotationBlackist.test(id, block)));
	}
}
