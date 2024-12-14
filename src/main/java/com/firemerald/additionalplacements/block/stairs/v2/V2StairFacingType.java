package com.firemerald.additionalplacements.block.stairs.v2;

import com.firemerald.additionalplacements.util.ComplexFacing;

public enum V2StairFacingType {
	NORMAL {
		@Override
		public ComplexFacing fromCompressedFacing(V2StairFacing facing) {
			return facing.normal;
		}
	},
	FLIPPED {
		@Override
		public ComplexFacing fromCompressedFacing(V2StairFacing facing) {
			return facing.flipped;
		}
	},
	VERTICAL {
		@Override
		public ComplexFacing fromCompressedFacing(V2StairFacing facing) {
			return facing.vertical;
		}
	};
	
	public abstract ComplexFacing fromCompressedFacing(V2StairFacing facing);
}
