package com.shr4pnel.casino.scene;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.rafaskoberg.gdx.typinglabel.TypingAdapter;
import com.rafaskoberg.gdx.typinglabel.TypingLabel;
import com.shr4pnel.casino.Casino;
import com.shr4pnel.casino.blackjack.BlackjackGame;
import com.shr4pnel.casino.blackjack.BlackjackPlayer;
import com.shr4pnel.casino.builders.LabelBuilder;
import com.shr4pnel.casino.builders.TypingAdapterBuilder;
import com.shr4pnel.casino.input.BlackjackButtonManager;
import com.shr4pnel.casino.style.StyleManager;
import com.shr4pnel.casino.util.AsciiArt;
import com.shr4pnel.casino.util.TextureManager;

/**
 * Handles the UI for blackjack. logic should be kept to a minimum in here!
 * @author shrapnelnet
 * @since 0.1.0
 * @see ManagedButtonGame
 */
public class Slots extends ManagedButtonGame {
    private ButtonGroup<TextButton> textButtonGroup = new ButtonGroup<>();
    private TextButton hit, stand, split, doubleDown, increaseBet, decreaseBet, mediumIncreaseBet, mediumDecreaseBet, largeIncreaseBet, largeDecreaseBet, bet, restart;
    private Label chipCount;
    private TypingLabel phase;
    private Image cardPlain;
    private Table root, playerHandRoot, aiHandRoot, playerButtonRoot, chipTable, status;
    private TextureManager textureManager;
    private BlackjackGame game;
    private LabelBuilder labelBuilder = new LabelBuilder();
    private AsciiArt asciiArt = new AsciiArt();

    /**
     * Get the blackjack scene
     * @return A table, containing the scene
     */
    public Table get() {
        root = new Table(StyleManager.getSkin());
        playerHandRoot = new Table(StyleManager.getSkin());
        aiHandRoot = new Table(StyleManager.getSkin());
        playerButtonRoot = new Table(StyleManager.getSkin());
        chipTable = new Table(StyleManager.getSkin());
        status = new Table(StyleManager.getSkin());

        game = new BlackjackGame();
        phase = labelBuilder
            .start("Phase: {SLIDE}" + game.getPhaseAsString() + "{ENDSLIDE}")
            .build();

        status.add(phase).row();

        root.setDebug(true, true);
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
        restart = newTextButton("Restart");

        setPlayerButtonPaneByPhase();
        root.add(status).expand().top().left().row();
        root.add(aiHandRoot).top().row();
        root.add(playerHandRoot).bottom().row();
        root.add(playerButtonRoot).expand().bottom();
        return root;
    }

    /**
     * Set all relevant button fields to the correct values
     * @param t A variable amount of textbuttons
     */
    private void setAllButtons(TextButton... t) {
        playerButtonRoot.clear();

        if (t == null)
            return;

        playerButtonRoot.add().expandX();
        playerButtonRoot.add(t).right();

        textButtonGroup.clear();
        textButtonGroup.add(t);

        if (buttonGroupManager == null) {
            buttonGroupManager = new BlackjackButtonManager(t);
            buttonGroupManager.setListener(activeButton -> textButtonGroup.setChecked(activeButton.getName()));
        } else {
            buttonGroupManager.setMenuButtonGroup(t);
        }
    }

    private Image newBlankCard() {
        return textureManager.getImage("card/card.jpg");
    }

    /**
     * Create a new text button
     * @param content The content of the button
     * @return A correctly built text button
     */
    private TextButton newTextButton(String content) {
        TextButton t = new TextButton(content, StyleManager.getSkin(), "toggle");
        t.setName(content);
        return t;
    }

    /**
     * Get the game instance for accessing the state and logic of the game from outside of the stage
     * @return The current game instance
     */
    public BlackjackGame getGameInstance() {
        return game;
    }

    /**
     * Update the chip counter shown during the BET phase
     */
    public void updateChipDisplay() {
        chipCount.setText("Bet: " + game.getPlayer().getBet() + "/" + game.getPlayer().getChips());
    }

    /**
     * Set the correct button pane for the user, based on the phase of the game
     * @see BlackjackGame
     */
    private void setPlayerButtonPaneByPhase() {
        switch (game.getPhase()) {
            case BET -> bet();
            case DEAL -> deal();
            case PLAYER_TURN -> setAllButtons(hit, stand, doubleDown);
            case BUST -> bust();
        }
    }

    private void bust() {
        setAllButtons(restart);
        BlackjackPlayer p = game.getPlayer();
        p.incrementChips(-p.getBet());
    }

    private void bet() {
        setAllButtons(largeDecreaseBet, mediumDecreaseBet, decreaseBet, increaseBet, mediumIncreaseBet, largeIncreaseBet, bet);
        chipTable.clear();
        chipCount = new Label("Bet: 0/" + game.getPlayer().getChips(), StyleManager.getSkin());
        chipTable.add(chipCount).width(190);
        playerButtonRoot.add(chipTable).right().expand().pad(0, 150, 0, 0).row();
    }

    public Table redrawCards(BlackjackPlayer p) {
        // fetch user and ai hands as arrays (this is why logic needs to fire before UI code)
        if (!p.isPlayerControlled())
            return redrawCardsAi();

        TypingLabel l;
        Table t, container;
        container = new Table(StyleManager.getSkin());
        String[] cards = asciiArt.getCards(p);
        for (String card: cards) {
            l = labelBuilder
                .start(card)
                .build();
            t = new Table(StyleManager.getSkin());
            l.setFontScale(0.5f);
            t.add(l);
            container.add(t);
            container.add().width(15);
        }
        return container;
    }

    public Table redrawCardsAi() {
        boolean firstIter = true;
        Table t;
        Table aiContainer = new Table(StyleManager.getSkin());
        TypingLabel l;
        String[] aiCards = asciiArt.getCards(game.getAi());
        TypingAdapterBuilder typingAdapterBuilder = new TypingAdapterBuilder();

        for (String card: aiCards) {
            t = new Table(StyleManager.getSkin());

            // hide first drawn card from user
            if (firstIter) {
                TypingAdapter adapter = typingAdapterBuilder
                    .addEvent("bjdeal_complete")
                    .dontStopSound()
                    .build();

                l = labelBuilder
                    .start(asciiArt.getCardBack())
                    .build();

                l.addTypingListener(adapter);

                l.setFontScale(0.5f);
                t.add(l);
                aiContainer.add(t);
                aiContainer.add().width(15);
                firstIter = false;
                continue;
            }

            l = labelBuilder
                .start(card)
                .build();

            l.setFontScale(0.5f);

            t.add(l);
            aiContainer.add(t);
            aiContainer.add().width(15);
        }
        return aiContainer;
    }

    private void deal() {
        // blank all buttons in ui
        setAllButtons();

        // containers for player and AI hands
        Table playerContainer = redrawCards(game.getPlayer());
        Table aiContainer = redrawCards(game.getAi());

        playerHandRoot.add(playerContainer);
        aiHandRoot.add(aiContainer).padBottom(50);
    }

    public void updatePhase() {
        final String prettyPhase = game.getPhaseAsString();
        phase.setText("Phase: {SLIDE}" + prettyPhase + "{ENDSLIDE}");
        setPlayerButtonPaneByPhase();
    }

    public void hit() {
        boolean success = game.hit();
        if (!success) {
            game.setPhase(BlackjackGame.BlackjackPhase.BUST);
            updatePhase();
            setPlayerButtonPaneByPhase();
            return;
        }


        Table newCardDisplay = redrawCards(game.getPlayer());
        playerHandRoot.clear();
        playerHandRoot.add(newCardDisplay);
    }

    public void restart() {
        game = new BlackjackGame();
        playerHandRoot.clear();
        aiHandRoot.clear();
        playerButtonRoot.clear();
        setPlayerButtonPaneByPhase();
    }
}
