package com.shr4pnel.casino.scene;

import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.rafaskoberg.gdx.typinglabel.TypingLabel;

import com.shr4pnel.casino.builders.LabelBuilder;
import com.shr4pnel.casino.style.StyleManager;
import com.shr4pnel.casino.input.ButtonGroupManager;

/**
 * Handles the UI for the menu.
 * @author shrapnelnet
 * @since 0.1.0
 * @see ManagedButtonScene
 */
public class Poker extends ManagedButtonScene {
    private final LabelBuilder labelBuilder = new LabelBuilder();
    private TextButton Return;
    private TypingLabel label;
    private ButtonGroup<TextButton> textButtonGroup = new ButtonGroup<>();

    /**
     * @return The Intro Scene2D ui, as a table
     */
    @Override
    public Table get() {
        Table root = new Table(StyleManager.getSkin());
        Table labelRoot = new Table(StyleManager.getSkin());

        label = labelBuilder
            .start("{WIND}Poker{ENDWIND}")
            .noDelay()
            .build();


        Return = new TextButton("Return", StyleManager.getSkin(), "toggle");


        Return.setName("Return");


        textButtonGroup.add(Return);
        textButtonGroup.setMaxCheckCount(1);
        textButtonGroup.setMinCheckCount(1);
        buttonGroupManager = new ButtonGroupManager(Return);
        buttonGroupManager.setListener(activeButton -> textButtonGroup.setChecked(activeButton.getName()));

        labelRoot.add(label).center();
        root.background("window");
        root.setSize(800, 450);

        // without colspan, i stretch the play button out to 800 width
        root.add(labelRoot).colspan(3).center().row();
        root.add(Return).pad(10);

        return root;
    }
}
