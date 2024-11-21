package com.firemerald.additionalplacements.client.models;

import net.minecraft.client.renderer.model.IModelTransform;
import net.minecraft.client.renderer.model.ModelRotation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.TransformationMatrix;

public class PlacementModelState implements IModelTransform {
	private static final PlacementModelState[][] VALUES = new PlacementModelState[4][4];
	
	static {
		for (int x = 0; x < 4; ++x) for (int y = 0; y < 4; ++y) VALUES[x][y] = new PlacementModelState(x * 90, y * 90);
	}
	
	public static PlacementModelState by(int xRot, int yRot) {
		return VALUES[MathHelper.positiveModulo(xRot / 90, 4)][MathHelper.positiveModulo(yRot / 90, 4)];
	}
	
	private final TransformationMatrix rotation;
	
	private PlacementModelState(int xRot, int yRot) {
		this.rotation = ModelRotation.by(xRot, yRot).getRotation();
	}
	
    @Override
    public TransformationMatrix getRotation() {
        return rotation;
    }

    @Override
    public boolean isUvLocked() {
        return true;
    }
}
