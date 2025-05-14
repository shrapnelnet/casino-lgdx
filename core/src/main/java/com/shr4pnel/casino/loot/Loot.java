package com.shr4pnel.casino.loot;

public class Loot {
    private String name;
    private int value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Loot(final String name, final int value) {
        this.name = name;
        this.value = value;
    }


    @Override
    public String toString() {
        return name;
    }
}
