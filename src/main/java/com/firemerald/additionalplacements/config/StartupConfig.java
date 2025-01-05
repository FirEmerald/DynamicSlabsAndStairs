package com.firemerald.additionalplacements.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.electronwill.nightconfig.core.ConfigFormat;
import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.electronwill.nightconfig.toml.TomlFormat;
import com.firemerald.additionalplacements.generation.GenerationType;
import com.firemerald.additionalplacements.generation.Registration;

import net.minecraftforge.common.ForgeConfigSpec;

public class StartupConfig {
	public final GenerationBlacklist blacklist = new GenerationBlacklist.Builder().build();

	public StartupConfig(ForgeConfigSpec.Builder builder) {
        builder.comment("Startup settings").push("startup");
		builder
		.comment("Options for controlling which blocks can generate variants of a their type (if one exists).")
		.push("enabled");
		blacklist.addToConfig(builder);
		builder.pop();
        Registration.buildConfig(builder, GenerationType::buildStartupConfig);
	}

	public void loadConfig(Path configPath, ForgeConfigSpec spec) {
        final CommentedFileConfig config = CommentedFileConfig.builder(configPath, TomlFormat.instance())
        		.sync()
                .preserveInsertionOrder()
                .onFileNotFound(this::setupConfigFile)
                .writingMode(WritingMode.REPLACE)
                .build();
        config.load();
		spec.acceptConfig(config);
		onConfigLoaded();
		config.close();
	}

    private boolean setupConfigFile(final Path file, final ConfigFormat<?> conf) throws IOException {
        Files.createFile(file);
        conf.initEmptyFile(file);
        return true;
    }

	public void onConfigLoaded() {
		blacklist.loadListsFromConfig();
		Registration.forEach(GenerationType::onStartupConfigLoaded);
	}
}
