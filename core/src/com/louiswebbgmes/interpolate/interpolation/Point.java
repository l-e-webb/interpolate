package com.louiswebbgmes.interpolate.interpolation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.Align;

public class Point extends Group {

    static final int SEGMENTS = 12;
    static final float POINT_RADIUS = 0.01f;
    static final float WIDTH_ADJUSTMENT = 1.5f;
    static final float TAP_SQUARE_SIZE = POINT_RADIUS;

    Vector2 position;

    Point(Vector2 position, boolean grabbable) {
        super();
        setPosition(position.x, position.y);
        setSize(POINT_RADIUS * 2 * WIDTH_ADJUSTMENT, POINT_RADIUS * 2 * WIDTH_ADJUSTMENT);
        this.position = position;
        if (grabbable) {
            DragListener dragListener = new DragListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return super.touchDown(event, x, y, pointer, button);
                }

                @Override
                public void drag(InputEvent event, float x, float y, int pointer) {
                    super.drag(event, x, y, pointer);
                    Vector2 newPos = localToStageCoordinates(new Vector2(x, y));
                    Point.this.position.set(newPos);
                }
            };
            dragListener.setTapSquareSize(TAP_SQUARE_SIZE);
            ClickListener doubleClickListener = new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (getTapCount() >= 2) {
                        //TODO: set location via text.
                        Gdx.app.log("", "Point double-clicked.");
                    }
                }
            };
            doubleClickListener.setTapSquareSize(TAP_SQUARE_SIZE);
            addListener(doubleClickListener);
            addListener(dragListener);
        }

        //Uncomment to see debug boxes around points.
        //setDebug(true);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setPosition(position.x, position.y, Align.center);
    }

    public void render(ShapeRenderer renderer) {
        renderer.circle(position.x, position.y, POINT_RADIUS, SEGMENTS);
    }
}
