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

    public boolean isEven(int b) {
        return b % 2 == 0;
    }

    public boolean isOdd(int b) {
        return b % 2 == 1;
    }

    public boolean isRed(int b) {
        int[] reds = rouletteTable.getReds();
        for (int i : reds)
            if (b == reds[i])
                return true;
        return false;
    }

    public boolean isBlack(int b) {
        int[] blacks = rouletteTable.getBlacks();
        for (int i : blacks)
            if (b == blacks[i])
                return true;
        return false;
    }

    public boolean isFirst(int b) {
        int[] first = rouletteTable.getFirst();
        for (int i : first)
            if (b == first[i])
                return true;
        return false;
    }

    public boolean isSecond(int b) {
        int[] second = rouletteTable.getSecond();
        for (int i : second)
            if (b == second[i])
                return true;
        return false;
    }

    public boolean isThird(int b) {
        int[] third = rouletteTable.getSecond();
        for (int i : third)
            if (b == third[i])
                return true;
        return false;
    }




}
