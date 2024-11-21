package com.firemerald.additionalplacements.client.models.definitions;

import com.firemerald.additionalplacements.AdditionalPlacementsMod;
import com.firemerald.additionalplacements.block.VerticalStairBlock;
import com.firemerald.additionalplacements.util.stairs.CompressedStairFacing;
import com.firemerald.additionalplacements.util.stairs.CompressedStairShape;
import com.firemerald.additionalplacements.util.stairs.StairConnections;

import net.minecraft.block.BlockState;
import net.minecraft.util.ResourceLocation;

public class StairModels {
	public static final ResourceLocation BASE_MODEL_FOLDER = new ResourceLocation(AdditionalPlacementsMod.MOD_ID, "block/stairs/base");
	public static final ResourceLocation COLUMN_MODEL_FOLDER = new ResourceLocation(AdditionalPlacementsMod.MOD_ID, "block/stairs/column");
	public static final ResourceLocation SIDE_ALL_MODEL_FOLDER = new ResourceLocation(AdditionalPlacementsMod.MOD_ID, "block/stairs/side_all");

	public static final String[] MODELS = new String[] {
			"/top/straight",
			"/top/inner",
			"/top/outer_back",
			"/top/outer_bottom_left",
			"/top/outer_bottom_right",
			"/top/outer_both",
			"/top/twist_left",
			"/top/twist_right",
			"/bottom/straight",
			"/bottom/inner",
			"/bottom/outer_back",
			"/bottom/outer_top_left",
			"/bottom/outer_top_right",
			"/bottom/outer_both",
			"/bottom/twist_left",
			"/bottom/twist_right",
			"/side/straight",
			"/side/twist_left",
			"/side/twist_right"
	};
	
	public static final StateModelDefinition[][] MODEL_DEFINITIONS = new StateModelDefinition[8][40];

	static
	{
		/*
		setUpStairModelDefs(ComplexFacing.SOUTH_UP, ComplexFacing.UP_SOUTH, 0);
		setUpStairModelDefs(ComplexFacing.WEST_UP, ComplexFacing.UP_WEST, 90);
		setUpStairModelDefs(ComplexFacing.NORTH_UP, ComplexFacing.UP_NORTH, 180);
		setUpStairModelDefs(ComplexFacing.EAST_UP, ComplexFacing.UP_EAST, 270);

		setDownStairModelDefs(ComplexFacing.SOUTH_DOWN, ComplexFacing.DOWN_SOUTH, 0);
		setDownStairModelDefs(ComplexFacing.WEST_DOWN, ComplexFacing.DOWN_WEST, 90);
		setDownStairModelDefs(ComplexFacing.NORTH_DOWN, ComplexFacing.DOWN_NORTH, 180);
		setDownStairModelDefs(ComplexFacing.EAST_DOWN, ComplexFacing.DOWN_EAST, 270);

		setVerticalStairModelDefs(ComplexFacing.SOUTH_EAST, ComplexFacing.EAST_SOUTH, 0);
		setVerticalStairModelDefs(ComplexFacing.WEST_SOUTH, ComplexFacing.SOUTH_WEST, 90);
		setVerticalStairModelDefs(ComplexFacing.NORTH_WEST, ComplexFacing.WEST_NORTH, 180);
		setVerticalStairModelDefs(ComplexFacing.EAST_NORTH, ComplexFacing.NORTH_EAST, 270);
		
		Map<StateModelDefinition, List<Pair<CompressedStairFacing, CompressedStairShape>>> map = new LinkedHashMap<>();
		for (String model : MODELS) for (int i = 0; i < 360; i += 90) map.put(new StateModelDefinition(model, i), new ArrayList<>());
		for (CompressedStairFacing facing : CompressedStairFacing.ALL_FACINGS) for (CompressedStairShape shape : CompressedStairShape.ALL_SHAPES) {
			map.get(MODEL_DEFINITIONS[facing.ordinal()][shape.ordinal()]).add(Pair.of(facing, shape));
		}
		map.forEach((model, states) -> {
			StringJoiner joiner = new StringJoiner("\n");
			states.forEach(state -> {
				joiner.add("MODEL_DEFINITIONS[CompressedStairFacing." + state.getLeft().name() + ".ordinal()][CompressedStairShape." + state.getRight().name() + ".ordinal()] = ");
			});
			System.out.println(joiner.toString() + "new StateModelDefinition(\"" + model.model() + "\", " + model.yRotation() + ");");
		});
		*/
		
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_UP_EAST.ordinal()][CompressedStairShape.FLIPPED_STRAIGHT.ordinal()] = new StateModelDefinition("/top/straight", 0);
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_UP_SOUTH.ordinal()][CompressedStairShape.FLIPPED_STRAIGHT.ordinal()] = new StateModelDefinition("/top/straight", 90);
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_UP_WEST.ordinal()][CompressedStairShape.FLIPPED_STRAIGHT.ordinal()] = new StateModelDefinition("/top/straight", 180);
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_UP_NORTH.ordinal()][CompressedStairShape.FLIPPED_STRAIGHT.ordinal()] = new StateModelDefinition("/top/straight", 270);
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_UP_EAST.ordinal()][CompressedStairShape.NORMAL_INNER_TOP_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_UP_EAST.ordinal()][CompressedStairShape.NORMAL_INNER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_UP_EAST.ordinal()][CompressedStairShape.FLIPPED_INNER_FRONT_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_UP_EAST.ordinal()][CompressedStairShape.FLIPPED_INNER_TOP_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_UP_EAST.ordinal()][CompressedStairShape.FLIPPED_INNER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_UP_EAST.ordinal()][CompressedStairShape.VERTICAL_INNER_FRONT_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_UP_EAST.ordinal()][CompressedStairShape.VERTICAL_INNER_TOP_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_UP_EAST.ordinal()][CompressedStairShape.VERTICAL_INNER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_UP_NORTH.ordinal()][CompressedStairShape.NORMAL_INNER_TOP_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_UP_NORTH.ordinal()][CompressedStairShape.NORMAL_INNER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_UP_NORTH.ordinal()][CompressedStairShape.FLIPPED_INNER_FRONT_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_UP_NORTH.ordinal()][CompressedStairShape.FLIPPED_INNER_TOP_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_UP_NORTH.ordinal()][CompressedStairShape.FLIPPED_INNER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_DOWN_SOUTH.ordinal()][CompressedStairShape.VERTICAL_INNER_FRONT_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_DOWN_SOUTH.ordinal()][CompressedStairShape.VERTICAL_INNER_TOP_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_DOWN_SOUTH.ordinal()][CompressedStairShape.VERTICAL_INNER_BOTH_LEFT.ordinal()] = new StateModelDefinition("/top/inner", 0);
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_UP_EAST.ordinal()][CompressedStairShape.NORMAL_INNER_TOP_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_UP_EAST.ordinal()][CompressedStairShape.NORMAL_INNER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_UP_EAST.ordinal()][CompressedStairShape.FLIPPED_INNER_FRONT_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_UP_EAST.ordinal()][CompressedStairShape.FLIPPED_INNER_TOP_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_UP_EAST.ordinal()][CompressedStairShape.FLIPPED_INNER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_UP_SOUTH.ordinal()][CompressedStairShape.NORMAL_INNER_TOP_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_UP_SOUTH.ordinal()][CompressedStairShape.NORMAL_INNER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_UP_SOUTH.ordinal()][CompressedStairShape.FLIPPED_INNER_FRONT_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_UP_SOUTH.ordinal()][CompressedStairShape.FLIPPED_INNER_TOP_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_UP_SOUTH.ordinal()][CompressedStairShape.FLIPPED_INNER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_UP_SOUTH.ordinal()][CompressedStairShape.VERTICAL_INNER_FRONT_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_UP_SOUTH.ordinal()][CompressedStairShape.VERTICAL_INNER_TOP_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_UP_SOUTH.ordinal()][CompressedStairShape.VERTICAL_INNER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_DOWN_WEST.ordinal()][CompressedStairShape.VERTICAL_INNER_FRONT_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_DOWN_WEST.ordinal()][CompressedStairShape.VERTICAL_INNER_TOP_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_DOWN_WEST.ordinal()][CompressedStairShape.VERTICAL_INNER_BOTH_LEFT.ordinal()] = new StateModelDefinition("/top/inner", 90);
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_UP_SOUTH.ordinal()][CompressedStairShape.NORMAL_INNER_TOP_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_UP_SOUTH.ordinal()][CompressedStairShape.NORMAL_INNER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_UP_SOUTH.ordinal()][CompressedStairShape.FLIPPED_INNER_FRONT_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_UP_SOUTH.ordinal()][CompressedStairShape.FLIPPED_INNER_TOP_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_UP_SOUTH.ordinal()][CompressedStairShape.FLIPPED_INNER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_UP_WEST.ordinal()][CompressedStairShape.NORMAL_INNER_TOP_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_UP_WEST.ordinal()][CompressedStairShape.NORMAL_INNER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_UP_WEST.ordinal()][CompressedStairShape.FLIPPED_INNER_FRONT_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_UP_WEST.ordinal()][CompressedStairShape.FLIPPED_INNER_TOP_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_UP_WEST.ordinal()][CompressedStairShape.FLIPPED_INNER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_UP_WEST.ordinal()][CompressedStairShape.VERTICAL_INNER_FRONT_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_UP_WEST.ordinal()][CompressedStairShape.VERTICAL_INNER_TOP_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_UP_WEST.ordinal()][CompressedStairShape.VERTICAL_INNER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_DOWN_NORTH.ordinal()][CompressedStairShape.VERTICAL_INNER_FRONT_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_DOWN_NORTH.ordinal()][CompressedStairShape.VERTICAL_INNER_TOP_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_DOWN_NORTH.ordinal()][CompressedStairShape.VERTICAL_INNER_BOTH_LEFT.ordinal()] = new StateModelDefinition("/top/inner", 180);
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_UP_WEST.ordinal()][CompressedStairShape.NORMAL_INNER_TOP_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_UP_WEST.ordinal()][CompressedStairShape.NORMAL_INNER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_UP_WEST.ordinal()][CompressedStairShape.FLIPPED_INNER_FRONT_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_UP_WEST.ordinal()][CompressedStairShape.FLIPPED_INNER_TOP_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_UP_WEST.ordinal()][CompressedStairShape.FLIPPED_INNER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_UP_NORTH.ordinal()][CompressedStairShape.NORMAL_INNER_TOP_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_UP_NORTH.ordinal()][CompressedStairShape.NORMAL_INNER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_UP_NORTH.ordinal()][CompressedStairShape.FLIPPED_INNER_FRONT_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_UP_NORTH.ordinal()][CompressedStairShape.FLIPPED_INNER_TOP_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_UP_NORTH.ordinal()][CompressedStairShape.FLIPPED_INNER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_UP_NORTH.ordinal()][CompressedStairShape.VERTICAL_INNER_FRONT_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_UP_NORTH.ordinal()][CompressedStairShape.VERTICAL_INNER_TOP_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_UP_NORTH.ordinal()][CompressedStairShape.VERTICAL_INNER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_DOWN_EAST.ordinal()][CompressedStairShape.VERTICAL_INNER_FRONT_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_DOWN_EAST.ordinal()][CompressedStairShape.VERTICAL_INNER_TOP_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_DOWN_EAST.ordinal()][CompressedStairShape.VERTICAL_INNER_BOTH_LEFT.ordinal()] = new StateModelDefinition("/top/inner", 270);
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_UP_EAST.ordinal()][CompressedStairShape.FLIPPED_OUTER_BOTTOM_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_UP_NORTH.ordinal()][CompressedStairShape.FLIPPED_OUTER_BOTTOM_LEFT.ordinal()] = new StateModelDefinition("/top/outer_back", 0);
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_UP_EAST.ordinal()][CompressedStairShape.FLIPPED_OUTER_BOTTOM_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_UP_SOUTH.ordinal()][CompressedStairShape.FLIPPED_OUTER_BOTTOM_RIGHT.ordinal()] = new StateModelDefinition("/top/outer_back", 90);
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_UP_SOUTH.ordinal()][CompressedStairShape.FLIPPED_OUTER_BOTTOM_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_UP_WEST.ordinal()][CompressedStairShape.FLIPPED_OUTER_BOTTOM_RIGHT.ordinal()] = new StateModelDefinition("/top/outer_back", 180);
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_UP_WEST.ordinal()][CompressedStairShape.FLIPPED_OUTER_BOTTOM_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_UP_NORTH.ordinal()][CompressedStairShape.FLIPPED_OUTER_BOTTOM_RIGHT.ordinal()] = new StateModelDefinition("/top/outer_back", 270);
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_UP_EAST.ordinal()][CompressedStairShape.NORMAL_OUTER_BOTTOM_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_UP_EAST.ordinal()][CompressedStairShape.FLIPPED_OUTER_BACK_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_UP_EAST.ordinal()][CompressedStairShape.VERTICAL_OUTER_BOTTOM_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_DOWN_SOUTH.ordinal()][CompressedStairShape.VERTICAL_OUTER_BACK_LEFT.ordinal()] = new StateModelDefinition("/top/outer_bottom_left", 0);
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_UP_SOUTH.ordinal()][CompressedStairShape.NORMAL_OUTER_BOTTOM_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_UP_SOUTH.ordinal()][CompressedStairShape.FLIPPED_OUTER_BACK_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_UP_SOUTH.ordinal()][CompressedStairShape.VERTICAL_OUTER_BOTTOM_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_DOWN_WEST.ordinal()][CompressedStairShape.VERTICAL_OUTER_BACK_LEFT.ordinal()] = new StateModelDefinition("/top/outer_bottom_left", 90);
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_UP_WEST.ordinal()][CompressedStairShape.NORMAL_OUTER_BOTTOM_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_UP_WEST.ordinal()][CompressedStairShape.FLIPPED_OUTER_BACK_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_UP_WEST.ordinal()][CompressedStairShape.VERTICAL_OUTER_BOTTOM_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_DOWN_NORTH.ordinal()][CompressedStairShape.VERTICAL_OUTER_BACK_LEFT.ordinal()] = new StateModelDefinition("/top/outer_bottom_left", 180);
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_UP_NORTH.ordinal()][CompressedStairShape.NORMAL_OUTER_BOTTOM_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_UP_NORTH.ordinal()][CompressedStairShape.FLIPPED_OUTER_BACK_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_UP_NORTH.ordinal()][CompressedStairShape.VERTICAL_OUTER_BOTTOM_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_DOWN_EAST.ordinal()][CompressedStairShape.VERTICAL_OUTER_BACK_LEFT.ordinal()] = new StateModelDefinition("/top/outer_bottom_left", 270);
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_UP_EAST.ordinal()][CompressedStairShape.NORMAL_OUTER_BOTTOM_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_UP_EAST.ordinal()][CompressedStairShape.FLIPPED_OUTER_BACK_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_UP_SOUTH.ordinal()][CompressedStairShape.VERTICAL_OUTER_BACK_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_DOWN_WEST.ordinal()][CompressedStairShape.VERTICAL_OUTER_BOTTOM_LEFT.ordinal()] = new StateModelDefinition("/top/outer_bottom_right", 0);
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_UP_SOUTH.ordinal()][CompressedStairShape.NORMAL_OUTER_BOTTOM_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_UP_SOUTH.ordinal()][CompressedStairShape.FLIPPED_OUTER_BACK_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_UP_WEST.ordinal()][CompressedStairShape.VERTICAL_OUTER_BACK_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_DOWN_NORTH.ordinal()][CompressedStairShape.VERTICAL_OUTER_BOTTOM_LEFT.ordinal()] = new StateModelDefinition("/top/outer_bottom_right", 90);
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_UP_WEST.ordinal()][CompressedStairShape.NORMAL_OUTER_BOTTOM_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_UP_WEST.ordinal()][CompressedStairShape.FLIPPED_OUTER_BACK_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_UP_NORTH.ordinal()][CompressedStairShape.VERTICAL_OUTER_BACK_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_DOWN_EAST.ordinal()][CompressedStairShape.VERTICAL_OUTER_BOTTOM_LEFT.ordinal()] = new StateModelDefinition("/top/outer_bottom_right", 180);
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_UP_EAST.ordinal()][CompressedStairShape.VERTICAL_OUTER_BACK_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_UP_NORTH.ordinal()][CompressedStairShape.NORMAL_OUTER_BOTTOM_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_UP_NORTH.ordinal()][CompressedStairShape.FLIPPED_OUTER_BACK_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_DOWN_SOUTH.ordinal()][CompressedStairShape.VERTICAL_OUTER_BOTTOM_LEFT.ordinal()] = new StateModelDefinition("/top/outer_bottom_right", 270);
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_UP_EAST.ordinal()][CompressedStairShape.NORMAL_OUTER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_UP_EAST.ordinal()][CompressedStairShape.FLIPPED_OUTER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_UP_EAST.ordinal()][CompressedStairShape.VERTICAL_OUTER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_UP_NORTH.ordinal()][CompressedStairShape.NORMAL_OUTER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_UP_NORTH.ordinal()][CompressedStairShape.FLIPPED_OUTER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_DOWN_SOUTH.ordinal()][CompressedStairShape.VERTICAL_OUTER_BOTH_LEFT.ordinal()] = new StateModelDefinition("/top/outer_both", 0);
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_UP_EAST.ordinal()][CompressedStairShape.NORMAL_OUTER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_UP_EAST.ordinal()][CompressedStairShape.FLIPPED_OUTER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_UP_SOUTH.ordinal()][CompressedStairShape.NORMAL_OUTER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_UP_SOUTH.ordinal()][CompressedStairShape.FLIPPED_OUTER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_UP_SOUTH.ordinal()][CompressedStairShape.VERTICAL_OUTER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_DOWN_WEST.ordinal()][CompressedStairShape.VERTICAL_OUTER_BOTH_LEFT.ordinal()] = new StateModelDefinition("/top/outer_both", 90);
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_UP_SOUTH.ordinal()][CompressedStairShape.NORMAL_OUTER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_UP_SOUTH.ordinal()][CompressedStairShape.FLIPPED_OUTER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_UP_WEST.ordinal()][CompressedStairShape.NORMAL_OUTER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_UP_WEST.ordinal()][CompressedStairShape.FLIPPED_OUTER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_UP_WEST.ordinal()][CompressedStairShape.VERTICAL_OUTER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_DOWN_NORTH.ordinal()][CompressedStairShape.VERTICAL_OUTER_BOTH_LEFT.ordinal()] = new StateModelDefinition("/top/outer_both", 180);
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_UP_WEST.ordinal()][CompressedStairShape.NORMAL_OUTER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_UP_WEST.ordinal()][CompressedStairShape.FLIPPED_OUTER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_UP_NORTH.ordinal()][CompressedStairShape.NORMAL_OUTER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_UP_NORTH.ordinal()][CompressedStairShape.FLIPPED_OUTER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_UP_NORTH.ordinal()][CompressedStairShape.VERTICAL_OUTER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_DOWN_EAST.ordinal()][CompressedStairShape.VERTICAL_OUTER_BOTH_LEFT.ordinal()] = new StateModelDefinition("/top/outer_both", 270);
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_UP_EAST.ordinal()][CompressedStairShape.NORMAL_OUTER_BACK_RIGHT_BOTTOM_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_UP_EAST.ordinal()][CompressedStairShape.FLIPPED_OUTER_BACK_RIGHT_BOTTOM_LEFT.ordinal()] = new StateModelDefinition("/top/twist_left", 0);
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_UP_SOUTH.ordinal()][CompressedStairShape.NORMAL_OUTER_BACK_RIGHT_BOTTOM_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_UP_SOUTH.ordinal()][CompressedStairShape.FLIPPED_OUTER_BACK_RIGHT_BOTTOM_LEFT.ordinal()] = new StateModelDefinition("/top/twist_left", 90);
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_UP_WEST.ordinal()][CompressedStairShape.NORMAL_OUTER_BACK_RIGHT_BOTTOM_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_UP_WEST.ordinal()][CompressedStairShape.FLIPPED_OUTER_BACK_RIGHT_BOTTOM_LEFT.ordinal()] = new StateModelDefinition("/top/twist_left", 180);
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_UP_NORTH.ordinal()][CompressedStairShape.NORMAL_OUTER_BACK_RIGHT_BOTTOM_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_UP_NORTH.ordinal()][CompressedStairShape.FLIPPED_OUTER_BACK_RIGHT_BOTTOM_LEFT.ordinal()] = new StateModelDefinition("/top/twist_left", 270);
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_UP_EAST.ordinal()][CompressedStairShape.NORMAL_OUTER_BACK_LEFT_BOTTOM_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_UP_EAST.ordinal()][CompressedStairShape.FLIPPED_OUTER_BACK_LEFT_BOTTOM_RIGHT.ordinal()] = new StateModelDefinition("/top/twist_right", 0);
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_UP_SOUTH.ordinal()][CompressedStairShape.NORMAL_OUTER_BACK_LEFT_BOTTOM_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_UP_SOUTH.ordinal()][CompressedStairShape.FLIPPED_OUTER_BACK_LEFT_BOTTOM_RIGHT.ordinal()] = new StateModelDefinition("/top/twist_right", 90);
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_UP_WEST.ordinal()][CompressedStairShape.NORMAL_OUTER_BACK_LEFT_BOTTOM_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_UP_WEST.ordinal()][CompressedStairShape.FLIPPED_OUTER_BACK_LEFT_BOTTOM_RIGHT.ordinal()] = new StateModelDefinition("/top/twist_right", 180);
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_UP_NORTH.ordinal()][CompressedStairShape.NORMAL_OUTER_BACK_LEFT_BOTTOM_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_UP_NORTH.ordinal()][CompressedStairShape.FLIPPED_OUTER_BACK_LEFT_BOTTOM_RIGHT.ordinal()] = new StateModelDefinition("/top/twist_right", 270);
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_DOWN_WEST.ordinal()][CompressedStairShape.FLIPPED_STRAIGHT.ordinal()] = new StateModelDefinition("/bottom/straight", 0);
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_DOWN_NORTH.ordinal()][CompressedStairShape.FLIPPED_STRAIGHT.ordinal()] = new StateModelDefinition("/bottom/straight", 90);
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_DOWN_EAST.ordinal()][CompressedStairShape.FLIPPED_STRAIGHT.ordinal()] = new StateModelDefinition("/bottom/straight", 180);
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_DOWN_SOUTH.ordinal()][CompressedStairShape.FLIPPED_STRAIGHT.ordinal()] = new StateModelDefinition("/bottom/straight", 270);
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_UP_EAST.ordinal()][CompressedStairShape.VERTICAL_INNER_FRONT_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_UP_EAST.ordinal()][CompressedStairShape.VERTICAL_INNER_TOP_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_UP_EAST.ordinal()][CompressedStairShape.VERTICAL_INNER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_DOWN_WEST.ordinal()][CompressedStairShape.NORMAL_INNER_TOP_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_DOWN_WEST.ordinal()][CompressedStairShape.NORMAL_INNER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_DOWN_WEST.ordinal()][CompressedStairShape.FLIPPED_INNER_FRONT_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_DOWN_WEST.ordinal()][CompressedStairShape.FLIPPED_INNER_TOP_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_DOWN_WEST.ordinal()][CompressedStairShape.FLIPPED_INNER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_DOWN_SOUTH.ordinal()][CompressedStairShape.NORMAL_INNER_TOP_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_DOWN_SOUTH.ordinal()][CompressedStairShape.NORMAL_INNER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_DOWN_SOUTH.ordinal()][CompressedStairShape.FLIPPED_INNER_FRONT_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_DOWN_SOUTH.ordinal()][CompressedStairShape.FLIPPED_INNER_TOP_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_DOWN_SOUTH.ordinal()][CompressedStairShape.FLIPPED_INNER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_DOWN_SOUTH.ordinal()][CompressedStairShape.VERTICAL_INNER_FRONT_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_DOWN_SOUTH.ordinal()][CompressedStairShape.VERTICAL_INNER_TOP_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_DOWN_SOUTH.ordinal()][CompressedStairShape.VERTICAL_INNER_BOTH_RIGHT.ordinal()] = new StateModelDefinition("/bottom/inner", 0);
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_UP_SOUTH.ordinal()][CompressedStairShape.VERTICAL_INNER_FRONT_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_UP_SOUTH.ordinal()][CompressedStairShape.VERTICAL_INNER_TOP_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_UP_SOUTH.ordinal()][CompressedStairShape.VERTICAL_INNER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_DOWN_WEST.ordinal()][CompressedStairShape.NORMAL_INNER_TOP_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_DOWN_WEST.ordinal()][CompressedStairShape.NORMAL_INNER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_DOWN_WEST.ordinal()][CompressedStairShape.FLIPPED_INNER_FRONT_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_DOWN_WEST.ordinal()][CompressedStairShape.FLIPPED_INNER_TOP_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_DOWN_WEST.ordinal()][CompressedStairShape.FLIPPED_INNER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_DOWN_WEST.ordinal()][CompressedStairShape.VERTICAL_INNER_FRONT_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_DOWN_WEST.ordinal()][CompressedStairShape.VERTICAL_INNER_TOP_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_DOWN_WEST.ordinal()][CompressedStairShape.VERTICAL_INNER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_DOWN_NORTH.ordinal()][CompressedStairShape.NORMAL_INNER_TOP_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_DOWN_NORTH.ordinal()][CompressedStairShape.NORMAL_INNER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_DOWN_NORTH.ordinal()][CompressedStairShape.FLIPPED_INNER_FRONT_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_DOWN_NORTH.ordinal()][CompressedStairShape.FLIPPED_INNER_TOP_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_DOWN_NORTH.ordinal()][CompressedStairShape.FLIPPED_INNER_BOTH_LEFT.ordinal()] = new StateModelDefinition("/bottom/inner", 90);
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_UP_WEST.ordinal()][CompressedStairShape.VERTICAL_INNER_FRONT_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_UP_WEST.ordinal()][CompressedStairShape.VERTICAL_INNER_TOP_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_UP_WEST.ordinal()][CompressedStairShape.VERTICAL_INNER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_DOWN_NORTH.ordinal()][CompressedStairShape.NORMAL_INNER_TOP_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_DOWN_NORTH.ordinal()][CompressedStairShape.NORMAL_INNER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_DOWN_NORTH.ordinal()][CompressedStairShape.FLIPPED_INNER_FRONT_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_DOWN_NORTH.ordinal()][CompressedStairShape.FLIPPED_INNER_TOP_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_DOWN_NORTH.ordinal()][CompressedStairShape.FLIPPED_INNER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_DOWN_NORTH.ordinal()][CompressedStairShape.VERTICAL_INNER_FRONT_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_DOWN_NORTH.ordinal()][CompressedStairShape.VERTICAL_INNER_TOP_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_DOWN_NORTH.ordinal()][CompressedStairShape.VERTICAL_INNER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_DOWN_EAST.ordinal()][CompressedStairShape.NORMAL_INNER_TOP_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_DOWN_EAST.ordinal()][CompressedStairShape.NORMAL_INNER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_DOWN_EAST.ordinal()][CompressedStairShape.FLIPPED_INNER_FRONT_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_DOWN_EAST.ordinal()][CompressedStairShape.FLIPPED_INNER_TOP_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_DOWN_EAST.ordinal()][CompressedStairShape.FLIPPED_INNER_BOTH_LEFT.ordinal()] = new StateModelDefinition("/bottom/inner", 180);
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_UP_NORTH.ordinal()][CompressedStairShape.VERTICAL_INNER_FRONT_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_UP_NORTH.ordinal()][CompressedStairShape.VERTICAL_INNER_TOP_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_UP_NORTH.ordinal()][CompressedStairShape.VERTICAL_INNER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_DOWN_EAST.ordinal()][CompressedStairShape.NORMAL_INNER_TOP_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_DOWN_EAST.ordinal()][CompressedStairShape.NORMAL_INNER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_DOWN_EAST.ordinal()][CompressedStairShape.FLIPPED_INNER_FRONT_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_DOWN_EAST.ordinal()][CompressedStairShape.FLIPPED_INNER_TOP_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_DOWN_EAST.ordinal()][CompressedStairShape.FLIPPED_INNER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_DOWN_EAST.ordinal()][CompressedStairShape.VERTICAL_INNER_FRONT_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_DOWN_EAST.ordinal()][CompressedStairShape.VERTICAL_INNER_TOP_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_DOWN_EAST.ordinal()][CompressedStairShape.VERTICAL_INNER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_DOWN_SOUTH.ordinal()][CompressedStairShape.NORMAL_INNER_TOP_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_DOWN_SOUTH.ordinal()][CompressedStairShape.NORMAL_INNER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_DOWN_SOUTH.ordinal()][CompressedStairShape.FLIPPED_INNER_FRONT_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_DOWN_SOUTH.ordinal()][CompressedStairShape.FLIPPED_INNER_TOP_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_DOWN_SOUTH.ordinal()][CompressedStairShape.FLIPPED_INNER_BOTH_LEFT.ordinal()] = new StateModelDefinition("/bottom/inner", 270);
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_DOWN_WEST.ordinal()][CompressedStairShape.FLIPPED_OUTER_BOTTOM_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_DOWN_SOUTH.ordinal()][CompressedStairShape.FLIPPED_OUTER_BOTTOM_RIGHT.ordinal()] = new StateModelDefinition("/bottom/outer_back", 0);
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_DOWN_WEST.ordinal()][CompressedStairShape.FLIPPED_OUTER_BOTTOM_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_DOWN_NORTH.ordinal()][CompressedStairShape.FLIPPED_OUTER_BOTTOM_LEFT.ordinal()] = new StateModelDefinition("/bottom/outer_back", 90);
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_DOWN_NORTH.ordinal()][CompressedStairShape.FLIPPED_OUTER_BOTTOM_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_DOWN_EAST.ordinal()][CompressedStairShape.FLIPPED_OUTER_BOTTOM_LEFT.ordinal()] = new StateModelDefinition("/bottom/outer_back", 180);
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_DOWN_EAST.ordinal()][CompressedStairShape.FLIPPED_OUTER_BOTTOM_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_DOWN_SOUTH.ordinal()][CompressedStairShape.FLIPPED_OUTER_BOTTOM_LEFT.ordinal()] = new StateModelDefinition("/bottom/outer_back", 270);
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_UP_EAST.ordinal()][CompressedStairShape.VERTICAL_OUTER_BOTTOM_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_DOWN_WEST.ordinal()][CompressedStairShape.NORMAL_OUTER_BOTTOM_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_DOWN_WEST.ordinal()][CompressedStairShape.FLIPPED_OUTER_BACK_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_DOWN_SOUTH.ordinal()][CompressedStairShape.VERTICAL_OUTER_BACK_RIGHT.ordinal()] = new StateModelDefinition("/bottom/outer_top_left", 0);
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_UP_SOUTH.ordinal()][CompressedStairShape.VERTICAL_OUTER_BOTTOM_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_DOWN_WEST.ordinal()][CompressedStairShape.VERTICAL_OUTER_BACK_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_DOWN_NORTH.ordinal()][CompressedStairShape.NORMAL_OUTER_BOTTOM_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_DOWN_NORTH.ordinal()][CompressedStairShape.FLIPPED_OUTER_BACK_LEFT.ordinal()] = new StateModelDefinition("/bottom/outer_top_left", 90);
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_UP_WEST.ordinal()][CompressedStairShape.VERTICAL_OUTER_BOTTOM_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_DOWN_NORTH.ordinal()][CompressedStairShape.VERTICAL_OUTER_BACK_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_DOWN_EAST.ordinal()][CompressedStairShape.NORMAL_OUTER_BOTTOM_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_DOWN_EAST.ordinal()][CompressedStairShape.FLIPPED_OUTER_BACK_LEFT.ordinal()] = new StateModelDefinition("/bottom/outer_top_left", 180);
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_UP_NORTH.ordinal()][CompressedStairShape.VERTICAL_OUTER_BOTTOM_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_DOWN_EAST.ordinal()][CompressedStairShape.VERTICAL_OUTER_BACK_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_DOWN_SOUTH.ordinal()][CompressedStairShape.NORMAL_OUTER_BOTTOM_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_DOWN_SOUTH.ordinal()][CompressedStairShape.FLIPPED_OUTER_BACK_LEFT.ordinal()] = new StateModelDefinition("/bottom/outer_top_left", 270);
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_UP_SOUTH.ordinal()][CompressedStairShape.VERTICAL_OUTER_BACK_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_DOWN_WEST.ordinal()][CompressedStairShape.NORMAL_OUTER_BOTTOM_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_DOWN_WEST.ordinal()][CompressedStairShape.FLIPPED_OUTER_BACK_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_DOWN_WEST.ordinal()][CompressedStairShape.VERTICAL_OUTER_BOTTOM_RIGHT.ordinal()] = new StateModelDefinition("/bottom/outer_top_right", 0);
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_UP_WEST.ordinal()][CompressedStairShape.VERTICAL_OUTER_BACK_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_DOWN_NORTH.ordinal()][CompressedStairShape.NORMAL_OUTER_BOTTOM_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_DOWN_NORTH.ordinal()][CompressedStairShape.FLIPPED_OUTER_BACK_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_DOWN_NORTH.ordinal()][CompressedStairShape.VERTICAL_OUTER_BOTTOM_RIGHT.ordinal()] = new StateModelDefinition("/bottom/outer_top_right", 90);
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_UP_NORTH.ordinal()][CompressedStairShape.VERTICAL_OUTER_BACK_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_DOWN_EAST.ordinal()][CompressedStairShape.NORMAL_OUTER_BOTTOM_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_DOWN_EAST.ordinal()][CompressedStairShape.FLIPPED_OUTER_BACK_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_DOWN_EAST.ordinal()][CompressedStairShape.VERTICAL_OUTER_BOTTOM_RIGHT.ordinal()] = new StateModelDefinition("/bottom/outer_top_right", 180);
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_UP_EAST.ordinal()][CompressedStairShape.VERTICAL_OUTER_BACK_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_DOWN_SOUTH.ordinal()][CompressedStairShape.NORMAL_OUTER_BOTTOM_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_DOWN_SOUTH.ordinal()][CompressedStairShape.FLIPPED_OUTER_BACK_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_DOWN_SOUTH.ordinal()][CompressedStairShape.VERTICAL_OUTER_BOTTOM_RIGHT.ordinal()] = new StateModelDefinition("/bottom/outer_top_right", 270);
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_UP_EAST.ordinal()][CompressedStairShape.VERTICAL_OUTER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_DOWN_WEST.ordinal()][CompressedStairShape.NORMAL_OUTER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_DOWN_WEST.ordinal()][CompressedStairShape.FLIPPED_OUTER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_DOWN_SOUTH.ordinal()][CompressedStairShape.NORMAL_OUTER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_DOWN_SOUTH.ordinal()][CompressedStairShape.FLIPPED_OUTER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_DOWN_SOUTH.ordinal()][CompressedStairShape.VERTICAL_OUTER_BOTH_RIGHT.ordinal()] = new StateModelDefinition("/bottom/outer_both", 0);
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_UP_SOUTH.ordinal()][CompressedStairShape.VERTICAL_OUTER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_DOWN_WEST.ordinal()][CompressedStairShape.NORMAL_OUTER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_DOWN_WEST.ordinal()][CompressedStairShape.FLIPPED_OUTER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_DOWN_WEST.ordinal()][CompressedStairShape.VERTICAL_OUTER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_DOWN_NORTH.ordinal()][CompressedStairShape.NORMAL_OUTER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_DOWN_NORTH.ordinal()][CompressedStairShape.FLIPPED_OUTER_BOTH_LEFT.ordinal()] = new StateModelDefinition("/bottom/outer_both", 90);
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_UP_WEST.ordinal()][CompressedStairShape.VERTICAL_OUTER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_DOWN_NORTH.ordinal()][CompressedStairShape.NORMAL_OUTER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_DOWN_NORTH.ordinal()][CompressedStairShape.FLIPPED_OUTER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_DOWN_NORTH.ordinal()][CompressedStairShape.VERTICAL_OUTER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_DOWN_EAST.ordinal()][CompressedStairShape.NORMAL_OUTER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_DOWN_EAST.ordinal()][CompressedStairShape.FLIPPED_OUTER_BOTH_LEFT.ordinal()] = new StateModelDefinition("/bottom/outer_both", 180);
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_UP_NORTH.ordinal()][CompressedStairShape.VERTICAL_OUTER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_DOWN_EAST.ordinal()][CompressedStairShape.NORMAL_OUTER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_DOWN_EAST.ordinal()][CompressedStairShape.FLIPPED_OUTER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_DOWN_EAST.ordinal()][CompressedStairShape.VERTICAL_OUTER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_DOWN_SOUTH.ordinal()][CompressedStairShape.NORMAL_OUTER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_DOWN_SOUTH.ordinal()][CompressedStairShape.FLIPPED_OUTER_BOTH_LEFT.ordinal()] = new StateModelDefinition("/bottom/outer_both", 270);
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_DOWN_WEST.ordinal()][CompressedStairShape.NORMAL_OUTER_BACK_LEFT_BOTTOM_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_DOWN_WEST.ordinal()][CompressedStairShape.FLIPPED_OUTER_BACK_LEFT_BOTTOM_RIGHT.ordinal()] = new StateModelDefinition("/bottom/twist_left", 0);
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_DOWN_NORTH.ordinal()][CompressedStairShape.NORMAL_OUTER_BACK_LEFT_BOTTOM_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_DOWN_NORTH.ordinal()][CompressedStairShape.FLIPPED_OUTER_BACK_LEFT_BOTTOM_RIGHT.ordinal()] = new StateModelDefinition("/bottom/twist_left", 90);
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_DOWN_EAST.ordinal()][CompressedStairShape.NORMAL_OUTER_BACK_LEFT_BOTTOM_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_DOWN_EAST.ordinal()][CompressedStairShape.FLIPPED_OUTER_BACK_LEFT_BOTTOM_RIGHT.ordinal()] = new StateModelDefinition("/bottom/twist_left", 180);
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_DOWN_SOUTH.ordinal()][CompressedStairShape.NORMAL_OUTER_BACK_LEFT_BOTTOM_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_DOWN_SOUTH.ordinal()][CompressedStairShape.FLIPPED_OUTER_BACK_LEFT_BOTTOM_RIGHT.ordinal()] = new StateModelDefinition("/bottom/twist_left", 270);
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_DOWN_WEST.ordinal()][CompressedStairShape.NORMAL_OUTER_BACK_RIGHT_BOTTOM_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_DOWN_WEST.ordinal()][CompressedStairShape.FLIPPED_OUTER_BACK_RIGHT_BOTTOM_LEFT.ordinal()] = new StateModelDefinition("/bottom/twist_right", 0);
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_DOWN_NORTH.ordinal()][CompressedStairShape.NORMAL_OUTER_BACK_RIGHT_BOTTOM_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_DOWN_NORTH.ordinal()][CompressedStairShape.FLIPPED_OUTER_BACK_RIGHT_BOTTOM_LEFT.ordinal()] = new StateModelDefinition("/bottom/twist_right", 90);
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_DOWN_EAST.ordinal()][CompressedStairShape.NORMAL_OUTER_BACK_RIGHT_BOTTOM_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_DOWN_EAST.ordinal()][CompressedStairShape.FLIPPED_OUTER_BACK_RIGHT_BOTTOM_LEFT.ordinal()] = new StateModelDefinition("/bottom/twist_right", 180);
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_DOWN_SOUTH.ordinal()][CompressedStairShape.NORMAL_OUTER_BACK_RIGHT_BOTTOM_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_DOWN_SOUTH.ordinal()][CompressedStairShape.FLIPPED_OUTER_BACK_RIGHT_BOTTOM_LEFT.ordinal()] = new StateModelDefinition("/bottom/twist_right", 270);
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_UP_EAST.ordinal()][CompressedStairShape.VERTICAL_STRAIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_DOWN_SOUTH.ordinal()][CompressedStairShape.VERTICAL_STRAIGHT.ordinal()] = new StateModelDefinition("/side/straight", 0);
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_UP_SOUTH.ordinal()][CompressedStairShape.VERTICAL_STRAIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_DOWN_WEST.ordinal()][CompressedStairShape.VERTICAL_STRAIGHT.ordinal()] = new StateModelDefinition("/side/straight", 90);
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_UP_WEST.ordinal()][CompressedStairShape.VERTICAL_STRAIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_DOWN_NORTH.ordinal()][CompressedStairShape.VERTICAL_STRAIGHT.ordinal()] = new StateModelDefinition("/side/straight", 180);
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_UP_NORTH.ordinal()][CompressedStairShape.VERTICAL_STRAIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_DOWN_EAST.ordinal()][CompressedStairShape.VERTICAL_STRAIGHT.ordinal()] = new StateModelDefinition("/side/straight", 270);
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_UP_EAST.ordinal()][CompressedStairShape.VERTICAL_OUTER_BACK_LEFT_BOTTOM_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_DOWN_SOUTH.ordinal()][CompressedStairShape.VERTICAL_OUTER_BACK_LEFT_BOTTOM_RIGHT.ordinal()] = new StateModelDefinition("/side/twist_left", 0);
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_UP_SOUTH.ordinal()][CompressedStairShape.VERTICAL_OUTER_BACK_LEFT_BOTTOM_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_DOWN_WEST.ordinal()][CompressedStairShape.VERTICAL_OUTER_BACK_LEFT_BOTTOM_RIGHT.ordinal()] = new StateModelDefinition("/side/twist_left", 90);
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_UP_WEST.ordinal()][CompressedStairShape.VERTICAL_OUTER_BACK_LEFT_BOTTOM_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_DOWN_NORTH.ordinal()][CompressedStairShape.VERTICAL_OUTER_BACK_LEFT_BOTTOM_RIGHT.ordinal()] = new StateModelDefinition("/side/twist_left", 180);
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_UP_NORTH.ordinal()][CompressedStairShape.VERTICAL_OUTER_BACK_LEFT_BOTTOM_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_DOWN_EAST.ordinal()][CompressedStairShape.VERTICAL_OUTER_BACK_LEFT_BOTTOM_RIGHT.ordinal()] = new StateModelDefinition("/side/twist_left", 270);
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_UP_EAST.ordinal()][CompressedStairShape.VERTICAL_OUTER_BACK_RIGHT_BOTTOM_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_DOWN_SOUTH.ordinal()][CompressedStairShape.VERTICAL_OUTER_BACK_RIGHT_BOTTOM_LEFT.ordinal()] = new StateModelDefinition("/side/twist_right", 0);
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_UP_SOUTH.ordinal()][CompressedStairShape.VERTICAL_OUTER_BACK_RIGHT_BOTTOM_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.SOUTH_DOWN_WEST.ordinal()][CompressedStairShape.VERTICAL_OUTER_BACK_RIGHT_BOTTOM_LEFT.ordinal()] = new StateModelDefinition("/side/twist_right", 90);
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_UP_WEST.ordinal()][CompressedStairShape.VERTICAL_OUTER_BACK_RIGHT_BOTTOM_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.WEST_DOWN_NORTH.ordinal()][CompressedStairShape.VERTICAL_OUTER_BACK_RIGHT_BOTTOM_LEFT.ordinal()] = new StateModelDefinition("/side/twist_right", 180);
		MODEL_DEFINITIONS[CompressedStairFacing.EAST_UP_NORTH.ordinal()][CompressedStairShape.VERTICAL_OUTER_BACK_RIGHT_BOTTOM_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[CompressedStairFacing.NORTH_DOWN_EAST.ordinal()][CompressedStairShape.VERTICAL_OUTER_BACK_RIGHT_BOTTOM_LEFT.ordinal()] = new StateModelDefinition("/side/twist_right", 270);
	}
	
	/*
	private static void setUpStairModelDefs(ComplexFacing normal, ComplexFacing flipped, int rotation) {
		Pair<CompressedStairFacing, StairFacingType> normalPair = CompressedStairFacing.getCompressedFacing(normal);
		Pair<CompressedStairFacing, StairFacingType> flippedPair = CompressedStairFacing.getCompressedFacing(flipped);
		setUpStairModelDefs(MODEL_DEFINITIONS[normalPair.getLeft().ordinal()], normalPair.getRight(), MODEL_DEFINITIONS[flippedPair.getLeft().ordinal()], flippedPair.getRight(), rotation);
	}
	
	private static void setUpStairModelDefs(StateModelDefinition[] normal, StairFacingType normalType, StateModelDefinition[] flipped, StairFacingType flippedType, int rotation) {
		int rotation2 = (rotation + 90) % 360;
		setStairModelDef(normal, normalType, flipped, flippedType, StairShape.STRAIGHT, new StateModelDefinition("/top/straight", rotation));

		setStairModelDef(normal, normalType, flipped, flippedType, StairShape.INNER_FRONT_LEFT, new StateModelDefinition("/top/inner", rotation));
		setStairModelDef(normal, normalType, flipped, flippedType, StairShape.INNER_FRONT_RIGHT, new StateModelDefinition("/top/inner", rotation2));
		setStairModelDef(normal, normalType, flipped, flippedType, StairShape.INNER_TOP_LEFT, new StateModelDefinition("/top/inner", rotation));
		setStairModelDef(normal, normalType, flipped, flippedType, StairShape.INNER_TOP_RIGHT, new StateModelDefinition("/top/inner", rotation2));
		setStairModelDef(normal, normalType, flipped, flippedType, StairShape.INNER_BOTH_LEFT, new StateModelDefinition("/top/inner", rotation));
		setStairModelDef(normal, normalType, flipped, flippedType, StairShape.INNER_BOTH_RIGHT, new StateModelDefinition("/top/inner", rotation2));

		setStairModelDef(normal, normalType, flipped, flippedType, StairShape.OUTER_BACK_LEFT, new StateModelDefinition("/top/outer_back", rotation));
		setStairModelDef(normal, normalType, flipped, flippedType, StairShape.OUTER_BACK_RIGHT, new StateModelDefinition("/top/outer_back", rotation2));
		setStairModelDef(normal, normalType, flipped, flippedType, StairShape.OUTER_BOTTOM_LEFT, new StateModelDefinition("/top/outer_bottom_left", rotation));
		setStairModelDef(normal, normalType, flipped, flippedType, StairShape.OUTER_BOTTOM_RIGHT, new StateModelDefinition("/top/outer_bottom_right", rotation));
		setStairModelDef(normal, normalType, flipped, flippedType, StairShape.OUTER_BOTH_LEFT, new StateModelDefinition("/top/outer_both", rotation));
		setStairModelDef(normal, normalType, flipped, flippedType, StairShape.OUTER_BOTH_RIGHT, new StateModelDefinition("/top/outer_both", rotation2));

		setStairModelDef(normal, normalType, flipped, flippedType, StairShape.OUTER_BACK_LEFT_BOTTOM_RIGHT, new StateModelDefinition("/top/twist_right", rotation));
		setStairModelDef(normal, normalType, flipped, flippedType, StairShape.OUTER_BACK_RIGHT_BOTTOM_LEFT, new StateModelDefinition("/top/twist_left", rotation));
	}
	
	private static void setDownStairModelDefs(ComplexFacing normal, ComplexFacing flipped, int rotation) {
		Pair<CompressedStairFacing, StairFacingType> normalPair = CompressedStairFacing.getCompressedFacing(normal);
		Pair<CompressedStairFacing, StairFacingType> flippedPair = CompressedStairFacing.getCompressedFacing(flipped);
		setDownStairModelDefs(MODEL_DEFINITIONS[normalPair.getLeft().ordinal()], normalPair.getRight(), MODEL_DEFINITIONS[flippedPair.getLeft().ordinal()], flippedPair.getRight(), rotation);
	}
	
	private static void setDownStairModelDefs(StateModelDefinition[] normal, StairFacingType normalType, StateModelDefinition[] flipped, StairFacingType flippedType, int rotation) {
		int rotation2 = (rotation + 90) % 360;
		setStairModelDef(normal, normalType, flipped, flippedType, StairShape.STRAIGHT, new StateModelDefinition("/bottom/straight", rotation));

		setStairModelDef(normal, normalType, flipped, flippedType, StairShape.INNER_FRONT_LEFT, new StateModelDefinition("/bottom/inner", rotation2));
		setStairModelDef(normal, normalType, flipped, flippedType, StairShape.INNER_FRONT_RIGHT, new StateModelDefinition("/bottom/inner", rotation));
		setStairModelDef(normal, normalType, flipped, flippedType, StairShape.INNER_TOP_LEFT, new StateModelDefinition("/bottom/inner", rotation2));
		setStairModelDef(normal, normalType, flipped, flippedType, StairShape.INNER_TOP_RIGHT, new StateModelDefinition("/bottom/inner", rotation));
		setStairModelDef(normal, normalType, flipped, flippedType, StairShape.INNER_BOTH_LEFT, new StateModelDefinition("/bottom/inner", rotation2));
		setStairModelDef(normal, normalType, flipped, flippedType, StairShape.INNER_BOTH_RIGHT, new StateModelDefinition("/bottom/inner", rotation));

		setStairModelDef(normal, normalType, flipped, flippedType, StairShape.OUTER_BACK_LEFT, new StateModelDefinition("/bottom/outer_back", rotation2));
		setStairModelDef(normal, normalType, flipped, flippedType, StairShape.OUTER_BACK_RIGHT, new StateModelDefinition("/bottom/outer_back", rotation));
		setStairModelDef(normal, normalType, flipped, flippedType, StairShape.OUTER_BOTTOM_LEFT, new StateModelDefinition("/bottom/outer_top_right", rotation));
		setStairModelDef(normal, normalType, flipped, flippedType, StairShape.OUTER_BOTTOM_RIGHT, new StateModelDefinition("/bottom/outer_top_left", rotation));
		setStairModelDef(normal, normalType, flipped, flippedType, StairShape.OUTER_BOTH_LEFT, new StateModelDefinition("/bottom/outer_both", rotation2));
		setStairModelDef(normal, normalType, flipped, flippedType, StairShape.OUTER_BOTH_RIGHT, new StateModelDefinition("/bottom/outer_both", rotation));

		setStairModelDef(normal, normalType, flipped, flippedType, StairShape.OUTER_BACK_LEFT_BOTTOM_RIGHT, new StateModelDefinition("/bottom/twist_left", rotation));
		setStairModelDef(normal, normalType, flipped, flippedType, StairShape.OUTER_BACK_RIGHT_BOTTOM_LEFT, new StateModelDefinition("/bottom/twist_right", rotation));
	}
	
	private static void setVerticalStairModelDefs(ComplexFacing normal, ComplexFacing flipped, int rotation) {
		Pair<CompressedStairFacing, StairFacingType> normalPair = CompressedStairFacing.getCompressedFacing(normal);
		Pair<CompressedStairFacing, StairFacingType> flippedPair = CompressedStairFacing.getCompressedFacing(flipped);
		setVerticalStairModelDefs(MODEL_DEFINITIONS[normalPair.getLeft().ordinal()], normalPair.getRight(), MODEL_DEFINITIONS[flippedPair.getLeft().ordinal()], flippedPair.getRight(), rotation);
	}
	
	private static void setVerticalStairModelDefs(StateModelDefinition[] normal, StairFacingType normalType, StateModelDefinition[] flipped, StairFacingType flippedType, int rotation) {
		int rotation2 = (rotation + 270) % 360;
		setStairModelDef(normal, normalType, flipped, flippedType, StairShape.STRAIGHT, new StateModelDefinition("/side/straight", rotation));

		setStairModelDef(normal, normalType, flipped, flippedType, StairShape.INNER_FRONT_LEFT, new StateModelDefinition("/bottom/inner", rotation));
		setStairModelDef(normal, normalType, flipped, flippedType, StairShape.INNER_FRONT_RIGHT, new StateModelDefinition("/top/inner", rotation));
		setStairModelDef(normal, normalType, flipped, flippedType, StairShape.INNER_TOP_LEFT, new StateModelDefinition("/bottom/inner", rotation));
		setStairModelDef(normal, normalType, flipped, flippedType, StairShape.INNER_TOP_RIGHT, new StateModelDefinition("/top/inner", rotation));
		setStairModelDef(normal, normalType, flipped, flippedType, StairShape.INNER_BOTH_LEFT, new StateModelDefinition("/bottom/inner", rotation));
		setStairModelDef(normal, normalType, flipped, flippedType, StairShape.INNER_BOTH_RIGHT, new StateModelDefinition("/top/inner", rotation));

		setStairModelDef(normal, normalType, flipped, flippedType, StairShape.OUTER_BACK_LEFT, new StateModelDefinition("/bottom/outer_top_right", rotation2));
		setStairModelDef(normal, normalType, flipped, flippedType, StairShape.OUTER_BACK_RIGHT, new StateModelDefinition("/top/outer_bottom_right", rotation2));
		setStairModelDef(normal, normalType, flipped, flippedType, StairShape.OUTER_BOTTOM_LEFT, new StateModelDefinition("/bottom/outer_top_left", rotation));
		setStairModelDef(normal, normalType, flipped, flippedType, StairShape.OUTER_BOTTOM_RIGHT, new StateModelDefinition("/top/outer_bottom_left", rotation));
		setStairModelDef(normal, normalType, flipped, flippedType, StairShape.OUTER_BOTH_LEFT, new StateModelDefinition("/bottom/outer_both", rotation));
		setStairModelDef(normal, normalType, flipped, flippedType, StairShape.OUTER_BOTH_RIGHT, new StateModelDefinition("/top/outer_both", rotation));

		setStairModelDef(normal, normalType, flipped, flippedType, StairShape.OUTER_BACK_LEFT_BOTTOM_RIGHT, new StateModelDefinition("/side/twist_left", rotation));
		setStairModelDef(normal, normalType, flipped, flippedType, StairShape.OUTER_BACK_RIGHT_BOTTOM_LEFT, new StateModelDefinition("/side/twist_right", rotation));
	}
	
	private static void setStairModelDef(StateModelDefinition[] normal, StairFacingType normalType, StateModelDefinition[] flipped, StairFacingType flippedType, StairShape shape, StateModelDefinition model) {
		CompressedStairShape normalShape = CompressedStairShape.getCompressedShape(normalType, shape);
		if (normalShape != null) normal[normalShape.ordinal()] = model;
		CompressedStairShape flippedShape = CompressedStairShape.getCompressedShape(flippedType, shape.flipped());
		if (flippedShape != null) flipped[flippedShape.ordinal()] = model;
	}
	*/

	public static StateModelDefinition getModelDefinition(BlockState state, StairConnections allowedConnections) {
		return MODEL_DEFINITIONS[state.getValue(VerticalStairBlock.FACING).ordinal()][state.getValue(allowedConnections.shapeProperty).ordinal()];
	}
}
