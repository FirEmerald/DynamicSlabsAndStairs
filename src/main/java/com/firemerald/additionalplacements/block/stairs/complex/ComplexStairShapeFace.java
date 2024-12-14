package com.firemerald.additionalplacements.block.stairs.complex;

import com.firemerald.additionalplacements.util.ComplexFacing;

public enum ComplexStairShapeFace {
	NORMAL {
		@Override
		public ComplexFacing getFacing(ComplexStairFacing multiFacing) {
			return multiFacing.normal;
		}
	},
	UP_FRONT {
		@Override
		public ComplexFacing getFacing(ComplexStairFacing multiFacing) {
			return multiFacing.upFront;
		}
	},
	UP_TOP {
		@Override
		public ComplexFacing getFacing(ComplexStairFacing multiFacing) {
			return multiFacing.upTop;
		}
	},
	DOWN_FRONT {
		@Override
		public ComplexFacing getFacing(ComplexStairFacing multiFacing) {
			return multiFacing.downFront;
		}
	},
	DOWN_TOP {
		@Override
		public ComplexFacing getFacing(ComplexStairFacing multiFacing) {
			return multiFacing.downTop;
		}
	};
	
	public abstract ComplexFacing getFacing(ComplexStairFacing multiFacing);
}
