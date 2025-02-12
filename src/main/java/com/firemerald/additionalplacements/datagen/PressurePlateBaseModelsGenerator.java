package com.firemerald.additionalplacements.datagen;

import com.firemerald.additionalplacements.block.AdditionalBasePressurePlateBlock;
import com.firemerald.additionalplacements.client.models.definitions.*;

import net.minecraft.world.level.block.BasePressurePlateBlock;

public abstract class PressurePlateBaseModelsGenerator<T extends BasePressurePlateBlock, U extends AdditionalBasePressurePlateBlock<T>, V extends PressurePlateBaseModelsGenerator<T, U, V>> extends SimpleModelsGenerator<T, U, V> {
	@Override
	public String[] modelPaths() {
		return PressurePlateModels.MODELS;
	}
}
