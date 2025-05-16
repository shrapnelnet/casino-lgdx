package com.shr4pnel.casino.scene;

import com.badlogic.gdx.scenes.scene2d.ui.*;

import com.rafaskoberg.gdx.typinglabel.TypingAdapter;
import com.rafaskoberg.gdx.typinglabel.TypingLabel;
import com.shr4pnel.casino.Casino;
import com.shr4pnel.casino.base.Game;
import com.shr4pnel.casino.input.BlackjackButtonManager;
import com.shr4pnel.casino.input.RouletteButtonManager;
import com.shr4pnel.casino.roulette.RouletteGame;
import com.shr4pnel.casino.roulette.RoulettePlayer;
import com.shr4pnel.casino.builders.LabelBuilder;
import com.shr4pnel.casino.builders.TypingAdapterBuilder;
import com.shr4pnel.casino.style.StyleManager;
import com.shr4pnel.casino.util.TextureManager;

public class Roulette extends ManagedButtonGame {
    private ButtonGroup<TextButton> textButtonGroup = new ButtonGroup<>();
    private TextButton bet, increaseBet, decreaseBet, mediumIncreaseBet, mediumDecreaseBet, largeIncreaseBet, largeDecreaseBet, restart, odds, evens, reds, blacks, first, second, third, increaseChosenNumber, decreaseChosenNumber, largeDecreaseChosenNumber, largeIncreaseChosenNumber; //buttons
    private Label chipCount, chosenNumber, spinText;
    private Table root, chipTable, betTable;
    private TextureManager textureManager;
    private RouletteGame game;
    private LabelBuilder labelBuilder = new LabelBuilder();

    @Override
    public RouletteGame getGameInstance() {
        return game;
    }

    @Override
    public Table get() {
        root = new Table(StyleManager.getSkin());
        chipTable = new Table(StyleManager.getSkin());
        betTable = new Table(StyleManager.getSkin());
        game = new RouletteGame();

        root.setDebug(true, true);
        root.setSize(800, 450);
        root.background("window");

        bet = newTextButton("Bet");
        increaseBet = newTextButton("+");
        decreaseBet = newTextButton("-");
        mediumIncreaseBet = newTextButton("++");
        mediumDecreaseBet = newTextButton("--");
        largeDecreaseBet = newTextButton("---");
        largeIncreaseBet = newTextButton("+++");
        restart = newTextButton("Restart");
        odds = newTextButton("Odds");
        evens = newTextButton("Evens");
        reds = newTextButton("Red");
        blacks = newTextButton("Black");
        first = newTextButton("1 - 12");
        second = newTextButton("13 - 24");
        third = newTextButton("25 - 36");
        increaseChosenNumber = newTextButton("+");
        decreaseChosenNumber = newTextButton("-");
        largeIncreaseChosenNumber = newTextButton("+");
        largeDecreaseChosenNumber = newTextButton("-");

        setPlayerButtonPaneByPhase();
        return root;
    }

    public void updateChipDisplay() {
        chipCount.setText("Bet: " + game.getPlayer().getBet() + "/" + game.getPlayer().getChips());
    }

    private void setPlayerButtonPaneByPhase(){
        switch (game.getPhase()) {
            case BET -> bet();
            case SPIN -> spin();
            case PAYOUT -> payout();

        }
    }

    private void bet() {
        betTable.setSize(800, 450);
        betTable.add(largeDecreaseChosenNumber,decreaseChosenNumber).setFillParent(true);
        betTable.add(increaseChosenNumber,largeIncreaseChosenNumber).setWidth(200);
        betTable.row();
        betTable.add(evens,odds);
        betTable.row();
        betTable.add(reds,blacks);
        betTable.row();
        betTable.add(first,second,third);
        root.add(betTable);
    }

    private void spin() {

    }

    private void payout() {

    }

}
