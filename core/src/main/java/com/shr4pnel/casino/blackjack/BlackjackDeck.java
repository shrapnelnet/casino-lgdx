package com.shr4pnel.casino.blackjack;

import com.shr4pnel.casino.base.Card;
import com.shr4pnel.casino.base.Deck;

public class BlackjackDeck extends Deck {
    public BlackjackDeck() {
        makeDeck();
        shuffle();
    }

    private void makeDeck() {
        for (int i = 0; i < 52; ++i) {
            // this division means that we move up one index per 13 iterations
            int currentSuitIndex = Math.floorDiv(i, 13);
            // this index goes up each iteration, then resets to 0 every 13 iterations
            int currentTypeValueIndex = i % 13;
            Card card = new BlackjackCard(suitList[currentSuitIndex], cardValueList[currentTypeValueIndex], cardTypeList[currentTypeValueIndex]);
            cards.push(card);
        }
    }
}
