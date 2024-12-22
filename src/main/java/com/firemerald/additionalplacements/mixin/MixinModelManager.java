package com.firemerald.additionalplacements.mixin;

import java.util.Map;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.firemerald.additionalplacements.block.AdditionalPlacementBlock;
import com.firemerald.additionalplacements.client.models.PlacementModelState;
import com.firemerald.additionalplacements.client.models.UnbakedPlacementModel;
import com.firemerald.additionalplacements.client.models.definitions.StateModelDefinition;
import com.firemerald.additionalplacements.generation.Registration;
import com.firemerald.additionalplacements.util.BlockRotation;

import net.minecraft.client.renderer.block.BlockModelShaper;
import net.minecraft.client.renderer.block.model.UnbakedBlockStateModel;
import net.minecraft.client.resources.model.*;
import net.minecraft.client.resources.model.BlockStateModelLoader.LoadedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;

@Mixin(ModelManager.class)
public class MixinModelManager {
	@Inject(
			method = "discoverModelDependencies", 
			at = @At("HEAD"),
			require = 1
			)
	private static void discoverModelDependencies(UnbakedModel missingModel, Map<ResourceLocation, UnbakedModel> unbakedModels, BlockStateModelLoader.LoadedModels loadedModels, ClientItemInfoLoader.LoadedClientInfos loadedClientInfos, CallbackInfoReturnable<ModelDiscovery> cli) {
		Map<ModelResourceLocation, LoadedModel> models = loadedModels.models();
		Registration.forEachCreated(entry -> {
			AdditionalPlacementBlock<?> block = entry.newBlock();
			block.getStateDefinition().getPossibleStates().forEach(ourState -> {
				ModelResourceLocation ourModelLocation = BlockModelShaper.stateToModelLocation(ourState);
				if (!models.containsKey(ourModelLocation) || models.get(ourModelLocation).model() == missingModel) {
					BlockState theirState = block.getModelState(ourState);
					StateModelDefinition modelDefinition = block.getModelDefinition(ourState);
					ResourceLocation ourModel = modelDefinition.location(block.getBaseModelPrefix());
					ModelState ourModelRotation = PlacementModelState.by(modelDefinition.xRotation(), modelDefinition.yRotation());
					ModelResourceLocation theirModelLocation = BlockModelShaper.stateToModelLocation(theirState);
					if (models.containsKey(theirModelLocation)) {
						UnbakedBlockStateModel theirModel = models.get(theirModelLocation).model();
						BlockRotation theirModelRotation = block.getRotation(ourState);
						models.put(ourModelLocation, 
								new BlockStateModelLoader.LoadedModel(
										ourState,
										UnbakedPlacementModel.of(block, ourModel, ourModelRotation, theirModel, theirModelRotation)
										)
								);
					}
				}
			});
		});
		UnbakedPlacementModel.clearCache();
	}
}
