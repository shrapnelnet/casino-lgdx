package com.shr4pnel.casino.roulette;

import com.shr4pnel.casino.base.Game;
import com.shr4pnel.casino.base.Player;


import java.util.*;
import java.util.HashMap;

public class RouletteGame extends Game {
    private Map<RoulettePhase, String> phaseToString = new HashMap<>();
    private Map<RoulettePhase,RoulettePhase> nextPhaseMap = new HashMap<>();
    private Map<RouletteBetType, List<Integer>> betTypeMembers = new HashMap<>();
    private Map<RouletteBetType,Integer> betTypeToMult = new HashMap<>();
    private RoulettePhase phase = RoulettePhase.BET;

    private int chosenNumber;
    private int mult;

    public RoulettePlayer player;
    public RouletteWheel rouletteWheel = new RouletteWheel();

    public RouletteGame() {

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

        betTypeToMult.put(RouletteBetType.SINGLE,35);
        betTypeToMult.put(RouletteBetType.EVEN,1);
        betTypeToMult.put(RouletteBetType.ODD,1);
        betTypeToMult.put(RouletteBetType.RED,2);
        betTypeToMult.put(RouletteBetType.BLACK,2);
        betTypeToMult.put(RouletteBetType.FIRST,3);
        betTypeToMult.put(RouletteBetType.SECOND,3);
        betTypeToMult.put(RouletteBetType.THIRD,3);

        player = new RoulettePlayer(true);
    }

    public int getChosenNumber() {
        return chosenNumber;
    }

    public void setChosenNumber(int chosenNumber) {
        this.chosenNumber = chosenNumber;
    }

    public int getMult() {
        return mult;
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

    public long spin(){
        int spinResult = rouletteWheel.spin();
        RoulettePlayer p = getPlayer();
        RouletteBetType playerBetType = p.getPlayerBetType();
        int mult = betTypeToMult.get(playerBetType);
        int rouletteNumberChosen = p.getRouletteNumberChosen();

        if (betTypeMembers.get(playerBetType).contains(chosenNumber)){
            return (p.getBet() * mult);
        }else{
            return p.getBet() * -1;
        }

    }

}
