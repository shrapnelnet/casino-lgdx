package com.shr4pnel.casino.input;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.shr4pnel.casino.Casino;
import com.shr4pnel.casino.base.Game;
import com.shr4pnel.casino.base.Player;
import com.shr4pnel.casino.scene.Blackjack;
import com.shr4pnel.casino.scene.ManagedButtonGame;
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

    /**
     * Increment the displayed bet
     * @param name The name of the button, assigned when they are created
     * @return True, when incrementing is complete
     * @see Blackjack
     */
    private boolean incrementBet(String name) {
        Casino c = Casino.getInstance();
        ManagedButtonGame scene = c.getGameInstance(SceneManager.getActiveScene());
        Game g = scene.getGameInstance();
        Player p = g.getPlayer();
        p.incrementBet(incrementStringToLong.get(name));
        ((Blackjack) scene).updateChipDisplay();
        return true;
    }

    @Override
    public boolean enter() {
        TextButton active = getActiveButton();
        return switch (active.getName()) {
            case "---", "--", "-", "+", "++", "+++" -> incrementBet(active.getName());
            default -> false;
        };
    }
}
