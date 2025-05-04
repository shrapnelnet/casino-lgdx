package com.shr4pnel.casino.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public abstract class Deck {
    protected Stack<Card> cards = new Stack<>();
    protected final String[] suitList = {"clubs", "spades", "diamonds", "hearts"};
    protected final String[] cardTypeList = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
    protected final int[] cardValueList = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            return null;
        }
        return cards.pop();
    }

    public Card[] drawCards(int n) {
        List<Card> subCards = new ArrayList<>();
        if (n < 1 || n > cards.size())
            return null;

        for (int i = 0; i < n; ++i)
            subCards.add(cards.pop());

        return subCards.toArray(Card[]::new);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }
}
