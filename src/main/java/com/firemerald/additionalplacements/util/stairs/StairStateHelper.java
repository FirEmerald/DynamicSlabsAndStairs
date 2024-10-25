package com.firemerald.additionalplacements.util.stairs;

import org.apache.commons.lang3.tuple.Pair;

import com.firemerald.additionalplacements.block.VerticalStairBlock;
import com.firemerald.additionalplacements.util.ComplexFacing;

import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;
import net.minecraft.state.properties.Half;

public class StairStateHelper
{
	@SuppressWarnings("incomplete-switch")
	public static ComplexFacing getFacingFromVanilla(BlockState blockState) {
		if (blockState.getValue(StairsBlock.HALF) == Half.BOTTOM) switch (blockState.getValue(StairsBlock.FACING))
		{
		case SOUTH: return ComplexFacing.NORTH_UP;
		case WEST: return ComplexFacing.EAST_UP;
		case NORTH: return ComplexFacing.SOUTH_UP;
		case EAST: return ComplexFacing.WEST_UP;
		}
		else switch (blockState.getValue(StairsBlock.FACING))
		{
		case SOUTH: return ComplexFacing.NORTH_DOWN;
		case WEST: return ComplexFacing.EAST_DOWN;
		case NORTH: return ComplexFacing.SOUTH_DOWN;
		case EAST: return ComplexFacing.WEST_DOWN;
		}
		return null;
	}
	
	public static ComplexFacing getFacingFromAdditional(BlockState blockState) {
		return blockState.getValue(((VerticalStairBlock) blockState.getBlock()).shapeProperty()).facingType.fromCompressedFacing(blockState.getValue(VerticalStairBlock.FACING));
	}
	
	public static ComplexFacing getFacing(BlockState blockState) {
		if (blockState.getBlock() instanceof StairsBlock) return getFacingFromVanilla(blockState);
		else if (blockState.getBlock() instanceof VerticalStairBlock) return getFacingFromAdditional(blockState);
		else return null;
	}
	
	public static StairShape getShapeFromVanilla(BlockState blockState) {
		return StairShape.getShape(blockState.getValue(StairsBlock.SHAPE), blockState.getValue(StairsBlock.HALF));
	}
	
	public static StairShape getShapeFromAdditional(BlockState blockState) {
		return blockState.getValue(((VerticalStairBlock) blockState.getBlock()).shapeProperty()).shape;
	}
	
	public static StairShape getShape(BlockState blockState) {
		if (blockState.getBlock() instanceof StairsBlock) return getShapeFromVanilla(blockState);
		else if (blockState.getBlock() instanceof VerticalStairBlock) return getShapeFromAdditional(blockState);
		else return null;
	}

	public static Pair<ComplexFacing, StairShape> getFullState(BlockState blockState)
	{
		if (blockState.getBlock() instanceof StairsBlock) return Pair.of(getFacingFromVanilla(blockState), getShapeFromVanilla(blockState));
		else return Pair.of(getFacingFromAdditional(blockState), getShapeFromAdditional(blockState));
	}
}
