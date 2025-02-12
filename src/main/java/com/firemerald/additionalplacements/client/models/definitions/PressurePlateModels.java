package com.firemerald.additionalplacements.client.models.definitions;

import com.firemerald.additionalplacements.AdditionalPlacementsMod;
import com.firemerald.additionalplacements.block.AdditionalFloorBlock;

import net.minecraft.client.data.models.blockstates.VariantProperties;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.WeightedPressurePlateBlock;
import net.minecraft.world.level.block.state.BlockState;

public class PressurePlateModels {
	public static final ResourceLocation BASE_MODEL_FOLDER = ResourceLocation.tryBuild(AdditionalPlacementsMod.MOD_ID, "block/pressure_plates/base");
	public static final ResourceLocation COLUMN_MODEL_FOLDER = ResourceLocation.tryBuild(AdditionalPlacementsMod.MOD_ID, "block/pressure_plates/column");
	public static final ResourceLocation SIDE_ALL_MODEL_FOLDER = ResourceLocation.tryBuild(AdditionalPlacementsMod.MOD_ID, "block/pressure_plates/side_all");

	public static final String[] MODELS = new String[] {
			"/unpressed",
			"/pressed",
	};

	public static final StateModelDefinition[][] MODEL_DEFINITIONS = new StateModelDefinition[5][2];

	static
	{
		setStateModelDefinitions(Direction.UP, VariantProperties.Rotation.R180, VariantProperties.Rotation.R0);
		setStateModelDefinitions(Direction.SOUTH, VariantProperties.Rotation.R270, VariantProperties.Rotation.R180);
		setStateModelDefinitions(Direction.EAST, VariantProperties.Rotation.R270, VariantProperties.Rotation.R270);
		setStateModelDefinitions(Direction.NORTH, VariantProperties.Rotation.R270, VariantProperties.Rotation.R0);
		setStateModelDefinitions(Direction.WEST, VariantProperties.Rotation.R270, VariantProperties.Rotation.R90);
	}

	static void setStateModelDefinitions(Direction dir, VariantProperties.Rotation rotX, VariantProperties.Rotation rotY)
	{
		StateModelDefinition[] array = MODEL_DEFINITIONS[dir.ordinal() - 1];
		array[0] = new StateModelDefinition("/unpressed", rotX, rotY);
		array[1] = new StateModelDefinition("/pressed", rotX, rotY);
	}

	public static StateModelDefinition getModel(Direction direction, boolean powered) {
		return MODEL_DEFINITIONS[direction.ordinal() - 1][powered ? 1 : 0];
	}

	public static StateModelDefinition getPressurePlateModel(BlockState state) {
		return getModel(state.getValue(AdditionalFloorBlock.PLACING), state.getValue(PressurePlateBlock.POWERED));
	}

	public static StateModelDefinition getModel(Direction direction, int power) {
		return getModel(direction, power > 0);
	}

	public static StateModelDefinition getWeightedPressurePlateModel(BlockState state) {
		return getModel(state.getValue(AdditionalFloorBlock.PLACING), state.getValue(WeightedPressurePlateBlock.POWER));
	}
}
