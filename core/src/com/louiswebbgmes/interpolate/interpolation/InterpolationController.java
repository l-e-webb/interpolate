package com.louiswebbgmes.interpolate.interpolation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.SnapshotArray;
import com.badlogic.gdx.utils.viewport.Viewport;

public class InterpolationController {

    Interpolation interpolation1;
    Interpolation interpolation2;

    boolean single;
    boolean filled;
    boolean free;
    boolean showPoints;
    boolean showAxes;

    Color color1;
    Color color2;

    private Stage stage;
    private Group interpolation1Group;
    private Group interpolation2Group;

    public InterpolationController(Viewport viewport, InputMultiplexer input, InterpolationSettings settings) {
        stage = new Stage(viewport);
        interpolation1Group = new Group();
        interpolation2Group = new Group();
        stage.addActor(interpolation1Group);
        stage.addActor(interpolation2Group);
        input.addProcessor(stage);
        applySettings(settings);
    }

    public void update(float delta) {
        if (free) {
            if (interpolation1 != null) ((EvolvingInterpolation)interpolation1).update(delta);
            if (interpolation2 != null) ((EvolvingInterpolation)interpolation2).update(delta);
        }
        stage.act(delta);
        if (interpolation1 != null) interpolation1.graph();
        if (interpolation2 != null) interpolation2.graph();
    }

    public void render(ShapeRenderer renderer) {
        if (!filled) {
            if (interpolation1 != null) {
                interpolation1.render(renderer, color1, false);
            }
            if (!single && interpolation2 != null) {
                interpolation2.render(renderer, color2, false);
            }
        } else {
            if (single && interpolation1 != null) {
                interpolation1.render(renderer, color1, true);
            } else if (!single
                    && interpolation1 != null
                    && interpolation2 != null) {
                GraphRenderer.render(
                        interpolation1.graph,
                        interpolation2.graph,
                        renderer,
                        color1
                );
            }
        }

        if (showAxes) {
            renderer.begin(ShapeRenderer.ShapeType.Line);
            renderer.setColor(Color.RED);
            renderer.line(0, 0, 1, 0);
            renderer.line(0.005f, -0.5f, 0.005f, 0.5f);
            renderer.end();
        }

        if (showPoints) {
            renderer.begin(ShapeRenderer.ShapeType.Filled);
            renderer.setColor(color1);
            SnapshotArray<Actor> points = interpolation1Group.getChildren();
            for (Actor actor : points.begin()) {
                if (actor == null) continue;
                Point point = (Point) actor;
                point.render(renderer);
            }
            points.end();
            if (!single) {
                renderer.setColor(color2);
                points = interpolation2Group.getChildren();
                for (Actor actor : points.begin()) {
                    if (actor == null) continue;
                    Point point = (Point) actor;
                    point.render(renderer);
                }
                points.end();
            }
            renderer.end();

            stage.draw();
        }
    }

    public void setInterpolation1(Interpolation interpolation) {
        interpolation1 = interpolation;
        interpolation1Group.clear();
        for (Vector2 point : interpolation1.interpolationPoints) {
            interpolation1Group.addActor(new Point(point, !free));
        }
    }

    public void setInterpolation2(Interpolation interpolation) {
        interpolation2 = interpolation;
        interpolation2Group.clear();
        for (Vector2 point : interpolation2.interpolationPoints) {
            interpolation2Group.addActor(new Point(point, !free));
        }
    }

    public void resetInterpolations(InterpolationSettings settings) {
        if (settings.free) {
            setInterpolation1(new FreeInterpolation(
                    settings.numPoints1,
                    Constants.SPEED_SETTINGS[settings.speedSetting1],
                    Constants.INTERP_POINT_MAX_Y,
                    Constants.GENERATION_TYPES[settings.motionSetting1]
            ));
            if (!settings.single) {
                setInterpolation2(new FreeInterpolation(
                        settings.numPoints2,
                        Constants.SPEED_SETTINGS[settings.speedSetting2],
                        Constants.INTERP_POINT_MAX_Y,
                        Constants.GENERATION_TYPES[settings.motionSetting2]
                ));
            }
        } else {
            setInterpolation1(new Interpolation(settings.numPoints1));
            if (!settings.single) {
                setInterpolation2(new Interpolation(settings.numPoints2));
            }
        }
    }

    public void applySettings(InterpolationSettings settings, boolean resetInterpolations) {
        filled = settings.filled;
        single = settings.single;
        //If going from static to free, we have to reset the interpolations.
        if (!free && settings.free) {
            resetInterpolations = true;
        }
        free = settings.free;
        showPoints = settings.showPoints;
        showAxes = settings.showAxes;
        color1 = settings.color1;
        color2 = settings.color2;
        if (resetInterpolations) {
            resetInterpolations(settings);
        }
    }

    public void applySettings(InterpolationSettings settings) {
        applySettings(settings, true);
    }

}
