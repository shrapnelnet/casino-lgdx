package com.shr4pnel.casino.loot;

public class Loot {
    private String name;
    private int weight; // higher = more common

    public Loot(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Loot{name='" + name + "', weight=" + weight + '}';
    }
}

