package com.shr4pnel.casino.roulette;

public class RouletteTable {
    private final int[] reds = {1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36};
    private final int[] blacks = {2,4,6,8,10,11,13,15,17,20,22,24,26,28,29,31,33,35};
    private final int[] first = {1,2,3,4,5,6,7,8,9,10,11,12};
    private final int[] second = {13,14,15,16,17,18,19,20,21,22,23,24};
    private final int[] third = {25,26,27,28,29,30,31,32,33,34,35,36};

    public int[] getReds() {
        return reds;
    }

    public int[] getBlacks() {
        return blacks;
    }

    public int[] getFirst() {
        return first;
    }

    public int[] getSecond() {
        return second;
    }

    public int[] getThird() {
        return third;
    }
}
