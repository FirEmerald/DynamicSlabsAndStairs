package com.firemerald.additionalplacements.client.gui;

import java.util.List;

import com.firemerald.additionalplacements.client.gui.screen.ConnectionErrorsScreen;
import com.firemerald.additionalplacements.util.MessageTree;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;

public class ConnectionErrorsList extends BetterObjectSelectionList<ConnectionErrorsList.Entry> {
	public ConnectionErrorsList(ConnectionErrorsScreen screen, int x, int y, int width, int height, MessageTree rootError) {
		super(screen.getMinecraft(), x, y, width, height, screen.getFont().lineHeight + 4);
		this.setRenderHeader(true, 2);
		int spaceWidth = screen.getFont().width("  ");
		rootError.forEach((message, level) -> addEntry(new Entry(screen, message, level * spaceWidth)), 0);
	}

	public class Entry extends BetterObjectSelectionList.Entry<Entry> {
		private final ConnectionErrorsScreen screen;
		private final Component contents;
		private List<FormattedCharSequence> split;
		private final int tabulation;

		public Entry(ConnectionErrorsScreen screen, Component contents, int tabulation) {
			this.screen = screen;
			this.contents = contents;
			this.tabulation = tabulation;
			updateContents();
		}

		@Override
		public Component getNarration() {
			return contents;
		}

		public void updateContents() {
			split = screen.getFont().split(contents, ConnectionErrorsList.this.getRowWidth() - (tabulation + 10));
		}

		@Override
		public int getHeight() {
			return split.size() * screen.getFont().lineHeight + 4;
		}

		@Override
		public void render(GuiGraphics guiGraphics, int top, int left, int width, int height, int mouseX, int mouseY, boolean hovering, float partialTick) {
            Font font = screen.getFont();
            int y = top + 2;
            for (FormattedCharSequence string : split) {
            	guiGraphics.drawString(font, string, left + 5 + tabulation, y, 0xFFFFFF, false);
                y += font.lineHeight;
            }
		}
	}
}
