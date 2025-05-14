package com.shr4pnel.casino.scene;

import com.badlogic.gdx.scenes.scene2d.ui.*;

import com.rafaskoberg.gdx.typinglabel.TypingAdapter;
import com.rafaskoberg.gdx.typinglabel.TypingLabel;
import com.shr4pnel.casino.Casino;
import com.shr4pnel.casino.base.Game;
import com.shr4pnel.casino.roulette.RouletteGame;
import com.shr4pnel.casino.roulette.RoulettePlayer;
import com.shr4pnel.casino.builders.LabelBuilder;
import com.shr4pnel.casino.builders.TypingAdapterBuilder;
import com.shr4pnel.casino.style.StyleManager;
import com.shr4pnel.casino.util.AsciiArt;
import com.shr4pnel.casino.util.TextureManager;

public class Roulette extends ManagedButtonGame {
    //change these to what's needed
    private ButtonGroup<TextButton> textButtonGroup = new ButtonGroup<>();
    private TextButton bet ; //buttons
    private Label chipCount;
    private TypingLabel phase;
    private Image cardPlain;
    private Table root, playerHandRoot, aiHandRoot, playerButtonRoot, chipTable, status;
    private TextureManager textureManager;
    private RouletteGame game;
    private LabelBuilder labelBuilder = new LabelBuilder();
    private AsciiArt asciiArt = new AsciiArt();

    @Override
    public RouletteGame getGameInstance() {
        return null;
    }

    @Override
    public Table get() {
        root = new Table(StyleManager.getSkin());
        return root;
    }
}

