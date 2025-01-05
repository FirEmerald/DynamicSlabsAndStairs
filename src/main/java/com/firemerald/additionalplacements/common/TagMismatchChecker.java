package com.firemerald.additionalplacements.common;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.function.IntPredicate;

import org.apache.commons.lang3.tuple.Triple;

import com.firemerald.additionalplacements.AdditionalPlacementsMod;
import com.firemerald.additionalplacements.block.AdditionalPlacementBlock;
import com.firemerald.additionalplacements.config.APConfigs;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.*;
import net.minecraft.network.chat.ClickEvent.Action;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.rcon.RconConsoleSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;

public class TagMismatchChecker extends Thread
{
	private static TagMismatchChecker thread = null;
	public static final Component MESSAGE = new TranslatableComponent("msg.additionalplacements.mismatchedtags.0").append(
			new TextComponent("/ap_tags_export").setStyle(Style.EMPTY.withClickEvent(new ClickEvent(Action.RUN_COMMAND, "/ap_tags_export")).withColor(ChatFormatting.BLUE).withUnderlined(true)).append(
					new TranslatableComponent("msg.additionalplacements.mismatchedtags.1").withStyle(Style.EMPTY.withUnderlined(false).withColor(ChatFormatting.WHITE)).append(
							new TextComponent("/reload").setStyle(Style.EMPTY.withClickEvent(new ClickEvent(Action.RUN_COMMAND, "/reload")).withColor(ChatFormatting.BLUE).withUnderlined(true)).append(
									new TranslatableComponent("msg.additionalplacements.mismatchedtags.2").withStyle(Style.EMPTY.withUnderlined(false).withColor(ChatFormatting.WHITE))
									)
							)
					)
			);
	public static final Component FAILED = new TranslatableComponent("msg.additionalplacements.generate.notfixed").setStyle(Style.EMPTY.withColor(ChatFormatting.RED));

	public static void startChecker(MinecraftServer server, boolean autoGenerate, boolean fromAutoGenerate)
	{
		setThread(new TagMismatchChecker(server, autoGenerate, fromAutoGenerate));
		thread.setPriority(APConfigs.common().checkerPriority.get());
		CommonModEvents.misMatchedTags = false;
		thread.start();
	}

	public static void stopChecker()
	{
		setThread(null);
	}

	private static synchronized void setThread(TagMismatchChecker newThread) {
		if (thread != null) thread.halted = true;
		thread = newThread;
	}


	private final MinecraftServer server;
	private final boolean autoGenerate, fromAutoGenerate;
	private boolean halted = false;
	private final List<Triple<Block, Collection<TagKey<Block>>, Collection<TagKey<Block>>>> blockMissingExtra = new LinkedList<>();

	private TagMismatchChecker(MinecraftServer server, boolean autoGenerate, boolean fromAutoGenerate)
	{
		super("Additional Placements Tag Mismatch Checker");
		this.server = server;
		this.autoGenerate = autoGenerate;
		this.fromAutoGenerate = fromAutoGenerate;
	}

	@Override
	public void run()
	{
		for (Block block : Registry.BLOCK)
		{
			if (halted) return;
			if (block instanceof AdditionalPlacementBlock)
			{
				Triple<Block, Collection<TagKey<Block>>, Collection<TagKey<Block>>> mismatch = ((AdditionalPlacementBlock<?>) block).checkTagMismatch();
				if (mismatch != null) blockMissingExtra.add(mismatch);
			}
		}
		if (!halted) server.submit(this::process);
	}

	//this is only ever called on the server thread
	public void process()
	{
		thread = null;
		if (!halted) //wasn't canceled
		{
			if (!blockMissingExtra.isEmpty())
			{
				CommonModEvents.misMatchedTags = true;
				if (fromAutoGenerate) CommonModEvents.autoGenerateFailed = true;
				if (!autoGenerate) {
					Component message = fromAutoGenerate ? FAILED : MESSAGE;
					server.getPlayerList().getPlayers().forEach(player -> {
						if (canGenerateTags(player)) player.sendMessage(message, Util.NIL_UUID);
					});
				}
				AdditionalPlacementsMod.LOGGER.warn("Found missing and/or extra tags on generated blocks. Use \"/ap_tags_export\" to generate the tags, then \"/reload\" to re-load them (or re-load the world if that fails).");
				if (APConfigs.common().logTagMismatch.get())
				{
					AdditionalPlacementsMod.LOGGER.warn("====== BEGIN LIST ======");
					blockMissingExtra.forEach(blockMissingExtra -> {
						AdditionalPlacementsMod.LOGGER.warn("\t" + Registry.BLOCK.getKey(blockMissingExtra.getLeft()));
						Collection<TagKey<Block>> missing = blockMissingExtra.getMiddle();
						if (!missing.isEmpty())
						{
							AdditionalPlacementsMod.LOGGER.warn("\t\tmissing");
							missing.forEach(tag -> AdditionalPlacementsMod.LOGGER.warn("\t\t\t" + tag.location()));
						}
						Collection<TagKey<Block>> extra = blockMissingExtra.getRight();
						if (!extra.isEmpty())
						{
							AdditionalPlacementsMod.LOGGER.warn("\t\textra");
							extra.forEach(tag -> AdditionalPlacementsMod.LOGGER.warn("\t\t\t" + tag.location()));
						}
					});
					AdditionalPlacementsMod.LOGGER.warn("====== END LIST ======");
				}
				else AdditionalPlacementsMod.LOGGER.info("Not logging tag mismatches as it is disabled in the common config");
				if (autoGenerate)
				{
					AdditionalPlacementsMod.LOGGER.info("Rebuilding block tags and reloading datapacks as automatic tag rebuilding is enabled");
					CommandDispatcher<CommandSourceStack> dispatch = server.getCommands().getDispatcher();
					CommandSourceStack source = server.createCommandSourceStack();
					try
					{
						CommonModEvents.reloadedFromChecker = true;
						dispatch.execute("ap_tags_export", source);
						dispatch.execute("reload", source);
					}
					catch (CommandSyntaxException e)
					{
						AdditionalPlacementsMod.LOGGER.error("Unexpected error whilst automatically rebuilding tags", e);
					}
				}
			}
		}
	}

	public static boolean canGenerateTags(Player player, IntPredicate hasPermission)
	{
		if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) return canGenerateTagsClient(player);
		else return hasPermission.test(2);
	}

	@SuppressWarnings("resource")
	@Environment(EnvType.CLIENT)
	public static boolean canGenerateTagsClient(Player player)
	{
		Player clientPlayer = Minecraft.getInstance().player;
		return clientPlayer == null || player.getGameProfile().getId().equals(clientPlayer.getGameProfile().getId());
	}

	public static boolean canGenerateTags(Player player)
	{
		return canGenerateTags(player, player::hasPermissions);
	}

	public static boolean canGenerateTags(CommandSourceStack source)
	{
		return source.source instanceof RconConsoleSource || source.source instanceof MinecraftServer || (source.getEntity() instanceof Player && canGenerateTags((Player) source.getEntity(), source::hasPermission));
	}
}