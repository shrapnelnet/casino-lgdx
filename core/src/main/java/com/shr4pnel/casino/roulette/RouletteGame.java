package com.shr4pnel.casino.roulette;

import com.shr4pnel.casino.base.Game;
import com.shr4pnel.casino.base.Player;


public class RouletteGame extends Game {
    @Override
    public RoulettePlayer getPlayer() {
        return (RoulettePlayer) player;
    }

    @Override
    public Player getAi() {
        return null;
    }
}
