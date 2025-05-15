package com.shr4pnel.casino.slots;

import com.shr4pnel.casino.util.AsciiArt;
import com.shr4pnel.casino.util.AsciiArt.Fruits;

public class SlotsReal {
    private AsciiArt asciiArt = new AsciiArt();
    private Fruits[] real;

    public Fruits[] getReal() {
        Fruits current;
        for(int i = 0;i<10;i++){
        current = asciiArt.getRanFruit();
        this.real[i] = current;
        }
        return this.real;

    }

}