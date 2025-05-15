package com.shr4pnel.casino.scene;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.rafaskoberg.gdx.typinglabel.TypingLabel;
import com.shr4pnel.casino.base.Game;
import com.shr4pnel.casino.builders.LabelBuilder;
import com.shr4pnel.casino.slots.SlotsGame;
import com.shr4pnel.casino.style.StyleManager;
import com.shr4pnel.casino.util.AsciiArt;
public class Slots extends ManagedButtonGame {
   private SlotsGame game;
   private Table root,fruitContainer;
   private TypingLabel label;
   private AsciiArt asciiArt;


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

        label = labelBuilder
            .start(asciiArt.getFruit(asciiArt.getRanFruit(),"32"))
            .build();
        fruitContainer = new Table(StyleManager.getSkin());
        fruitContainer.add(label).center().row();
        root.add(fruitContainer).center().row();


        return root;
        
        
    }
    
}
