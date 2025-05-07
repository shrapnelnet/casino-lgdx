package com.shr4pnel.casino.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * <p>Base class for all Decks of cards</p>
 * <p>Constructors should be created on a per implementation basis</p>
 * @author shrapnelnet
 * @since 0.1.0
 * @see com.shr4pnel.casino.blackjack.BlackjackDeck
 */
public abstract class Deck {
    protected Stack<Card> cards = new Stack<>();
    protected final String[] suitList = {"clubs", "spades", "diamonds", "hearts"};
    protected final String[] cardTypeList = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
    protected final int[] cardValueList = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};

    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * Draw a card from the top of the stack
     * @return A card, or null if the deck is empty
     */
    public Card drawCard() {
        if (cards.isEmpty()) {
            return null;
        }
        return cards.pop();
    }

    /**
     * Draw a variable number of cards
     * @param n The number of cards to draw
     * @return An array of cards, or null if less than one, or more than the length of the deck
     */
    public Card[] drawCards(int n) {
        List<Card> subCards = new ArrayList<>();
        if (n < 1 || n > cards.size())
            return null;

        for (int i = 0; i < n; ++i)
            subCards.add(cards.pop());

        return subCards.toArray(Card[]::new);
    }

    /**
     * Check if the deck is empty
     * <p>
     * This is a better alternative to waiting for a null return from drawCard(s)
     * @return true, if the deck is empty
     */
    public boolean isEmpty() {
        return cards.isEmpty();
    }
}
