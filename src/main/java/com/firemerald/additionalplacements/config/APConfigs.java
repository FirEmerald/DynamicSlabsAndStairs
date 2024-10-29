package com.firemerald.additionalplacements.config;

import java.util.function.Consumer;

import org.apache.commons.lang3.tuple.Pair;

import com.firemerald.additionalplacements.AdditionalPlacementsMod;
import com.firemerald.additionalplacements.generation.GenerationType;
import com.firemerald.additionalplacements.generation.Registration;

import fuzs.forgeconfigapiport.fabric.api.neoforge.v4.NeoForgeConfigRegistry;
import fuzs.forgeconfigapiport.fabric.api.neoforge.v4.NeoForgeModConfigEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

public class APConfigs {
    private static ConfigBootup bootup;
    private static ModConfigSpec bootupSpec;
    private static ConfigCommon common;
    private static ModConfigSpec commonSpec;
    private static ConfigServer server;
    private static ModConfigSpec serverSpec;
    private static ConfigClient client;
    private static ModConfigSpec clientSpec;
    
    public static void init() {
    	NeoForgeModConfigEvents.loading(AdditionalPlacementsMod.MOD_ID).register(APConfigs::onModConfigLoaded);
    	NeoForgeModConfigEvents.reloading(AdditionalPlacementsMod.MOD_ID).register(APConfigs::onModConfigReloaded);
        final Pair<ConfigBootup, ModConfigSpec> bootupSpecPair = new ModConfigSpec.Builder().configure(ConfigBootup::new);
        bootup = bootupSpecPair.getLeft();
        bootupSpec = bootupSpecPair.getRight();
		bootup.loadConfig(FabricLoader.getInstance().getConfigDir().resolve("additionalplacements-bootup.toml"), bootupSpec);
        final Pair<ConfigCommon, ModConfigSpec> commonSpecPair = new ModConfigSpec.Builder().configure(ConfigCommon::new);
        common = commonSpecPair.getLeft();
        NeoForgeConfigRegistry.INSTANCE.register(AdditionalPlacementsMod.MOD_ID, ModConfig.Type.COMMON, commonSpec = commonSpecPair.getRight());
        final Pair<ConfigServer, ModConfigSpec> serverSpecPair = new ModConfigSpec.Builder().configure(ConfigServer::new);
        server = serverSpecPair.getLeft();
        NeoForgeConfigRegistry.INSTANCE.register(AdditionalPlacementsMod.MOD_ID, ModConfig.Type.SERVER, serverSpec = serverSpecPair.getRight());
        final Pair<ConfigClient, ModConfigSpec> clientSpecPair = new ModConfigSpec.Builder().configure(ConfigClient::new);
        client = clientSpecPair.getLeft();
        NeoForgeConfigRegistry.INSTANCE.register(AdditionalPlacementsMod.MOD_ID, ModConfig.Type.CLIENT, clientSpec = clientSpecPair.getRight());
    }
    
    public static ConfigBootup bootup() {
    	return bootup;
    }
    
    public static ConfigCommon common() {
    	return common;
    }
    
    public static ConfigServer server() {
    	return server;
    }
    
    public static boolean serverLoaded() {
    	return serverSpec.isLoaded();
    }
    
    public static ConfigClient client() {
    	return client;
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
    
    protected static void sendConfigEvent(Consumer<GenerationType<?, ?>> action) {
    	Registration.forEach((name, type) -> action.accept(type));
    }
}