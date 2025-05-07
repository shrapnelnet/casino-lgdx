package com.shr4pnel.casino.base;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
    protected List<Card> hand = new ArrayList<>();
    protected boolean playerControlled;
    protected Long chips = 100L;
    protected Long bet = 0L;

    public Player(boolean playerControlled) {
        this.playerControlled = playerControlled;
    }

    public Long getChips() {
        return chips;
    }

    public void setChips(Long l) {
        chips = l;
    }

    public void incrementChips(Long l) {
        chips += l;
    }

    public Long getBet() {
        return bet;
    }

    public void incrementBet(Long l) {
        if ((bet + l) > chips || (bet + l) < 0)
            return;

        bet += l;
    }

    public void add(Card card) {
        hand.add(card);
    }

    public void add(List<Card> cards) {
        hand.addAll(cards);
    }
}
