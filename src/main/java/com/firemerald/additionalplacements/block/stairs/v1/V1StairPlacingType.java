package com.firemerald.additionalplacements.block.stairs.v1;

import com.firemerald.additionalplacements.util.ComplexFacing;

public enum V1StairPlacingType {
	NORMAL {
		@Override
		public ComplexFacing fromV1Placing(V1StairPlacing placing) {
			return placing.equivalent;
		}
	},
	UP_CLOCKWISE {
		@Override
		public ComplexFacing fromV1Placing(V1StairPlacing placing) {
			return placing.cwTop;
		}
	},
	UP_COUNTER_CLOCKWISE {
		@Override
		public ComplexFacing fromV1Placing(V1StairPlacing placing) {
			return placing.ccwTop;
		}
	},
	DOWN_CLOCKWISE {
		@Override
		public ComplexFacing fromV1Placing(V1StairPlacing placing) {
			return placing.cwBottom;
		}
	},
	DOWN_COUNTER_CLOCKWISE {
		@Override
		public ComplexFacing fromV1Placing(V1StairPlacing placing) {
			return placing.ccwBottom;
		}
	};

	public abstract ComplexFacing fromV1Placing(V1StairPlacing placing);
}
