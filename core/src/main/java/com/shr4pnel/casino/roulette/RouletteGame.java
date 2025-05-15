package com.shr4pnel.casino.roulette;

import com.shr4pnel.casino.base.Game;
import com.shr4pnel.casino.base.Player;
import com.shr4pnel.casino.roulette.RouletteTable;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Iterator;


public class RouletteGame extends Game {
    private Map<RouletteBetType, Integer> betTypeToMult = new HashMap<>();
    private boolean containsEven;
    private boolean containsOdd;
    private boolean containsRed;
    private boolean containsBlack;
    private boolean containsFirst;
    private boolean containsSecond;
    private boolean containsThird;
    public RoulettePlayer player;
    public RouletteTable rouletteTable = new RouletteTable();

    public RouletteGame() {
        betTypeToMult.put(RouletteBetType.SINGLE, 35);
        betTypeToMult.put(RouletteBetType.EVEN, 1);
        betTypeToMult.put(RouletteBetType.ODD, 1);
        betTypeToMult.put(RouletteBetType.BLACK, 1);
        betTypeToMult.put(RouletteBetType.RED, 1);
        betTypeToMult.put(RouletteBetType.FIRST, 3);
        betTypeToMult.put(RouletteBetType.SECOND, 3);
        betTypeToMult.put(RouletteBetType.THIRD, 3);
    }


    public enum RouletteBetType {
        SINGLE,
        EVEN,
        ODD,
        BLACK,
        RED,
        FIRST,
        SECOND,
        THIRD,
        NULL
    }

    @Override
    public RoulettePlayer getPlayer() {
        return (RoulettePlayer) player;
    }

    @Override
    public Player getAi() {
        return null;
    }

    public boolean isRed() {
        int[] reds = rouletteTable.getReds();
        Iterator<Integer> redsIt = reds.iterator();
        while (redsIt.hasNext()) {
            System.out.println(redsIt.next());
        }


        return false;
    }
}
