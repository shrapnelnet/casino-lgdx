package com.shr4pnel.casino.roulette;

import com.shr4pnel.casino.base.Game;
import com.shr4pnel.casino.base.Player;

import java.util.HashMap;
import java.util.Map;


public class RouletteGame extends Game {
    private Map<RouletteBetType, Integer> betTypeToMult = new HashMap<>();
    private RouletteBetType playerBetType;
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



    public RouletteGame() {
        betTypeToMult.put(RouletteBetType.SINGLE, 35);
        betTypeToMult.put(RouletteBetType.EVEN, 1);
        betTypeToMult.put(RouletteBetType.ODD, 1);
        betTypeToMult.put(RouletteBetType.BLACK, 1);
        betTypeToMult.put(RouletteBetType.RED, 1);
        betTypeToMult.put(RouletteBetType.FIRST, 1);
        betTypeToMult.put(RouletteBetType.SECOND, 1);
        betTypeToMult.put(RouletteBetType.THIRD, 1);
    }


    public enum RouletteBetType {
        SINGLE,
        EVEN,
        ODD,
        BLACK,
        RED,
        FIRST,
        SECOND,
        THIRD
    }

    @Override
    public RoulettePlayer getPlayer() {
        return (RoulettePlayer) player;
    }

    @Override
    public Player getAi() {
        return null;
    }

    public void setPlayerBetType(RouletteBetType b){
        playerBetType = b;
    }
}
