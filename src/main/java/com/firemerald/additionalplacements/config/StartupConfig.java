package com.firemerald.additionalplacements.config;

import com.firemerald.additionalplacements.generation.GenerationType;
import com.firemerald.additionalplacements.generation.Registration;

import net.neoforged.neoforge.common.ModConfigSpec;

public class StartupConfig {
	public final GenerationBlacklist blacklist = new GenerationBlacklist.Builder().build();

	public StartupConfig(ModConfigSpec.Builder builder) {
        builder.comment("Startup settings").push("startup");
		builder
		.comment("Options for controlling which blocks can generate variants of a their type (if one exists).")
		.push("enabled");
		blacklist.addToConfig(builder);
		builder.pop();
        Registration.buildConfig(builder, GenerationType::buildStartupConfig);
	}

	public void onConfigLoaded() {
		blacklist.loadListsFromConfig();
		Registration.forEach(GenerationType::onStartupConfigLoaded);
	}
}
