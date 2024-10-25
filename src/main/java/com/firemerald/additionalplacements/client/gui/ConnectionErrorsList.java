package com.firemerald.additionalplacements.client.gui;

import java.util.List;

import com.firemerald.additionalplacements.client.gui.screen.ConnectionErrorsScreen;
import com.firemerald.additionalplacements.util.MessageTree;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.IReorderingProcessor;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class ConnectionErrorsList extends BetterObjectSelectionList<ConnectionErrorsList.Entry> {
	public ConnectionErrorsList(ConnectionErrorsScreen screen, int x, int y, int width, int height, MessageTree rootError, boolean wasSinglePlayer) {
		super(screen.getMinecraft(), x, y, width, height, screen.getFont().lineHeight + 4);
		this.setRenderHeader(true, 2);
		int spaceWidth = screen.getFont().width("  ");
		if (wasSinglePlayer) addEntry(new Entry(screen, new TranslationTextComponent("msg.additionalplacements.local_world_notice"), 0));
		rootError.forEach((message, level) -> addEntry(new Entry(screen, message, level * spaceWidth)), 0);
	}

	public class Entry extends BetterObjectSelectionList.Entry<Entry> {
		private final ConnectionErrorsScreen screen;
		private final ITextComponent contents;
		private List<IReorderingProcessor> split;
		private final int tabulation;
		
		public Entry(ConnectionErrorsScreen screen, ITextComponent contents, int tabulation) {
			this.screen = screen;
			this.contents = contents;
			this.tabulation = tabulation;
			updateContents();
		}
		
		public void updateContents() {
			split = screen.getFont().split(contents, ConnectionErrorsList.this.getRowWidth() - (tabulation + 10));
		}

		@Override
		public int getHeight() {
			return split.size() * screen.getFont().lineHeight + 4;
		}

		@Override
		public void render(MatrixStack poseStack, int top, int left, int width, int height, int mouseX, int mouseY, boolean hovering, float partialTick) {
			FontRenderer font = screen.getFont();
            int y = top + 2;
            for (IReorderingProcessor string : split) {
            	font.draw(poseStack, string, left + 5 + tabulation, y, 0xFFFFFF);
                y += font.lineHeight;
            }
		}
	}
}
