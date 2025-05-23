package com.shr4pnel.casino.input;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.shr4pnel.casino.Casino;
import com.shr4pnel.casino.base.Player;
import com.shr4pnel.casino.roulette.RouletteGame;
import com.shr4pnel.casino.roulette.RoulettePlayer;
import com.shr4pnel.casino.scene.Roulette;
import com.shr4pnel.casino.scene.SceneManager;

public class RouletteButtonManager extends ButtonGroupManager {

    public RouletteButtonManager(TextButton... t) {
        super(t);
    }

    private Roulette getScene() {
        Casino c = Casino.getInstance();
        return (Roulette) c.getGameInstance(SceneManager.getActiveScene());
    }

    private RouletteGame getGame() {
        Roulette scene = getScene();
        return scene.getGameInstance();
    }

    private boolean incrementBet(String name) {
        Roulette scene = getScene();
        RouletteGame g = getGame();
        Player p = g.getPlayer();
        p.incrementBet(incrementStringToLong.get(name));
        scene.updateChipDisplay();
        return true;
    }

    public void odd(){
        RouletteGame g = getGame();
        RoulettePlayer p = g.getPlayer();
        p.setPlayerBetType(RouletteGame.RouletteBetType.ODD);
    }

    public void even(){
        RouletteGame g = getGame();
        RoulettePlayer p = g.getPlayer();
        p.setPlayerBetType(RouletteGame.RouletteBetType.EVEN);
    }

    public void red(){
        RouletteGame g = getGame();
        RoulettePlayer p = g.getPlayer();
        p.setPlayerBetType(RouletteGame.RouletteBetType.RED);
    }

    public void black(){
        RouletteGame g = getGame();
        RoulettePlayer p = g.getPlayer();
        p.setPlayerBetType(RouletteGame.RouletteBetType.BLACK);
    }

    public void first(){
        RouletteGame g = getGame();
        RoulettePlayer p = g.getPlayer();
        p.setPlayerBetType(RouletteGame.RouletteBetType.FIRST);
    }

    public void second(){
        RouletteGame g = getGame();
        RoulettePlayer p = g.getPlayer();
        p.setPlayerBetType(RouletteGame.RouletteBetType.SECOND);
    }

    public void third(){
        RouletteGame g = getGame();
        RoulettePlayer p = g.getPlayer();
        p.setPlayerBetType(RouletteGame.RouletteBetType.THIRD);
    }

    public void increaseChosenNumber(){
        RouletteGame g = getGame();
        RoulettePlayer p = g.getPlayer();
        p.incrementRouletteNumber((short) 1);
    }

    public void decreaseChosenNumber(){
        RouletteGame g = getGame();
        RoulettePlayer p = g.getPlayer();
        p.decrementRouletteNumber((short) 1);
    }

    public void largeDecreaseChosenNumber(){
        RouletteGame g = getGame();
        RoulettePlayer p = g.getPlayer();
        p.decrementRouletteNumber((short) 5);
    }

    public void largeIncreaseChosenNumber(){
        RouletteGame g = getGame();
        RoulettePlayer p = g.getPlayer();
        p.incrementRouletteNumber((short) 5);
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
            default -> false;
        };
    }


}
