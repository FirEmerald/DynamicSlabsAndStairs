package com.firemerald.additionalplacements.common;

import java.util.ArrayList;
import java.util.List;

import com.firemerald.additionalplacements.AdditionalPlacementsMod;
import com.firemerald.additionalplacements.config.APConfigs;
import com.firemerald.additionalplacements.datagen.ModelGenerator;
import com.firemerald.additionalplacements.generation.Registration;

import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.RegistryEvent.NewRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonModEventHandler
{
	public static boolean lockGenerationTypeRegistry = false;
	
	@SubscribeEvent
	public static void onNewRegistry(NewRegistry event) { //best hook I could find for loading a config after all mods have been processed but before registries are built
		if (!lockGenerationTypeRegistry) {
			lockGenerationTypeRegistry = true;
			APConfigs.init();
			APConfigs.STARTUP.loadConfig(FMLPaths.CONFIGDIR.get().resolve("additionalplacements-startup.toml"), APConfigs.STARTUP_SPEC);
		}
	}
	
	@SubscribeEvent
	public static void onBlockRegistry(RegistryEvent.Register<Block> event)
	{
		IForgeRegistry<Block> registry = event.getRegistry();
		List<Block> created = new ArrayList<>();
		registry.getValues().forEach(block -> Registration.tryApply(block, block.getRegistryName(), (id, obj) -> created.add(obj)));
		created.forEach(registry::register);
		AdditionalPlacementsMod.dynamicRegistration = true;
	}

	@SubscribeEvent
	public static void onGatherData(GatherDataEvent event)
	{
		if (event.includeClient())
		{
			event.getGenerator().addProvider(new ModelGenerator(event.getGenerator(), AdditionalPlacementsMod.MOD_ID, event.getExistingFileHelper()));
		}
	}

	public static boolean doubleslabsLoaded;

	@SubscribeEvent
	public static void onFMLCommonSetup(FMLCommonSetupEvent event)
	{
		doubleslabsLoaded = ModList.get().isLoaded("doubleslabs");
	}
}