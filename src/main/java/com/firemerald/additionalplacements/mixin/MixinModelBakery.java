package com.firemerald.additionalplacements.mixin;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.firemerald.additionalplacements.block.AdditionalPlacementBlock;
import com.firemerald.additionalplacements.client.models.PlacementModelState;
import com.firemerald.additionalplacements.client.models.UnbakedPlacementModel;
import com.firemerald.additionalplacements.client.models.definitions.StateModelDefinition;
import com.firemerald.additionalplacements.util.BlockRotation;
import com.mojang.datafixers.util.Pair;

import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.renderer.block.BlockModelShaper;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.resources.model.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;

@Mixin(ModelBakery.class)
public class MixinModelBakery {
	@Shadow
	@Final
	private BlockColors blockColors;
	@Shadow
	public UnbakedModel getModel(ResourceLocation modelLocation) {
		return null;
	}
	
	@Inject(method = "<init>", at = @At("RETURN"))
	public void init(BlockColors blockColors, ProfilerFiller profilerFiller, Map<ResourceLocation, BlockModel> modelResources, Map<ResourceLocation, List<ModelBakery.LoadedJson>> blockStateResources, CallbackInfo cli) {
		UnbakedPlacementModel.clearCache();
	}

	@ModifyVariable(
			method = {"lambda$loadModel$22", "m_119331_"}, 
			at = @At("STORE"), 
			index = 7
			)
	private Pair<UnbakedModel, Supplier<ModelBakery.ModelGroupKey>> loadModelLambda(
			Pair<UnbakedModel, Supplier<ModelBakery.ModelGroupKey>> modelPair, 
			Map<ModelResourceLocation, BlockState> modelToState, 
			ResourceLocation blockId, 
			Pair<UnbakedModel, Supplier<ModelBakery.ModelGroupKey>> missingModelPair, 
			Map<ModelBakery.ModelGroupKey, Set<BlockState>> modelGroups, 
			ModelResourceLocation currentModelLocation, 
			BlockState ourState) {
		if (modelPair == null) { //replace only states which do not already have a model
			if (ourState != null && ourState.getBlock() instanceof AdditionalPlacementBlock) {
				AdditionalPlacementBlock<?> block = (AdditionalPlacementBlock<?>) ourState.getBlock();

				BlockState theirState = block.getModelState(ourState);
				StateModelDefinition modelDefinition = block.getModelDefinition(ourState);
				ResourceLocation ourModel = modelDefinition.location(block.getBaseModelPrefix());
				ModelState ourModelRotation = PlacementModelState.by(modelDefinition.xRotation(), modelDefinition.yRotation());
				ModelResourceLocation theirModelLocation = BlockModelShaper.stateToModelLocation(theirState);
				UnbakedModel theirModel = getModel(theirModelLocation);
				BlockRotation theirModelRotation = block.getRotation(ourState);
				UnbakedPlacementModel unbakedModel = UnbakedPlacementModel.of(block, ourModel, ourModelRotation, theirModelLocation, theirModel, theirModelRotation);
				
				List<Property<?>> coloringProperties = this.blockColors.getColoringProperties(theirState.getBlock()).stream()
						.filter(block::isValidProperty) //just in case
						.collect(Collectors.toList());
	            return Pair.of(unbakedModel, () -> ModelBakery.ModelGroupKey.create(ourState, unbakedModel, coloringProperties));
			}
		}
		return modelPair;
	}
}
