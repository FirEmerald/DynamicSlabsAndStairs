package com.firemerald.additionalplacements.config;

import com.firemerald.additionalplacements.generation.Registration;
import com.firemerald.additionalplacements.generation.GenerationType;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.LongValue;

public class ClientConfig
{
	public final BooleanValue defaultPlacementLogicState;
	public final LongValue toggleQuickpressTime;
	public final BooleanValue useDynamicModels;

	public ClientConfig(ForgeConfigSpec.Builder builder)
	{
        builder.comment("Client settings").push("client");
        defaultPlacementLogicState = builder
        		.comment("Default enabled state for Additional Placement placement logic. Please note that this value takes effect any time you load a world or log in to a server.")
        		.define("default_placement_logic_state", true);
        toggleQuickpressTime = builder
        		.comment("The length of time in milliseconds for which the placement toggle key must be held for it to automatically return to the previous state when the key is released. setting to 0 turns the key into hold only, setting it to a high value (such as 1000000) will make it generally behave as always a toggle")
        		.defineInRange("toggle_quickpress_time", 500l, 0, Long.MAX_VALUE);
        useDynamicModels = builder
        		.comment("Utilize dynamic block models. May have improper rendering, such as improper AO or block lighting, and may have missing or generic textures when used outside of normal placement I.E. when pushed by a piston. Will reduce RAM usage.")
        		.define("use_dynamic_models", false);
        Registration.buildConfig(builder, GenerationType::buildClientConfig);
	}
}