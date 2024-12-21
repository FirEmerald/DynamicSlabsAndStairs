package com.firemerald.additionalplacements.block.stairs;

import com.firemerald.additionalplacements.block.stairs.common.CommonStairShapeState;

public abstract class SpecificStairShapeStateBase extends StairShapeStateBase {
	protected CommonStairShapeState common;
	
	protected SpecificStairShapeStateBase(int ordinal) {
		super(ordinal);
	}
	
	public void setCommon(CommonStairShapeState common) {
		this.common = common;
	}
	
	public CommonStairShapeState common() {
		return common;
	}
}
