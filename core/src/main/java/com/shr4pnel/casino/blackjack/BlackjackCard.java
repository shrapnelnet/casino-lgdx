package com.shr4pnel.casino.blackjack;

import com.shr4pnel.casino.base.Card;

public class BlackjackCard extends Card {
    public BlackjackCard(String suit, int cardValue, String cardType) {
        super(suit, cardValue, cardType);
    }

    public boolean isAce() {
        return cardType.equals("ace");
    }
}
