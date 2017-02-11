package com.louiswebbgmes.interpolate;

import com.badlogic.gdx.math.Vector2;

public class EvolvingInterpolation extends Interpolation {

    Vector2[] priorPointSet;
    Vector2[] nextPointSet;
    Vector2[] interpolationLines;
    float duration;
    float timer;
    boolean evolving;

    public EvolvingInterpolation(float duration) {
        super();
        this.duration = duration;
        evolving = false;
        timer = 0;
    }

    public EvolvingInterpolation(Vector2[] points, float duration) {
        super(points);
        this.duration = duration;
        evolving = false;
        timer = 0;
    }

    public void update(float delta){
        if (evolving) {
            timer += delta;
            if (timer >= duration) {
                setInterpolationPoints(nextPointSet);
                onFinish();
                return;
            }
            for (int i = 0; i < interpolationPoints.length; i++) {
                interpolationPoints[i].set(
                        new Vector2(priorPointSet[i]).add(new Vector2(interpolationLines[i]).scl(timer / duration))
                );
            }
            graph();
        }
    }

    public void onFinish() {
        evolving = false;
        timer = 0;
    }

    public void setTargetPoints(Vector2... points) {
        nextPointSet = points;
        interpolationLines = new Vector2[points.length];
        for (int i = 0; i < points.length; i++) {
            interpolationLines[i] = new Vector2(nextPointSet[i]).sub(interpolationPoints[i]);
        }
        evolving = true;
    }

    protected void setPriorPointSet(Vector2[] points) {
        priorPointSet = new Vector2[points.length];
        for (int i = 0; i < points.length; i++) {
            priorPointSet[i] = new Vector2(points[i]);
        }
    }

    @Override
    public void setInterpolationPoints(Vector2... points) {
        super.setInterpolationPoints(points);
        setPriorPointSet(points);
    }
}
