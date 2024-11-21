package com.firemerald.additionalplacements.client.models;

import com.mojang.math.Transformation;

import net.minecraft.client.resources.model.BlockModelRotation;
import net.minecraft.client.resources.model.ModelState;
import net.minecraft.util.Mth;

public class PlacementModelState implements ModelState {
	private static final PlacementModelState[][] VALUES = new PlacementModelState[4][4];
	
	static {
		for (int x = 0; x < 4; ++x) for (int y = 0; y < 4; ++y) VALUES[x][y] = new PlacementModelState(x * 90, y * 90);
	}
	
	public static PlacementModelState by(int xRot, int yRot) {
		return VALUES[Mth.positiveModulo(xRot / 90, 4)][Mth.positiveModulo(yRot / 90, 4)];
	}
	
	private final Transformation rotation;
	
	private PlacementModelState(int xRot, int yRot) {
		this.rotation = BlockModelRotation.by(xRot, yRot).getRotation();
	}
	
    @Override
    public Transformation getRotation() {
        return rotation;
    }

    @Override
    public boolean isUvLocked() {
        return true;
    }
}
