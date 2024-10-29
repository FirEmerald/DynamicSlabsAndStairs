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
    public static final StartupConfig STARTUP;
    public static final ModConfigSpec STARTUP_SPEC;
    public static final CommonConfig COMMON;
    public static final ModConfigSpec COMMON_SPEC;
    public static final ServerConfig SERVER;
    public static final ModConfigSpec SERVER_SPEC;
    public static final ClientConfig CLIENT;
    public static final ModConfigSpec CLIENT_SPEC;
    
    static {
        final Pair<StartupConfig, ModConfigSpec> startupSpecPair = new ModConfigSpec.Builder().configure(StartupConfig::new);
        STARTUP = startupSpecPair.getLeft();
        STARTUP_SPEC = startupSpecPair.getRight();
        final Pair<CommonConfig, ModConfigSpec> commonSpecPair = new ModConfigSpec.Builder().configure(CommonConfig::new);
        COMMON = commonSpecPair.getLeft();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, COMMON_SPEC = commonSpecPair.getRight());
        final Pair<ServerConfig, ModConfigSpec> serverSpecPair = new ModConfigSpec.Builder().configure(ServerConfig::new);
        SERVER = serverSpecPair.getLeft();
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, SERVER_SPEC = serverSpecPair.getRight());
        final Pair<ClientConfig, ModConfigSpec> clientSpecPair = new ModConfigSpec.Builder().configure(ClientConfig::new);
        CLIENT = clientSpecPair.getLeft();
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, CLIENT_SPEC = clientSpecPair.getRight());
    }
    
    public static void init() {}
    
    public static void onModConfigsLoaded(ModConfigEvent.Loading event) {
    	if (event.getConfig().getSpec() == COMMON_SPEC) sendConfigEvent(GenerationType::onCommonConfigLoaded);
    	else if (event.getConfig().getSpec() == SERVER_SPEC) sendConfigEvent(GenerationType::onServerConfigLoaded);
    	else if (event.getConfig().getSpec() == CLIENT_SPEC) sendConfigEvent(GenerationType::onClientConfigLoaded);
    }
    
    public static void onModConfigsReloaded(ModConfigEvent.Reloading event) {
    	if (event.getConfig().getSpec() == COMMON_SPEC) sendConfigEvent(GenerationType::onCommonConfigReloaded);
    	else if (event.getConfig().getSpec() == SERVER_SPEC) sendConfigEvent(GenerationType::onServerConfigReloaded);
    	else if (event.getConfig().getSpec() == CLIENT_SPEC) sendConfigEvent(GenerationType::onClientConfigReloaded);
    }
    
    public static void sendConfigEvent(Consumer<GenerationType<?, ?>> action) {
    	Registration.forEach((name, type) -> action.accept(type));
    }
}