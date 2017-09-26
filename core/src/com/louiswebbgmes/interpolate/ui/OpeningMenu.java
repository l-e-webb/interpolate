package com.louiswebbgmes.interpolate.ui;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.louiswebbgmes.interpolate.InterpolateGame;
import com.louiswebbgmes.interpolate.interpolation.InterpolationSettings;

public class OpeningMenu extends Stage {

    InterpolateGame game;

    Table mainPane;
    Label titleLabel;
    MainSettingsBank mainSettingsBank;
    PolynomialSettingsBank polynomialSettingsBank1;
    PolynomialSettingsBank polynomialSettingsBank2;

    Widget backButton;

    public OpeningMenu(InterpolateGame game, Viewport viewport) {
        super(viewport);
        this.game = game;
        init();
    }

    void init() {
        initMenu();
        backButton = new Image(UiConstants.backButton);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                onBackButtonPressed();
            }
        });
        addActor(backButton);
        hideBackButton();
        resize();
        //Uncomment to see debug lines.
        //setDebugAll(true);
    }

    public void resize() {
        Viewport viewport = getViewport();
        float width = MathUtils.clamp(
                viewport.getWorldWidth() * UiConstants.MENU_WIDTH_RATIO,
                UiConstants.MENU_MIN_WIDTH,
                UiConstants.MENU_MAX_WIDTH
        );
        mainPane.setSize(width, mainPane.getPrefHeight());
        mainPane.setPosition(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, Align.center);
        backButton.setPosition(0, 0);
    }

    void initMenu() {
        Skin skin = UiConstants.skin;
        mainPane = new Table();
        mainPane.pad(UiConstants.PADDING);
        mainPane.clear();

        titleLabel = new Label(UiText.MENU_TITLE, skin, UiConstants.TITLE_LABEL);
        titleLabel.setAlignment(Align.center);
        mainPane.add(titleLabel).center();
        mainPane.row().padTop(UiConstants.PADDING);

        mainSettingsBank = new MainSettingsBank(this, skin);
        mainPane.add(mainSettingsBank).growX();
        mainPane.row().padTop(UiConstants.PADDING);

        polynomialSettingsBank1 = new PolynomialSettingsBank(UiText.FIRST_POLYNOMIAL_SETTINGS , UiConstants.settingsBankStyle);
        mainPane.add(polynomialSettingsBank1).growX();
        mainPane.row().padTop(UiConstants.PADDING);

        polynomialSettingsBank2 = new PolynomialSettingsBank(UiText.SECOND_POLYNOMIAL_SETTINGS , UiConstants.settingsBankStyle);
        mainPane.add(polynomialSettingsBank2).growX();
        mainPane.row().padTop(UiConstants.PADDING);

        Button beginButton = new TextButton(UiText.BEGIN, skin);
        beginButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                onBeginButtonPressed();
            }
        });
        mainPane.add(beginButton).center().minWidth(UiConstants.MIN_BUTTON_WIDTH);
        mainPane.row();

        addActor(mainPane);

        update();
    }

    void onBeginButtonPressed() {
        InterpolationSettings settings = new InterpolationSettings();
        settings.single = mainSettingsBank.single();
        settings.filled = mainSettingsBank.filled();
        settings.free = mainSettingsBank.free();
        settings.showPoints = mainSettingsBank.showPoints();
        settings.showAxes = mainSettingsBank.showAxes();
        settings.numPoints1 = polynomialSettingsBank1.getNumPoints();
        settings.speedSetting1 = polynomialSettingsBank1.getSpeedSetting();
        settings.motionSetting1 = polynomialSettingsBank1.getMotionSetting();
        settings.numPoints2 = polynomialSettingsBank2.getNumPoints();
        settings.speedSetting2 = polynomialSettingsBank2.getSpeedSetting();
        settings.motionSetting2 = polynomialSettingsBank2.getMotionSetting();
        //TODO: get both colors from UI.
        settings.color1 = com.badlogic.gdx.graphics.Color.WHITE;
        settings.color2 = com.badlogic.gdx.graphics.Color.CYAN;
        game.initInterpolation(settings);
    }

    void onBackButtonPressed() {
        game.returnToMenu();
    }

    public void hideMenu() {
        mainPane.setVisible(false);
    }

    public void showMenu() {
        mainPane.setVisible(true);
    }

    public void hideBackButton() {
        backButton.setVisible(false);
    }

    public void showBackButton() {
        backButton.setVisible(true);
    }

    void update() {
        boolean free = mainSettingsBank.free();
        boolean single = mainSettingsBank.single();
        polynomialSettingsBank1.setDisabled(false);
        polynomialSettingsBank2.setDisabled(single);
        polynomialSettingsBank1.setFreeEnabled(free);
        polynomialSettingsBank2.setFreeEnabled(free);
    }

}
