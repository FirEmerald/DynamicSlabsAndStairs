package com.firemerald.additionalplacements.client.gui;

import net.minecraft.client.Minecraft;

public class BetterObjectSelectionList<E extends AbstractBetterSelectionList.Entry<E>> extends AbstractBetterSelectionList<E> {
	private boolean inFocus;
	
	public BetterObjectSelectionList(Minecraft minecraft, int x, int y, int width, int height, int normalItemHeight) {
		super(minecraft, x, y, width, height, normalItemHeight);
	}

	public boolean changeFocus(boolean focus) {
		if (!this.inFocus && this.getItemCount() == 0) return false;
		else {
			this.inFocus = !this.inFocus;
			if (this.inFocus && this.getSelected() == null && this.getItemCount() > 0) this.moveSelection(AbstractBetterSelectionList.SelectionDirection.DOWN);
			else if (this.inFocus && this.getSelected() != null) this.refreshSelection();
			return this.inFocus;
		}
	}
}
