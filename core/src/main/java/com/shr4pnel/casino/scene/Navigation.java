package com.shr4pnel.casino.scene;

import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.rafaskoberg.gdx.typinglabel.TypingLabel;
import com.shr4pnel.casino.builders.LabelBuilder;
import com.shr4pnel.casino.input.ButtonGroupManager;
import com.shr4pnel.casino.style.StyleManager;

/**
 * Handles the UI for the menu.
 * @author shrapnelnet
 * @since 0.1.0
 * @see ManagedButtonScene
 */
public class Navigation extends ManagedButtonScene {
    private final LabelBuilder labelBuilder = new LabelBuilder();
    private TextButton Blackjack, Poker, Slots, Roulette, Lootboxes, Back;
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
            .start("{WIND}Select a game{ENDWIND}")
            .noDelay()
            .build();

        Blackjack = new TextButton("Blackjack", StyleManager.getSkin(), "toggle");
        Poker = new TextButton("Poker", StyleManager.getSkin(), "toggle");
        Slots = new TextButton("Slots", StyleManager.getSkin(), "toggle");
        Roulette = new TextButton("Roulette", StyleManager.getSkin(), "toggle");
        Lootboxes = new TextButton("Lootboxes", StyleManager.getSkin(), "toggle");
        Back = new TextButton("Back", StyleManager.getSkin(), "toggle");

        Blackjack.setName("Blackjack");
        Poker.setName("Poker");
        Slots.setName("Slots");
        Roulette.setName("Roulette");
        Lootboxes.setName("Lootboxes");
        Back.setName("Back");

        textButtonGroup.add(Blackjack, Poker,Slots,Roulette,Lootboxes,Back);
        textButtonGroup.setMaxCheckCount(1);
        textButtonGroup.setMinCheckCount(1);
        buttonGroupManager = new ButtonGroupManager(Blackjack, Poker,Slots,Roulette,Lootboxes ,Back);
        buttonGroupManager.setListener(activeButton -> textButtonGroup.setChecked(activeButton.getName()));

        labelRoot.add(label).center();
        root.background("window");
        root.setSize(800, 450);

        // without colspan, i stretch the Blackjack button out to 800 width
        root.add(labelRoot).colspan(3).center().row();
        root.add(Blackjack).pad(10);
        root.add(Poker).pad(10).row();
        root.add(Slots).pad(10);
        root.add(Roulette).pad(10).row();
        root.add(Lootboxes).pad(10);
        root.add(Back).pad(10).row();

        return root;
    }
}
