package com.louiswebbgmes.interpolate.ui;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.louiswebbgmes.interpolate.util.Executable;

public class NumberSelector extends Table {

    private Executable onChanged;

    boolean enabled;

    int value;
    int minValue;
    int maxValue;

    Label label;
    Image leftButton;
    Image rightButton;
    Label numLabel;

    NumberSelectorStyle style;

    NumberSelector(int minValue, int maxValue, String text, NumberSelectorStyle style) {
        enabled = true;
        this.minValue = minValue;
        this.maxValue = maxValue;
        value = minValue;
        this.style = style;

        label = new Label(text, style.labelStyle);
        leftButton = new Image();
        leftButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                onLeftButtonClick();
            }
        });
        rightButton = new Image();
        rightButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                onRightButtonClick();
            }
        });
        numLabel = new Label("", style.labelStyle);
        numLabel.setAlignment(Align.center);

        add(label).right().padRight(UiConstants.SMALL_PAD);
        add(leftButton);
        add(numLabel).minWidth(UiConstants.NUMBER_BOX_WIDTH)
                .padLeft(UiConstants.SMALL_PAD).padRight(UiConstants.SMALL_PAD)
                .fill();
        add(rightButton);
        row();
        update();
    }

    void onLeftButtonClick() {
        if (!enabled) return;
        value--;
        value = MathUtils.clamp(value, minValue, maxValue);
        update();
    }

    void onRightButtonClick() {
        if (!enabled) return;
        value++;
        value = MathUtils.clamp(value, minValue, maxValue);
        update();
    }

    void update() {
        if (enabled) {
            Drawable leftButtonImage = value > minValue ? style.leftButtonUp : style.leftButtonDown;
            Drawable rightButtonImage = value < maxValue ? style.rightButtonUp : style.rightButtonDown;
            leftButton.setDrawable(leftButtonImage);
            rightButton.setDrawable(rightButtonImage);
            label.setStyle(style.labelStyle);
            numLabel.setStyle(style.numLabelStyle);
            numLabel.setText(value + "");
            if (onChanged != null) onChanged.execute();
        } else {
            leftButton.setDrawable(style.leftButtonDown);
            rightButton.setDrawable(style.rightButtonDown);
            label.setStyle(style.disabledLabelStyle);
            numLabel.setStyle(style.disabledNumLabelStyle);
        }
    }

    void setDisabled(boolean disabled) {
        enabled = !disabled;
        update();
    }

    void setOnChangedListener(Executable onChanged) {
        this.onChanged = onChanged;
    }

    public int getValue() {
        return value;
    }

    public static class NumberSelectorStyle {
        Label.LabelStyle labelStyle;
        Label.LabelStyle disabledLabelStyle;
        Label.LabelStyle numLabelStyle;
        Label.LabelStyle disabledNumLabelStyle;
        Drawable leftButtonUp;
        Drawable leftButtonDown;
        Drawable rightButtonUp;
        Drawable rightButtonDown;
    }
}
