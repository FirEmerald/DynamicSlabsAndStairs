package com.firemerald.additionalplacements.mixin;

import java.util.Map;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.firemerald.additionalplacements.block.AdditionalPlacementBlock;
import com.firemerald.additionalplacements.client.models.PlacementModelState;
import com.firemerald.additionalplacements.client.models.UnbakedPlacementModel;
import com.firemerald.additionalplacements.client.models.definitions.StateModelDefinition;
import com.firemerald.additionalplacements.generation.GenerationType;
import com.firemerald.additionalplacements.generation.Registration;
import com.firemerald.additionalplacements.util.BlockRotation;

import net.minecraft.client.renderer.block.BlockModelShaper;
import net.minecraft.client.resources.model.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;

@Mixin(ModelBakery.class)
public class MixinModelBakery {
	@Inject(method = "<init>", at = @At("RETURN"))
	public void init(Map<ModelResourceLocation, UnbakedModel> topLevelModels, Map<ResourceLocation, UnbakedModel> unbakedModels, UnbakedModel missingModel, CallbackInfo cli) {
		Registration.types().flatMap(GenerationType::created).forEach(entry -> {
			AdditionalPlacementBlock<?> block = entry.newBlock();
			block.getStateDefinition().getPossibleStates().forEach(ourState -> {
				ModelResourceLocation ourModelLocation = BlockModelShaper.stateToModelLocation(ourState);
				if (!topLevelModels.containsKey(ourModelLocation)) {
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
