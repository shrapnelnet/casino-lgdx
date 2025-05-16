package com.shr4pnel.casino.scene;

import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.rafaskoberg.gdx.typinglabel.TypingLabel;
import com.shr4pnel.casino.base.Game;
import com.shr4pnel.casino.blackjack.BlackjackGame;
import com.shr4pnel.casino.builders.LabelBuilder;
import com.shr4pnel.casino.builders.TypingAdapterBuilder;
import com.shr4pnel.casino.input.SlotsButtonManager;
import com.shr4pnel.casino.slots.SlotsGame;
import com.shr4pnel.casino.slots.SlotsReal;
import com.shr4pnel.casino.style.StyleManager;
import com.shr4pnel.casino.util.AsciiArt;

public class Slots extends ManagedButtonGame {
    private SlotsGame game;
    private Table root, fruitContainer, buttons,playerButtonRoot,container;

    private TypingLabel label, updatefruit, topReal, middleReal, bottomReal;
    private TextButton pull, increaseBet, decreaseBet, mediumIncreaseBet, largeIncreaseBet, mediumDecreaseBet, largeDecreaseBet;
    private AsciiArt asciiArt;
    private SlotsReal slotsReal = new SlotsReal();
    private TypingAdapterBuilder typingAdapterBuilder = new TypingAdapterBuilder();
    private ButtonGroup<TextButton> textButtonGroup = new ButtonGroup<TextButton>();

    @Override
    public Game getGameInstance() {
        return game;
    }


    @Override
    public Table get() {
        game = new SlotsGame();
        root = new Table(StyleManager.getSkin());

        root.setDebug(true, true);
        root.setSize(800, 450);
        root.background("window");

        asciiArt = new AsciiArt();

        LabelBuilder labelBuilder = new LabelBuilder();
        SlotsGame game = (SlotsGame) getGameInstance();
        pull = newTextButton("Pull");
        increaseBet = newTextButton("+");
        decreaseBet = newTextButton("-");
        mediumIncreaseBet = newTextButton("++");
        mediumDecreaseBet = newTextButton("--");
        largeDecreaseBet = newTextButton("---");
        largeIncreaseBet = newTextButton("+++");

        for (int i = 0; i < 3; i++) {

            topReal = labelBuilder

                .start(asciiArt.getFruit(game.getReals().get(0)[i], "32"))
                .build();

            middleReal = labelBuilder

                .start(asciiArt.getFruit(game.getReals().get(1)[i], "32"))
                .build();

            bottomReal = labelBuilder
                .start(asciiArt.getFruit(game.getReals().get(2)[i], "32"))
                .build();

             //TypingAdapter adapter = typingAdapterBuilder();
             // .chainTypingLabel(label, null)


            buttons = new Table(StyleManager.getSkin());
            playerButtonRoot = new Table(StyleManager.getSkin());
            container = new Table(StyleManager.getSkin());

            setAllButtons(pull, increaseBet, decreaseBet, mediumIncreaseBet, largeIncreaseBet, mediumDecreaseBet, largeDecreaseBet);
            playerButtonRoot.add(pull, increaseBet, decreaseBet, mediumIncreaseBet, largeIncreaseBet, mediumDecreaseBet, largeDecreaseBet);


            fruitContainer = new Table(StyleManager.getSkin());
            topReal.setFontScale(0.6f);
            middleReal.setFontScale(0.6f);
            bottomReal.setFontScale(0.6f);
            fruitContainer.add(topReal).center().row();
            fruitContainer.add(middleReal).center().row();
            fruitContainer.add(bottomReal).center().row();

            root.add(fruitContainer);
        }
        return root;
    }

    public void updateFruit(int real, int index) {
        if (real > 2 || real < 0 || index > 10 || index < 0)
            return;

        label.setText(asciiArt.getFruit(game.getReals().get(real)[index], "32"));
    }

    private void setAllButtons(TextButton... t) {
        playerButtonRoot.clear();

        if (t == null)
            return;

        playerButtonRoot.add().expandX();
        playerButtonRoot.add(t).right();

        textButtonGroup.clear();
        textButtonGroup.add(t);

        if (buttonGroupManager == null) {
            buttonGroupManager = new SlotsButtonManager(t);
            buttonGroupManager.setListener(activeButton -> textButtonGroup.setChecked(activeButton.getName()));
        } else {
            buttonGroupManager.setMenuButtonGroup(t);
        }
    }

}

