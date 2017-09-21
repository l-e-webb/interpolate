package com.louiswebbgmes.interpolate;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;

public class GraphRenderer {

    public static void render(float[] graph, ShapeRenderer renderer, Color color, boolean fill) {
        renderer.setColor(color);
        if (fill) {
            renderFilled(graph, renderer);
        } else {
            renderLines(graph, renderer);
        }
    }

    public static void render(float[] graph1, float[] graph2, ShapeRenderer renderer, Color color, boolean fill) {
        renderer.setColor(color);
        if (fill) {
            renderBetweenGraphs(graph1, graph2, renderer);
        } else {
            renderLines(graph1, renderer);
            renderLines(graph2, renderer);
        }
    }

    protected static void renderLines(float[] graph, ShapeRenderer renderer) {
        renderer.begin(ShapeRenderer.ShapeType.Line);
        for (int i = 0; i < graph.length - 4; i += 2) {
            renderer.line(graph[i], graph[i + 1], graph[i + 2], graph[i + 3]);
        }
        renderer.end();
    }

    protected static void renderFilled(float[] graph, ShapeRenderer renderer) {
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        for (int i = 0; i < graph.length - 4; i+= 2) {
            float x1 = graph[i];
            float y1 = graph[i + 1];
            float x2 = graph[i + 2];
            float y2 = graph[i + 3];
            Vector2 intersection = new Vector2();
            if (Intersector.intersectSegments(
                    x1, y1, x2, y2,
                    x1, 0, x2, 0,
                    intersection
            )) {
                renderer.triangle(
                        x1, y1,
                        x1, 0,
                        intersection.x, 0
                );
                renderer.triangle(
                        intersection.x, 0,
                        x2, 0,
                        x2, y2
                );
            } else {
                renderer.triangle(
                        x1, y1,
                        x2, y2,
                        x1, 0
                );
                renderer.triangle(
                        x2, y2,
                        x1, 0,
                        x2, 0
                );
            }
        }
        renderer.end();
    }

    protected static void renderBetweenGraphs(float[] graph1, float[] graph2, ShapeRenderer renderer) {
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        for (int i = 0; i < graph1.length - 4; i+= 2) {
            renderFourPointRegion(new float[]{
                    graph1[i], graph1[i + 1],
                    graph1[i + 2], graph1[i + 3],
                    graph2[i + 2], graph2[i + 3],
                    graph2[i], graph2[i + 1]
            }, renderer);
        }
        renderer.end();
    }

    protected static void renderFourPointRegion(float[] vertices, ShapeRenderer renderer) {
        Vector2 intersection = new Vector2();
        //If the graphs cross each other, draw two triangles in a horizontal hourglass shape.
        //Otherwise, draw a quadrilateral.
        if (Intersector.intersectSegments(
                vertices[0], vertices[1],
                vertices[2], vertices[3],
                vertices[4], vertices[5],
                vertices[6], vertices[7],
                intersection
        )) {
            renderer.triangle(
                    vertices[0], vertices[1],
                    vertices[6], vertices[7],
                    intersection.x, intersection.y
            );
            renderer.triangle(
                    vertices[2], vertices[3],
                    vertices[4], vertices[5],
                    intersection.x, intersection.y
            );
        } else {
            renderer.triangle(
                    vertices[0], vertices[1],
                    vertices[2], vertices[3],
                    vertices[6], vertices[7]
            );
            renderer.triangle(
                    vertices[2], vertices[3],
                    vertices[4], vertices[5],
                    vertices[6], vertices[7]
            );
//            float xa1 = vertices[0];
//            float ya1 = vertices[1];
//            float xa2 = vertices[2];
//            float ya2 = vertices[3];
//            float xa3;
//            float ya3;
//            float xb1 = vertices[4];
//            float yb1 = vertices[5];
//            float xb2 = vertices[6];
//            float yb2 = vertices[7];
//            float xb3;
//            float yb3;
//            if (ya1 > yb1) {
//                if (ya1 > ya2) {
//                    xa3 = xa1;
//                    ya3 = ya2;
//                } else {
//                    xa3 = xa2;
//                    ya3 = ya1;
//                }
//                if (yb1 > yb2) {
//                    xb3 = xb2;
//                    yb3 = yb1;
//                } else {
//                    xb3 = xb1;
//                    yb3 = yb2;
//                }
//            } else {
//                if (ya1 > ya2) {
//                    xa3 = xa2;
//                    ya3 = ya1;
//                } else {
//                    xa3 = xa1;
//                    ya3 = ya2;
//                }
//                if (yb1 > yb2) {
//                    xb3 = xb1;
//                    yb3 = yb2;
//                } else {
//                    xb3 = xb2;
//                    yb3 = yb1;
//                }
//            }
//            renderer.triangle(xa1, ya1, xa2, ya2, xa3, ya3);
//            renderer.triangle(xb1, yb1, xb2, yb2, xb3, yb3);
//            if (ya3 < yb3) {
//                renderer.rect(xa1, ya3, xa2 - xa1, yb3 - ya3);
//            } else {
//                renderer.rect(xa1, yb3, xa2 - xa1, ya3 - yb3);
//            }
        }
    }

}
