package com.shr4pnel.casino.roulette;

import java.util.concurrent.ThreadLocalRandom;

public class RouletteWheel {

    public int spin() {
        return ThreadLocalRandom.current().nextInt(1,37);
    }



}
