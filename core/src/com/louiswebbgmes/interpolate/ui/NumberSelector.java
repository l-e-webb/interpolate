package com.louiswebbgmes.interpolate.ui;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.louiswebbgmes.interpolate.util.Executable;

public class NumberSelector extends HorizontalGroup {

    protected Executable onChanged;

    int value;
    int minValue;
    int maxValue;

    Image leftButton;
    Image rightButton;
    Label numLabel;

    NumberSelectorStyle style;

    NumberSelector(int minValue, int maxValue, NumberSelectorStyle style) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        value = minValue;
        this.style = style;
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
        addActor(leftButton);
        addActor(numLabel);
        addActor(rightButton);
        update();
    }

    void onLeftButtonClick() {
        value--;
        value = MathUtils.clamp(value, minValue, maxValue);
        update();
    }

    void onRightButtonClick() {
        value++;
        value = MathUtils.clamp(value, minValue, maxValue);
        update();
    }

    void update() {
        Drawable leftButtonImage = value > minValue ? style.leftButtonUp : style.leftButtonDown;
        Drawable rightButtonImage = value < maxValue ? style.rightButtonUp : style.rightButtonDown;
        leftButton.setDrawable(leftButtonImage);
        rightButton.setDrawable(rightButtonImage);
        numLabel.setText(value + "");
        if (onChanged != null) onChanged.execute();
    }

    void setOnChangedListener(Executable onChanged) {
        this.onChanged = onChanged;
    }

    public int getValue() {
        return value;
    }

    public static class NumberSelectorStyle {
        Label.LabelStyle labelStyle;
        Drawable leftButtonUp;
        Drawable leftButtonDown;
        Drawable rightButtonUp;
        Drawable rightButtonDown;
    }
}
