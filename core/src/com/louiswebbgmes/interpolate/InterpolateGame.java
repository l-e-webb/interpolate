package com.louiswebbgmes.interpolate;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class InterpolateGame extends ApplicationAdapter {

    boolean showAxes = false;

    Viewport viewport;
    ShapeRenderer renderer;
    Stage stage;
    EvolvingInterpolation interpolation;
    EvolvingInterpolation interpolation2;

	@Override
	public void create () {
        Interpolation.setSegments(500);
        viewport = new ExtendViewport(1, 0.5f, 1, 0);
        updateCamera();
        renderer = new ShapeRenderer();
        interpolation = new FreeInterpolator(4, 15, 0.1f, FreeInterpolator.GenerationType.RANDOM);
        interpolation2 = new FreeInterpolator(7, 6, 0.25f, FreeInterpolator.GenerationType.STRIPES);
	}

	@Override
	public void render () {
        if ((Gdx.input.isKeyPressed(Input.Keys.ALT_LEFT) || Gdx.input.isKeyPressed(Input.Keys.ALT_RIGHT)) &&
                Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            if (Gdx.graphics.isFullscreen()) {
                Gdx.graphics.setWindowedMode(600, 600);
            } else {
                Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
            }
        }

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float delta = Gdx.graphics.getDeltaTime();
        interpolation.update(delta);
        interpolation2.update(delta);

        renderer.setProjectionMatrix(viewport.getCamera().combined);

        if (showAxes) {
            renderer.begin(ShapeRenderer.ShapeType.Line);
            renderer.setColor(Color.RED);
            renderer.line(0, 0, 1, 0);
            renderer.line(0.005f, -0.5f, 0.005f, 0.5f);
            renderer.end();
        }

        GraphRenderer.render(interpolation.getGraph(), interpolation2.getGraph(), renderer, Color.WHITE, true);
	}
	
	@Override
	public void dispose () {
        renderer.dispose();
	}

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        updateCamera();
    }

    public void updateCamera() {
        viewport.getCamera().position.x = 0.5f;
        viewport.getCamera().position.y = 0;
    }
}
