package com.shr4pnel.casino.base;

public abstract class Game {
    protected Player player;
    protected Player ai;

    public abstract Player getPlayer();
    public abstract Player getAi();
}
