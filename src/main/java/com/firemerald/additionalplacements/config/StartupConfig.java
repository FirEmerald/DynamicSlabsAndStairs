package com.firemerald.additionalplacements.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.electronwill.nightconfig.core.ConfigFormat;
import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.electronwill.nightconfig.toml.TomlFormat;
import com.firemerald.additionalplacements.generation.Registration;

import net.neoforged.neoforge.common.ModConfigSpec;

import com.firemerald.additionalplacements.generation.GenerationType;

public class StartupConfig {
	public StartupConfig(ModConfigSpec.Builder builder) {
        builder.comment("Startup settings").push("startup");
        Registration.buildConfig(builder, GenerationType::buildStartupConfig);
	}
	
	public void loadConfig(Path configPath, ModConfigSpec spec) {
        final CommentedFileConfig config = CommentedFileConfig.builder(configPath, TomlFormat.instance())
        		.sync()
                .preserveInsertionOrder()
                .onFileNotFound(this::setupConfigFile)
                .writingMode(WritingMode.REPLACE)
                .build();
        config.load();
		spec.acceptConfig(config);
		Registration.forEach(GenerationType::onStartupConfigLoaded);
		config.close();
	}

    private boolean setupConfigFile(final Path file, final ConfigFormat<?> conf) throws IOException {
        Files.createFile(file);
        conf.initEmptyFile(file);
        return true;
    }
}
