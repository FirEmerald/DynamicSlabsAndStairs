package com.firemerald.additionalplacements.client;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix3f;
import com.mojang.math.Matrix4f;

public class BlockHighlightHelper {
	public static void line(VertexConsumer vertexConsumer, Matrix4f poseMat, Matrix3f normMat, float z, float r, float g, float b, float a, float x1, float y1, float x2, float y2) {
		vertexConsumer.vertex(poseMat, x1, y1, z).color(r, g, b, a).normal(normMat, 0, 0, 1).endVertex();
		vertexConsumer.vertex(poseMat, x2, y2, z).color(r, g, b, a).normal(normMat, 0, 0, 1).endVertex();
	}

	public static void lineAxis(VertexConsumer vertexConsumer, Matrix4f poseMat, Matrix3f normMat, float z, float r, float g, float b, float a, float offset1, float offset2) {
		line(vertexConsumer, poseMat, normMat, z, r, g, b, a,  offset1,  0      ,  offset2,  0      );
		line(vertexConsumer, poseMat, normMat, z, r, g, b, a,  0      ,  offset1,  0      ,  offset2);
		line(vertexConsumer, poseMat, normMat, z, r, g, b, a, -offset1,  0      , -offset2,  0      );
		line(vertexConsumer, poseMat, normMat, z, r, g, b, a,  0      , -offset1,  0      , -offset2);
	}

	public static void lineAxis(VertexConsumer vertexConsumer, Matrix4f poseMat, Matrix3f normMat, float z, float r, float g, float b, float a, float offset) {
		lineAxis(vertexConsumer, poseMat, normMat, z, r, g, b, a, 0, offset);
	}

	public static void lineAxisDiagonal(VertexConsumer vertexConsumer, Matrix4f poseMat, Matrix3f normMat, float z, float r, float g, float b, float a, float offset1, float offset2) {
		line(vertexConsumer, poseMat, normMat, z, r, g, b, a,  offset1,  offset1,  offset2,  offset2);
		line(vertexConsumer, poseMat, normMat, z, r, g, b, a, -offset1,  offset1, -offset2,  offset2);
		line(vertexConsumer, poseMat, normMat, z, r, g, b, a, -offset1, -offset1, -offset2, -offset2);
		line(vertexConsumer, poseMat, normMat, z, r, g, b, a,  offset1, -offset1,  offset2, -offset2);
	}

	public static void lineAxisDiagonal(VertexConsumer vertexConsumer, Matrix4f poseMat, Matrix3f normMat, float z, float r, float g, float b, float a, float offset) {
		lineAxisDiagonal(vertexConsumer, poseMat, normMat, z, r, g, b, a, 0, offset);
	}

	public static void lineOctal(VertexConsumer vertexConsumer, Matrix4f poseMat, Matrix3f normMat, float z, float r, float g, float b, float a, float offset1A, float offset1B, float offset2A, float offset2B) {
		line(vertexConsumer, poseMat, normMat, z, r, g, b, a,
				 offset1A,  offset1B,  offset2A,  offset2B);
		line(vertexConsumer, poseMat, normMat, z, r, g, b, a,
				 offset1B,  offset1A,  offset2B,  offset2A);
		line(vertexConsumer, poseMat, normMat, z, r, g, b, a,
				-offset1A,  offset1B, -offset2A,  offset2B);
		line(vertexConsumer, poseMat, normMat, z, r, g, b, a,
				-offset1B,  offset1A, -offset2B,  offset2A);
		line(vertexConsumer, poseMat, normMat, z, r, g, b, a,
				-offset1A, -offset1B, -offset2A, -offset2B);
		line(vertexConsumer, poseMat, normMat, z, r, g, b, a,
				-offset1B, -offset1A, -offset2B, -offset2A);
		line(vertexConsumer, poseMat, normMat, z, r, g, b, a,
				 offset1A, -offset1B,  offset2A, -offset2B);
		line(vertexConsumer, poseMat, normMat, z, r, g, b, a,
				 offset1B, -offset1A,  offset2B, -offset2A);
	}

	public static void lineCenteredGrid(VertexConsumer vertexConsumer, Matrix4f poseMat, Matrix3f normMat, float z, float r, float g, float b, float a, float offset1, float offset2) {
		line(vertexConsumer, poseMat, normMat, z, r, g, b, a,
				-offset1, -offset2,
				-offset1,  offset2);
		line(vertexConsumer, poseMat, normMat, z, r, g, b, a,
				-offset2, -offset1,
				 offset2, -offset1);
		line(vertexConsumer, poseMat, normMat, z, r, g, b, a,
				 offset1, -offset2,
				 offset1,  offset2);
		line(vertexConsumer, poseMat, normMat, z, r, g, b, a,
				-offset2,  offset1,
				 offset2,  offset1);
	}

	public static void lineCenteredSquare(VertexConsumer vertexConsumer, Matrix4f poseMat, Matrix3f normMat, float z, float r, float g, float b, float a, float offset) {
		lineRectangle(vertexConsumer, poseMat, normMat, z, r, g, b, a, -offset, -offset, offset, offset);
	}

	public static void lineRectangle(VertexConsumer vertexConsumer, Matrix4f poseMat, Matrix3f normMat, float z, float r, float g, float b, float a, float x1, float y1, float x2, float y2) {
		line(vertexConsumer, poseMat, normMat, z, r, g, b, a,
				x1, y1,
				x2, y1);
		line(vertexConsumer, poseMat, normMat, z, r, g, b, a,
				x2, y1,
				x2, y2);
		line(vertexConsumer, poseMat, normMat, z, r, g, b, a,
				x2, y2,
				x1, y2);
		line(vertexConsumer, poseMat, normMat, z, r, g, b, a,
				x1, y2,
				x1, y1);
	}

	public static void lineCenteredPlus(VertexConsumer vertexConsumer, Matrix4f poseMat, Matrix3f normMat, float z, float r, float g, float b, float a, float offset) {
		linePlus(vertexConsumer, poseMat, normMat, z, r, g, b, a,
				-offset, -offset,
				 offset,  offset);
	}

	public static void linePlus(VertexConsumer vertexConsumer, Matrix4f poseMat, Matrix3f normMat, float z, float r, float g, float b, float a, float x1, float y1, float x2, float y2) {
		float xC = (x1 + x2) / 2;
		float yC = (y1 + y2) / 2;
		line(vertexConsumer, poseMat, normMat, z, r, g, b, a,
				x1, yC,
				x2, yC);
		line(vertexConsumer, poseMat, normMat, z, r, g, b, a,
				xC, y1,
				xC, y2);
	}

	public static void lineCenteredCross(VertexConsumer vertexConsumer, Matrix4f poseMat, Matrix3f normMat, float z, float r, float g, float b, float a, float offset) {
		lineCross(vertexConsumer, poseMat, normMat, z, r, g, b, a,
				-offset, -offset,
				 offset,  offset);
	}

	public static void lineCross(VertexConsumer vertexConsumer, Matrix4f poseMat, Matrix3f normMat, float z, float r, float g, float b, float a, float x1, float y1, float x2, float y2) {
		line(vertexConsumer, poseMat, normMat, z, r, g, b, a,
				x1, y1,
				x2, y2);
		line(vertexConsumer, poseMat, normMat, z, r, g, b, a,
				x2, y1,
				x1, y2);
	}

	public static void lineList(VertexConsumer vertexConsumer, Matrix4f poseMat, Matrix3f normMat, float z, float r, float g, float b, float a, float... points) {
		for (int i = 0; i < points.length; i += 4) {
			line(vertexConsumer, poseMat, normMat, z, r, g, b, a,
					points[i    ], points[i + 1],
					points[i + 2], points[i + 3]);
		}
	}

	public static void lineStrip(VertexConsumer vertexConsumer, Matrix4f poseMat, Matrix3f normMat, float z, float r, float g, float b, float a, float... points) {
		for (int i = 2; i < points.length; i += 2) {
			line(vertexConsumer, poseMat, normMat, z, r, g, b, a,
					points[i - 2], points[i - 1],
					points[i    ], points[i + 1]);
		}
	}

	public static void lineLoop(VertexConsumer vertexConsumer, Matrix4f poseMat, Matrix3f normMat, float z, float r, float g, float b, float a, float... points) {
		int i1 = 0, i2 = 0;
		do {
			if ((i2 += 2) >= points.length) i2 = 0;
			line(vertexConsumer, poseMat, normMat, z, r, g, b, a,
					points[i1], points[i1 + 1],
					points[i2], points[i2 + 1]);
			i1 = i2;
		} while (i2 > 0);
	}
}
