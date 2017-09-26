package com.louiswebbgmes.interpolate.interpolation;

public class Constants {

    static final float INTERP_POINT_MAX_Y = 0.4f;

    static final float[] SPEED_SETTINGS = new float[]{
            10f,
            6.5f,
            4f,
            2f,
            0.75f
    };

    static final FreeInterpolation.GenerationType[] GENERATION_TYPES = new FreeInterpolation.GenerationType[] {
            FreeInterpolation.GenerationType.STRIPES,
            FreeInterpolation.GenerationType.RANDOM
    };

}