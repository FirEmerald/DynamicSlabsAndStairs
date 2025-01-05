package com.firemerald.additionalplacements.common;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import com.firemerald.additionalplacements.AdditionalPlacementsMod;
import com.firemerald.additionalplacements.config.APConfigs;
import com.firemerald.additionalplacements.datagen.ModelGenerator;
import com.firemerald.additionalplacements.generation.Registration;
import com.firemerald.additionalplacements.network.APNetwork;
import com.firemerald.additionalplacements.network.CheckDataConfigurationTask;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.network.event.RegisterConfigurationTasksEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.registries.NewRegistryEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class CommonModEventHandler
{
	private static boolean init = false;

	@SubscribeEvent
	public static void onNewRegistry(NewRegistryEvent event) { //best hook I could find for loading a config after all mods have been processed but before registries are built
		if (!init) {
			Registration.registerTypes();
			APConfigs.init();
			init = true;
		}
	}

	@SubscribeEvent
	public static void onBlockRegistry(RegisterEvent event)
	{
		if (event.getRegistry() == BuiltInRegistries.BLOCK)
		{
			List<Pair<ResourceKey<Block>, Block>> created = new ArrayList<>();
			BuiltInRegistries.BLOCK.entrySet().forEach(entry -> {
				ResourceLocation name = entry.getKey().location();
				Block block = entry.getValue();
				Registration.tryApply(block, name, (key, obj) -> created.add(Pair.of(key, obj)));
			});
			created.forEach(pair -> Registry.register(BuiltInRegistries.BLOCK, pair.getLeft(), pair.getRight()));
			AdditionalPlacementsMod.dynamicRegistration = true;
		}
	}

	@SubscribeEvent
	public static void onGatherClientData(GatherDataEvent.Client event)
	{
		event.getGenerator().addProvider(true, (DataProvider.Factory<ModelGenerator>) (PackOutput pack) -> new ModelGenerator(pack, AdditionalPlacementsMod.MOD_ID, event.getExistingFileHelper()));
	}

	public static boolean doubleslabsLoaded;

	@SubscribeEvent
	public static void onFMLCommonSetup(FMLCommonSetupEvent event)
	{
		doubleslabsLoaded = ModList.get().isLoaded("doubleslabs");
	}

	@SubscribeEvent
    public static void register(RegisterPayloadHandlersEvent event)
    {
		APNetwork.register(event);
    }

	@SubscribeEvent
	public static void onRegisterConfigurationTasks(RegisterConfigurationTasksEvent event) {
		event.register(new CheckDataConfigurationTask());
	}
}