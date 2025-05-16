package com.shr4pnel.casino.slots;

import com.shr4pnel.casino.util.AsciiArt;
import com.shr4pnel.casino.util.AsciiArt.Fruits;

public class SlotsReal {
    private AsciiArt asciiArt = new AsciiArt();

    public Fruits[] getReal(int size) {
        Fruits[] real = new Fruits[size];
        Fruits current;
        for (int i = 0; i < size; i++) {
            current = asciiArt.getRanFruit();
            real[i] = current;
        }
        return real;
    }

}