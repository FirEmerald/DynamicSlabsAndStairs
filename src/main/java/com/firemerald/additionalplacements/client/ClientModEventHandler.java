package com.firemerald.additionalplacements.client;

import java.util.Collections;
import java.util.Optional;

import com.firemerald.additionalplacements.AdditionalPlacementsMod;
import com.firemerald.additionalplacements.block.AdditionalPlacementBlock;
import com.firemerald.additionalplacements.client.models.*;
import com.firemerald.additionalplacements.client.models.dynamic.DynamicModelLoader;
import com.firemerald.additionalplacements.client.models.fixed.FixedModelLoader;
import com.firemerald.additionalplacements.client.models.fixed.UnbakedFixedModel;
import com.firemerald.additionalplacements.client.resources.APDynamicResources;

import me.pepperbell.continuity.client.model.CtmBakedModel;
import me.pepperbell.continuity.client.model.EmissiveBakedModel;
import net.minecraft.SharedConstants;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackSelectionConfig;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.KnownPack;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.Pack.Metadata;
import net.minecraft.server.packs.repository.Pack.Position;
import net.minecraft.server.packs.repository.Pack.ResourcesSupplier;
import net.minecraft.server.packs.repository.PackCompatibility;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.ModelEvent.RegisterGeometryLoaders;
import net.neoforged.neoforge.client.event.RegisterClientReloadListenersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.event.AddPackFindersEvent;
import team.chisel.ctm.client.model.AbstractCTMBakedModel;

@EventBusSubscriber(value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
@OnlyIn(Dist.CLIENT)
public class ClientModEventHandler
{
	public static final Pack GENERATED_RESOURCES_PACK = new Pack(
			new PackLocationInfo(
					"Additional Placements dynamic resources",
					Component.literal("title"), 
					PackSource.BUILT_IN, 
					Optional.of(new KnownPack(
							AdditionalPlacementsMod.MOD_ID, 
							"Additional Placements dynamic resources", 
							SharedConstants.getCurrentVersion().getId()))),
			new ResourcesSupplier() {
				@Override
				public PackResources openPrimary(PackLocationInfo info) {
					return new APDynamicResources(info);
				}

				@Override
				public PackResources openFull(PackLocationInfo info, Metadata meta) {
					return new APDynamicResources(info);
				}
			},
			new Metadata(
					Component.literal("Additional Placements dynamic resources"),
					PackCompatibility.COMPATIBLE,
					FeatureFlagSet.of(),
					Collections.emptyList(),
					true
					),
			new PackSelectionConfig(
					true,
					Position.BOTTOM,
					true
					)
			);

	@SubscribeEvent
	public static void onAddPackFinders(AddPackFindersEvent event)
	{
		if (event.getPackType() == PackType.CLIENT_RESOURCES) event.addRepositorySource(addPack -> addPack.accept(GENERATED_RESOURCES_PACK));
	}

	@SubscribeEvent
	public static void onModelRegistryEvent(RegisterGeometryLoaders event)
	{
		event.register(FixedModelLoader.ID, new FixedModelLoader());
		event.register(DynamicModelLoader.ID, new DynamicModelLoader());
	}

    @SubscribeEvent(priority = EventPriority.LOWEST)
	public static void onRegisterBlockColorHandlers(RegisterColorHandlersEvent.Block event)
    {
		event.register(new AdditionalBlockColor(), BuiltInRegistries.BLOCK.stream().filter(block -> block instanceof AdditionalPlacementBlock && !((AdditionalPlacementBlock<?>) block).hasCustomColors()).toArray(Block[]::new));
    }

    @SubscribeEvent
    public static void onRegisterKeyMappings(RegisterKeyMappingsEvent event)
    {
    	event.register(APClientData.AP_PLACEMENT_KEY);
    }
    
    @SubscribeEvent
    public static void onRegisterClientReloadListeners(RegisterClientReloadListenersEvent event) {
    	event.registerReloadListener((ResourceManagerReloadListener) resourceManager -> {
    		UnbakedFixedModel.clearCache();
    	});
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
    	if (ModList.get().isLoaded("continuity")) {
    		AdditionalPlacementsMod.LOGGER.info("Continuity detected, registering continuity BakedModel unwrappers");
    		Unwrapper.registerUnwrapper(model -> {
    			if (model instanceof CtmBakedModel ctm) return ctm.getWrappedModel();
    			else if (model instanceof EmissiveBakedModel emm) return emm.getWrappedModel();
    			else return null;
    		});
    	}
    	if (ModList.get().isLoaded("ctm")) {
    		AdditionalPlacementsMod.LOGGER.info("Connected Textures Mod (ctm) detected, registering ctm BakedModel unwrappers");
    		Unwrapper.registerUnwrapper(model -> {
    			if (model instanceof AbstractCTMBakedModel ctm) return ctm.getParent();
    			else return null;
    		});
    	}
    }
}