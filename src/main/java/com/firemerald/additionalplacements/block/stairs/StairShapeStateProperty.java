package com.firemerald.additionalplacements.block.stairs;

import java.util.*;
import java.util.stream.Collectors;

import com.firemerald.additionalplacements.block.stairs.common.CommonStairShapeState;

import net.minecraft.world.level.block.state.properties.Property;

public class StairShapeStateProperty extends Property<CommonStairShapeState> {
	private final Collection<CommonStairShapeState> values;
	private final Map<String, CommonStairShapeState> valueMap;

	protected StairShapeStateProperty(String name, CommonStairShapeState... values) {
		super(name, CommonStairShapeState.class);
		this.values = Collections.unmodifiableList(Arrays.asList(values));
		this.valueMap = this.values.stream().collect(Collectors.toMap(CommonStairShapeState::getSerializedName, state -> state));
	}
	
	@Override
	public Collection<CommonStairShapeState> getPossibleValues() {
		return values;
	}

	@Override
	public String getName(CommonStairShapeState value) {
		return value.getSerializedName();
	}

	@Override
	public Optional<CommonStairShapeState> getValue(String name) {
		return Optional.ofNullable(valueMap.get(name));
	}

	@Override
	public boolean equals(Object pOther) {
		if (this == pOther) return true;
		else if (pOther instanceof StairShapeStateProperty shapeStateProperty && super.equals(pOther)) return this.values.equals(shapeStateProperty.values) && this.valueMap.equals(shapeStateProperty.valueMap);
		else return false;
	}
	
	@Override
	public int generateHashCode() {
		return 31 * (31 * super.generateHashCode() + this.values.hashCode()) + this.valueMap.hashCode();
	}
	
	public boolean isValid(CommonStairShapeState value) {
		return isValid(value.getSerializedName());
	}
	
	public boolean isValid(String name) {
		return valueMap.containsKey(name);
	}
}
