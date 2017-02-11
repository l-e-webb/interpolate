package com.louiswebbgmes.interpolate;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Interpolation {

    static int segments;

    Vector2[] interpolationPoints;
    float[] graph;

    public Interpolation() {}

    public Interpolation(Vector2[] points) {
        setInterpolationPoints(points);
        graph();
    }

    public float evaluate(float x) {
        float y = 0;
        for (int i = 0; i < interpolationPoints.length; i++) {
            float value = interpolationPoints[i].y;
            for (int j = 0; j < interpolationPoints.length; j++) {
                if (i == j) continue;
                value *= (x - interpolationPoints[j].x) / (interpolationPoints[i].x - interpolationPoints[j].x);
            }
            y += value;
        }
        return y;
    }

    public void setInterpolationPoints(Vector2... points) {
        this.interpolationPoints = points;
        graph();
    }

    public float[] getGraph() {
        return graph;
    }

    public void graph() {
        graph = new float[segments * 2 + 2];
        float segmentLength = 1f / segments;
        for (int i = 0; i <= segments; i++) {
            float x = segmentLength * i;
            float y = evaluate(x);
            graph[i * 2] = x;
            graph[i * 2 + 1] = y;
        }
    }

    public void render(ShapeRenderer shapeRenderer, Color color, boolean filled) {
        GraphRenderer.render(graph, shapeRenderer, color, filled);
    }

    public static void setSegments(int segments) {
        Interpolation.segments = segments;
    }

}
