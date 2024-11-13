package com.firemerald.additionalplacements.client;

import com.firemerald.additionalplacements.block.AdditionalPlacementBlock;
import com.firemerald.additionalplacements.client.models.dynamic.DynamicModelLoader;
import com.firemerald.additionalplacements.client.models.fixed.FixedModelLoader;
import com.firemerald.additionalplacements.client.resources.APDynamicResources;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.resources.IPackNameDecorator;
import net.minecraft.resources.PackCompatibility;
import net.minecraft.resources.ResourcePackInfo;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
@OnlyIn(Dist.CLIENT)
public class ClientModEventHandler
{
	public static final ResourcePackInfo GENERATED_RESOURCES_PACK = new ResourcePackInfo(
			"Additional Placements blockstate redirection pack",
			true,
			APDynamicResources::new,
			new StringTextComponent("title"),
			new StringTextComponent("description"),
			PackCompatibility.COMPATIBLE,
			ResourcePackInfo.Priority.BOTTOM,
			true,
			IPackNameDecorator.BUILT_IN,
			true
			);

	@SubscribeEvent
	public static void onModelRegistryEvent(ModelRegistryEvent event)
	{
		ModelLoaderRegistry.registerLoader(FixedModelLoader.ID, new FixedModelLoader());
		ModelLoaderRegistry.registerLoader(DynamicModelLoader.ID, new DynamicModelLoader());
	}

	@SubscribeEvent
	public static void onConstructModEvent(FMLConstructModEvent event)
	{
		Minecraft mc = Minecraft.getInstance();
		if (mc != null) mc.getResourcePackRepository().addPackFinder((addPack, buildPack) -> addPack.accept(GENERATED_RESOURCES_PACK));
	}

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void init(FMLClientSetupEvent event) {
    	ForgeRegistries.BLOCKS.forEach(block -> {
    		if (block instanceof AdditionalPlacementBlock)
    		{
				BlockState otherState = ((AdditionalPlacementBlock<?>) block).getOtherBlockState();
    			RenderTypeLookup.setRenderLayer(block, (layer) -> RenderTypeLookup.canRenderInLayer(otherState, layer));
    		}
    	});
    	Minecraft.getInstance().getBlockColors().register(new AdditionalBlockColor(), ForgeRegistries.BLOCKS.getValues().stream().filter(block -> block instanceof AdditionalPlacementBlock && !((AdditionalPlacementBlock<?>) block).hasCustomColors()).toArray(Block[]::new));
    	ClientRegistry.registerKeyBinding(APClientData.AP_PLACEMENT_KEY);
    }
}