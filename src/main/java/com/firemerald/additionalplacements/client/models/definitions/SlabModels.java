package com.firemerald.additionalplacements.client.models.definitions;

import com.firemerald.additionalplacements.AdditionalPlacementsMod;
import com.firemerald.additionalplacements.block.VerticalSlabBlock;

import net.minecraft.core.Direction.Axis;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SlabType;

public class SlabModels {
	public static final ResourceLocation BASE_MODEL_FOLDER = new ResourceLocation(AdditionalPlacementsMod.MOD_ID, "block/slabs/base");
	public static final ResourceLocation COLUMN_MODEL_FOLDER = new ResourceLocation(AdditionalPlacementsMod.MOD_ID, "block/slabs/column");
	public static final ResourceLocation SIDE_ALL_MODEL_FOLDER = new ResourceLocation(AdditionalPlacementsMod.MOD_ID, "block/slabs/side_all");
	
	public static final String[] MODELS = new String[] {
			"/negative",
			"/positive",
			"/double"
	};
	
	public static final StateModelDefinition[][] MODEL_DEFINITIONS = new StateModelDefinition[2][3];

	static
	{
		MODEL_DEFINITIONS[0][SlabType.BOTTOM.ordinal()] = new StateModelDefinition("/negative", 270);
		MODEL_DEFINITIONS[0][SlabType.TOP.ordinal()] = new StateModelDefinition("/positive", 270);
		MODEL_DEFINITIONS[0][SlabType.DOUBLE.ordinal()] = new StateModelDefinition("/double", 270);
		MODEL_DEFINITIONS[1][SlabType.BOTTOM.ordinal()] = new StateModelDefinition("/negative", 0);
		MODEL_DEFINITIONS[1][SlabType.TOP.ordinal()] = new StateModelDefinition("/positive", 0);
		MODEL_DEFINITIONS[1][SlabType.DOUBLE.ordinal()] = new StateModelDefinition("/double", 0);
	}

	public static StateModelDefinition getModel(BlockState state) {
		return MODEL_DEFINITIONS[state.getValue(VerticalSlabBlock.AXIS) == Axis.X ? 0 : 1][state.getValue(SlabBlock.TYPE).ordinal()];
	}
}
