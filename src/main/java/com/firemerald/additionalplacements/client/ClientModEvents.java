package com.firemerald.additionalplacements.client;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.jetbrains.annotations.Nullable;

import com.firemerald.additionalplacements.AdditionalPlacementsMod;
import com.firemerald.additionalplacements.block.AdditionalPlacementBlock;
import com.firemerald.additionalplacements.block.interfaces.IPlacementBlock;
import com.firemerald.additionalplacements.common.CommonModEvents;
import com.firemerald.additionalplacements.config.APConfigs;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.SharedConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackSelectionConfig;
import net.minecraft.server.packs.repository.KnownPack;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.Pack.Metadata;
import net.minecraft.server.packs.repository.Pack.Position;
import net.minecraft.server.packs.repository.Pack.ResourcesSupplier;
import net.minecraft.server.packs.repository.PackCompatibility;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

@Environment(EnvType.CLIENT)
public class ClientModEvents implements ClientModInitializer
{
	public static final Pack GENERATED_RESOURCES_PACK = new Pack(
			new PackLocationInfo(
					"Additional Placements blockstate redirection pack", 
					Component.literal("title"), 
					PackSource.BUILT_IN, 
					Optional.of(new KnownPack(
							AdditionalPlacementsMod.MOD_ID, 
							"Additional Placements blockstate redirection pack", 
							SharedConstants.getCurrentVersion().getId()))),
			new ResourcesSupplier() {
				@Override
				public PackResources openPrimary(PackLocationInfo info) {
					return new BlockstatesPackResources(info);
				}

				@Override
				public PackResources openFull(PackLocationInfo info, Metadata meta) {
					return new BlockstatesPackResources(info);
				}
			},
			new Metadata(
					Component.literal("Additional Placements blockstate redirection pack"),
					PackCompatibility.COMPATIBLE,
					FeatureFlagSet.of(),
					Collections.emptyList()
					),
			new PackSelectionConfig(
					true,
					Position.BOTTOM,
					true
					)
			);

	@Override
	public void onInitializeClient()
	{
		ItemTooltipCallback.EVENT.register(ClientModEvents::onItemTooltip);
		WorldRenderEvents.BEFORE_BLOCK_OUTLINE.register(ClientModEvents::onHighlightBlock);
		ClientLifecycleEvents.CLIENT_STARTED.register(client -> CommonModEvents.init());
		ClientLifecycleEvents.CLIENT_STARTED.register(ClientModEvents::init);
		ClientTickEvents.END_CLIENT_TICK.register(ClientModEvents::onClientEndTick);
		ClientPlayConnectionEvents.JOIN.register(ClientModEvents::onServerJoined);
		KeyBindingHelper.registerKeyBinding(APClientData.AP_PLACEMENT_KEY);
	}

	private static boolean hasInit = false;

	public static void init(Minecraft client)
	{
		if (!hasInit)
		{
			BuiltInRegistries.BLOCK.forEach(block -> {
				if (block instanceof AdditionalPlacementBlock)
				{
					@SuppressWarnings("deprecation")
					BlockState modelState = ((AdditionalPlacementBlock<?>) block).getModelState();
					BlockRenderLayerMap.INSTANCE.putBlock(block, ItemBlockRenderTypes.getChunkRenderType(modelState));
				}
			});
			client.getBlockColors().register(new AdditionalBlockColor(), BuiltInRegistries.BLOCK.stream().filter(block -> block instanceof AdditionalPlacementBlock && !((AdditionalPlacementBlock<?>) block).hasCustomColors()).toArray(Block[]::new));
			hasInit = true;
		}
	}

	public static void onItemTooltip(ItemStack stack, Item.TooltipContext context, TooltipFlag tooltipType, List<Component> lines)
	{
		if (stack.getItem() instanceof BlockItem)
		{
			Block block = ((BlockItem) stack.getItem()).getBlock();
			if (block instanceof IPlacementBlock)
			{
				IPlacementBlock<?> verticalBlock = ((IPlacementBlock<?>) block);
				if (verticalBlock.hasAdditionalStates()) verticalBlock.appendHoverTextImpl(stack, context, lines, tooltipType);
			}
		}
	}

	public static boolean onHighlightBlock(WorldRenderContext context, @Nullable HitResult hitResult)
	{
		if (hitResult != null && hitResult.getType() == HitResult.Type.BLOCK)
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
					if (verticalBlock.hasAdditionalStates()) verticalBlock.renderHighlight(context.matrixStack(), context.consumers().getBuffer(RenderType.LINES), player, (BlockHitResult) hitResult, context.camera(), context.tickCounter());
				}
			}
		}
		return true;
	}

	public static void onServerJoined(ClientPacketListener handler, PacketSender sender, Minecraft client)
	{
		APClientData.setPlacementEnabledAndSynchronize(APConfigs.client().defaultPlacementLogicState.get());
	}

	public static void onClientEndTick(Minecraft mc)
	{
		if (mc.player == null) return;
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
		if ((System.currentTimeMillis() - APClientData.lastSynchronizedTime) > 10000) //synchronize every 10 seconds in case of desync
		{
			APClientData.synchronizePlacementEnabled();
		}
	}
}