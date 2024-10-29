package com.firemerald.additionalplacements.config;

import java.util.function.Consumer;

import org.apache.commons.lang3.tuple.Pair;

import com.firemerald.additionalplacements.generation.GenerationType;
import com.firemerald.additionalplacements.generation.Registration;

import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.config.ModConfigEvent;
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
        final Pair<ConfigBootup, ModConfigSpec> bootupSpecPair = new ModConfigSpec.Builder().configure(ConfigBootup::new);
        bootup = bootupSpecPair.getLeft();
        ModLoadingContext.get().getActiveContainer().registerConfig(ModConfig.Type.STARTUP, bootupSpec = bootupSpecPair.getRight());
        final Pair<ConfigCommon, ModConfigSpec> commonSpecPair = new ModConfigSpec.Builder().configure(ConfigCommon::new);
        common = commonSpecPair.getLeft();
        ModLoadingContext.get().getActiveContainer().registerConfig(ModConfig.Type.COMMON, commonSpec = commonSpecPair.getRight());
        final Pair<ConfigServer, ModConfigSpec> serverSpecPair = new ModConfigSpec.Builder().configure(ConfigServer::new);
        server = serverSpecPair.getLeft();
        ModLoadingContext.get().getActiveContainer().registerConfig(ModConfig.Type.SERVER, serverSpec = serverSpecPair.getRight());
        final Pair<ConfigClient, ModConfigSpec> clientSpecPair = new ModConfigSpec.Builder().configure(ConfigClient::new);
        client = clientSpecPair.getLeft();
        ModLoadingContext.get().getActiveContainer().registerConfig(ModConfig.Type.CLIENT, clientSpec = clientSpecPair.getRight());}
    
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
    
    public static void onModConfigsLoaded(ModConfigEvent.Loading event) {
    	if (event.getConfig().getSpec() == bootupSpec) sendConfigEvent(GenerationType::onBootupConfigLoaded);
    	else if (event.getConfig().getSpec() == commonSpec) sendConfigEvent(GenerationType::onCommonConfigLoaded);
    	else if (event.getConfig().getSpec() == serverSpec) sendConfigEvent(GenerationType::onServerConfigLoaded);
    	else if (event.getConfig().getSpec() == clientSpec) sendConfigEvent(GenerationType::onClientConfigLoaded);
    }
    
    public static void onModConfigsReloaded(ModConfigEvent.Reloading event) {
    	if (event.getConfig().getSpec() == commonSpec) sendConfigEvent(GenerationType::onCommonConfigReloaded);
    	else if (event.getConfig().getSpec() == serverSpec) sendConfigEvent(GenerationType::onServerConfigReloaded);
    	else if (event.getConfig().getSpec() == clientSpec) sendConfigEvent(GenerationType::onClientConfigReloaded);
    }
    
    public static void sendConfigEvent(Consumer<GenerationType<?, ?>> action) {
    	Registration.forEach((name, type) -> action.accept(type));
    }
}