package com.louiswebbgmes.interpolate;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.louiswebbgmes.interpolate.interpolation.Interpolation;
import com.louiswebbgmes.interpolate.interpolation.InterpolationController;
import com.louiswebbgmes.interpolate.interpolation.InterpolationSettings;
import com.louiswebbgmes.interpolate.ui.OpeningMenu;
import com.louiswebbgmes.interpolate.ui.UiConstants;

public class InterpolateGame extends ApplicationAdapter {

    boolean interpolationMode;

    Viewport interpolationViewport;
    Viewport uiViewport;
    ShapeRenderer renderer;
    InterpolationController controller;
    OpeningMenu openingMenu;
    InputMultiplexer input;

	@Override
	public void create () {
        UiConstants.initUi();
        Interpolation.setSegments(500);

        uiViewport = new ScreenViewport();
        openingMenu = new OpeningMenu(this, uiViewport);
        renderer = new ShapeRenderer();

        input = new InputMultiplexer();
        input.addProcessor(openingMenu);
        Gdx.input.setInputProcessor(input);
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

        if (interpolationMode) {
            float delta = Gdx.graphics.getDeltaTime();
            controller.update(delta);

            updateCamera();
            renderer.setProjectionMatrix(interpolationViewport.getCamera().combined);
            controller.render(renderer);
        }

        openingMenu.act();
        openingMenu.draw();
	}
	
	@Override
	public void dispose () {
        renderer.dispose();
	}

    @Override
    public void resize(int width, int height) {
        if (interpolationViewport != null) {
            interpolationViewport.update(width, height);
            updateCamera();
        }
        uiViewport.update(width, height, true);
        openingMenu.resize();
    }

    void updateCamera() {
        interpolationViewport.getCamera().position.x = 0.5f;
        interpolationViewport.getCamera().position.y = 0;
    }

    public void initInterpolation(InterpolationSettings settings) {
        interpolationViewport = new ExtendViewport(1, 0.5f, 1, 0);
        controller = new InterpolationController(interpolationViewport, input, settings);
        //Voodoo:
        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        interpolationMode = true;
        openingMenu.hideMenu();
        openingMenu.showBackButton();
        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public void returnToMenu() {
        interpolationMode = false;
        openingMenu.hideBackButton();
        openingMenu.showMenu();
        input.clear();
        input.addProcessor(openingMenu);
        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }
}
