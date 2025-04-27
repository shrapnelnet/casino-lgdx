package com.shr4pnel.casino.blackjack;

import com.shr4pnel.casino.base.Deck;
import com.shr4pnel.casino.base.Player;

public class BlackjackGame {
    private BlackjackPlayer player = new BlackjackPlayer(true);
    private Player ai = new BlackjackPlayer(false);
    private Deck deck = new BlackjackDeck();

    public BlackjackPlayer getPlayer() {
        return player;
    }

    public Player getAi() {
        return ai;
    }

    private void hit(Player p) {
        p.add(deck.drawCard());
    }

    /**
     * Draws a card to the player's deck
     */
    public void hit() {
        player.add(deck.drawCard());
    }
}
