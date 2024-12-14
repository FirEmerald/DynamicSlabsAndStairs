package com.firemerald.additionalplacements.block.stairs.extended;

import com.firemerald.additionalplacements.util.ComplexFacing;

public enum ExtendedStairShapeFace {
	VANILLA {
		@Override
		public ComplexFacing getFacing(ExtendedStairFacing facing) {
			return facing.vanilla;
		}
	},
	VANILLA_FLIPPED {
		@Override
		public ComplexFacing getFacing(ExtendedStairFacing facing) {
			return facing.vanillaFlipped;
		}
	},
	VERTICAL {
		@Override
		public ComplexFacing getFacing(ExtendedStairFacing facing) {
			return facing.vertical;
		}
	};
	
	public abstract ComplexFacing getFacing(ExtendedStairFacing facing);
}
