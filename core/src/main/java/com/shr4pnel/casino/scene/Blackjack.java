package com.shr4pnel.casino.scene;

import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;

import com.shr4pnel.casino.style.StyleManager;
import com.shr4pnel.casino.util.ButtonGroupManager;

public class Blackjack {
    private static ButtonGroup<TextButton> textButtonGroup = new ButtonGroup<>();
    private static ButtonGroupManager buttonGroupManager;
    private static TextButton hit, stand, split, doubleDown;

    public static Table get() {
        Table root = new Table(StyleManager.getSkin());
        root.setDebug(true);
        root.setSize(800, 450);
        root.background("window");

        hit = new TextButton("Hit", StyleManager.getSkin(), "toggle");
        stand = new TextButton("Stand", StyleManager.getSkin(), "toggle");
        split = new TextButton("Split", StyleManager.getSkin(), "toggle");
        doubleDown = new TextButton("Double Down", StyleManager.getSkin(), "toggle");

        hit.setName("Hit");
        stand.setName("Stand");
        split.setName("Split");
        doubleDown.setName("Double Down");

        textButtonGroup.add(hit, stand, split, doubleDown);
        textButtonGroup.setMinCheckCount(1);
        textButtonGroup.setMaxCheckCount(1);
        buttonGroupManager = new ButtonGroupManager(hit, stand, split, doubleDown);
        buttonGroupManager.setListener(activeButton -> textButtonGroup.setChecked(activeButton.getName()));

        root.add(hit, stand, split, doubleDown).align(Align.bottom).row();
        root.add().height(20);
        return root;
    }

    public static ButtonGroupManager getButtonGroupManager() {
        return buttonGroupManager;
    }
}
