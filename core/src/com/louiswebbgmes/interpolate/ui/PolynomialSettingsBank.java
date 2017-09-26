package com.louiswebbgmes.interpolate.ui;

import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

class PolynomialSettingsBank extends Table {

    boolean enabled;
    boolean freeEnabled;

    PolynomialSettingsBankStyle style;
    Label headerLabel;
    Label freeModeLabel;
    Label speedSelectLabel;
    Label motionSelectLabel;
    NumberSelector numPointsSelector;
    SelectBox<String> speedSelectBox;
    SelectBox<String> motionSelectBox;

    PolynomialSettingsBank(String title, PolynomialSettingsBankStyle style) {
        super();

        this.style = style;
        setBackground(style.background);
        pad(UiConstants.PADDING);

        //Add header.
        headerLabel = new Label(title, style.labelStyle);
        add(headerLabel).colspan(5).center();

        row().padTop(UiConstants.PADDING);

        //Add number of points control.
        numPointsSelector = new NumberSelector(
                UiConstants.MIN_POINTS,
                UiConstants.MAX_POINTS,
                UiText.NUM_POINTS + ":",
                style.numberSelectorStyle
        );
        add(numPointsSelector).colspan(5).left();

        row().padTop(UiConstants.PADDING);

        //Free mode settings label.
        freeModeLabel = new Label(UiText.FREE_MODE_SETTINGS + ":", style.labelStyle);
        add(freeModeLabel).padRight(UiConstants.SMALL_PAD);

        //Add speed selection.
        speedSelectLabel = new Label(UiText.SPEED + ":", style.labelStyle);
        add(speedSelectLabel).expandX().right().padRight(UiConstants.SMALL_PAD);
        speedSelectBox = new SelectBox<String>(style.selectBoxStyle);
        speedSelectBox.setItems(UiText.SPEED_OPTIONS);
        add(speedSelectBox).minWidth(UiConstants.MIN_BUTTON_WIDTH).padRight(UiConstants.PADDING);

        //Add motion selection.
        motionSelectLabel = new Label(UiText.MOTION_MODE + ":", style.labelStyle);
        add(motionSelectLabel).expandX().right().padRight(UiConstants.SMALL_PAD);
        motionSelectBox = new SelectBox<String>(style.selectBoxStyle);
        motionSelectBox.setItems(UiText.MOTION_OPTION);
        add(motionSelectBox).minWidth(UiConstants.MIN_BUTTON_WIDTH);
    }

    void update() {
        Label.LabelStyle labelStyle = enabled ? style.labelStyle : style.disabledLabelStyle;
        headerLabel.setStyle(labelStyle);
        if (!freeEnabled) labelStyle = style.disabledLabelStyle;
        freeModeLabel.setStyle(labelStyle);
        speedSelectLabel.setStyle(labelStyle);
        motionSelectLabel.setStyle(labelStyle);
        numPointsSelector.setDisabled(!enabled);
        speedSelectBox.setDisabled(!enabled || !freeEnabled);
        motionSelectBox.setDisabled(!enabled || !freeEnabled);
        setBackground(enabled ? style.background : style.disabledBackground);
    }

    void setDisabled(boolean disabled) {
        enabled = !disabled;
        update();
    }

    void setFreeEnabled(boolean free) {
        freeEnabled = free;
        update();
    }

    int getNumPoints() {
        return numPointsSelector.getValue();
    }

    int getSpeedSetting() {
        return speedSelectBox.getSelectedIndex();
    }

    int getMotionSetting() {
        return motionSelectBox.getSelectedIndex();
    }

    static class PolynomialSettingsBankStyle {
        Drawable background;
        Drawable disabledBackground;
        Label.LabelStyle labelStyle;
        Label.LabelStyle disabledLabelStyle;
        NumberSelector.NumberSelectorStyle numberSelectorStyle;
        SelectBox.SelectBoxStyle selectBoxStyle;
    }
}
