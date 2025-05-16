package com.shr4pnel.casino.input;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.shr4pnel.casino.Casino;
import com.shr4pnel.casino.base.Player;
import com.shr4pnel.casino.scene.SceneManager;
import com.shr4pnel.casino.scene.Slots;
import com.shr4pnel.casino.slots.SlotsGame;

import java.util.HashMap;
import java.util.Map;

public class SlotsButtonManager extends ButtonGroupManager {
    private final Map<String, Long> incrementStringToLong = new HashMap<>();

    public SlotsButtonManager(TextButton... t) {
        super(t);
        incrementStringToLong.put("---", -100L);
        incrementStringToLong.put("--", -10L);
        incrementStringToLong.put("-", -1L);
        incrementStringToLong.put("+", 1L);
        incrementStringToLong.put("++", 10L);
        incrementStringToLong.put("+++", 100L);
    }

    private Slots getScene() {
        Casino c = Casino.getInstance();
        return (Slots) c.getGameInstance(SceneManager.getActiveScene());
    }

    private SlotsGame getGame() {
        Slots scene = getScene();
        return (SlotsGame) scene.getGameInstance();
    }

    /**
     * Increment the displayed bet
     * @param name The name of the button, assigned when they are created
     * @return True, when incrementing is complete
     * @see SlotsGame
     */
    private boolean incrementBet(String name) {
        Slots scene = getScene();
        SlotsGame g = getGame();
        Player p = g.getPlayer();
        p.incrementBet(incrementStringToLong.get(name));
        return true;
    }

    /**
     * Locks in bet, and advances Blackjack phase
     * @return True if bet is larger than zero, otherwise false
     */



    private boolean pull() {
       // SlotsGame.spin();
        return true;
    }





    @Override
    public boolean enter() {
        /*
        I prevent enter from being pressed on a button that is no longer visible (Bet)
        if i am not here i can skip the dealing phase and cause all sorts of nasty NPEs...
         */

        boolean handled = super.enter();
        if (handled)
            return true;

        TextButton active = getActiveButton();
        return switch (active.getName()) {
            case "---", "--", "-", "+", "++", "+++" -> incrementBet(active.getName());

            case "Pull" -> pull();

            default -> false;
        };
    }
}
