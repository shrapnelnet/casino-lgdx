package com.shr4pnel.casino.roulette;

import com.shr4pnel.casino.base.Game;
import com.shr4pnel.casino.base.Player;
import com.shr4pnel.casino.roulette.RouletteGame;
import com.shr4pnel.casino.roulette.RouletteWheel;

import java.util.*;


public class RouletteGame extends Game {
    private Map<RouletteBetType, Integer> betTypeToMult = new HashMap<>();
    private Map<RoulettePhase, String> phaseToString = new HashMap<>();
    private Map<RoulettePhase,RoulettePhase> nextPhaseMap = new HashMap<>();
    private Map<RouletteBetType, List<Integer>> betTypeMembers = new HashMap<>();
    private RoulettePhase phase = RoulettePhase.BET;

    private boolean containsEven;
    private boolean containsOdd;
    private boolean containsRed;
    private boolean containsBlack;
    private boolean containsFirst;
    private boolean containsSecond;
    private boolean containsThird;

    public int getMult() {
        return mult;
    }

    private int mult;
    public RoulettePlayer player;
    public RouletteTable rouletteTable = new RouletteTable();
    public RouletteWheel rouletteWheel = new RouletteWheel();



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

        betTypeMembers.put(RouletteBetType.RED, Arrays.asList(1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36));
        betTypeMembers.put(RouletteBetType.BLACK, Arrays.asList(2,4,6,8,10,11,13,15,17,20,22,24,26,28,29,31,33,35));
        betTypeMembers.put(RouletteBetType.FIRST, Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12));
        betTypeMembers.put(RouletteBetType.SECOND, Arrays.asList(13,14,15,16,17,18,19,20,21,22,23,24));
        betTypeMembers.put(RouletteBetType.THIRD, Arrays.asList(25,26,27,28,29,30,31,32,33,34,35,36));

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

    public float betTypeToMult(int chosenNumber, int spinResult){
        mult = 0;
        if (betTypeMembers.get(RouletteBetType.RED).contains(chosenNumber)){
            mult++;
        }

        if (betTypeMembers.get(RouletteBetType.BLACK).contains(chosenNumber)){
            mult++;
        }

        if (betTypeMembers.get(RouletteBetType.EVEN).contains(chosenNumber)){
            mult += 2;
        }

        if (betTypeMembers.get(RouletteBetType.ODD).contains(chosenNumber)){
            mult += 2;
        }

        if (betTypeMembers.get(RouletteBetType.FIRST).contains(chosenNumber)){
            mult += 3;
        }

        if (betTypeMembers.get(RouletteBetType.SECOND).contains(chosenNumber)){
            mult += 3;
        }

        if (betTypeMembers.get(RouletteBetType.THIRD).contains(chosenNumber)){
            mult += 3;
        }

        if (spinResult == chosenNumber){
            mult += 35;
        }

        return mult + 1;
    }

    //public int Spin(){
    //    int spinResult = rouletteWheel.spin();
    //    if(spinResult == )
//
    //}

}
