package com.firemerald.additionalplacements.client.models.definitions;

import com.firemerald.additionalplacements.AdditionalPlacementsMod;
import com.firemerald.additionalplacements.block.AdditionalCarpetBlock;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;

public class CarpetModels {
	public static final ResourceLocation BASE_MODEL_FOLDER = new ResourceLocation(AdditionalPlacementsMod.MOD_ID, "block/carpets/base");
	public static final ResourceLocation DYNAMIC_MODEL_FOLDER = new ResourceLocation(AdditionalPlacementsMod.MOD_ID, "block/carpets/dynamic");
	public static final ResourceLocation COLUMN_MODEL_FOLDER = new ResourceLocation(AdditionalPlacementsMod.MOD_ID, "block/carpets/column");
	public static final ResourceLocation SIDE_ALL_MODEL_FOLDER = new ResourceLocation(AdditionalPlacementsMod.MOD_ID, "block/carpets/side_all");

	public static final String[] MODELS = new String[] {
			"/carpet"
	};
	
	public static final StateModelDefinition[] MODEL_DEFINITIONS = new StateModelDefinition[5];

	static
	{
		MODEL_DEFINITIONS[Direction.UP.ordinal() - 1] = new StateModelDefinition("/carpet", 180, 0);
		MODEL_DEFINITIONS[Direction.SOUTH.ordinal() - 1] = new StateModelDefinition("/carpet", 270, 180);
		MODEL_DEFINITIONS[Direction.WEST.ordinal() - 1] = new StateModelDefinition("/carpet", 270, 270);
		MODEL_DEFINITIONS[Direction.NORTH.ordinal() - 1] = new StateModelDefinition("/carpet", 270, 0);
		MODEL_DEFINITIONS[Direction.EAST.ordinal() - 1] = new StateModelDefinition("/carpet", 270, 90);
	}
	
	public static StateModelDefinition getModel(BlockState state) {
		return MODEL_DEFINITIONS[state.getValue(AdditionalCarpetBlock.PLACING).ordinal() - 1];
	}
}
