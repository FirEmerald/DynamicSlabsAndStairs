package com.firemerald.additionalplacements.client;

import java.util.Collections;
import java.util.Optional;

import com.firemerald.additionalplacements.AdditionalPlacementsMod;
import com.firemerald.additionalplacements.block.AdditionalPlacementBlock;
import com.firemerald.additionalplacements.client.models.BakedRetexturedBlockModel;
import com.firemerald.additionalplacements.client.models.BakedRotatedBlockModel;
import com.firemerald.additionalplacements.client.models.PlacementBlockModelLoader;

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
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraft.server.packs.repository.PackCompatibility;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ModelEvent.RegisterGeometryLoaders;
import net.neoforged.neoforge.client.event.RegisterClientReloadListenersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.event.AddPackFindersEvent;

@EventBusSubscriber(value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
@OnlyIn(Dist.CLIENT)
public class ClientModEventHandler
{
	//create(String p_252257_, Component p_248717_, boolean p_248811_, Pack.ResourcesSupplier p_248969_, Pack.Info p_251314_, Pack.Position p_252110_, boolean p_250237_, PackSource p_248524_) {
	//public Info(Component description, PackCompatibility compatibility, FeatureFlagSet requestedFeatures, List<String> overlays)
	public static final Pack GENERATED_RESOURCES_PACK = new Pack(
			new PackLocationInfo(
					"Additional Placements blockstate redirection pack", 
					Component.literal("title"), 
					PackSource.BUILT_IN, 
					Optional.of(new KnownPack(
							AdditionalPlacementsMod.MOD_ID, 
							"Additional Placements blockstate redirection pack", 
							SharedConstants.getCurrentVersion().getId()))),
			new ResourcesSupplier() {
				@Override
				public PackResources openPrimary(PackLocationInfo info) {
					return new BlockstatesPackResources(info);
				}

				@Override
				public PackResources openFull(PackLocationInfo info, Metadata meta) {
					return new BlockstatesPackResources(info);
				}
			},
			new Metadata(
					Component.literal("Additional Placements blockstate redirection pack"),
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
	private static PlacementBlockModelLoader loader;

	@SubscribeEvent
	public static void onAddPackFinders(AddPackFindersEvent event)
	{
		if (event.getPackType() == PackType.CLIENT_RESOURCES) event.addRepositorySource(addPack -> addPack.accept(GENERATED_RESOURCES_PACK));
	}

	@SubscribeEvent
	public static void onModelRegistryEvent(RegisterGeometryLoaders event)
	{
		loader = new PlacementBlockModelLoader();
		event.register(PlacementBlockModelLoader.ID, loader);
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
    		BakedRetexturedBlockModel.clearCache();
    		BakedRotatedBlockModel.clearCache();
    	});
    }
}