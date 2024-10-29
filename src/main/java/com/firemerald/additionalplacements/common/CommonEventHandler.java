package com.firemerald.additionalplacements.common;

import com.firemerald.additionalplacements.block.interfaces.IPlacementBlock;
import com.firemerald.additionalplacements.commands.CommandExportTags;
import com.firemerald.additionalplacements.commands.CommandGenerateStairsDebugger;
import com.firemerald.additionalplacements.config.APConfigs;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.TagsUpdatedEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.neoforged.neoforge.event.server.ServerStoppingEvent;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.GAME)
public class CommonEventHandler
{
	public static boolean misMatchedTags = false;

	@SubscribeEvent
	public static void onItemTooltip(ItemTooltipEvent event)
	{
		if (event.getItemStack().getItem() instanceof BlockItem)
		{
			Block block = ((BlockItem) event.getItemStack().getItem()).getBlock();
			if (block instanceof IPlacementBlock)
			{
				IPlacementBlock<?> verticalBlock = ((IPlacementBlock<?>) block);
				if (verticalBlock.hasAdditionalStates()) verticalBlock.appendHoverTextImpl(event.getItemStack(), event.getContext(), event.getToolTip(), event.getFlags());
			}
		}
	}

	@SubscribeEvent
	public static void onRegisterCommands(RegisterCommandsEvent event)
	{
		CommandExportTags.register(event.getDispatcher());
		CommandGenerateStairsDebugger.register(event.getDispatcher(), event.getBuildContext());
	}

	@SubscribeEvent
	public static void onTagsUpdated(TagsUpdatedEvent event)
	{
		misMatchedTags = false;
		if (APConfigs.common().checkTags.get() && (!APConfigs.serverLoaded() || APConfigs.server().checkTags.get()))
			TagMismatchChecker.startChecker(); //TODO halt on datapack reload
	}

	@SubscribeEvent
	public static void onPlayerLogin(PlayerLoggedInEvent event)
	{
		if (misMatchedTags && !(APConfigs.common().autoRebuildTags.get() && APConfigs.server().autoRebuildTags.get()) && TagMismatchChecker.canGenerateTags(event.getEntity())) event.getEntity().sendSystemMessage(TagMismatchChecker.MESSAGE);
	}

	@SubscribeEvent
	public static void onServerStopping(ServerStoppingEvent event)
	{
		TagMismatchChecker.stopChecker();
	}
}