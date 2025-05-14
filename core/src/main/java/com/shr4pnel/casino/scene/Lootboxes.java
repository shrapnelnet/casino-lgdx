package com.shr4pnel.casino.scene;


import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.rafaskoberg.gdx.typinglabel.TypingLabel;

import com.shr4pnel.casino.builders.LabelBuilder;
import com.shr4pnel.casino.loot.Loot;
import com.shr4pnel.casino.loot.LootManager;
import com.shr4pnel.casino.style.StyleManager;
import com.shr4pnel.casino.input.ButtonGroupManager;
import java.util.ArrayList;
import java.util.List;


/**
 * Handles the UI for the menu.
 * @author shrapnelnet
 * @since 0.1.0
 * @see ManagedButtonScene
 */
public class Lootboxes extends ManagedButtonScene {
    List<Loot> items = new ArrayList<>();
        items.add(new Loot("Gold Coin", 70));
        items.add(new Loot("Diamond", 25));
        items.add(new Loot("Excalibur", 5));
        items.add(new Loot("Health Potion", 50));
        items.add(new Loot("Iron Sword", 30));
        items.add(new Loot("Magic Ring", 10));

    LootManager manager = new LootManager(items);
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
            .start("{WIND}Lootboxes{ENDWIND}")
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
