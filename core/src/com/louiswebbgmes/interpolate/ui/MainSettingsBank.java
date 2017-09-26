package com.louiswebbgmes.interpolate.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.louiswebbgmes.interpolate.util.Executable;

class MainSettingsBank extends Table {

    private OpeningMenu menu;

    private NumberSelector numPolynomialsSelector;
    private SelectBox<String> drawingStyleSelect;
    private SelectBox<String> interpolateModeSelect;
    private CheckBox showAxesBox;
    private CheckBox showPointsBox;

    MainSettingsBank(OpeningMenu menu, Skin skin) {
        super();

        this.menu = menu;

        setBackground(UiConstants.baseButton);
        pad(UiConstants.PADDING);

        //Add header label.
        add(new Label(UiText.MAIN_SETTINGS, skin)).colspan(4).center();
        row().padTop(UiConstants.PADDING);

        //Add # of polynomial control.
        numPolynomialsSelector = new NumberSelector(1, 2, UiText.NUM_INTERPOLATIONS + ":", UiConstants.numSelectorStyle);
        numPolynomialsSelector.setOnChangedListener(new Executable() {
            @Override
            public void execute() {
                MainSettingsBank.this.menu.update();
            }
        });
        add(numPolynomialsSelector).colspan(2);

        //Add interpolation mode controls.
        add(new Label(UiText.INTERPOLATION_MODE + ":", skin)).right().padRight(UiConstants.SMALL_PAD);
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
                MainSettingsBank.this.menu.update();
            }
        });
        add(interpolateModeSelect).minWidth(UiConstants.MIN_BUTTON_WIDTH);

        row().padTop(UiConstants.PADDING);

        //Add show axes and show points boxes.
        showAxesBox = new CheckBox(UiText.SHOW_AXES, skin);
        showAxesBox.getImageCell().padRight(UiConstants.SMALL_PAD);
        showAxesBox.setChecked(true);
        add(showAxesBox).padRight(UiConstants.SMALL_PAD);
        showPointsBox = new CheckBox(UiText.SHOW_INTERPOLATION_POINTS, skin);
        showPointsBox.getImageCell().padRight(UiConstants.SMALL_PAD);
        showPointsBox.setChecked(true);
        showPointsBox.setDisabled(true);
        add(showPointsBox);

        //Add graphing style control.
        add(new Label(UiText.DRAW_STYLE + ":", skin)).right().padRight(UiConstants.SMALL_PAD);
        drawingStyleSelect = new SelectBox<String>(skin);
        drawingStyleSelect.setItems(UiText.DRAW_STYLE_OPTIONS);
        add(drawingStyleSelect).minWidth(UiConstants.MIN_BUTTON_WIDTH);

        //Uncomment to view debug lines
        //setDebug(true, true);
    }

    boolean showPoints() {
        return showPointsBox.isChecked();
    }

    boolean showAxes() {
        return showAxesBox.isChecked();
    }

    boolean single() {
        return numPolynomialsSelector.getValue() == 1;
    }

    boolean free() {
        return interpolateModeSelect.getSelectedIndex() == UiText.FREE_INDEX;
    }

    boolean filled() {
        return drawingStyleSelect.getSelectedIndex() == UiText.FILLED_INDEX;
    }

}
