package com.shr4pnel.casino.blackjack;

import com.shr4pnel.casino.base.Deck;
import com.shr4pnel.casino.base.Game;

public class BlackjackGame extends Game {
    protected BlackjackPlayer player = new BlackjackPlayer(true);
    protected BlackjackPlayer ai = new BlackjackPlayer(false);
    private Deck deck = new BlackjackDeck();
    private BlackjackPhase phase = BlackjackPhase.BET;

    public enum BlackjackPhase {
        BET,
        DEAL,
        PLAYER_TURN,
        DEALER_TURN,
        SHOWDOWN
    }

    @Override
    public BlackjackPlayer getPlayer() {
        return player;
    }

    @Override
    public BlackjackPlayer getAi() {
        return ai;
    }

    private void hit(BlackjackPlayer p) {
        p.add(deck.drawCard());
    }

    /**
     * Draws a card to the player's deck
     */
    public void hit() {
        player.add(deck.drawCard());
    }

    public BlackjackPhase getPhase() {
        return phase;
    }
}
