package com.firemerald.additionalplacements.config;

import com.firemerald.additionalplacements.generation.Registration;

import net.neoforged.neoforge.common.ModConfigSpec;

import com.firemerald.additionalplacements.generation.GenerationType;

public class StartupConfig {
	public StartupConfig(ModConfigSpec.Builder builder) {
        builder.comment("Startup settings").push("startup");
        Registration.buildConfig(builder, GenerationType::buildStartupConfig);
	}
}
