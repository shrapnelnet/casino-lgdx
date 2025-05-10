package com.shr4pnel.casino.blackjack;

import com.shr4pnel.casino.base.Deck;
import com.shr4pnel.casino.base.Game;

import java.util.HashMap;
import java.util.Map;

public class BlackjackGame extends Game {
    protected BlackjackPlayer player = new BlackjackPlayer(true);
    protected BlackjackPlayer ai = new BlackjackPlayer(false);
    private Deck deck = new BlackjackDeck();
    private BlackjackPhase phase = BlackjackPhase.BET;
    private Map<BlackjackPhase, String> phaseToString = new HashMap<>();
    private Map<BlackjackPhase, BlackjackPhase> nextPhaseMap = new HashMap<>();

    public BlackjackGame() {
        phaseToString.put(BlackjackPhase.BET, "Bet");
        phaseToString.put(BlackjackPhase.DEAL, "Deal");
        phaseToString.put(BlackjackPhase.PLAYER_TURN, "Player's turn");
        phaseToString.put(BlackjackPhase.DEALER_TURN, "Dealer's turn");
        phaseToString.put(BlackjackPhase.SHOWDOWN, "Showdown!");

        nextPhaseMap.put(BlackjackPhase.BET, BlackjackPhase.DEAL);
        nextPhaseMap.put(BlackjackPhase.DEAL, BlackjackPhase.PLAYER_TURN);
        nextPhaseMap.put(BlackjackPhase.PLAYER_TURN, BlackjackPhase.DEALER_TURN);
        nextPhaseMap.put(BlackjackPhase.DEALER_TURN, BlackjackPhase.SHOWDOWN);
        nextPhaseMap.put(BlackjackPhase.SHOWDOWN, BlackjackPhase.BET);
    }

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

    private void hit(BlackjackPlayer p, int n) {
        for (int i = 0; i < n; ++i)
            hit(ai);
    }

    /**
     * Draws a card to the player's deck
     */
    public void hit() {
        player.add(deck.drawCard());
    }

    public void hit(int n) {
        for (int i = 0; i < n; ++i) {
            hit();
        }
    }

    public BlackjackPhase getPhase() {
        return phase;
    }

    public String getPhaseAsString() {
        return phaseToString.get(phase);
    }

    public void setPhase(BlackjackPhase phase) {
        this.phase = phase;
    }

    public void nextPhase() {
        phase = nextPhaseMap.get(phase);
    }

    public void deal() {
        hit(2);
        hit(ai, 2);
    }
}
