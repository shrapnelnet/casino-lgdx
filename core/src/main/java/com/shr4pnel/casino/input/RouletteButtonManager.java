package com.shr4pnel.casino.input;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.shr4pnel.casino.Casino;
import com.shr4pnel.casino.base.Player;
import com.shr4pnel.casino.roulette.RouletteGame;
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
        getGame().setPlayerBetType(RouletteGame.RouletteBetType.ODD);
    }

    public void even(){
        getGame().setPlayerBetType(RouletteGame.RouletteBetType.EVEN);
    }

    public void red(){
        getGame().setPlayerBetType(RouletteGame.RouletteBetType.RED);
    }

    public void black(){
        getGame().setPlayerBetType(RouletteGame.RouletteBetType.BLACK);
    }

    public void first(){
        getGame().setPlayerBetType(RouletteGame.RouletteBetType.FIRST);
    }

    public void second(){
        getGame().setPlayerBetType(RouletteGame.RouletteBetType.SECOND);
    }

    public void third(){
        getGame().setPlayerBetType(RouletteGame.RouletteBetType.THIRD);
    }

    public void increaseChosenNumber(){
        getGame().incrementPlayerBet((short) 1);
    }

    public void decreaseChosenNumber(){
        getGame().decrementPlayerBet((short) 1);
    }

    public void largeDecreaseChosenNumber(){
        getGame().decrementPlayerBet((short) 5);
    }

    public void largeIncreaseChosenNumber(){
        getGame().incrementPlayerBet((short) 5);
    }


}
