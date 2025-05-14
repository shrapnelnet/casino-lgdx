package com.shr4pnel.casino.roulette;

import com.shr4pnel.casino.base.Game;
import com.shr4pnel.casino.base.Player;
import com.shr4pnel.casino.blackjack.BlackjackGame;

import java.util.HashMap;
import java.util.Map;


public class RouletteGame extends Game {
    private Map<RouletteGame.rouletteBetType, Integer> betTypeToMult = new HashMap<>();

    public RouletteGame() {
        betTypeToMult.put(rouletteBetType.SINGLE, 35);
        betTypeToMult.put(RouletteGame.rouletteBetType.EVEN, 1);
        betTypeToMult.put(RouletteGame.rouletteBetType.ODD, 1);
        betTypeToMult.put(RouletteGame.rouletteBetType.BLACK, 1);
        betTypeToMult.put(RouletteGame.rouletteBetType.RED, 1);
    }


    public enum rouletteBetType {
        SINGLE,
        EVEN,
        ODD,
        BLACK,
        RED
    }

    @Override
    public RoulettePlayer getPlayer() {
        return (RoulettePlayer) player;
    }

    @Override
    public Player getAi() {
        return null;
    }
}
