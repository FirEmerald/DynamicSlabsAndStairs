package com.firemerald.additionalplacements.datagen;

import java.util.stream.Stream;

import com.firemerald.additionalplacements.AdditionalPlacementsMod;
import com.firemerald.additionalplacements.client.models.definitions.CarpetModels;
import com.firemerald.additionalplacements.client.models.definitions.PressurePlateModels;
import com.firemerald.additionalplacements.client.models.definitions.SlabModels;
import com.firemerald.additionalplacements.client.models.definitions.StairModels;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.core.Holder;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;

public class AdditionalPlacementsModelProvider extends ModelProvider {
    public AdditionalPlacementsModelProvider(PackOutput output) {
        super(output, AdditionalPlacementsMod.MOD_ID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
    	new StairModelsGenerator().parentFolder(StairModels.BASE_MODEL_FOLDER)
    	.textures(BetterTextureMapping.SIDE_ALL_TO_COMPLETE).generate(StairModels.SIDE_ALL_MODEL_FOLDER, blockModels)
    	.textures(BetterTextureMapping.PILLAR_TO_COMPLETE).generate(StairModels.COLUMN_MODEL_FOLDER, blockModels);
    	new SlabModelsGenerator().parentFolder(SlabModels.BASE_MODEL_FOLDER)
    	.textures(BetterTextureMapping.SIDE_ALL_TO_COMPLETE).generate(SlabModels.SIDE_ALL_MODEL_FOLDER, blockModels)
    	.textures(BetterTextureMapping.PILLAR_TO_COMPLETE).generate(SlabModels.COLUMN_MODEL_FOLDER, blockModels);
    	new CarpetModelsGenerator().parentFolder(CarpetModels.BASE_MODEL_FOLDER)
    	.textures(BetterTextureMapping.SIDE_ALL_TO_COMPLETE).generate(CarpetModels.SIDE_ALL_MODEL_FOLDER, blockModels)
    	.textures(BetterTextureMapping.PILLAR_TO_COMPLETE).generate(CarpetModels.COLUMN_MODEL_FOLDER, blockModels);
    	new PressurePlateModelsGenerator().parentFolder(PressurePlateModels.BASE_MODEL_FOLDER)
    	.textures(BetterTextureMapping.SIDE_ALL_TO_COMPLETE).generate(PressurePlateModels.SIDE_ALL_MODEL_FOLDER, blockModels)
    	.textures(BetterTextureMapping.PILLAR_TO_COMPLETE).generate(PressurePlateModels.COLUMN_MODEL_FOLDER, blockModels);
    }
    
    @Override
    protected Stream<? extends Holder<Block>> getKnownBlocks() {
    	return Stream.empty();
    }
}
