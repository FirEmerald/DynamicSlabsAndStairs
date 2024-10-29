package com.firemerald.additionalplacements.client.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.narration.NarrationSupplier;
import net.minecraft.network.chat.Component;

public class BetterObjectSelectionList<E extends BetterObjectSelectionList.Entry<E>> extends AbstractBetterSelectionList<E> {
	   private static final Component USAGE_NARRATION = Component.translatable("narration.selection.usage");
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
	
	public void updateNarration(NarrationElementOutput narrationElementOutput) {
		E hovered = this.getHovered();
		if (hovered != null) {
			this.narrateListElementPosition(narrationElementOutput.nest(), hovered);
			hovered.updateNarration(narrationElementOutput);
		} else {
			E selected = this.getSelected();
			if (selected != null) {
				this.narrateListElementPosition(narrationElementOutput.nest(), selected);
				selected.updateNarration(narrationElementOutput);
			}
		}
		if (this.isFocused()) narrationElementOutput.add(NarratedElementType.USAGE, USAGE_NARRATION);
	}
	
	@Environment(EnvType.CLIENT)
	public abstract static class Entry<E extends BetterObjectSelectionList.Entry<E>> extends AbstractBetterSelectionList.Entry<E> implements NarrationSupplier {
		public abstract Component getNarration();

		/**
		 * Updates the narration output with the current narration information.
		 * 
		 * @param pNarrationElementOutput the output to update with narration
		 *                                information.
		 */
		@Override
		public void updateNarration(NarrationElementOutput narrationElementOutput) {
			narrationElementOutput.add(NarratedElementType.TITLE, this.getNarration());
		}
	}
}
