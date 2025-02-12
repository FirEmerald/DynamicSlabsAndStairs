package com.firemerald.additionalplacements.config;

import com.firemerald.additionalplacements.generation.GenerationType;
import com.firemerald.additionalplacements.generation.Registration;

import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.BooleanValue;
import net.neoforged.neoforge.common.ModConfigSpec.IntValue;

public class CommonConfig
{
	public final BooleanValue showTooltip;
	public final BooleanValue checkTags, autoRebuildTags, logTagMismatch;
	public final IntValue checkerPriority;
	public final BooleanValue fixStates, fixOldStates;

	public CommonConfig(ModConfigSpec.Builder builder)
	{
        builder.comment("Common settings").push("common");
        showTooltip = builder
        		.comment("Show tooltip when a block has additional placements")
        		.define("tooltip", true);
        checkTags = builder
        		.comment("Check for and notify of mismatching tags. Only works when the same option in the server/world config is true.")
        		.define("check_tags", true);
        autoRebuildTags = builder
        		.comment("Automatically rebuild and reload the generated tags datapack when a tagging mismatch is detected. Only works when the same option in the server/world config is true.")
        		.define("auto_rebuild_tags", true);
        logTagMismatch = builder
        		.comment("Log missing or additional tags on generated blocks.")
        		.define("log_tag_mismatch", false);
        checkerPriority = builder
        		.comment("The thread priority of the mismatched tag checker. " + Thread.MIN_PRIORITY + " is lowest, " + Thread.MAX_PRIORITY + " is highest, " + Thread.NORM_PRIORITY + " is normal.")
        		.defineInRange("checker_priority", Thread.MIN_PRIORITY, Thread.MIN_PRIORITY, Thread.MAX_PRIORITY);
        fixStates = builder
        		.comment("Fix incorrect states. Adds a slight bit of additional overhead to chunk loading. \n"
        				+ "You should only disable this if the worlds you are using were not made using an older version of Additional Placements AND you are not changing the possible placement states of any stair blocks after they were created.")
        		.define("fix_states", true);
        fixOldStates = builder
        		.comment("Upgrade the blockstates from older versions of Additional Placements. Adds a slight bit of additional overhead to chunk loading - more than having JUST fix_states on. Does nothing if fix_states is off. \n"
        				+ "You should only disable this if the worlds you are using were not made using an older version of Additional Placements.")
        		.define("fix_old_states", true);
        Registration.buildConfig(builder, GenerationType::buildCommonConfig);
	}

	public void onConfigLoaded() {
		Registration.forEach(GenerationType::onCommonConfigLoaded);
	}
}