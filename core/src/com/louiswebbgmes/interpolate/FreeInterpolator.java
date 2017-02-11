package com.louiswebbgmes.interpolate;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class FreeInterpolator extends EvolvingInterpolation {

    float maxYAmp;
    GenerationType genType;
    int numPoints;

    public FreeInterpolator(int points, float duration, float maxYAmp, GenerationType genType) {
        super(duration);
        this.maxYAmp = maxYAmp;
        this.genType = genType;
        this.numPoints = points;
        setInterpolationPoints(getRandomPointSet());
        setTargetPoints(getRandomPointSet());
    }

    @Override
    public void onFinish() {
        super.onFinish();
        setTargetPoints(getRandomPointSet(interpolationPoints.length, maxYAmp, genType));
    }

    protected Vector2[] getRandomPointSet() {
        return FreeInterpolator.getRandomPointSet(numPoints, maxYAmp, genType);
    }

    protected static Vector2[] getRandomPointSet(int numPoints, float maxYAmp, GenerationType genType) {
        Vector2[] points = new Vector2[numPoints];
        switch (genType) {
            case RANDOM: default:
                for (int i = 0; i < numPoints; i++) {
                    points[i] = new Vector2(MathUtils.random(0f, 1f), MathUtils.random(-maxYAmp, maxYAmp));
                }
                break;
            case STRIPES:
                for (int i = 0; i < numPoints; i++) {
                    points[i] = new Vector2(MathUtils.random(i * 1f / numPoints, (i + 1) * 1f / numPoints), MathUtils.random(-maxYAmp, maxYAmp));
                }
        }
        return points;
    }

    public enum GenerationType {
        RANDOM,
        STRIPES
    }
}
