package com.shr4pnel.casino.scene;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.rafaskoberg.gdx.typinglabel.TypingLabel;
import com.shr4pnel.casino.base.Game;
import com.shr4pnel.casino.builders.LabelBuilder;
import com.shr4pnel.casino.builders.TypingAdapterBuilder;
import com.shr4pnel.casino.slots.SlotsGame;
import com.shr4pnel.casino.slots.SlotsReal;
import com.shr4pnel.casino.style.StyleManager;
import com.shr4pnel.casino.util.AsciiArt;

public class Slots extends ManagedButtonGame {
    private SlotsGame game;
    private Table root, fruitContainer,buttons;
    private TypingLabel label, updatefruit, topReal, middleReal, bottomReal;
    private TextButton pull,increaseBet,decreaseBet,mediumIncreaseBet,largeIncreaseBet,mediumDecreaseBet,largeDecreaseBet;
    private AsciiArt asciiArt;
    private SlotsReal slotsReal = new SlotsReal();
  private TypingAdapterBuilder typingAdapterBuilder = new TypingAdapterBuilder();



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

        for (int i = 0; i < 4; i++) {

            topReal = labelBuilder

                    .start(asciiArt.getFruit(game.getReals().get(0)[i], "32"))
                    .build();

            middleReal = labelBuilder

                .start(asciiArt.getFruit(game.getReals().get(1)[i], "32"))
                .build();

             bottomReal = labelBuilder

                .start(asciiArt.getFruit(game.getReals().get(2)[i], "32"))
                .build();

              //  TypingAdapter adapter = typingAdapterBuilder
                   // .chainTypingLabel(label, null)




            buttons= new Table(StyleManager.getSkin());





            fruitContainer = new Table(StyleManager.getSkin());
            fruitContainer.add(topReal).center().row();
           fruitContainer.add(middleReal).center().row();
            fruitContainer.add(bottomReal).center().row();
            root.add(fruitContainer).center();
        }
        return root;
    }

    public void updateFruit(int real, int index) {
        if(real > 2 || real < 0 || index > 10 || index < 0)
            return;

        label.setText(asciiArt.getFruit(game.getReals().get(real)[index], "32"));
    }


}

