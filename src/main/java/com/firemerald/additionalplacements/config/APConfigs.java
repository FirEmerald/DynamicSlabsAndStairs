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
    private static ConfigBootup bootup;
    private static ForgeConfigSpec bootupSpec;
    private static ConfigCommon common;
    private static ForgeConfigSpec commonSpec;
    private static ConfigServer server;
    private static ForgeConfigSpec serverSpec;
    private static ConfigClient client;
    private static ForgeConfigSpec clientSpec;
    
    public static void init() {
    	ModConfigEvents.loading(AdditionalPlacementsMod.MOD_ID).register(APConfigs::onModConfigLoaded);
    	ModConfigEvents.reloading(AdditionalPlacementsMod.MOD_ID).register(APConfigs::onModConfigReloaded);
        final Pair<ConfigBootup, ForgeConfigSpec> bootupSpecPair = new ForgeConfigSpec.Builder().configure(ConfigBootup::new);
        bootup = bootupSpecPair.getLeft();
        bootupSpec = bootupSpecPair.getRight();
		bootup.loadConfig(FabricLoader.getInstance().getConfigDir().resolve("additionalplacements-bootup.toml"), bootupSpec);
        final Pair<ConfigCommon, ForgeConfigSpec> commonSpecPair = new ForgeConfigSpec.Builder().configure(ConfigCommon::new);
        common = commonSpecPair.getLeft();
        ModLoadingContext.registerConfig(AdditionalPlacementsMod.MOD_ID, ModConfig.Type.COMMON, commonSpec = commonSpecPair.getRight());
        final Pair<ConfigServer, ForgeConfigSpec> serverSpecPair = new ForgeConfigSpec.Builder().configure(ConfigServer::new);
        server = serverSpecPair.getLeft();
        ModLoadingContext.registerConfig(AdditionalPlacementsMod.MOD_ID, ModConfig.Type.SERVER, serverSpec = serverSpecPair.getRight());
        final Pair<ConfigClient, ForgeConfigSpec> clientSpecPair = new ForgeConfigSpec.Builder().configure(ConfigClient::new);
        client = clientSpecPair.getLeft();
        ModLoadingContext.registerConfig(AdditionalPlacementsMod.MOD_ID, ModConfig.Type.CLIENT, clientSpec = clientSpecPair.getRight());
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