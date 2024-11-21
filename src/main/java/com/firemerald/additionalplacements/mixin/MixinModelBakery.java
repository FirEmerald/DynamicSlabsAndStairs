package com.firemerald.additionalplacements.mixin;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.firemerald.additionalplacements.block.AdditionalPlacementBlock;
import com.firemerald.additionalplacements.client.models.PlacementModelState;
import com.firemerald.additionalplacements.client.models.UnbakedPlacementModel;
import com.firemerald.additionalplacements.client.models.definitions.StateModelDefinition;
import com.firemerald.additionalplacements.generation.GenerationType;
import com.firemerald.additionalplacements.generation.Registration;
import com.firemerald.additionalplacements.util.BlockRotation;

import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.BlockModelShapes;
import net.minecraft.client.renderer.model.IModelTransform;
import net.minecraft.client.renderer.model.IUnbakedModel;
import net.minecraft.client.renderer.model.ModelBakery;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.profiler.IProfiler;
import net.minecraft.util.ResourceLocation;

@Mixin(ModelBakery.class)
public class MixinModelBakery {
	@Shadow
	@Final
	public Map<ResourceLocation, IUnbakedModel> topLevelModels;
	@Shadow
	@Final
    private Map<ResourceLocation, IUnbakedModel> unbakedCache;
	
	@Inject(method = "processLoading", at = @At("RETURN"))
	public void processLoading(IProfiler profiler, int maxMipmapLevel, CallbackInfo cli) {
		Map<IUnbakedModel, ResourceLocation> topLevelModelsReversed = topLevelModels.entrySet().stream().collect(Collectors.toMap(Entry::getValue, Entry::getKey, (o, n) -> o));
		IUnbakedModel missingModel = unbakedCache.get(ModelBakery.MISSING_MODEL_LOCATION);
		//TODO replace with hook in net.minecraft.client.resources.model.BlockStateModelLoader.loadBlockStateDefinitions(ResourceLocation, StateDefinition) line 166 at BlockStateModelLoader.LoadedModel loadedModel = (BlockStateModelLoader.LoadedModel)map2.get(blockState);
		Registration.types().flatMap(GenerationType::created).forEach(entry -> {
			AdditionalPlacementBlock<?> block = entry.newBlock;
			block.getStateDefinition().getPossibleStates().forEach(ourState -> {
				ModelResourceLocation ourModelLocation = BlockModelShapes.stateToModelLocation(ourState);
				if (topLevelModels.get(ourModelLocation) == missingModel) {
					BlockState theirState = block.getModelState(ourState);
					StateModelDefinition modelDefinition = block.getModelDefinition(ourState);
					ResourceLocation ourModel = modelDefinition.location(block.getBaseModelPrefix());
					IModelTransform ourModelRotation = PlacementModelState.by(modelDefinition.xRotation, modelDefinition.yRotation);
					IUnbakedModel theirModel = topLevelModels.getOrDefault(BlockModelShapes.stateToModelLocation(theirState), missingModel);
					BlockRotation theirModelRotation = block.getRotation(ourState);
					UnbakedPlacementModel unbakedModel = UnbakedPlacementModel.of(block, ourModel, ourModelRotation, topLevelModelsReversed.get(theirModel), theirModel, theirModelRotation);
					topLevelModels.put(ourModelLocation, unbakedModel);
					unbakedCache.put(ourModelLocation, unbakedModel);
				}
			});
		});
		UnbakedPlacementModel.clearCache();
	}
	
	@Inject(method = "getSpecialModels", at = @At("RETURN"))
	public void getSpecialModels(CallbackInfoReturnable<Set<ResourceLocation>> cli) {
		Set<ResourceLocation> set = new HashSet<>(cli.getReturnValue());
    	Registration.types()
    	.flatMap(GenerationType::created)
    	.flatMap(entry -> entry.newBlock.allBaseModels())
    	.forEach(set::add);
    	cli.setReturnValue(set);
	}
}
