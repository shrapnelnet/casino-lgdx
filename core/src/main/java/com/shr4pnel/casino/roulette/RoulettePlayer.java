package com.shr4pnel.casino.roulette;

import com.shr4pnel.casino.base.Player;

public class RoulettePlayer extends Player {

    private RouletteGame.RouletteBetType playerBetType;
    private int playerBet = 0;

    public int getPlayerBet() {
        return playerBet;
    }

    public void setPlayerBet(int playerBet) {
        if (playerBet >= 36 || playerBet <= 0)
            return;
        this.playerBet = playerBet;

    }

    public void incrementPlayerBet(short amount) {
        int newAmount = playerBet + amount;
        setPlayerBet(newAmount);
    }

    public void decrementPlayerBet(short amount) {
        int newAmount = playerBet - amount;
        setPlayerBet(newAmount);
    }

    public void setPlayerBetType(RouletteGame.RouletteBetType b){
        playerBetType = b;
    }



    public RoulettePlayer(boolean playerControlled) {
        super(playerControlled);
    }
}
