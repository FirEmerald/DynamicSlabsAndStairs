package com.firemerald.additionalplacements.network.client;

import java.util.List;

import org.apache.commons.lang3.tuple.Triple;

import com.firemerald.additionalplacements.AdditionalPlacementsMod;
import com.firemerald.additionalplacements.client.gui.screen.ConnectionErrorsScreen;
import com.firemerald.additionalplacements.util.BufferUtils;
import com.firemerald.additionalplacements.util.MessageTree;
import com.mojang.realmsclient.RealmsMainScreen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.MainMenuScreen;
import net.minecraft.client.gui.screen.MultiplayerScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.network.NetworkEvent;

public class ConfigurationCheckFailedPacket extends ClientLoginPacket
{
	private final List<Triple<ResourceLocation, List<MessageTree>, List<MessageTree>>> compiledErrors;
	
	public ConfigurationCheckFailedPacket(List<Triple<ResourceLocation, List<MessageTree>, List<MessageTree>>> compiledErrors) {
		this.compiledErrors = compiledErrors;
	}

	public ConfigurationCheckFailedPacket(PacketBuffer buf)
	{
		compiledErrors = BufferUtils.readList(buf, buf2 -> {
			ResourceLocation id = buf2.readResourceLocation();
			List<MessageTree> clientErrors = BufferUtils.readList(buf2, MessageTree::new);
			List<MessageTree> serverErrors = BufferUtils.readList(buf2, MessageTree::new);
			return Triple.of(id, clientErrors, serverErrors);
		});
	}

	@Override
	public void write(PacketBuffer buf)
	{
		BufferUtils.writeCollection(buf, compiledErrors, (buf2, data) -> {
			buf2.writeResourceLocation(data.getLeft());
			BufferUtils.writeCollection(buf2, data.getMiddle(), MessageTree::write);
			BufferUtils.writeCollection(buf2, data.getRight(), MessageTree::write);
		});
	}

	@Override
	public void handle(NetworkEvent.Context context)
	{
		context.setPacketHandled(true);
		MessageTree rootError = new MessageTree(new TranslationTextComponent("msg.additionalplacements.errors.type"));
		compiledErrors.forEach(data -> {
			MessageTree typeError = new MessageTree(new StringTextComponent(data.getLeft().toString())); //TODO friendly name?
			List<MessageTree> clientErrors = data.getMiddle();
			if (!clientErrors.isEmpty()) {
				MessageTree clientError = new MessageTree(new TranslationTextComponent("msg.additionalplacements.errors.client"), clientErrors);
				typeError.children.add(clientError);
			}
			List<MessageTree> serverErrors = data.getRight();
			if (!serverErrors.isEmpty()) {
				MessageTree serverError = new MessageTree(new TranslationTextComponent("msg.additionalplacements.errors.server"), serverErrors);
				typeError.children.add(serverError);
			}
			rootError.children.add(typeError);
		});
		rootError.output(AdditionalPlacementsMod.LOGGER::warn);
		//TODO move disconnect to a point that works?
		context.enqueueWork(() -> {
			Minecraft minecraft = Minecraft.getInstance();
			Screen desScreen = new MainMenuScreen();
			boolean wasSinglePlayer;
			if (!minecraft.isLocalServer()) {
				wasSinglePlayer = false;
				if (minecraft.isConnectedToRealms()) {
					desScreen = new RealmsMainScreen(desScreen);
				} else {
					desScreen = new MultiplayerScreen(desScreen);
				}
			} else wasSinglePlayer = true;
			minecraft.clearLevel(new ConnectionErrorsScreen(rootError, desScreen, wasSinglePlayer));
		});
	}
}