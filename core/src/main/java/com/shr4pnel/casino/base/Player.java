package com.shr4pnel.casino.base;

import java.util.ArrayList;
import java.util.List;

/**
 * The base class for all players
 * @author shrapnelnet
 * @since 0.1.0
 * @see com.shr4pnel.casino.blackjack.BlackjackPlayer
 */
public abstract class Player {
    protected List<Card> hand = new ArrayList<>();
    protected boolean playerControlled;
    protected Long chips = 100L;
    protected Long bet = 0L;

    public Player(boolean playerControlled) {
        this.playerControlled = playerControlled;
    }

    /**
     * @return Amount of chips the player has
     */
    public Long getChips() {
        return chips;
    }

    public void setChips(Long l) {
        chips = l;
    }

    /**
     * Add a number to the existing chip count
     * @param l The amount to add, can be negative
     */
    public void incrementChips(Long l) {
        chips += l;
    }

    /**
     * @return The players current bet
     */
    public Long getBet() {
        return bet;
    }

    /**
     *
     * @param l
     */
    public void incrementBet(Long l) {
        if ((bet + l) < 0) {
            bet = 0L;
            return;
        }

        if ((bet + l) > chips) {
            bet = chips;
            return;
        }

        bet += l;
    }

    /**
     * Add a card to the players hand
     * @param card The card to add
     */
    public void add(Card card) {
        hand.add(card);
    }

    /**
     * Add several cards to the players hand
     * @param cards A list of cards
     */
    public void add(List<Card> cards) {
        hand.addAll(cards);
    }
}
