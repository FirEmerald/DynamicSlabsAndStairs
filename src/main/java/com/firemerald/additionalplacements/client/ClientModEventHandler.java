package com.firemerald.additionalplacements.client;

import com.firemerald.additionalplacements.AdditionalPlacementsMod;
import com.firemerald.additionalplacements.block.AdditionalPlacementBlock;
import com.firemerald.additionalplacements.client.models.*;
import com.firemerald.additionalplacements.generation.Registration;

import me.pepperbell.continuity.client.model.CtmBakedModel;
import me.pepperbell.continuity.client.model.EmissiveBakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.ModelEvent.RegisterAdditional;
import net.neoforged.neoforge.client.event.RegisterClientReloadListenersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import team.chisel.ctm.client.model.AbstractCTMBakedModel;

@EventBusSubscriber(value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
@OnlyIn(Dist.CLIENT)
public class ClientModEventHandler
{
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
    		BakedPlacementModel.clearCache();
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
    
    @SubscribeEvent
    public static void onRegisterAdditionalModels(RegisterAdditional event) {
    	Registration.created()
    	.flatMap(entry -> entry.newBlock().allBaseModels())
    	.map(model -> new ModelResourceLocation(model, ModelResourceLocation.STANDALONE_VARIANT))
    	.forEach(event::register);
    }
}