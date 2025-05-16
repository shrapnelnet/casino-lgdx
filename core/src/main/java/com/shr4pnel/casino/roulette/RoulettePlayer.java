package com.shr4pnel.casino.roulette;

import com.shr4pnel.casino.base.Player;
import com.shr4pnel.casino.roulette.RouletteGame.RouletteBetType;

public class RoulettePlayer extends Player {

    private RouletteBetType playerBetType;
    private int rouletteNumberChosen = 0;
    private int playerBet = 0;

    public int getRouletteNumberChosen() {
        return rouletteNumberChosen;
    }

    public void setRouletteNumberChosen(int rouletteNumberChosen) {
        if (rouletteNumberChosen >= 36 || rouletteNumberChosen <= 0)
            return;
        this.rouletteNumberChosen = rouletteNumberChosen;

    }

    public void setPlayerBetType(RouletteGame.RouletteBetType b){
        playerBetType = b;
    }

    public RouletteBetType getPlayerBetType() {
        return playerBetType;
    }

    public void incrementRouletteNumber(short amount) {
        int newAmount = rouletteNumberChosen + amount;
        setRouletteNumberChosen(newAmount);
    }

    public void decrementRouletteNumber(short amount) {
        int newAmount = rouletteNumberChosen - amount;
        setRouletteNumberChosen(newAmount);
    }

    public void setPlayerBet(int playerBet) {
        this.playerBet = playerBet;
    }

    public RoulettePlayer(boolean playerControlled) {
        super(playerControlled);
    }
}
