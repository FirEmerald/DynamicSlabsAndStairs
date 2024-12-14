package com.firemerald.additionalplacements.block.stairs;

import com.firemerald.additionalplacements.block.interfaces.IStateFixer;
import com.firemerald.additionalplacements.block.stairs.common.CommonStairShapeState;
import com.firemerald.additionalplacements.block.stairs.complex.AdditionalComplexStairBlock;
import com.firemerald.additionalplacements.block.stairs.complex.ComplexStairFacing;
import com.firemerald.additionalplacements.block.stairs.complex.ComplexStairShape;
import com.firemerald.additionalplacements.block.stairs.complex.ComplexStairShapeState;
import com.firemerald.additionalplacements.block.stairs.extended.AdditionalExtendedStairBlock;
import com.firemerald.additionalplacements.block.stairs.extended.ExtendedStairFacing;
import com.firemerald.additionalplacements.block.stairs.extended.ExtendedStairShape;
import com.firemerald.additionalplacements.block.stairs.extended.ExtendedStairShapeState;
import com.firemerald.additionalplacements.block.stairs.simple.AdditionalSimpleStairBlock;
import com.firemerald.additionalplacements.block.stairs.simple.SimpleStairShapeState;
import com.firemerald.additionalplacements.block.stairs.v1.V1StairPlacing;
import com.firemerald.additionalplacements.block.stairs.v1.V1StairShape;
import com.firemerald.additionalplacements.block.stairs.v1.V1StairShapeState;
import com.firemerald.additionalplacements.block.stairs.v2.V2StairFacing;
import com.firemerald.additionalplacements.block.stairs.v2.V2StairShape;
import com.firemerald.additionalplacements.block.stairs.v2.V2StairShapeState;

import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.StairsShape;

public enum StairPropertyVersion implements StringRepresentable {
	OLD_V1("old_v1") {
		@Override
		public CommonStairShapeState apply(CompoundTag properties) {
			//AdditionalPlacementsMod.LOGGER.debug(this + " Potentially fixing potential V1 block state: " + properties);
			V1StairPlacing placing = V1StairPlacing.get(properties.getString("placing"));
			V1StairShape shape = V1StairShape.get(properties.getString("shape"));
			if (placing != null && shape != null) { //V1
				//AdditionalPlacementsMod.LOGGER.debug(this + " Fixing V1 block state");
				properties.remove("placing");
				properties.remove("shape");
				return V1StairShapeState.toCommon(placing, shape);
			} else return null;
		}
	},
	OLD_V2("old_v2") {
		@Override
		public CommonStairShapeState apply(CompoundTag properties) {
			//AdditionalPlacementsMod.LOGGER.debug(this + " Potentially fixing potential V2 block state: " + properties);
			V2StairFacing facing = V2StairFacing.get(properties.getString("facing"));
			V2StairShape shape = V2StairShape.get(properties.getString("shape"));
			if (facing != null && shape != null) { //V2
				//AdditionalPlacementsMod.LOGGER.debug(this + " Fixing V2 block state");
				properties.remove("facing");
				properties.remove("shape");
				return V2StairShapeState.toCommon(facing, shape);
			} else return null;
		}
	},
	SIMPLE_V1("simple_v1") {
		@Override
		public CommonStairShapeState apply(CompoundTag properties) {
			Axis axis = IStateFixer.getProperty(properties, AdditionalSimpleStairBlock.AXIS);
			Direction facing = IStateFixer.getProperty(properties, StairBlock.FACING);
			Half half = IStateFixer.getProperty(properties, StairBlock.HALF);
			StairsShape shape = IStateFixer.getProperty(properties, StairBlock.SHAPE);
			if (axis != null && facing != null && half != null && shape != null) {
				properties.remove(AdditionalSimpleStairBlock.AXIS.getName());
				properties.remove(StairBlock.FACING.getName());
				properties.remove(StairBlock.HALF.getName());
				properties.remove(StairBlock.SHAPE.getName());
				return SimpleStairShapeState.toCommon(axis, facing, half, shape);
			}
			else return null;
		}
	},
	EXTENDED_V1("extended_v1") {
		@Override
		public CommonStairShapeState apply(CompoundTag properties) {
			ExtendedStairFacing facing = IStateFixer.getProperty(properties, AdditionalExtendedStairBlock.FACING);
			ExtendedStairShape shape = IStateFixer.getProperty(properties, AdditionalExtendedStairBlock.SHAPE);
			if (facing != null && shape != null) {
				properties.remove(AdditionalExtendedStairBlock.FACING.getName());
				properties.remove(AdditionalExtendedStairBlock.SHAPE.getName());
				return ExtendedStairShapeState.toCommon(facing, shape);
			}
			else return null;
		}
	},
	COMPLEX_V1("complex_v1") {
		@Override
		public CommonStairShapeState apply(CompoundTag properties) {
			ComplexStairFacing facing = IStateFixer.getProperty(properties, AdditionalComplexStairBlock.FACING);
			ComplexStairShape shape = IStateFixer.getProperty(properties, AdditionalComplexStairBlock.SHAPE);
			if (facing != null && shape != null) {
				properties.remove(AdditionalExtendedStairBlock.FACING.getName());
				properties.remove(AdditionalExtendedStairBlock.SHAPE.getName());
				return ComplexStairShapeState.toCommon(facing, shape);
			}
			else return null;
		}
	};
	
	public static StairPropertyVersion get(String name) {
		for (StairPropertyVersion version : values()) if (version.name.equals(name)) return version;
		return null;
	}
	
	public final String name;

	StairPropertyVersion(String name) {
		this.name = name;
	}

	@Override
	public String getSerializedName() {
		return name;
	}
	
	public abstract CommonStairShapeState apply(CompoundTag properties);
}
