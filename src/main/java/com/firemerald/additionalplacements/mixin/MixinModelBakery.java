package com.firemerald.additionalplacements.mixin;

import java.util.List;
import java.util.Map;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.firemerald.additionalplacements.block.AdditionalPlacementBlock;
import com.firemerald.additionalplacements.client.models.PlacementModelState;
import com.firemerald.additionalplacements.client.models.UnbakedPlacementModel;
import com.firemerald.additionalplacements.client.models.definitions.StateModelDefinition;
import com.firemerald.additionalplacements.generation.Registration;
import com.firemerald.additionalplacements.util.BlockRotation;

import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.renderer.block.BlockModelShaper;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.resources.model.*;
import net.minecraft.client.resources.model.BlockStateModelLoader.LoadedJson;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.level.block.state.BlockState;

@Mixin(ModelBakery.class)
public class MixinModelBakery {
	@Shadow
	@Final
	public Map<ModelResourceLocation, UnbakedModel> topLevelModels;
	@Shadow
	@Final
	private UnbakedModel missingModel;
	
	@Inject(method = "<init>", at = @At("RETURN"))
	public void init(BlockColors blockColors, ProfilerFiller profilerFiller, Map<ResourceLocation, BlockModel> modelResources, Map<ResourceLocation, List<LoadedJson>> blockStateResources, CallbackInfo cli) {
		//TODO replace with hook in net.minecraft.client.resources.model.BlockStateModelLoader.loadBlockStateDefinitions(ResourceLocation, StateDefinition) line 166 at BlockStateModelLoader.LoadedModel loadedModel = (BlockStateModelLoader.LoadedModel)map2.get(blockState);
		Registration.forEachCreated(entry -> {
			AdditionalPlacementBlock<?> block = entry.newBlock();
			block.getStateDefinition().getPossibleStates().forEach(ourState -> {
				ModelResourceLocation ourModelLocation = BlockModelShaper.stateToModelLocation(ourState);
				if (topLevelModels.get(ourModelLocation) == missingModel) {
					BlockState theirState = block.getModelState(ourState);
					StateModelDefinition modelDefinition = block.getModelDefinition(ourState);
					ResourceLocation ourModel = modelDefinition.location(block.getBaseModelPrefix());
					ModelState ourModelRotation = PlacementModelState.by(modelDefinition.xRotation(), modelDefinition.yRotation());
					UnbakedModel theirModel = topLevelModels.getOrDefault(BlockModelShaper.stateToModelLocation(theirState), missingModel);
					BlockRotation theirModelRotation = block.getRotation(ourState);
					topLevelModels.put(ourModelLocation, UnbakedPlacementModel.of(block, ourModel, ourModelRotation, theirModel, theirModelRotation));
				}
			});
		});
		UnbakedPlacementModel.clearCache();
	}
}
