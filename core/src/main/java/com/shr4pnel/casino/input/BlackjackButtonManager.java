package com.shr4pnel.casino.input;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.shr4pnel.casino.Casino;
import com.shr4pnel.casino.base.Player;
import com.shr4pnel.casino.blackjack.BlackjackGame;
import com.shr4pnel.casino.scene.Blackjack;
import com.shr4pnel.casino.scene.SceneManager;

import java.util.HashMap;
import java.util.Map;

public class BlackjackButtonManager extends ButtonGroupManager {
    private final Map<String, Long> incrementStringToLong = new HashMap<>();

    public BlackjackButtonManager(TextButton... t) {
        super(t);
        incrementStringToLong.put("---", -100L);
        incrementStringToLong.put("--", -10L);
        incrementStringToLong.put("-", -1L);
        incrementStringToLong.put("+", 1L);
        incrementStringToLong.put("++", 10L);
        incrementStringToLong.put("+++", 100L);
    }

    private Blackjack getScene() {
        Casino c = Casino.getInstance();
        return (Blackjack) c.getGameInstance(SceneManager.getActiveScene());
    }

    private BlackjackGame getGame() {
        Blackjack scene = getScene();
        return scene.getGameInstance();
    }

    /**
     * Increment the displayed bet
     * @param name The name of the button, assigned when they are created
     * @return True, when incrementing is complete
     * @see Blackjack
     */
    private boolean incrementBet(String name) {
        Blackjack scene = getScene();
        BlackjackGame g = getGame();
        Player p = g.getPlayer();
        p.incrementBet(incrementStringToLong.get(name));
        scene.updateChipDisplay();
        return true;
    }

    /**
     * Locks in bet, and advances Blackjack phase
     * @return True if bet is larger than zero, otherwise false
     */
    private boolean bet() {
        BlackjackGame g = getGame();
        Blackjack scene = getScene();

        if (g.getPlayer().getBet() == 0) {
            return false;
        }

        // this order is important, logic has to happen before UI updates or things will unexpectedly be empty
        g.nextPhase();
        g.deal();
        scene.updatePhase();
        return true;
    }

    private boolean hit() {
        getScene().hit();
        return true;
    }

    @Override
    public boolean enter() {
        /*
        I prevent enter from being pressed on a button that is no longer visible (Bet)
        if i am not here i can skip the dealing phase and cause all sorts of nasty NPEs...
         */
        if (getGame().getPhase() == BlackjackGame.BlackjackPhase.DEAL)
            return false;

        boolean handled = super.enter();
        if (handled)
            return true;

        TextButton active = getActiveButton();
        return switch (active.getName()) {
            case "---", "--", "-", "+", "++", "+++" -> incrementBet(active.getName());
            case "Bet" -> bet();
            case "Hit" -> hit();
            default -> false;
        };
    }
}
