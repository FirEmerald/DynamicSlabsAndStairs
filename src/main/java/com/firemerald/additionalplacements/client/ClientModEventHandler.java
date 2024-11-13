package com.firemerald.additionalplacements.client;

import com.firemerald.additionalplacements.AdditionalPlacementsMod;
import com.firemerald.additionalplacements.block.AdditionalPlacementBlock;
import com.firemerald.additionalplacements.client.models.BlockModelCache;
import com.firemerald.additionalplacements.client.models.dynamic.DynamicModelLoader;
import com.firemerald.additionalplacements.client.models.fixed.FixedModelLoader;
import com.firemerald.additionalplacements.client.resources.APDynamicResources;

import me.pepperbell.continuity.client.model.CtmBakedModel;
import me.pepperbell.continuity.client.model.EmissiveBakedModel;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ModelEvent.RegisterGeometryLoaders;
import net.minecraftforge.client.event.RegisterClientReloadListenersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
@OnlyIn(Dist.CLIENT)
public class ClientModEventHandler
{
	public static final Pack GENERATED_RESOURCES_PACK = Pack.create(
			"Additional Placements dynamic resources",
			Component.literal("title"),
			true,
			unknown -> new APDynamicResources(),
			new Pack.Info(Component.literal("description"), 9, 8, FeatureFlagSet.of(), true),
			PackType.CLIENT_RESOURCES,
			Pack.Position.BOTTOM,
			true,
			PackSource.BUILT_IN
			);

	@SubscribeEvent
	public static void onAddPackFinders(AddPackFindersEvent event)
	{
		if (event.getPackType() == PackType.CLIENT_RESOURCES) event.addRepositorySource(addPack -> addPack.accept(GENERATED_RESOURCES_PACK));
	}

	@SubscribeEvent
	public static void onModelRegistryEvent(RegisterGeometryLoaders event)
	{
		event.register(FixedModelLoader.ID.getPath(), new FixedModelLoader());
		event.register(DynamicModelLoader.ID.getPath(), new DynamicModelLoader());
	}

    @SubscribeEvent(priority = EventPriority.LOWEST)
	public static void onRegisterBlockColorHandlers(RegisterColorHandlersEvent.Block event)
    {
		event.register(new AdditionalBlockColor(), ForgeRegistries.BLOCKS.getValues().stream().filter(block -> block instanceof AdditionalPlacementBlock && !((AdditionalPlacementBlock<?>) block).hasCustomColors()).toArray(Block[]::new));
    }

    @SubscribeEvent
    public static void onRegisterKeyMappings(RegisterKeyMappingsEvent event)
    {
    	event.register(APClientData.AP_PLACEMENT_KEY);
    }
    
    @SubscribeEvent
    public static void onRegisterClientReloadListeners(RegisterClientReloadListenersEvent event) {
    	event.registerReloadListener((ResourceManagerReloadListener) resourceManager -> {
    		BlockModelCache.clearCache();
    	});
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
    	if (ModList.get().isLoaded("continuity")) {
    		AdditionalPlacementsMod.LOGGER.info("Continuity detected, registering continuity BakedModel unwrappers");
    		BlockModelCache.registerUnwrapper(model -> {
    			if (model instanceof CtmBakedModel ctm) return ctm.getWrappedModel();
    			else if (model instanceof EmissiveBakedModel emm) return emm.getWrappedModel();
    			else return null;
    		});
    	}
    }
}