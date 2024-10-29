package com.firemerald.additionalplacements.config;

import com.firemerald.additionalplacements.generation.Registration;

import net.neoforged.neoforge.common.ModConfigSpec;

import com.firemerald.additionalplacements.generation.GenerationType;

public class ConfigBootup {
	public ConfigBootup(ModConfigSpec.Builder builder) {
        builder.comment("Bootup settings").push("bootup");
        Registration.buildConfig(builder, GenerationType::buildBootupConfig);
	}
}
