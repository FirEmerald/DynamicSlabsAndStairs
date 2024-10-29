package com.firemerald.additionalplacements.client;

import com.firemerald.additionalplacements.block.interfaces.IPlacementBlock;
import com.firemerald.additionalplacements.client.gui.screen.ConnectionErrorsScreen;
import com.firemerald.additionalplacements.config.APConfigs;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.DisconnectedScreen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.*;

@EventBusSubscriber(value = Dist.CLIENT, bus = EventBusSubscriber.Bus.GAME)
@OnlyIn(Dist.CLIENT)
public class ClientEventHandler
{
	@SubscribeEvent
	public static void onHighlightBlock(RenderHighlightEvent.Block event)
	{
		@SuppressWarnings("resource")
		Player player = Minecraft.getInstance().player;
		ItemStack stack = player.getMainHandItem();
		if (stack.isEmpty()) stack = player.getOffhandItem();
		if (stack.getItem() instanceof BlockItem)
		{
			Block block = ((BlockItem) stack.getItem()).getBlock();
			if (block instanceof IPlacementBlock)
			{
				IPlacementBlock<?> verticalBlock = ((IPlacementBlock<?>) block);
				if (verticalBlock.hasAdditionalStates()) verticalBlock.renderHighlight(event.getPoseStack(), event.getMultiBufferSource().getBuffer(RenderType.LINES), player, event.getTarget(), event.getCamera(), event.getDeltaTracker());
			}
		}
	}

	@SuppressWarnings("resource")
	public static void onInput(InputEvent event)
	{
		if (Minecraft.getInstance().player == null) return;
		if (APClientData.AP_PLACEMENT_KEY.consumeClick() && !APClientData.placementKeyDown)
		{
			APClientData.togglePlacementEnabled();
			APClientData.placementKeyPressTime = System.currentTimeMillis();
			APClientData.placementKeyDown = true;
		}
		else if (APClientData.placementKeyDown && !APClientData.AP_PLACEMENT_KEY.isDown()) //released
		{
			APClientData.placementKeyDown = false;
			if ((System.currentTimeMillis() - APClientData.placementKeyPressTime) > APConfigs.client().toggleQuickpressTime.get()) //more than half-second press, toggle back
			{
				APClientData.togglePlacementEnabled();
			}
		}
	}

	@SubscribeEvent
	public static void onKeyInput(InputEvent.Key event)
	{
		onInput(event);
	}

	@SubscribeEvent
	public static void onKeyInput(InputEvent.MouseButton.Post event)
	{
		onInput(event);
	}

	@SubscribeEvent
	public static void onKeyInput(InputEvent.MouseScrollingEvent event)
	{
		onInput(event);
	}

	@SubscribeEvent
	public static void onPlayerLoggingIn(ClientPlayerNetworkEvent.LoggingIn event)
	{
		APClientData.setPlacementEnabledAndSynchronize(APConfigs.client().defaultPlacementLogicState.get());
	}

	@SuppressWarnings("resource")
	@SubscribeEvent
	public static void onClientTick(ClientTickEvent.Post event)
	{
		if (Minecraft.getInstance().player == null) return;
		if ((System.currentTimeMillis() - APClientData.lastSynchronizedTime) > 10000) //synchronize every 10 seconds in case of desync
		{
			APClientData.synchronizePlacementEnabled();
		}
	}

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void onScreenOpening(ScreenEvent.Opening event) {
		if (event.getCurrentScreen() instanceof ConnectionErrorsScreen && event.getNewScreen() instanceof DisconnectedScreen) event.setCanceled(true);
	}
}