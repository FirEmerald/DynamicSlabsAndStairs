package com.firemerald.additionalplacements.util;

import java.util.Map;
import java.util.WeakHashMap;

import com.firemerald.additionalplacements.client.BlockModelUtils;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public enum BlockRotation
{
	IDENTITY(Direction.values(), new int[6]) {
		@Override
		public Direction apply(Direction original) {
			return original;
		}
		
		@Override
		public Direction unapply(Direction original) {
			return original;
		}

		@Override
		public int getVertexShiftLeft(Direction original) {
			return 0;
		}
		
		@Override
		public int[] applyVertices(Direction original, int[] oldData, int vertexSize) {
			return BlockModelUtils.copyVertices(oldData);
		}

		@Override
		public void applyBlockSpace(float[] vertex) {}

		@Override
		public void rotatePos(int[] oldData, int oldPos, int[] newData, int newPos) {}

		@Override
		public VoxelShape applyBlockSpace(VoxelShape shape) {
			return shape;
		}

		@Override
		public VoxelShape createRotatedBox(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
			return Shapes.create(minX, minY, minZ, maxX, maxY, maxZ);
		}

		/* Too much information about the quad is hidden, making re-ordering vertices (to fix an annoying AO bug) impossible, so we will have to use vanilla model code
		@Override
		public boolean transform(MutableQuadView quad, boolean rotateUV) {
			return true;
		}

		@Override
		public void transformVertex(MutableQuadView quad, int index) {}
		*/
	},
	X_90(new Direction[] {
			Direction.SOUTH, Direction.NORTH,
			Direction.DOWN, Direction.UP,
			Direction.WEST, Direction.EAST,
	}, new int[] {
			0, 2,
			2, 0,
			3, 1
	}) {
		//x = x
		//y = z
		//z = -y
		@Override
		public void applyBlockSpace(float[] vertex) {
			float temp = vertex[1];
			vertex[1] = vertex[2];
			vertex[2] = 1 - temp;
		}

		@Override
		public void rotatePos(int[] oldData, int oldPos, int[] newData, int newPos) {
			//newData[newPos + 0] = oldData[oldPos + 0];
			newData[newPos + 1] = oldData[oldPos + 2];
			newData[newPos + 2] = Float.floatToRawIntBits(1 - Float.intBitsToFloat(oldData[oldPos + 1]));
		}

		@Override
		public VoxelShape createRotatedBox(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
			return Shapes.create(
	        		minX, 
	        		minZ, 
	        		1 - maxY, 
	        		maxX, 
	        		maxZ, 
	        		1 - minY);
		}

		/* Too much information about the quad is hidden, making re-ordering vertices (to fix an annoying AO bug) impossible, so we will have to use vanilla model code
		@Override
		public void transformVertex(MutableQuadView quad, int index) {
			quad.pos(index, 
					quad.x(index), 
					quad.z(index), 
					1 - quad.y(index));
			quad.normal(index, 
					quad.normalX(index), 
					quad.normalZ(index), 
					-quad.normalY(index));
		}
		*/
	},
	X_270(new Direction[] {
			Direction.NORTH, Direction.SOUTH,
			Direction.UP, Direction.DOWN,
			Direction.WEST, Direction.EAST,
	}, new int[] {
			2, 0,
			2, 0,
			1, 3
	}) {
		//x = x
		//y = -z
		//z = y
		@Override
		public void applyBlockSpace(float[] vertex) {
			float temp = vertex[1];
			vertex[1] = 1 - vertex[2];
			vertex[2] = temp;
		}

		@Override
		public void rotatePos(int[] oldData, int oldPos, int[] newData, int newPos) {
			//newData[newPos + 0] = oldData[oldPos + 0];
			newData[newPos + 1] = Float.floatToRawIntBits(1 - Float.intBitsToFloat(oldData[oldPos + 2]));
			newData[newPos + 2] = oldData[oldPos + 1];
		}

		@Override
		public VoxelShape createRotatedBox(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
			return Shapes.create(
	        		minX, 
	        		1 - maxZ, 
	        		minY, 
	        		maxX, 
	        		1 - minZ, 
	        		maxY);
		}

		/* Too much information about the quad is hidden, making re-ordering vertices (to fix an annoying AO bug) impossible, so we will have to use vanilla model code
		@Override
		public void transformVertex(MutableQuadView quad, int index) {
			quad.pos(index, 
					quad.x(index), 
					1 - quad.z(index), 
					quad.y(index));
			quad.normal(index, 
					quad.normalX(index), 
					-quad.normalZ(index), 
					quad.normalY(index));
		}
		*/
	},
	X_270_Y_90(new Direction[] {
			Direction.EAST, Direction.WEST,
			Direction.UP, Direction.DOWN,
			Direction.NORTH, Direction.SOUTH,
	}, new int[] {
			2, 0,
			3, 3,
			1, 3
	}) {
		//x = -y
		//y = -z
		//z = x
		@Override
		public void applyBlockSpace(float[] vertex) {
			float temp = vertex[0];
			vertex[0] = 1 - vertex[1];
			vertex[1] = 1 - vertex[2];
			vertex[2] = temp;
		}

		@Override
		public void rotatePos(int[] oldData, int oldPos, int[] newData, int newPos) {
			newData[newPos + 0] = Float.floatToRawIntBits(1 - Float.intBitsToFloat(oldData[oldPos + 1]));
			newData[newPos + 1] = Float.floatToRawIntBits(1 - Float.intBitsToFloat(oldData[oldPos + 2]));
			newData[newPos + 2] = oldData[oldPos + 0];
		}

		@Override
		public VoxelShape createRotatedBox(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
			return Shapes.create(
	        		1 - maxY, 
	        		1 - maxZ, 
	        		minX, 
	        		1 - minY, 
	        		1 - minZ, 
	        		maxX);
		}

		/* Too much information about the quad is hidden, making re-ordering vertices (to fix an annoying AO bug) impossible, so we will have to use vanilla model code
		@Override
		public void transformVertex(MutableQuadView quad, int index) {
			quad.pos(index, 
					1 - quad.y(index), 
					1 - quad.z(index), 
					quad.x(index));
			quad.normal(index, 
					-quad.normalY(index), 
					-quad.normalZ(index), 
					quad.normalX(index));
		}
		*/
	},
	X_270_Y_180(new Direction[] {
			Direction.SOUTH, Direction.NORTH,
			Direction.UP, Direction.DOWN,
			Direction.EAST, Direction.WEST,
	}, new int[] {
			2, 0,
			0, 2,
			1, 3
	}) {
		//x = -x
		//y = -z
		//z = -y
		@Override
		public void applyBlockSpace(float[] vertex) {
			vertex[0] = 1 - vertex[0];
			float temp = vertex[1];
			vertex[1] = 1 - vertex[2];
			vertex[2] = 1 - temp;
		}

		@Override
		public void rotatePos(int[] oldData, int oldPos, int[] newData, int newPos) {
			newData[newPos + 0] = Float.floatToRawIntBits(1 - Float.intBitsToFloat(oldData[oldPos + 0]));
			newData[newPos + 1] = Float.floatToRawIntBits(1 - Float.intBitsToFloat(oldData[oldPos + 2]));
			newData[newPos + 2] = Float.floatToRawIntBits(1 - Float.intBitsToFloat(oldData[oldPos + 1]));
		}

		@Override
		public VoxelShape createRotatedBox(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
			return Shapes.create(
	        		1 - maxX, 
	        		1 - maxZ, 
	        		1 - maxY, 
	        		1 - minX, 
	        		1 - minZ, 
	        		1 - minY);
		}

		/* Too much information about the quad is hidden, making re-ordering vertices (to fix an annoying AO bug) impossible, so we will have to use vanilla model code
		@Override
		public void transformVertex(MutableQuadView quad, int index) {
			quad.pos(index, 
					1 - quad.x(index), 
					1 - quad.z(index), 
					1 - quad.y(index));
			quad.normal(index, 
					-quad.normalX(index), 
					-quad.normalZ(index), 
					-quad.normalY(index));
		}
		*/
	},
	X_270_Y_270(new Direction[] {
			Direction.WEST, Direction.EAST,
			Direction.UP, Direction.DOWN,
			Direction.SOUTH, Direction.NORTH,
	}, new int[] {
			2, 0,
			1, 1,
			1, 3
	}) {
		//x = y
		//y = -z
		//z = -x
		@Override
		public void applyBlockSpace(float[] vertex) {
			float temp = vertex[0];
			vertex[0] = vertex[1];
			vertex[1] = 1 - vertex[2];
			vertex[2] = 1 - temp;
		}

		@Override
		public void rotatePos(int[] oldData, int oldPos, int[] newData, int newPos) {
			newData[newPos + 0] = oldData[oldPos + 1];
			newData[newPos + 1] = Float.floatToRawIntBits(1 - Float.intBitsToFloat(oldData[oldPos + 2]));
			newData[newPos + 2] = Float.floatToRawIntBits(1 - Float.intBitsToFloat(oldData[oldPos + 0]));
		}

		@Override
		public VoxelShape createRotatedBox(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
			return Shapes.create(
	        		minY, 
	        		1 - maxZ, 
	        		1 - maxX, 
	        		maxY, 
	        		1 - minZ, 
	        		1 - minX);
		}

		/* Too much information about the quad is hidden, making re-ordering vertices (to fix an annoying AO bug) impossible, so we will have to use vanilla model code
		@Override
		public void transformVertex(MutableQuadView quad, int index) {
			quad.pos(index, 
					quad.y(index), 
					1 - quad.z(index), 
					1 - quad.x(index));
			quad.normal(index, 
					quad.normalY(index), 
					-quad.normalZ(index), 
					-quad.normalX(index));
		}
		*/
	},
	X_180(new Direction[] {
			Direction.UP, Direction.DOWN,
			Direction.SOUTH, Direction.NORTH,
			Direction.WEST, Direction.EAST,
	}, new int[] {
			0, 0,
			2, 2,
			2, 2
	}) {
		//x = x
		//y = -y
		//z = -z
		@Override
		public void applyBlockSpace(float[] vertex) {
			vertex[1] = 1 - vertex[1];
			vertex[2] = 1 - vertex[2];
		}

		@Override
		public void rotatePos(int[] oldData, int oldPos, int[] newData, int newPos) {
			//newData[newPos + 0] = oldData[oldPos + 0];
			newData[newPos + 1] = Float.floatToRawIntBits(1 - Float.intBitsToFloat(oldData[oldPos + 1]));
			newData[newPos + 2] = Float.floatToRawIntBits(1 - Float.intBitsToFloat(oldData[oldPos + 2]));
		}

		@Override
		public VoxelShape createRotatedBox(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
			return Shapes.create(
	        		minX, 
	        		1 - maxY, 
	        		1 - maxZ, 
	        		maxX, 
	        		1 - minY, 
	        		1 - minZ);
		}

		/* Too much information about the quad is hidden, making re-ordering vertices (to fix an annoying AO bug) impossible, so we will have to use vanilla model code
		@Override
		public void transformVertex(MutableQuadView quad, int index) {
			quad.pos(index, 
					quad.x(index), 
					1 - quad.y(index), 
					1 - quad.z(index));
			quad.normal(index, 
					quad.normalX(index), 
					-quad.normalY(index), 
					-quad.normalZ(index));
		}
		*/
	};
	
	private final Map<VoxelShape, VoxelShape> shapeCache = new WeakHashMap<>(); //we cache these values, to avoid overhead, but weakly in case of dynamically computed shapes
	private final Direction[] applyDirection, unapplyDirection;
	private final int[] vertexShiftLeft;
	
	BlockRotation(Direction[] appliedDirection, int[] vertexShiftLeft) {
		applyDirection = appliedDirection;
		unapplyDirection = new Direction[6];
		for (int i = 0; i < 6; ++i) unapplyDirection[applyDirection[i].get3DDataValue()] = Direction.from3DDataValue(i);
		this.vertexShiftLeft = vertexShiftLeft;
	}
	
	public Direction apply(Direction original) {
		return original == null ? null : applyDirection[original.get3DDataValue()];
	}
	
	public Direction unapply(Direction original) {
		return original == null ? null : unapplyDirection[original.get3DDataValue()];
	}
	
	public int getVertexShiftLeft(Direction original) {
		return original == null ? 0 : vertexShiftLeft[original.get3DDataValue()];
	}
	
	public int[] applyVertices(Direction original, int[] oldData, int vertexSize) {
		return BlockModelUtils.copyVertices(oldData, vertexSize, getVertexShiftLeft(original));
	}
	
	public int[] applyVertices(Direction original, int[] oldData, int vertexSize, int posOffset, int uvOffset, boolean rotateUV, TextureAtlasSprite tex) {
		int shiftLeft = getVertexShiftLeft(original);
		rotateUV &= shiftLeft != 0;
		int[] newData = BlockModelUtils.copyVertices(oldData, vertexSize, shiftLeft);
		int oldPos = shiftLeft * vertexSize;
		for (int newPos = 0; newPos < oldData.length; newPos += vertexSize) {
			rotatePos(oldData, oldPos + posOffset, newData, newPos + posOffset);
			if (rotateUV) rotateUV(oldData, oldPos + uvOffset, newData, newPos + uvOffset, shiftLeft, tex);
			oldPos += vertexSize;
			if (oldPos >= oldData.length) oldPos = 0;
		}
		return newData;
	}
	
	public abstract void applyBlockSpace(float[] vertex);
	
	public abstract void rotatePos(int[] oldData, int oldPos, int[] newData, int newPos);
	
	public void rotateUV(int[] oldData, int oldPos, int[] newData, int newPos, int rotateUV, TextureAtlasSprite tex) {
		switch (rotateUV) {
		case 1:
			newData[newPos + 0] = Float.floatToRawIntBits(tex.getU(16 - tex.getVOffset(Float.intBitsToFloat(oldData[oldPos + 1])))); //1-V
			newData[newPos + 1] = Float.floatToRawIntBits(tex.getV(     tex.getUOffset(Float.intBitsToFloat(oldData[oldPos + 0])))); //U
			break;
		case 2:
			newData[newPos + 0] = Float.floatToRawIntBits(tex.getU0() + tex.getU1() - Float.intBitsToFloat(oldData[oldPos + 0])); //quick 1-U
			newData[newPos + 1] = Float.floatToRawIntBits(tex.getV0() + tex.getV1() - Float.intBitsToFloat(oldData[oldPos + 1])); //quick 1-V
			break;
		case 3:
			newData[newPos + 0] = Float.floatToRawIntBits(tex.getU(     tex.getVOffset(Float.intBitsToFloat(oldData[oldPos + 1])))); //V
			newData[newPos + 1] = Float.floatToRawIntBits(tex.getV(16 - tex.getUOffset(Float.intBitsToFloat(oldData[oldPos + 0])))); //1-U
			break;
		}
	}
	
	public VoxelShape applyBlockSpace(VoxelShape shape) {
		return shapeCache.computeIfAbsent(shape, nil -> {
	        VoxelShape[] buffer = new VoxelShape[] { Shapes.empty() };
	        shape.forAllBoxes((minX, minY, minZ, maxX, maxY, maxZ) -> buffer[0] = Shapes.or(buffer[0], createRotatedBox(minX, minY, minZ, maxX, maxY, maxZ)));
	        return buffer[0];
		});
	}
	
	protected abstract VoxelShape createRotatedBox(double minX, double minY, double minZ, double maxX, double maxY, double maxZ);

	/* Too much information about the quad is hidden, making re-ordering vertices (to fix an annoying AO bug) impossible, so we will have to use vanilla model code
	public boolean transform(MutableQuadView quad, boolean rotateUV) {
		for (int i = 0; i < 4; ++i) transformVertex(quad, i);
		quad.cullFace(apply(quad.cullFace()));
		quad.nominalFace(apply(quad.nominalFace()));
		if (rotateUV) {
			//texture rotate requires finding the sprite used :( why do you strip this data out fabric, why
			TextureAtlasSprite tex = (quad instanceof IMutableQuadViewExtensions ext) ? ext.sprite() : null;
			if (tex != null) { 
				int shiftLeft = getVertexShiftLeft(quad.nominalFace());
				for (int i = 0; i < 4; ++i) {
					rotateTex(quad, shiftLeft, tex);
				}
			} else {
				//TODO
				AdditionalPlacementsMod.LOGGER.warn("Model has quads with no known assigned sprite, textures cannot be rotated");
			}
		}
		return true;
	}
	
	public abstract void transformVertex(MutableQuadView quad, int index);
	
	public void rotateTex(MutableQuadView quad, int rotateUV, TextureAtlasSprite tex) {
		switch (rotateUV) {
		case 1:
			for (int i = 0; i < 4; ++i) quad.sprite(i, 0, 
					tex.getU(16 - tex.getVOffset(quad.spriteV(i, 0))), //1-V
					tex.getV(     tex.getUOffset(quad.spriteU(i, 0)))  //U
					);
			break;
		case 2:
			for (int i = 0; i < 4; ++i) quad.sprite(i, 0, 
					tex.getU(16 - tex.getUOffset(quad.spriteU(i, 0))), //1-U
					tex.getV(16 - tex.getVOffset(quad.spriteV(i, 0)))  //1-V
					);
			break;
		case 3:
			for (int i = 0; i < 4; ++i) quad.sprite(i, 0, 
					tex.getU(     tex.getVOffset(quad.spriteV(i, 0))), //V
					tex.getV(16 - tex.getUOffset(quad.spriteU(i, 0)))  //1-U
					);
			break;
		}
	}
	*/
}
