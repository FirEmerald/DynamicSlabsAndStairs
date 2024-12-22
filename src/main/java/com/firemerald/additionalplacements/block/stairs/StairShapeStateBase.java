package com.firemerald.additionalplacements.block.stairs;

public abstract class StairShapeStateBase {
	public final int ordinal;
	
	protected StairShapeStateBase(int ordinal) {
		this.ordinal = ordinal;
	}
	
	@Override
	public int hashCode() {
		return ordinal;
	}
}
