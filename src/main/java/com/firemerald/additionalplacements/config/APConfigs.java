package com.firemerald.additionalplacements.config;

import java.util.function.Consumer;

import org.apache.commons.lang3.tuple.Pair;

import com.firemerald.additionalplacements.AdditionalPlacementsMod;
import com.firemerald.additionalplacements.generation.GenerationType;
import com.firemerald.additionalplacements.generation.Registration;

import fuzs.forgeconfigapiport.fabric.api.neoforge.v4.NeoForgeConfigRegistry;
import fuzs.forgeconfigapiport.fabric.api.neoforge.v4.NeoForgeModConfigEvents;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

public class APConfigs {
    private static StartupConfig startup;
    private static ModConfigSpec startupSpec;
    private static CommonConfig common;
    private static ModConfigSpec commonSpec;
    private static ServerConfig server;
    private static ModConfigSpec serverSpec;
    private static ClientConfig client;
    private static ModConfigSpec clientSpec;
    
    public static void init() {
    	NeoForgeModConfigEvents.loading(AdditionalPlacementsMod.MOD_ID).register(APConfigs::onModConfigLoaded);
    	NeoForgeModConfigEvents.reloading(AdditionalPlacementsMod.MOD_ID).register(APConfigs::onModConfigReloaded);
        final Pair<StartupConfig, ModConfigSpec> startupSpecPair = new ModConfigSpec.Builder().configure(StartupConfig::new);
        startup = startupSpecPair.getLeft();
        NeoForgeConfigRegistry.INSTANCE.register(AdditionalPlacementsMod.MOD_ID, ModConfig.Type.STARTUP, startupSpec = startupSpecPair.getRight());
        final Pair<CommonConfig, ModConfigSpec> commonSpecPair = new ModConfigSpec.Builder().configure(CommonConfig::new);
        common = commonSpecPair.getLeft();
        NeoForgeConfigRegistry.INSTANCE.register(AdditionalPlacementsMod.MOD_ID, ModConfig.Type.COMMON, commonSpec = commonSpecPair.getRight());
        final Pair<ServerConfig, ModConfigSpec> serverSpecPair = new ModConfigSpec.Builder().configure(ServerConfig::new);
        server = serverSpecPair.getLeft();
        NeoForgeConfigRegistry.INSTANCE.register(AdditionalPlacementsMod.MOD_ID, ModConfig.Type.SERVER, serverSpec = serverSpecPair.getRight());
        final Pair<ClientConfig, ModConfigSpec> clientSpecPair = new ModConfigSpec.Builder().configure(ClientConfig::new);
        client = clientSpecPair.getLeft();
        NeoForgeConfigRegistry.INSTANCE.register(AdditionalPlacementsMod.MOD_ID, ModConfig.Type.CLIENT, clientSpec = clientSpecPair.getRight());
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
    	if (config.getSpec() == startupSpec) sendConfigEvent(GenerationType::onStartupConfigLoaded);
    	else if (config.getSpec() == commonSpec) sendConfigEvent(GenerationType::onCommonConfigLoaded);
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