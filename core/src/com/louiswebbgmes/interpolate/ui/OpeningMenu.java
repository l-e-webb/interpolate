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
import com.louiswebbgmes.interpolate.util.Executable;

public class OpeningMenu extends Stage {

    InterpolateGame game;

    ScrollPane mainPane;
    Label titleLabel;
    NumberSelector numPolynomialsSelector;
    NumberSelector numPointsSelector1;
    NumberSelector numPointsSelector2;
    SelectBox<String> drawingStyleSelect;
    SelectBox<String> interpolateModeSelect;
    CheckBox showAxesBox;
    CheckBox showPointsBox;

    Widget backButton;

    public OpeningMenu(InterpolateGame game, Viewport viewport) {
        super(viewport);
        this.game = game;
        init();
    }

    void init() {
        initMenu();
        backButton = new Image(UiConstants.leftArrow);
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
        float height = MathUtils.clamp(
                viewport.getWorldHeight() * UiConstants.MENU_HEIGHT_RATIO,
                UiConstants.MENU_MIN_HEIGHT,
                UiConstants.MENU_MAX_HEIGHT
        );
        mainPane.setSize(width, height);
        mainPane.setPosition(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, Align.center);
        backButton.setPosition(0, 0);
    }

    void initMenu() {
        Skin skin = UiConstants.skin;
        Table table = new Table();
        mainPane = new ScrollPane(table, skin);
        table.setFillParent(true);
        table.pad(UiConstants.PADDING);
        table.clear();

        titleLabel = new Label(UiText.MENU_TITLE, skin, UiConstants.TITLE_LABEL);
        titleLabel.setAlignment(Align.center);
        table.add(titleLabel).colspan(2).center();
        table.row().padTop(UiConstants.PADDING);

        table.add(new Label(UiText.NUM_INTERPOLATIONS, skin));
        numPolynomialsSelector = new NumberSelector(1, 2, UiConstants.numSelectorStyle);
        numPolynomialsSelector.space(UiConstants.PADDING);
        table.add(numPolynomialsSelector);
        table.row().padTop(UiConstants.PADDING);

        table.add(new Label(UiText.NUM_POINTS1, skin));
        numPointsSelector1 = new NumberSelector(UiConstants.MIN_POINTS, UiConstants.MAX_POINTS, UiConstants.numSelectorStyle);
        numPointsSelector1.space(UiConstants.PADDING);
        table.add(numPointsSelector1);
        table.row().padTop(UiConstants.PADDING);

        table.add(new Label(UiText.NUM_POINTS2, skin));
        numPointsSelector2 = new NumberSelector(UiConstants.MIN_POINTS, UiConstants.MAX_POINTS, UiConstants.numSelectorStyle);
        numPointsSelector2.space(UiConstants.PADDING);
        table.add(numPointsSelector2);
        table.row().padTop(UiConstants.PADDING);

        table.add(new Label(UiText.DRAW_STYLE, skin));
        drawingStyleSelect = new SelectBox<String>(skin);
        drawingStyleSelect.setItems(UiText.DRAW_STYLE_OPTIONS);
        table.add(drawingStyleSelect).minWidth(UiConstants.MIN_BUTTON_WIDTH);
        table.row().padTop(UiConstants.PADDING);

        table.add(new Label(UiText.INTERPOLATION_MODE, skin));
        interpolateModeSelect = new SelectBox<String>(skin);
        interpolateModeSelect.setItems(UiText.MODE_OPTIONS);
        interpolateModeSelect.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //If the interpolation is not free, we always show points.
                if (interpolateModeSelect.getSelectedIndex() != UiText.FREE_INDEX) {
                    showPointsBox.setChecked(true);
                    showPointsBox.setDisabled(true);
                } else {
                    showPointsBox.setDisabled(false);
                }
            }
        });
        table.add(interpolateModeSelect).minWidth(UiConstants.MIN_BUTTON_WIDTH);
        table.row().padTop(UiConstants.PADDING);

        showAxesBox = new CheckBox(UiText.SHOW_AXES, skin);
        showAxesBox.getImageCell().padRight(UiConstants.PADDING);
        showAxesBox.setChecked(true);
        table.add(showAxesBox);
        showPointsBox = new CheckBox(UiText.SHOW_INTERPOLATION_POINTS, skin);
        showPointsBox.getImageCell().padRight(UiConstants.PADDING);
        showPointsBox.setChecked(true);
        table.add(showPointsBox);
        table.row().padTop(UiConstants.PADDING);

        Button beginButton = new TextButton(UiText.BEGIN, skin);
        beginButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                onBeginButtonPressed();
            }
        });
        table.add(beginButton).colspan(2).center().minWidth(UiConstants.MIN_BUTTON_WIDTH);
        table.row();

        addActor(mainPane);
    }

    void onBeginButtonPressed() {
        InterpolationSettings settings = new InterpolationSettings();
        settings.single = numPolynomialsSelector.getValue() == 1;
        settings.filled = drawingStyleSelect.getSelectedIndex() == UiText.FILLED_INDEX;
        settings.free = interpolateModeSelect.getSelectedIndex() == UiText.FREE_INDEX;
        settings.showPoints = showPointsBox.isChecked();
        settings.numPoints1 = numPointsSelector1.getValue();
        settings.numPoints2 = numPointsSelector2.getValue();
        settings.showAxes = showAxesBox.isChecked();
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

}
