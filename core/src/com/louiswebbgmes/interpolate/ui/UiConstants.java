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

    static final float MENU_WIDTH_RATIO = 1f;
    static final float MENU_MIN_WIDTH = 400f;
    static final float MENU_MAX_WIDTH = 800f;
    static final float MENU_HEIGHT_RATIO = 1f;
    static final float MENU_MIN_HEIGHT = 300f;
    static final float MENU_MAX_HEIGHT = 800f;
    static final float PADDING = 15f;
    static final float MIN_BUTTON_WIDTH = 150f;

    static Drawable baseButton;
    static Drawable baseButtonFilled;
    static Drawable checkboxFilled;
    static Drawable leftArrow;
    static Drawable rightArrow;
    static Drawable empty;

    static NumberSelector.NumberSelectorStyle numSelectorStyle;

    static final Color TEXT_COLOR = Color.WHITE;
    static final Color TEXT_COLOR_INVERTED = Color.BLACK;
    static final float FONT_SCALE = 0.125f;
    static final float TITLE_FONT_SCALE = 0.25f;

    static final String TITLE_LABEL = "title";

    static final int MIN_POINTS = 2;
    static final int MAX_POINTS = 9;

    public static void initUi() {
        skin = new Skin();

        BitmapFont font = new BitmapFont(Gdx.files.internal("font.fnt"));
        font.getData().setScale(FONT_SCALE);
        font.getRegion().getTexture()
                .setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        BitmapFont titleFont = new BitmapFont(Gdx.files.internal("font.fnt"));
        titleFont.getData().setScale(TITLE_FONT_SCALE);
        titleFont.getRegion().getTexture()
                .setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        baseButton = new NinePatchDrawable(new NinePatch(
                new Texture("base_label_9patch.png"), 5, 5, 5, 5)
        );
        baseButtonFilled = new NinePatchDrawable(new NinePatch(
                new Texture("base_label_9patch_filled.png"), 5, 5, 5, 5)
        );
        checkboxFilled = new TextureRegionDrawable(
                new TextureRegion(new Texture("checkbox_checked.png"))
        );
        leftArrow = new TextureRegionDrawable(
                new TextureRegion(new Texture("left_arrow.png"))
        );
        rightArrow = new TextureRegionDrawable(
                new TextureRegion(new Texture("right_arrow.png"))
        );
        empty = new TextureRegionDrawable(
                new TextureRegion(new Texture("empty.png"))
        );

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font;
        labelStyle.fontColor = TEXT_COLOR;
        skin.add("default", labelStyle, Label.LabelStyle.class);

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
        checkboxStyle.font = font;
        checkboxStyle.fontColor = TEXT_COLOR;
        skin.add("default", checkboxStyle, CheckBox.CheckBoxStyle.class);

        ScrollPane.ScrollPaneStyle paneStyle = new ScrollPane.ScrollPaneStyle();
        paneStyle.background = baseButton;
        skin.add("default", paneStyle, ScrollPane.ScrollPaneStyle.class);

        List.ListStyle listStyle = new List.ListStyle(font, TEXT_COLOR_INVERTED, TEXT_COLOR, baseButtonFilled);
        skin.add("default", listStyle, List.ListStyle.class);

        SelectBox.SelectBoxStyle selectBoxStyle = new SelectBox.SelectBoxStyle();
        selectBoxStyle.font = font;
        selectBoxStyle.fontColor = TEXT_COLOR;
        selectBoxStyle.scrollStyle = paneStyle;
        selectBoxStyle.listStyle = listStyle;
        selectBoxStyle.background = baseButton;
        skin.add("default", selectBoxStyle, SelectBox.SelectBoxStyle.class);

        numSelectorStyle = new NumberSelector.NumberSelectorStyle();
        numSelectorStyle.labelStyle = labelStyle;
        numSelectorStyle.leftButtonDown = empty;
        numSelectorStyle.leftButtonUp = leftArrow;
        numSelectorStyle.rightButtonDown = empty;
        numSelectorStyle.rightButtonUp = rightArrow;
    }
}
