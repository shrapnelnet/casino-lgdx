package com.shr4pnel.casino.base;

/**
 * Base class for games
 * Used to make accessing players and AIs from games easy and implementation independent
 */
public abstract class Game {
    protected Player player;
    protected Player ai;

    public abstract Player getPlayer();
    public abstract Player getAi();
}
