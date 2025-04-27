package com.shr4pnel.casino.base;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
    protected List<Card> hand = new ArrayList<>();
    protected boolean playerControlled;

    public Player(boolean playerControlled) {
        this.playerControlled = playerControlled;
    }

    public void add(Card card) {
        hand.add(card);
    }

    public void add(List<Card> cards) {
        hand.addAll(cards);
    }
}
