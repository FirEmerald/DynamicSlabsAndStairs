package com.firemerald.additionalplacements.client.models.definitions;

import com.firemerald.additionalplacements.AdditionalPlacementsMod;
import com.firemerald.additionalplacements.block.stairs.common.CommonStairShape;
import com.firemerald.additionalplacements.block.stairs.common.CommonStairShapeState;
import com.firemerald.additionalplacements.util.ComplexFacing;

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
	
	public static final StateModelDefinition[][] MODEL_DEFINITIONS = new StateModelDefinition[24][15];

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
		
		Map<StateModelDefinition, List<Pair<ComplexFacing, CommonStairShape>>> map = new LinkedHashMap<>();
		for (String model : MODELS) for (int i = 0; i < 360; i += 90) map.put(new StateModelDefinition(model, i), new ArrayList<>());
		for (ComplexFacing facing : ComplexFacing.values()) for (CommonStairShape shape : CommonStairShape.values()) {
			map.get(MODEL_DEFINITIONS[facing.ordinal()][shape.ordinal()]).add(Pair.of(facing, shape));
		}
		map.forEach((model, states) -> {
			StringJoiner joiner = new StringJoiner("\n");
			states.forEach(state -> {
				joiner.add("MODEL_DEFINITIONS[ComplexFacing." + state.getLeft().name() + ".ordinal()][CommonStairShape." + state.getRight().name() + ".ordinal()] = ");
			});
			System.out.println(joiner.toString() + "new StateModelDefinition(\"" + model.model() + "\", " + model.yRotation() + ");");
		});
		*/
		
		MODEL_DEFINITIONS[ComplexFacing.SOUTH_UP.ordinal()][CommonStairShape.STRAIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.UP_SOUTH.ordinal()][CommonStairShape.STRAIGHT.ordinal()] = new StateModelDefinition("/top/straight", 0);
		MODEL_DEFINITIONS[ComplexFacing.WEST_UP.ordinal()][CommonStairShape.STRAIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.UP_WEST.ordinal()][CommonStairShape.STRAIGHT.ordinal()] = new StateModelDefinition("/top/straight", 90);
		MODEL_DEFINITIONS[ComplexFacing.NORTH_UP.ordinal()][CommonStairShape.STRAIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.UP_NORTH.ordinal()][CommonStairShape.STRAIGHT.ordinal()] = new StateModelDefinition("/top/straight", 180);
		MODEL_DEFINITIONS[ComplexFacing.EAST_UP.ordinal()][CommonStairShape.STRAIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.UP_EAST.ordinal()][CommonStairShape.STRAIGHT.ordinal()] = new StateModelDefinition("/top/straight", 270);
		MODEL_DEFINITIONS[ComplexFacing.SOUTH_UP.ordinal()][CommonStairShape.INNER_FRONT_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.SOUTH_UP.ordinal()][CommonStairShape.INNER_TOP_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.SOUTH_UP.ordinal()][CommonStairShape.INNER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.EAST_UP.ordinal()][CommonStairShape.INNER_FRONT_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.EAST_UP.ordinal()][CommonStairShape.INNER_TOP_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.EAST_UP.ordinal()][CommonStairShape.INNER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.UP_SOUTH.ordinal()][CommonStairShape.INNER_FRONT_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.UP_SOUTH.ordinal()][CommonStairShape.INNER_TOP_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.UP_SOUTH.ordinal()][CommonStairShape.INNER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.EAST_SOUTH.ordinal()][CommonStairShape.INNER_FRONT_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.EAST_SOUTH.ordinal()][CommonStairShape.INNER_TOP_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.EAST_SOUTH.ordinal()][CommonStairShape.INNER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.SOUTH_EAST.ordinal()][CommonStairShape.INNER_FRONT_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.SOUTH_EAST.ordinal()][CommonStairShape.INNER_TOP_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.SOUTH_EAST.ordinal()][CommonStairShape.INNER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.UP_EAST.ordinal()][CommonStairShape.INNER_FRONT_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.UP_EAST.ordinal()][CommonStairShape.INNER_TOP_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.UP_EAST.ordinal()][CommonStairShape.INNER_BOTH_LEFT.ordinal()] = new StateModelDefinition("/top/inner", 0);
		MODEL_DEFINITIONS[ComplexFacing.SOUTH_UP.ordinal()][CommonStairShape.INNER_FRONT_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.SOUTH_UP.ordinal()][CommonStairShape.INNER_TOP_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.SOUTH_UP.ordinal()][CommonStairShape.INNER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.WEST_UP.ordinal()][CommonStairShape.INNER_FRONT_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.WEST_UP.ordinal()][CommonStairShape.INNER_TOP_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.WEST_UP.ordinal()][CommonStairShape.INNER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.WEST_SOUTH.ordinal()][CommonStairShape.INNER_FRONT_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.WEST_SOUTH.ordinal()][CommonStairShape.INNER_TOP_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.WEST_SOUTH.ordinal()][CommonStairShape.INNER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.UP_SOUTH.ordinal()][CommonStairShape.INNER_FRONT_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.UP_SOUTH.ordinal()][CommonStairShape.INNER_TOP_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.UP_SOUTH.ordinal()][CommonStairShape.INNER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.SOUTH_WEST.ordinal()][CommonStairShape.INNER_FRONT_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.SOUTH_WEST.ordinal()][CommonStairShape.INNER_TOP_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.SOUTH_WEST.ordinal()][CommonStairShape.INNER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.UP_WEST.ordinal()][CommonStairShape.INNER_FRONT_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.UP_WEST.ordinal()][CommonStairShape.INNER_TOP_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.UP_WEST.ordinal()][CommonStairShape.INNER_BOTH_RIGHT.ordinal()] = new StateModelDefinition("/top/inner", 90);
		MODEL_DEFINITIONS[ComplexFacing.WEST_UP.ordinal()][CommonStairShape.INNER_FRONT_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.WEST_UP.ordinal()][CommonStairShape.INNER_TOP_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.WEST_UP.ordinal()][CommonStairShape.INNER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.NORTH_UP.ordinal()][CommonStairShape.INNER_FRONT_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.NORTH_UP.ordinal()][CommonStairShape.INNER_TOP_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.NORTH_UP.ordinal()][CommonStairShape.INNER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.WEST_NORTH.ordinal()][CommonStairShape.INNER_FRONT_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.WEST_NORTH.ordinal()][CommonStairShape.INNER_TOP_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.WEST_NORTH.ordinal()][CommonStairShape.INNER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.UP_NORTH.ordinal()][CommonStairShape.INNER_FRONT_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.UP_NORTH.ordinal()][CommonStairShape.INNER_TOP_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.UP_NORTH.ordinal()][CommonStairShape.INNER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.UP_WEST.ordinal()][CommonStairShape.INNER_FRONT_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.UP_WEST.ordinal()][CommonStairShape.INNER_TOP_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.UP_WEST.ordinal()][CommonStairShape.INNER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.NORTH_WEST.ordinal()][CommonStairShape.INNER_FRONT_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.NORTH_WEST.ordinal()][CommonStairShape.INNER_TOP_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.NORTH_WEST.ordinal()][CommonStairShape.INNER_BOTH_RIGHT.ordinal()] = new StateModelDefinition("/top/inner", 180);
		MODEL_DEFINITIONS[ComplexFacing.NORTH_UP.ordinal()][CommonStairShape.INNER_FRONT_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.NORTH_UP.ordinal()][CommonStairShape.INNER_TOP_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.NORTH_UP.ordinal()][CommonStairShape.INNER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.EAST_UP.ordinal()][CommonStairShape.INNER_FRONT_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.EAST_UP.ordinal()][CommonStairShape.INNER_TOP_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.EAST_UP.ordinal()][CommonStairShape.INNER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.UP_NORTH.ordinal()][CommonStairShape.INNER_FRONT_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.UP_NORTH.ordinal()][CommonStairShape.INNER_TOP_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.UP_NORTH.ordinal()][CommonStairShape.INNER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.EAST_NORTH.ordinal()][CommonStairShape.INNER_FRONT_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.EAST_NORTH.ordinal()][CommonStairShape.INNER_TOP_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.EAST_NORTH.ordinal()][CommonStairShape.INNER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.UP_EAST.ordinal()][CommonStairShape.INNER_FRONT_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.UP_EAST.ordinal()][CommonStairShape.INNER_TOP_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.UP_EAST.ordinal()][CommonStairShape.INNER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.NORTH_EAST.ordinal()][CommonStairShape.INNER_FRONT_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.NORTH_EAST.ordinal()][CommonStairShape.INNER_TOP_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.NORTH_EAST.ordinal()][CommonStairShape.INNER_BOTH_LEFT.ordinal()] = new StateModelDefinition("/top/inner", 270);
		MODEL_DEFINITIONS[ComplexFacing.SOUTH_UP.ordinal()][CommonStairShape.OUTER_BACK_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.EAST_UP.ordinal()][CommonStairShape.OUTER_BACK_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.UP_SOUTH.ordinal()][CommonStairShape.OUTER_BOTTOM_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.UP_EAST.ordinal()][CommonStairShape.OUTER_BOTTOM_LEFT.ordinal()] = new StateModelDefinition("/top/outer_back", 0);
		MODEL_DEFINITIONS[ComplexFacing.SOUTH_UP.ordinal()][CommonStairShape.OUTER_BACK_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.WEST_UP.ordinal()][CommonStairShape.OUTER_BACK_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.UP_SOUTH.ordinal()][CommonStairShape.OUTER_BOTTOM_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.UP_WEST.ordinal()][CommonStairShape.OUTER_BOTTOM_RIGHT.ordinal()] = new StateModelDefinition("/top/outer_back", 90);
		MODEL_DEFINITIONS[ComplexFacing.WEST_UP.ordinal()][CommonStairShape.OUTER_BACK_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.NORTH_UP.ordinal()][CommonStairShape.OUTER_BACK_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.UP_NORTH.ordinal()][CommonStairShape.OUTER_BOTTOM_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.UP_WEST.ordinal()][CommonStairShape.OUTER_BOTTOM_LEFT.ordinal()] = new StateModelDefinition("/top/outer_back", 180);
		MODEL_DEFINITIONS[ComplexFacing.NORTH_UP.ordinal()][CommonStairShape.OUTER_BACK_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.EAST_UP.ordinal()][CommonStairShape.OUTER_BACK_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.UP_NORTH.ordinal()][CommonStairShape.OUTER_BOTTOM_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.UP_EAST.ordinal()][CommonStairShape.OUTER_BOTTOM_RIGHT.ordinal()] = new StateModelDefinition("/top/outer_back", 270);
		MODEL_DEFINITIONS[ComplexFacing.SOUTH_UP.ordinal()][CommonStairShape.OUTER_BOTTOM_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.UP_SOUTH.ordinal()][CommonStairShape.OUTER_BACK_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.EAST_SOUTH.ordinal()][CommonStairShape.OUTER_BACK_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.SOUTH_EAST.ordinal()][CommonStairShape.OUTER_BOTTOM_RIGHT.ordinal()] = new StateModelDefinition("/top/outer_bottom_left", 0);
		MODEL_DEFINITIONS[ComplexFacing.WEST_UP.ordinal()][CommonStairShape.OUTER_BOTTOM_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.WEST_SOUTH.ordinal()][CommonStairShape.OUTER_BOTTOM_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.SOUTH_WEST.ordinal()][CommonStairShape.OUTER_BACK_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.UP_WEST.ordinal()][CommonStairShape.OUTER_BACK_RIGHT.ordinal()] = new StateModelDefinition("/top/outer_bottom_left", 90);
		MODEL_DEFINITIONS[ComplexFacing.NORTH_UP.ordinal()][CommonStairShape.OUTER_BOTTOM_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.WEST_NORTH.ordinal()][CommonStairShape.OUTER_BACK_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.UP_NORTH.ordinal()][CommonStairShape.OUTER_BACK_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.NORTH_WEST.ordinal()][CommonStairShape.OUTER_BOTTOM_RIGHT.ordinal()] = new StateModelDefinition("/top/outer_bottom_left", 180);
		MODEL_DEFINITIONS[ComplexFacing.EAST_UP.ordinal()][CommonStairShape.OUTER_BOTTOM_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.EAST_NORTH.ordinal()][CommonStairShape.OUTER_BOTTOM_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.UP_EAST.ordinal()][CommonStairShape.OUTER_BACK_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.NORTH_EAST.ordinal()][CommonStairShape.OUTER_BACK_LEFT.ordinal()] = new StateModelDefinition("/top/outer_bottom_left", 270);
		MODEL_DEFINITIONS[ComplexFacing.SOUTH_UP.ordinal()][CommonStairShape.OUTER_BOTTOM_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.WEST_SOUTH.ordinal()][CommonStairShape.OUTER_BACK_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.UP_SOUTH.ordinal()][CommonStairShape.OUTER_BACK_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.SOUTH_WEST.ordinal()][CommonStairShape.OUTER_BOTTOM_LEFT.ordinal()] = new StateModelDefinition("/top/outer_bottom_right", 0);
		MODEL_DEFINITIONS[ComplexFacing.WEST_UP.ordinal()][CommonStairShape.OUTER_BOTTOM_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.WEST_NORTH.ordinal()][CommonStairShape.OUTER_BOTTOM_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.UP_WEST.ordinal()][CommonStairShape.OUTER_BACK_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.NORTH_WEST.ordinal()][CommonStairShape.OUTER_BACK_RIGHT.ordinal()] = new StateModelDefinition("/top/outer_bottom_right", 90);
		MODEL_DEFINITIONS[ComplexFacing.NORTH_UP.ordinal()][CommonStairShape.OUTER_BOTTOM_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.UP_NORTH.ordinal()][CommonStairShape.OUTER_BACK_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.EAST_NORTH.ordinal()][CommonStairShape.OUTER_BACK_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.NORTH_EAST.ordinal()][CommonStairShape.OUTER_BOTTOM_LEFT.ordinal()] = new StateModelDefinition("/top/outer_bottom_right", 180);
		MODEL_DEFINITIONS[ComplexFacing.EAST_UP.ordinal()][CommonStairShape.OUTER_BOTTOM_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.EAST_SOUTH.ordinal()][CommonStairShape.OUTER_BOTTOM_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.SOUTH_EAST.ordinal()][CommonStairShape.OUTER_BACK_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.UP_EAST.ordinal()][CommonStairShape.OUTER_BACK_LEFT.ordinal()] = new StateModelDefinition("/top/outer_bottom_right", 270);
		MODEL_DEFINITIONS[ComplexFacing.SOUTH_UP.ordinal()][CommonStairShape.OUTER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.EAST_UP.ordinal()][CommonStairShape.OUTER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.UP_SOUTH.ordinal()][CommonStairShape.OUTER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.EAST_SOUTH.ordinal()][CommonStairShape.OUTER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.SOUTH_EAST.ordinal()][CommonStairShape.OUTER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.UP_EAST.ordinal()][CommonStairShape.OUTER_BOTH_LEFT.ordinal()] = new StateModelDefinition("/top/outer_both", 0);
		MODEL_DEFINITIONS[ComplexFacing.SOUTH_UP.ordinal()][CommonStairShape.OUTER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.WEST_UP.ordinal()][CommonStairShape.OUTER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.WEST_SOUTH.ordinal()][CommonStairShape.OUTER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.UP_SOUTH.ordinal()][CommonStairShape.OUTER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.SOUTH_WEST.ordinal()][CommonStairShape.OUTER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.UP_WEST.ordinal()][CommonStairShape.OUTER_BOTH_RIGHT.ordinal()] = new StateModelDefinition("/top/outer_both", 90);
		MODEL_DEFINITIONS[ComplexFacing.WEST_UP.ordinal()][CommonStairShape.OUTER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.NORTH_UP.ordinal()][CommonStairShape.OUTER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.WEST_NORTH.ordinal()][CommonStairShape.OUTER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.UP_NORTH.ordinal()][CommonStairShape.OUTER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.UP_WEST.ordinal()][CommonStairShape.OUTER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.NORTH_WEST.ordinal()][CommonStairShape.OUTER_BOTH_RIGHT.ordinal()] = new StateModelDefinition("/top/outer_both", 180);
		MODEL_DEFINITIONS[ComplexFacing.NORTH_UP.ordinal()][CommonStairShape.OUTER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.EAST_UP.ordinal()][CommonStairShape.OUTER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.UP_NORTH.ordinal()][CommonStairShape.OUTER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.EAST_NORTH.ordinal()][CommonStairShape.OUTER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.UP_EAST.ordinal()][CommonStairShape.OUTER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.NORTH_EAST.ordinal()][CommonStairShape.OUTER_BOTH_LEFT.ordinal()] = new StateModelDefinition("/top/outer_both", 270);
		MODEL_DEFINITIONS[ComplexFacing.SOUTH_UP.ordinal()][CommonStairShape.OUTER_BACK_RIGHT_BOTTOM_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.UP_SOUTH.ordinal()][CommonStairShape.OUTER_BACK_RIGHT_BOTTOM_LEFT.ordinal()] = new StateModelDefinition("/top/twist_left", 0);
		MODEL_DEFINITIONS[ComplexFacing.WEST_UP.ordinal()][CommonStairShape.OUTER_BACK_RIGHT_BOTTOM_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.UP_WEST.ordinal()][CommonStairShape.OUTER_BACK_RIGHT_BOTTOM_LEFT.ordinal()] = new StateModelDefinition("/top/twist_left", 90);
		MODEL_DEFINITIONS[ComplexFacing.NORTH_UP.ordinal()][CommonStairShape.OUTER_BACK_RIGHT_BOTTOM_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.UP_NORTH.ordinal()][CommonStairShape.OUTER_BACK_RIGHT_BOTTOM_LEFT.ordinal()] = new StateModelDefinition("/top/twist_left", 180);
		MODEL_DEFINITIONS[ComplexFacing.EAST_UP.ordinal()][CommonStairShape.OUTER_BACK_RIGHT_BOTTOM_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.UP_EAST.ordinal()][CommonStairShape.OUTER_BACK_RIGHT_BOTTOM_LEFT.ordinal()] = new StateModelDefinition("/top/twist_left", 270);
		MODEL_DEFINITIONS[ComplexFacing.SOUTH_UP.ordinal()][CommonStairShape.OUTER_BACK_LEFT_BOTTOM_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.UP_SOUTH.ordinal()][CommonStairShape.OUTER_BACK_LEFT_BOTTOM_RIGHT.ordinal()] = new StateModelDefinition("/top/twist_right", 0);
		MODEL_DEFINITIONS[ComplexFacing.WEST_UP.ordinal()][CommonStairShape.OUTER_BACK_LEFT_BOTTOM_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.UP_WEST.ordinal()][CommonStairShape.OUTER_BACK_LEFT_BOTTOM_RIGHT.ordinal()] = new StateModelDefinition("/top/twist_right", 90);
		MODEL_DEFINITIONS[ComplexFacing.NORTH_UP.ordinal()][CommonStairShape.OUTER_BACK_LEFT_BOTTOM_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.UP_NORTH.ordinal()][CommonStairShape.OUTER_BACK_LEFT_BOTTOM_RIGHT.ordinal()] = new StateModelDefinition("/top/twist_right", 180);
		MODEL_DEFINITIONS[ComplexFacing.EAST_UP.ordinal()][CommonStairShape.OUTER_BACK_LEFT_BOTTOM_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.UP_EAST.ordinal()][CommonStairShape.OUTER_BACK_LEFT_BOTTOM_RIGHT.ordinal()] = new StateModelDefinition("/top/twist_right", 270);
		MODEL_DEFINITIONS[ComplexFacing.SOUTH_DOWN.ordinal()][CommonStairShape.STRAIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.DOWN_SOUTH.ordinal()][CommonStairShape.STRAIGHT.ordinal()] = new StateModelDefinition("/bottom/straight", 0);
		MODEL_DEFINITIONS[ComplexFacing.WEST_DOWN.ordinal()][CommonStairShape.STRAIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.DOWN_WEST.ordinal()][CommonStairShape.STRAIGHT.ordinal()] = new StateModelDefinition("/bottom/straight", 90);
		MODEL_DEFINITIONS[ComplexFacing.NORTH_DOWN.ordinal()][CommonStairShape.STRAIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.DOWN_NORTH.ordinal()][CommonStairShape.STRAIGHT.ordinal()] = new StateModelDefinition("/bottom/straight", 180);
		MODEL_DEFINITIONS[ComplexFacing.EAST_DOWN.ordinal()][CommonStairShape.STRAIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.DOWN_EAST.ordinal()][CommonStairShape.STRAIGHT.ordinal()] = new StateModelDefinition("/bottom/straight", 270);
		MODEL_DEFINITIONS[ComplexFacing.SOUTH_DOWN.ordinal()][CommonStairShape.INNER_FRONT_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.SOUTH_DOWN.ordinal()][CommonStairShape.INNER_TOP_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.SOUTH_DOWN.ordinal()][CommonStairShape.INNER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.EAST_DOWN.ordinal()][CommonStairShape.INNER_FRONT_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.EAST_DOWN.ordinal()][CommonStairShape.INNER_TOP_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.EAST_DOWN.ordinal()][CommonStairShape.INNER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.DOWN_SOUTH.ordinal()][CommonStairShape.INNER_FRONT_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.DOWN_SOUTH.ordinal()][CommonStairShape.INNER_TOP_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.DOWN_SOUTH.ordinal()][CommonStairShape.INNER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.EAST_SOUTH.ordinal()][CommonStairShape.INNER_FRONT_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.EAST_SOUTH.ordinal()][CommonStairShape.INNER_TOP_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.EAST_SOUTH.ordinal()][CommonStairShape.INNER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.DOWN_EAST.ordinal()][CommonStairShape.INNER_FRONT_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.DOWN_EAST.ordinal()][CommonStairShape.INNER_TOP_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.DOWN_EAST.ordinal()][CommonStairShape.INNER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.SOUTH_EAST.ordinal()][CommonStairShape.INNER_FRONT_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.SOUTH_EAST.ordinal()][CommonStairShape.INNER_TOP_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.SOUTH_EAST.ordinal()][CommonStairShape.INNER_BOTH_LEFT.ordinal()] = new StateModelDefinition("/bottom/inner", 0);
		MODEL_DEFINITIONS[ComplexFacing.SOUTH_DOWN.ordinal()][CommonStairShape.INNER_FRONT_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.SOUTH_DOWN.ordinal()][CommonStairShape.INNER_TOP_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.SOUTH_DOWN.ordinal()][CommonStairShape.INNER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.WEST_DOWN.ordinal()][CommonStairShape.INNER_FRONT_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.WEST_DOWN.ordinal()][CommonStairShape.INNER_TOP_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.WEST_DOWN.ordinal()][CommonStairShape.INNER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.DOWN_SOUTH.ordinal()][CommonStairShape.INNER_FRONT_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.DOWN_SOUTH.ordinal()][CommonStairShape.INNER_TOP_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.DOWN_SOUTH.ordinal()][CommonStairShape.INNER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.WEST_SOUTH.ordinal()][CommonStairShape.INNER_FRONT_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.WEST_SOUTH.ordinal()][CommonStairShape.INNER_TOP_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.WEST_SOUTH.ordinal()][CommonStairShape.INNER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.DOWN_WEST.ordinal()][CommonStairShape.INNER_FRONT_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.DOWN_WEST.ordinal()][CommonStairShape.INNER_TOP_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.DOWN_WEST.ordinal()][CommonStairShape.INNER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.SOUTH_WEST.ordinal()][CommonStairShape.INNER_FRONT_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.SOUTH_WEST.ordinal()][CommonStairShape.INNER_TOP_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.SOUTH_WEST.ordinal()][CommonStairShape.INNER_BOTH_RIGHT.ordinal()] = new StateModelDefinition("/bottom/inner", 90);
		MODEL_DEFINITIONS[ComplexFacing.WEST_DOWN.ordinal()][CommonStairShape.INNER_FRONT_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.WEST_DOWN.ordinal()][CommonStairShape.INNER_TOP_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.WEST_DOWN.ordinal()][CommonStairShape.INNER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.NORTH_DOWN.ordinal()][CommonStairShape.INNER_FRONT_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.NORTH_DOWN.ordinal()][CommonStairShape.INNER_TOP_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.NORTH_DOWN.ordinal()][CommonStairShape.INNER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.DOWN_NORTH.ordinal()][CommonStairShape.INNER_FRONT_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.DOWN_NORTH.ordinal()][CommonStairShape.INNER_TOP_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.DOWN_NORTH.ordinal()][CommonStairShape.INNER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.WEST_NORTH.ordinal()][CommonStairShape.INNER_FRONT_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.WEST_NORTH.ordinal()][CommonStairShape.INNER_TOP_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.WEST_NORTH.ordinal()][CommonStairShape.INNER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.DOWN_WEST.ordinal()][CommonStairShape.INNER_FRONT_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.DOWN_WEST.ordinal()][CommonStairShape.INNER_TOP_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.DOWN_WEST.ordinal()][CommonStairShape.INNER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.NORTH_WEST.ordinal()][CommonStairShape.INNER_FRONT_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.NORTH_WEST.ordinal()][CommonStairShape.INNER_TOP_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.NORTH_WEST.ordinal()][CommonStairShape.INNER_BOTH_LEFT.ordinal()] = new StateModelDefinition("/bottom/inner", 180);
		MODEL_DEFINITIONS[ComplexFacing.NORTH_DOWN.ordinal()][CommonStairShape.INNER_FRONT_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.NORTH_DOWN.ordinal()][CommonStairShape.INNER_TOP_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.NORTH_DOWN.ordinal()][CommonStairShape.INNER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.EAST_DOWN.ordinal()][CommonStairShape.INNER_FRONT_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.EAST_DOWN.ordinal()][CommonStairShape.INNER_TOP_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.EAST_DOWN.ordinal()][CommonStairShape.INNER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.DOWN_NORTH.ordinal()][CommonStairShape.INNER_FRONT_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.DOWN_NORTH.ordinal()][CommonStairShape.INNER_TOP_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.DOWN_NORTH.ordinal()][CommonStairShape.INNER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.EAST_NORTH.ordinal()][CommonStairShape.INNER_FRONT_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.EAST_NORTH.ordinal()][CommonStairShape.INNER_TOP_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.EAST_NORTH.ordinal()][CommonStairShape.INNER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.DOWN_EAST.ordinal()][CommonStairShape.INNER_FRONT_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.DOWN_EAST.ordinal()][CommonStairShape.INNER_TOP_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.DOWN_EAST.ordinal()][CommonStairShape.INNER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.NORTH_EAST.ordinal()][CommonStairShape.INNER_FRONT_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.NORTH_EAST.ordinal()][CommonStairShape.INNER_TOP_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.NORTH_EAST.ordinal()][CommonStairShape.INNER_BOTH_RIGHT.ordinal()] = new StateModelDefinition("/bottom/inner", 270);
		MODEL_DEFINITIONS[ComplexFacing.SOUTH_DOWN.ordinal()][CommonStairShape.OUTER_BACK_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.EAST_DOWN.ordinal()][CommonStairShape.OUTER_BACK_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.DOWN_SOUTH.ordinal()][CommonStairShape.OUTER_BOTTOM_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.DOWN_EAST.ordinal()][CommonStairShape.OUTER_BOTTOM_RIGHT.ordinal()] = new StateModelDefinition("/bottom/outer_back", 0);
		MODEL_DEFINITIONS[ComplexFacing.SOUTH_DOWN.ordinal()][CommonStairShape.OUTER_BACK_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.WEST_DOWN.ordinal()][CommonStairShape.OUTER_BACK_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.DOWN_SOUTH.ordinal()][CommonStairShape.OUTER_BOTTOM_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.DOWN_WEST.ordinal()][CommonStairShape.OUTER_BOTTOM_LEFT.ordinal()] = new StateModelDefinition("/bottom/outer_back", 90);
		MODEL_DEFINITIONS[ComplexFacing.WEST_DOWN.ordinal()][CommonStairShape.OUTER_BACK_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.NORTH_DOWN.ordinal()][CommonStairShape.OUTER_BACK_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.DOWN_NORTH.ordinal()][CommonStairShape.OUTER_BOTTOM_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.DOWN_WEST.ordinal()][CommonStairShape.OUTER_BOTTOM_RIGHT.ordinal()] = new StateModelDefinition("/bottom/outer_back", 180);
		MODEL_DEFINITIONS[ComplexFacing.NORTH_DOWN.ordinal()][CommonStairShape.OUTER_BACK_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.EAST_DOWN.ordinal()][CommonStairShape.OUTER_BACK_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.DOWN_NORTH.ordinal()][CommonStairShape.OUTER_BOTTOM_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.DOWN_EAST.ordinal()][CommonStairShape.OUTER_BOTTOM_LEFT.ordinal()] = new StateModelDefinition("/bottom/outer_back", 270);
		MODEL_DEFINITIONS[ComplexFacing.SOUTH_DOWN.ordinal()][CommonStairShape.OUTER_BOTTOM_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.DOWN_SOUTH.ordinal()][CommonStairShape.OUTER_BACK_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.EAST_SOUTH.ordinal()][CommonStairShape.OUTER_BACK_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.SOUTH_EAST.ordinal()][CommonStairShape.OUTER_BOTTOM_LEFT.ordinal()] = new StateModelDefinition("/bottom/outer_top_left", 0);
		MODEL_DEFINITIONS[ComplexFacing.WEST_DOWN.ordinal()][CommonStairShape.OUTER_BOTTOM_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.WEST_SOUTH.ordinal()][CommonStairShape.OUTER_BOTTOM_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.DOWN_WEST.ordinal()][CommonStairShape.OUTER_BACK_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.SOUTH_WEST.ordinal()][CommonStairShape.OUTER_BACK_RIGHT.ordinal()] = new StateModelDefinition("/bottom/outer_top_left", 90);
		MODEL_DEFINITIONS[ComplexFacing.NORTH_DOWN.ordinal()][CommonStairShape.OUTER_BOTTOM_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.DOWN_NORTH.ordinal()][CommonStairShape.OUTER_BACK_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.WEST_NORTH.ordinal()][CommonStairShape.OUTER_BACK_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.NORTH_WEST.ordinal()][CommonStairShape.OUTER_BOTTOM_LEFT.ordinal()] = new StateModelDefinition("/bottom/outer_top_left", 180);
		MODEL_DEFINITIONS[ComplexFacing.EAST_DOWN.ordinal()][CommonStairShape.OUTER_BOTTOM_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.EAST_NORTH.ordinal()][CommonStairShape.OUTER_BOTTOM_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.DOWN_EAST.ordinal()][CommonStairShape.OUTER_BACK_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.NORTH_EAST.ordinal()][CommonStairShape.OUTER_BACK_RIGHT.ordinal()] = new StateModelDefinition("/bottom/outer_top_left", 270);
		MODEL_DEFINITIONS[ComplexFacing.SOUTH_DOWN.ordinal()][CommonStairShape.OUTER_BOTTOM_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.DOWN_SOUTH.ordinal()][CommonStairShape.OUTER_BACK_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.WEST_SOUTH.ordinal()][CommonStairShape.OUTER_BACK_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.SOUTH_WEST.ordinal()][CommonStairShape.OUTER_BOTTOM_RIGHT.ordinal()] = new StateModelDefinition("/bottom/outer_top_right", 0);
		MODEL_DEFINITIONS[ComplexFacing.WEST_DOWN.ordinal()][CommonStairShape.OUTER_BOTTOM_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.WEST_NORTH.ordinal()][CommonStairShape.OUTER_BOTTOM_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.DOWN_WEST.ordinal()][CommonStairShape.OUTER_BACK_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.NORTH_WEST.ordinal()][CommonStairShape.OUTER_BACK_LEFT.ordinal()] = new StateModelDefinition("/bottom/outer_top_right", 90);
		MODEL_DEFINITIONS[ComplexFacing.NORTH_DOWN.ordinal()][CommonStairShape.OUTER_BOTTOM_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.DOWN_NORTH.ordinal()][CommonStairShape.OUTER_BACK_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.EAST_NORTH.ordinal()][CommonStairShape.OUTER_BACK_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.NORTH_EAST.ordinal()][CommonStairShape.OUTER_BOTTOM_RIGHT.ordinal()] = new StateModelDefinition("/bottom/outer_top_right", 180);
		MODEL_DEFINITIONS[ComplexFacing.EAST_DOWN.ordinal()][CommonStairShape.OUTER_BOTTOM_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.EAST_SOUTH.ordinal()][CommonStairShape.OUTER_BOTTOM_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.DOWN_EAST.ordinal()][CommonStairShape.OUTER_BACK_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.SOUTH_EAST.ordinal()][CommonStairShape.OUTER_BACK_LEFT.ordinal()] = new StateModelDefinition("/bottom/outer_top_right", 270);
		MODEL_DEFINITIONS[ComplexFacing.SOUTH_DOWN.ordinal()][CommonStairShape.OUTER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.EAST_DOWN.ordinal()][CommonStairShape.OUTER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.DOWN_SOUTH.ordinal()][CommonStairShape.OUTER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.EAST_SOUTH.ordinal()][CommonStairShape.OUTER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.DOWN_EAST.ordinal()][CommonStairShape.OUTER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.SOUTH_EAST.ordinal()][CommonStairShape.OUTER_BOTH_LEFT.ordinal()] = new StateModelDefinition("/bottom/outer_both", 0);
		MODEL_DEFINITIONS[ComplexFacing.SOUTH_DOWN.ordinal()][CommonStairShape.OUTER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.WEST_DOWN.ordinal()][CommonStairShape.OUTER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.DOWN_SOUTH.ordinal()][CommonStairShape.OUTER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.WEST_SOUTH.ordinal()][CommonStairShape.OUTER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.DOWN_WEST.ordinal()][CommonStairShape.OUTER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.SOUTH_WEST.ordinal()][CommonStairShape.OUTER_BOTH_RIGHT.ordinal()] = new StateModelDefinition("/bottom/outer_both", 90);
		MODEL_DEFINITIONS[ComplexFacing.WEST_DOWN.ordinal()][CommonStairShape.OUTER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.NORTH_DOWN.ordinal()][CommonStairShape.OUTER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.DOWN_NORTH.ordinal()][CommonStairShape.OUTER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.WEST_NORTH.ordinal()][CommonStairShape.OUTER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.DOWN_WEST.ordinal()][CommonStairShape.OUTER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.NORTH_WEST.ordinal()][CommonStairShape.OUTER_BOTH_LEFT.ordinal()] = new StateModelDefinition("/bottom/outer_both", 180);
		MODEL_DEFINITIONS[ComplexFacing.NORTH_DOWN.ordinal()][CommonStairShape.OUTER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.EAST_DOWN.ordinal()][CommonStairShape.OUTER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.DOWN_NORTH.ordinal()][CommonStairShape.OUTER_BOTH_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.EAST_NORTH.ordinal()][CommonStairShape.OUTER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.DOWN_EAST.ordinal()][CommonStairShape.OUTER_BOTH_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.NORTH_EAST.ordinal()][CommonStairShape.OUTER_BOTH_RIGHT.ordinal()] = new StateModelDefinition("/bottom/outer_both", 270);
		MODEL_DEFINITIONS[ComplexFacing.SOUTH_DOWN.ordinal()][CommonStairShape.OUTER_BACK_LEFT_BOTTOM_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.DOWN_SOUTH.ordinal()][CommonStairShape.OUTER_BACK_LEFT_BOTTOM_RIGHT.ordinal()] = new StateModelDefinition("/bottom/twist_left", 0);
		MODEL_DEFINITIONS[ComplexFacing.WEST_DOWN.ordinal()][CommonStairShape.OUTER_BACK_LEFT_BOTTOM_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.DOWN_WEST.ordinal()][CommonStairShape.OUTER_BACK_LEFT_BOTTOM_RIGHT.ordinal()] = new StateModelDefinition("/bottom/twist_left", 90);
		MODEL_DEFINITIONS[ComplexFacing.NORTH_DOWN.ordinal()][CommonStairShape.OUTER_BACK_LEFT_BOTTOM_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.DOWN_NORTH.ordinal()][CommonStairShape.OUTER_BACK_LEFT_BOTTOM_RIGHT.ordinal()] = new StateModelDefinition("/bottom/twist_left", 180);
		MODEL_DEFINITIONS[ComplexFacing.EAST_DOWN.ordinal()][CommonStairShape.OUTER_BACK_LEFT_BOTTOM_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.DOWN_EAST.ordinal()][CommonStairShape.OUTER_BACK_LEFT_BOTTOM_RIGHT.ordinal()] = new StateModelDefinition("/bottom/twist_left", 270);
		MODEL_DEFINITIONS[ComplexFacing.SOUTH_DOWN.ordinal()][CommonStairShape.OUTER_BACK_RIGHT_BOTTOM_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.DOWN_SOUTH.ordinal()][CommonStairShape.OUTER_BACK_RIGHT_BOTTOM_LEFT.ordinal()] = new StateModelDefinition("/bottom/twist_right", 0);
		MODEL_DEFINITIONS[ComplexFacing.WEST_DOWN.ordinal()][CommonStairShape.OUTER_BACK_RIGHT_BOTTOM_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.DOWN_WEST.ordinal()][CommonStairShape.OUTER_BACK_RIGHT_BOTTOM_LEFT.ordinal()] = new StateModelDefinition("/bottom/twist_right", 90);
		MODEL_DEFINITIONS[ComplexFacing.NORTH_DOWN.ordinal()][CommonStairShape.OUTER_BACK_RIGHT_BOTTOM_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.DOWN_NORTH.ordinal()][CommonStairShape.OUTER_BACK_RIGHT_BOTTOM_LEFT.ordinal()] = new StateModelDefinition("/bottom/twist_right", 180);
		MODEL_DEFINITIONS[ComplexFacing.EAST_DOWN.ordinal()][CommonStairShape.OUTER_BACK_RIGHT_BOTTOM_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.DOWN_EAST.ordinal()][CommonStairShape.OUTER_BACK_RIGHT_BOTTOM_LEFT.ordinal()] = new StateModelDefinition("/bottom/twist_right", 270);
		MODEL_DEFINITIONS[ComplexFacing.EAST_SOUTH.ordinal()][CommonStairShape.STRAIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.SOUTH_EAST.ordinal()][CommonStairShape.STRAIGHT.ordinal()] = new StateModelDefinition("/side/straight", 0);
		MODEL_DEFINITIONS[ComplexFacing.WEST_SOUTH.ordinal()][CommonStairShape.STRAIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.SOUTH_WEST.ordinal()][CommonStairShape.STRAIGHT.ordinal()] = new StateModelDefinition("/side/straight", 90);
		MODEL_DEFINITIONS[ComplexFacing.WEST_NORTH.ordinal()][CommonStairShape.STRAIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.NORTH_WEST.ordinal()][CommonStairShape.STRAIGHT.ordinal()] = new StateModelDefinition("/side/straight", 180);
		MODEL_DEFINITIONS[ComplexFacing.EAST_NORTH.ordinal()][CommonStairShape.STRAIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.NORTH_EAST.ordinal()][CommonStairShape.STRAIGHT.ordinal()] = new StateModelDefinition("/side/straight", 270);
		MODEL_DEFINITIONS[ComplexFacing.EAST_SOUTH.ordinal()][CommonStairShape.OUTER_BACK_LEFT_BOTTOM_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.SOUTH_EAST.ordinal()][CommonStairShape.OUTER_BACK_LEFT_BOTTOM_RIGHT.ordinal()] = new StateModelDefinition("/side/twist_left", 0);
		MODEL_DEFINITIONS[ComplexFacing.WEST_SOUTH.ordinal()][CommonStairShape.OUTER_BACK_LEFT_BOTTOM_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.SOUTH_WEST.ordinal()][CommonStairShape.OUTER_BACK_LEFT_BOTTOM_RIGHT.ordinal()] = new StateModelDefinition("/side/twist_left", 90);
		MODEL_DEFINITIONS[ComplexFacing.WEST_NORTH.ordinal()][CommonStairShape.OUTER_BACK_LEFT_BOTTOM_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.NORTH_WEST.ordinal()][CommonStairShape.OUTER_BACK_LEFT_BOTTOM_RIGHT.ordinal()] = new StateModelDefinition("/side/twist_left", 180);
		MODEL_DEFINITIONS[ComplexFacing.EAST_NORTH.ordinal()][CommonStairShape.OUTER_BACK_LEFT_BOTTOM_RIGHT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.NORTH_EAST.ordinal()][CommonStairShape.OUTER_BACK_LEFT_BOTTOM_RIGHT.ordinal()] = new StateModelDefinition("/side/twist_left", 270);
		MODEL_DEFINITIONS[ComplexFacing.EAST_SOUTH.ordinal()][CommonStairShape.OUTER_BACK_RIGHT_BOTTOM_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.SOUTH_EAST.ordinal()][CommonStairShape.OUTER_BACK_RIGHT_BOTTOM_LEFT.ordinal()] = new StateModelDefinition("/side/twist_right", 0);
		MODEL_DEFINITIONS[ComplexFacing.WEST_SOUTH.ordinal()][CommonStairShape.OUTER_BACK_RIGHT_BOTTOM_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.SOUTH_WEST.ordinal()][CommonStairShape.OUTER_BACK_RIGHT_BOTTOM_LEFT.ordinal()] = new StateModelDefinition("/side/twist_right", 90);
		MODEL_DEFINITIONS[ComplexFacing.WEST_NORTH.ordinal()][CommonStairShape.OUTER_BACK_RIGHT_BOTTOM_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.NORTH_WEST.ordinal()][CommonStairShape.OUTER_BACK_RIGHT_BOTTOM_LEFT.ordinal()] = new StateModelDefinition("/side/twist_right", 180);
		MODEL_DEFINITIONS[ComplexFacing.EAST_NORTH.ordinal()][CommonStairShape.OUTER_BACK_RIGHT_BOTTOM_LEFT.ordinal()] = 
		MODEL_DEFINITIONS[ComplexFacing.NORTH_EAST.ordinal()][CommonStairShape.OUTER_BACK_RIGHT_BOTTOM_LEFT.ordinal()] = new StateModelDefinition("/side/twist_right", 270);
	}
	
	/*
	private static void setUpStairModelDefs(ComplexFacing vanilla, ComplexFacing vanillaFlipped, int rotation) {
		setUpStairModelDefs(MODEL_DEFINITIONS[vanilla.ordinal()], MODEL_DEFINITIONS[vanillaFlipped.ordinal()], rotation);
	}
	
	private static void setUpStairModelDefs(StateModelDefinition[] vanilla, StateModelDefinition[] vanillaFlipped, int rotation) {
		int rotation2 = (rotation + 90) % 360;
		setStairModelDef(vanilla, vanillaFlipped, CommonStairShape.STRAIGHT, new StateModelDefinition("/top/straight", rotation));

		setStairModelDef(vanilla, vanillaFlipped, CommonStairShape.INNER_FRONT_LEFT, new StateModelDefinition("/top/inner", rotation));
		setStairModelDef(vanilla, vanillaFlipped, CommonStairShape.INNER_FRONT_RIGHT, new StateModelDefinition("/top/inner", rotation2));
		setStairModelDef(vanilla, vanillaFlipped, CommonStairShape.INNER_TOP_LEFT, new StateModelDefinition("/top/inner", rotation));
		setStairModelDef(vanilla, vanillaFlipped, CommonStairShape.INNER_TOP_RIGHT, new StateModelDefinition("/top/inner", rotation2));
		setStairModelDef(vanilla, vanillaFlipped, CommonStairShape.INNER_BOTH_LEFT, new StateModelDefinition("/top/inner", rotation));
		setStairModelDef(vanilla, vanillaFlipped, CommonStairShape.INNER_BOTH_RIGHT, new StateModelDefinition("/top/inner", rotation2));

		setStairModelDef(vanilla, vanillaFlipped, CommonStairShape.OUTER_BACK_LEFT, new StateModelDefinition("/top/outer_back", rotation));
		setStairModelDef(vanilla, vanillaFlipped, CommonStairShape.OUTER_BACK_RIGHT, new StateModelDefinition("/top/outer_back", rotation2));
		setStairModelDef(vanilla, vanillaFlipped, CommonStairShape.OUTER_BOTTOM_LEFT, new StateModelDefinition("/top/outer_bottom_left", rotation));
		setStairModelDef(vanilla, vanillaFlipped, CommonStairShape.OUTER_BOTTOM_RIGHT, new StateModelDefinition("/top/outer_bottom_right", rotation));
		setStairModelDef(vanilla, vanillaFlipped, CommonStairShape.OUTER_BOTH_LEFT, new StateModelDefinition("/top/outer_both", rotation));
		setStairModelDef(vanilla, vanillaFlipped, CommonStairShape.OUTER_BOTH_RIGHT, new StateModelDefinition("/top/outer_both", rotation2));

		setStairModelDef(vanilla, vanillaFlipped, CommonStairShape.OUTER_BACK_LEFT_BOTTOM_RIGHT, new StateModelDefinition("/top/twist_right", rotation));
		setStairModelDef(vanilla, vanillaFlipped, CommonStairShape.OUTER_BACK_RIGHT_BOTTOM_LEFT, new StateModelDefinition("/top/twist_left", rotation));
	}
	
	private static void setDownStairModelDefs(ComplexFacing vanilla, ComplexFacing vanillaFlipped, int rotation) {
		setDownStairModelDefs(MODEL_DEFINITIONS[vanilla.ordinal()], MODEL_DEFINITIONS[vanillaFlipped.ordinal()], rotation);
	}
	
	private static void setDownStairModelDefs(StateModelDefinition[] vanilla, StateModelDefinition[] vanillaFlipped, int rotation) {
		int rotation2 = (rotation + 90) % 360;
		setStairModelDef(vanilla, vanillaFlipped, CommonStairShape.STRAIGHT, new StateModelDefinition("/bottom/straight", rotation));

		setStairModelDef(vanilla, vanillaFlipped, CommonStairShape.INNER_FRONT_LEFT, new StateModelDefinition("/bottom/inner", rotation2));
		setStairModelDef(vanilla, vanillaFlipped, CommonStairShape.INNER_FRONT_RIGHT, new StateModelDefinition("/bottom/inner", rotation));
		setStairModelDef(vanilla, vanillaFlipped, CommonStairShape.INNER_TOP_LEFT, new StateModelDefinition("/bottom/inner", rotation2));
		setStairModelDef(vanilla, vanillaFlipped, CommonStairShape.INNER_TOP_RIGHT, new StateModelDefinition("/bottom/inner", rotation));
		setStairModelDef(vanilla, vanillaFlipped, CommonStairShape.INNER_BOTH_LEFT, new StateModelDefinition("/bottom/inner", rotation2));
		setStairModelDef(vanilla, vanillaFlipped, CommonStairShape.INNER_BOTH_RIGHT, new StateModelDefinition("/bottom/inner", rotation));

		setStairModelDef(vanilla, vanillaFlipped, CommonStairShape.OUTER_BACK_LEFT, new StateModelDefinition("/bottom/outer_back", rotation2));
		setStairModelDef(vanilla, vanillaFlipped, CommonStairShape.OUTER_BACK_RIGHT, new StateModelDefinition("/bottom/outer_back", rotation));
		setStairModelDef(vanilla, vanillaFlipped, CommonStairShape.OUTER_BOTTOM_LEFT, new StateModelDefinition("/bottom/outer_top_right", rotation));
		setStairModelDef(vanilla, vanillaFlipped, CommonStairShape.OUTER_BOTTOM_RIGHT, new StateModelDefinition("/bottom/outer_top_left", rotation));
		setStairModelDef(vanilla, vanillaFlipped, CommonStairShape.OUTER_BOTH_LEFT, new StateModelDefinition("/bottom/outer_both", rotation2));
		setStairModelDef(vanilla, vanillaFlipped, CommonStairShape.OUTER_BOTH_RIGHT, new StateModelDefinition("/bottom/outer_both", rotation));

		setStairModelDef(vanilla, vanillaFlipped, CommonStairShape.OUTER_BACK_LEFT_BOTTOM_RIGHT, new StateModelDefinition("/bottom/twist_left", rotation));
		setStairModelDef(vanilla, vanillaFlipped, CommonStairShape.OUTER_BACK_RIGHT_BOTTOM_LEFT, new StateModelDefinition("/bottom/twist_right", rotation));
	}
	
	private static void setVerticalStairModelDefs(ComplexFacing vanilla, ComplexFacing vanillaFlipped, int rotation) {
		setVerticalStairModelDefs(MODEL_DEFINITIONS[vanilla.ordinal()], MODEL_DEFINITIONS[vanillaFlipped.ordinal()], rotation);
	}
	
	private static void setVerticalStairModelDefs(StateModelDefinition[] vanilla, StateModelDefinition[] vanillaFlipped, int rotation) {
		int rotation2 = (rotation + 270) % 360;
		setStairModelDef(vanilla, vanillaFlipped, CommonStairShape.STRAIGHT, new StateModelDefinition("/side/straight", rotation));

		setStairModelDef(vanilla, vanillaFlipped, CommonStairShape.INNER_FRONT_LEFT, new StateModelDefinition("/bottom/inner", rotation));
		setStairModelDef(vanilla, vanillaFlipped, CommonStairShape.INNER_FRONT_RIGHT, new StateModelDefinition("/top/inner", rotation));
		setStairModelDef(vanilla, vanillaFlipped, CommonStairShape.INNER_TOP_LEFT, new StateModelDefinition("/bottom/inner", rotation));
		setStairModelDef(vanilla, vanillaFlipped, CommonStairShape.INNER_TOP_RIGHT, new StateModelDefinition("/top/inner", rotation));
		setStairModelDef(vanilla, vanillaFlipped, CommonStairShape.INNER_BOTH_LEFT, new StateModelDefinition("/bottom/inner", rotation));
		setStairModelDef(vanilla, vanillaFlipped, CommonStairShape.INNER_BOTH_RIGHT, new StateModelDefinition("/top/inner", rotation));

		setStairModelDef(vanilla, vanillaFlipped, CommonStairShape.OUTER_BACK_LEFT, new StateModelDefinition("/bottom/outer_top_right", rotation2));
		setStairModelDef(vanilla, vanillaFlipped, CommonStairShape.OUTER_BACK_RIGHT, new StateModelDefinition("/top/outer_bottom_right", rotation2));
		setStairModelDef(vanilla, vanillaFlipped, CommonStairShape.OUTER_BOTTOM_LEFT, new StateModelDefinition("/bottom/outer_top_left", rotation));
		setStairModelDef(vanilla, vanillaFlipped, CommonStairShape.OUTER_BOTTOM_RIGHT, new StateModelDefinition("/top/outer_bottom_left", rotation));
		setStairModelDef(vanilla, vanillaFlipped, CommonStairShape.OUTER_BOTH_LEFT, new StateModelDefinition("/bottom/outer_both", rotation));
		setStairModelDef(vanilla, vanillaFlipped, CommonStairShape.OUTER_BOTH_RIGHT, new StateModelDefinition("/top/outer_both", rotation));

		setStairModelDef(vanilla, vanillaFlipped, CommonStairShape.OUTER_BACK_LEFT_BOTTOM_RIGHT, new StateModelDefinition("/side/twist_left", rotation));
		setStairModelDef(vanilla, vanillaFlipped, CommonStairShape.OUTER_BACK_RIGHT_BOTTOM_LEFT, new StateModelDefinition("/side/twist_right", rotation));
	}
	
	private static void setStairModelDef(StateModelDefinition[] vanilla, StateModelDefinition[] vanillaFlipped, CommonStairShape shape, StateModelDefinition model) {
		vanilla[shape.ordinal()] = vanillaFlipped[shape.flipped().ordinal()] = model;
	}
	*/

	public static StateModelDefinition getModelDefinition(CommonStairShapeState complexShapeState) {
		return getModelDefinition(complexShapeState.facing, complexShapeState.shape);
	}

	public static StateModelDefinition getModelDefinition(ComplexFacing facing, CommonStairShape shape) {
		return MODEL_DEFINITIONS[facing.ordinal()][shape.ordinal()];
	}
}
