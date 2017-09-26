package com.louiswebbgmes.interpolate.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class UiConstants {

    static Skin skin;

    static final float MENU_WIDTH_RATIO = 0.85f;
    static final float MENU_MIN_WIDTH = 400f;
    static final float MENU_MAX_WIDTH = 800f;
    static final float PADDING = 15f;
    static final float SMALL_PAD = 8f;
    static final float MIN_BUTTON_WIDTH = 150f;
    static final float NUMBER_BOX_WIDTH = 30f;

    static Drawable baseButton;
    static Drawable baseButtonGray;
    static Drawable baseButtonFilled;
    static Drawable checkboxFilled;
    static Drawable checkboxFilledGray;
    static Drawable leftArrow;
    static Drawable leftArrowGray;
    static Drawable rightArrow;
    static Drawable rightArrowGray;
    static Drawable backButton;
    static Drawable empty;

    static NumberSelector.NumberSelectorStyle numSelectorStyle;
    static PolynomialSettingsBank.PolynomialSettingsBankStyle settingsBankStyle;

    static final Color GRAY = Color.GRAY;
    static final Color TEXT_COLOR = Color.WHITE;
    static final Color TEXT_COLOR_INVERTED = Color.BLACK;
    static final float FONT_SCALE = 1f;
    static final float TITLE_FONT_SCALE = 1f;

    static final String TITLE_LABEL = "title";

    static final int MIN_POINTS = 2;
    static final int MAX_POINTS = 9;

    public static void initUi() {
        skin = new Skin();

        BitmapFont font = new BitmapFont(Gdx.files.internal("font.fnt"));
        font.getData().setScale(FONT_SCALE);
        font.getRegion().getTexture()
                .setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        BitmapFont titleFont = new BitmapFont(Gdx.files.internal("title_font.fnt"));
        titleFont.getData().setScale(TITLE_FONT_SCALE);
        titleFont.getRegion().getTexture()
                .setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        baseButton = new NinePatchDrawable(new NinePatch(
                new Texture("base_label_9patch.png"), 5, 5, 5, 5)
        );
        baseButtonGray = ((NinePatchDrawable)baseButton).tint(GRAY);
        baseButtonFilled = new NinePatchDrawable(new NinePatch(
                new Texture("base_label_9patch_filled.png"), 5, 5, 5, 5)
        );
        checkboxFilled = new TextureRegionDrawable(
                new TextureRegion(new Texture("checkbox_checked.png"))
        );
        checkboxFilledGray = ((TextureRegionDrawable)checkboxFilled).tint(GRAY);
        leftArrow = new TextureRegionDrawable(
                new TextureRegion(new Texture("left_arrow.png"))
        );
        leftArrowGray = ((TextureRegionDrawable)leftArrow).tint(GRAY);
        rightArrow = new TextureRegionDrawable(
                new TextureRegion(new Texture("right_arrow.png"))
        );
        rightArrowGray = ((TextureRegionDrawable)rightArrow).tint(GRAY);
        empty = new TextureRegionDrawable(
                new TextureRegion(new Texture("empty.png"))
        );
        backButton = new TextureRegionDrawable(
                new TextureRegion(new Texture("back_button.png"))
        );

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font;
        labelStyle.fontColor = TEXT_COLOR;
        skin.add("default", labelStyle, Label.LabelStyle.class);

        Label.LabelStyle boxLabelStyle = new Label.LabelStyle();
        boxLabelStyle.font = font;
        boxLabelStyle.fontColor = TEXT_COLOR;
        boxLabelStyle.background = baseButton;
        skin.add("boxed", boxLabelStyle, Label.LabelStyle.class);

        Label.LabelStyle grayLabelStyle = new Label.LabelStyle(labelStyle);
        grayLabelStyle.fontColor = GRAY;
        skin.add("gray", grayLabelStyle, Label.LabelStyle.class);

        Label.LabelStyle grayBoxLabelStyle = new Label.LabelStyle(boxLabelStyle);
        grayBoxLabelStyle.fontColor = GRAY;
        grayBoxLabelStyle.background = baseButtonGray;

        Label.LabelStyle titleLabelStyle = new Label.LabelStyle();
        titleLabelStyle.font = titleFont;
        titleLabelStyle.fontColor = TEXT_COLOR;
        skin.add(TITLE_LABEL, titleLabelStyle, Label.LabelStyle.class);

        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = font;
        buttonStyle.down = baseButton;
        buttonStyle.up = baseButton;
        buttonStyle.checked = baseButton;
        buttonStyle.over = baseButtonFilled;
        buttonStyle.checkedOver = baseButtonFilled;
        buttonStyle.fontColor = TEXT_COLOR;
        buttonStyle.checkedFontColor = TEXT_COLOR;
        buttonStyle.downFontColor = TEXT_COLOR;
        buttonStyle.checkedOverFontColor = TEXT_COLOR_INVERTED;
        buttonStyle.overFontColor = TEXT_COLOR_INVERTED;
        skin.add("default", buttonStyle, TextButton.TextButtonStyle.class);

        CheckBox.CheckBoxStyle checkboxStyle = new CheckBox.CheckBoxStyle();
        checkboxStyle.checkboxOff = baseButton;
        checkboxStyle.checkboxOn = checkboxFilled;
        checkboxStyle.checkboxOffDisabled = baseButtonGray;
        checkboxStyle.checkboxOnDisabled = checkboxFilledGray;
        checkboxStyle.font = font;
        checkboxStyle.fontColor = TEXT_COLOR;
        checkboxStyle.disabledFontColor = GRAY;

        skin.add("default", checkboxStyle, CheckBox.CheckBoxStyle.class);

        ScrollPane.ScrollPaneStyle paneStyle = new ScrollPane.ScrollPaneStyle();
        paneStyle.background = baseButton;
        skin.add("default", paneStyle, ScrollPane.ScrollPaneStyle.class);

        List.ListStyle listStyle = new List.ListStyle(font, TEXT_COLOR_INVERTED, TEXT_COLOR, baseButtonFilled);
        skin.add("default", listStyle, List.ListStyle.class);

        SelectBox.SelectBoxStyle selectBoxStyle = new SelectBox.SelectBoxStyle();
        selectBoxStyle.font = font;
        selectBoxStyle.fontColor = TEXT_COLOR;
        selectBoxStyle.disabledFontColor = GRAY;
        selectBoxStyle.scrollStyle = paneStyle;
        selectBoxStyle.listStyle = listStyle;
        selectBoxStyle.background = baseButton;
        selectBoxStyle.backgroundDisabled = baseButtonGray;
        skin.add("default", selectBoxStyle, SelectBox.SelectBoxStyle.class);

        numSelectorStyle = new NumberSelector.NumberSelectorStyle();
        numSelectorStyle.labelStyle = labelStyle;
        numSelectorStyle.disabledLabelStyle = grayLabelStyle;
        numSelectorStyle.numLabelStyle = boxLabelStyle;
        numSelectorStyle.disabledNumLabelStyle = grayBoxLabelStyle;
        numSelectorStyle.leftButtonDown = leftArrowGray;
        numSelectorStyle.leftButtonUp = leftArrow;
        numSelectorStyle.rightButtonDown = rightArrowGray;
        numSelectorStyle.rightButtonUp = rightArrow;

        settingsBankStyle = new PolynomialSettingsBank.PolynomialSettingsBankStyle();
        settingsBankStyle.background = baseButton;
        settingsBankStyle.disabledBackground = baseButtonGray;
        settingsBankStyle.labelStyle = labelStyle;
        settingsBankStyle.disabledLabelStyle = grayLabelStyle;
        settingsBankStyle.numberSelectorStyle = numSelectorStyle;
        settingsBankStyle.selectBoxStyle = selectBoxStyle;
    }
}
