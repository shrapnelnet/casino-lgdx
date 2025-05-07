package com.shr4pnel.casino.scene;

import com.badlogic.gdx.scenes.scene2d.ui.*;

import com.shr4pnel.casino.Casino;
import com.shr4pnel.casino.blackjack.BlackjackGame;
import com.shr4pnel.casino.builders.LabelBuilder;
import com.shr4pnel.casino.style.StyleManager;
import com.shr4pnel.casino.util.ButtonGroupManager;
import com.shr4pnel.casino.util.TextureManager;

public class Blackjack extends ManagedButtonGame {
    private ButtonGroup<TextButton> textButtonGroup = new ButtonGroup<>();
    private TextButton hit, stand, split, doubleDown, increaseBet, decreaseBet, mediumIncreaseBet, mediumDecreaseBet, largeIncreaseBet, largeDecreaseBet, bet;
    private Label chipCount, phase;
    private Image cardPlain;
    private Table root, userHandRoot, aiHandRoot, playerButtonRoot, chipTable;
    private TextureManager textureManager;
    private BlackjackGame game;
    private LabelBuilder labelBuilder;

    public Table get() {
        root = new Table(StyleManager.getSkin());
        userHandRoot = new Table(StyleManager.getSkin());
        aiHandRoot = new Table(StyleManager.getSkin());
        playerButtonRoot = new Table(StyleManager.getSkin());
        chipTable = new Table(StyleManager.getSkin());

        game = new BlackjackGame();
        chipCount = new Label("Bet: 0/" + game.getPlayer().getChips(), StyleManager.getSkin());

        root.setDebug(false, true);
        root.setSize(800, 450);
        root.background("window");

        textureManager = Casino.getInstance().getTextureManagerInstance();
        cardPlain = textureManager.getImage("card/card.jpg");

        hit = newTextButton("Hit");
        stand = newTextButton("Stand");
        split = newTextButton("Split");
        doubleDown = newTextButton("Double Down");
        increaseBet = newTextButton("+");
        decreaseBet = newTextButton("-");
        mediumIncreaseBet = newTextButton("++");
        mediumDecreaseBet = newTextButton("--");
        largeDecreaseBet = newTextButton("---");
        largeIncreaseBet = newTextButton("+++");
        bet = newTextButton("Bet");

        setPlayerButtonPaneByPhase();
        chipTable.add(chipCount).width(190);
        playerButtonRoot.add(chipTable).right().expand().pad(0, 150, 0, 0).row();
        root.add(playerButtonRoot).expand().bottom();
        return root;
    }

    private void setAllButtons(TextButton... t) {
        playerButtonRoot.clear();
        playerButtonRoot.add().expandX();
        playerButtonRoot.add(t).right();

        textButtonGroup.clear();
        textButtonGroup.add(t);

        if (buttonGroupManager == null) {
            buttonGroupManager = new ButtonGroupManager(t);
            buttonGroupManager.setListener(activeButton -> textButtonGroup.setChecked(activeButton.getName()));
        } else {
            buttonGroupManager.setMenuButtonGroup(t);
        }
    }

    private Image newBlankCard() {
        return textureManager.getImage("card/card.jpg");
    }

    private TextButton newTextButton(String content) {
        TextButton t = new TextButton(content, StyleManager.getSkin(), "toggle");
        t.setName(content);
        return t;
    }

    public BlackjackGame getGameInstance() {
        return game;
    }

    public void updateChipDisplay() {
        chipCount.setText("Bet: " + game.getPlayer().getBet() + "/" + game.getPlayer().getChips());
    }

    public void setPlayerButtonPaneByPhase() {
        switch (game.getPhase()) {
            case BET -> setAllButtons(largeDecreaseBet, mediumDecreaseBet, decreaseBet, increaseBet, mediumIncreaseBet, largeIncreaseBet, bet);
        }
    }

    public void alert(String msg) {

    }
}
