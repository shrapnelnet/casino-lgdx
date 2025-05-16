package com.shr4pnel.casino.roulette;

import com.shr4pnel.casino.base.Game;
import com.shr4pnel.casino.base.Player;
import com.shr4pnel.casino.roulette.RouletteGame;

import java.util.HashMap;
import java.util.Map;


public class RouletteGame extends Game {
    private Map<RouletteBetType, Integer> betTypeToMult = new HashMap<>();
    private Map<RoulettePhase, String> phaseToString = new HashMap<>();
    private Map<RoulettePhase,RoulettePhase> nextPhaseMap = new HashMap<>();
    private RoulettePhase phase = RoulettePhase.BET;

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

        //Roulette phase to string
        phaseToString.put(RoulettePhase.BET, "Bet");
        phaseToString.put(RoulettePhase.SPIN, "Spin");
        phaseToString.put(RoulettePhase.PAYOUT, "Payout");

        //next phase
        nextPhaseMap.put(RoulettePhase.BET, RoulettePhase.SPIN);
        nextPhaseMap.put(RoulettePhase.SPIN, RoulettePhase.PAYOUT);
        nextPhaseMap.put(RoulettePhase.PAYOUT, RoulettePhase.BET);

        player = new RoulettePlayer(true);
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

    public enum RoulettePhase {
        BET,
        SPIN,
        PAYOUT
    }

    @Override
    public RoulettePlayer getPlayer() {
        return player;
    }

    @Override
    public Player getAi() {
        return null;
    }

    public RoulettePhase getPhase() {
        return phase;
    }

    public String getPhaseAsString() {
        return phaseToString.get(phase);
    }

    public void setPhase(RoulettePhase phase) {
        this.phase = phase;
    }

    public void nextPhase() {
        phase = nextPhaseMap.get(phase);
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
