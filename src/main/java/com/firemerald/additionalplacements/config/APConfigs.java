package com.firemerald.additionalplacements.config;

import java.util.function.Consumer;

import org.apache.commons.lang3.tuple.Pair;

import com.firemerald.additionalplacements.AdditionalPlacementsMod;
import com.firemerald.additionalplacements.generation.GenerationType;
import com.firemerald.additionalplacements.generation.Registration;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraftforge.api.ModLoadingContext;
import net.minecraftforge.api.fml.event.config.ModConfigEvents;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;

public class APConfigs {
    private static StartupConfig startup;
    private static ForgeConfigSpec startupSpec;
    private static CommonConfig common;
    private static ForgeConfigSpec commonSpec;
    private static ServerConfig server;
    private static ForgeConfigSpec serverSpec;
    private static ClientConfig client;
    private static ForgeConfigSpec clientSpec;
    
    public static void init() {
    	ModConfigEvents.loading(AdditionalPlacementsMod.MOD_ID).register(APConfigs::onModConfigLoaded);
    	ModConfigEvents.reloading(AdditionalPlacementsMod.MOD_ID).register(APConfigs::onModConfigReloaded);
        final Pair<StartupConfig, ForgeConfigSpec> startupSpecPair = new ForgeConfigSpec.Builder().configure(StartupConfig::new);
        startup = startupSpecPair.getLeft();
        startupSpec = startupSpecPair.getRight();
		startup.loadConfig(FabricLoader.getInstance().getConfigDir().resolve("additionalplacements-startup.toml"), startupSpec);
        final Pair<CommonConfig, ForgeConfigSpec> commonSpecPair = new ForgeConfigSpec.Builder().configure(CommonConfig::new);
        common = commonSpecPair.getLeft();
        ModLoadingContext.registerConfig(AdditionalPlacementsMod.MOD_ID, ModConfig.Type.COMMON, commonSpec = commonSpecPair.getRight());
        final Pair<ServerConfig, ForgeConfigSpec> serverSpecPair = new ForgeConfigSpec.Builder().configure(ServerConfig::new);
        server = serverSpecPair.getLeft();
        ModLoadingContext.registerConfig(AdditionalPlacementsMod.MOD_ID, ModConfig.Type.SERVER, serverSpec = serverSpecPair.getRight());
        final Pair<ClientConfig, ForgeConfigSpec> clientSpecPair = new ForgeConfigSpec.Builder().configure(ClientConfig::new);
        client = clientSpecPair.getLeft();
        ModLoadingContext.registerConfig(AdditionalPlacementsMod.MOD_ID, ModConfig.Type.CLIENT, clientSpec = clientSpecPair.getRight());
    }
    
    public static StartupConfig startup() {
    	return startup;
    }
    
    public static boolean startupLoaded() {
    	return startupSpec.isLoaded();
    }
    
    public static CommonConfig common() {
    	return common;
    }
    
    public static boolean commonLoaded() {
    	return commonSpec.isLoaded();
    }
    
    public static ServerConfig server() {
    	return server;
    }
    
    public static boolean serverLoaded() {
    	return serverSpec.isLoaded();
    }
    
    public static ClientConfig client() {
    	return client;
    }
    
    public static boolean clientLoaded() {
    	return clientSpec.isLoaded();
    }
    
    private static void onModConfigLoaded(ModConfig config) {
    	if (config.getSpec() == commonSpec) sendConfigEvent(GenerationType::onCommonConfigLoaded);
    	else if (config.getSpec() == serverSpec) sendConfigEvent(GenerationType::onServerConfigLoaded);
    	else if (config.getSpec() == clientSpec) sendConfigEvent(GenerationType::onClientConfigLoaded);
    }
    
    private static void onModConfigReloaded(ModConfig config) {
    	if (config.getSpec() == commonSpec) sendConfigEvent(GenerationType::onCommonConfigReloaded);
    	else if (config.getSpec() == serverSpec) sendConfigEvent(GenerationType::onServerConfigReloaded);
    	else if (config.getSpec() == clientSpec) sendConfigEvent(GenerationType::onClientConfigReloaded);
    }

    public static void sendConfigEvent(Consumer<? super GenerationType<?, ?>> action) {
    	Registration.forEach(action);
    }
}