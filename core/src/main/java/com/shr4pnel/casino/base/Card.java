package com.shr4pnel.casino.base;

public abstract class Card {
    protected String suit;
    protected int cardValue;
    protected String cardType;

    public Card(String suit, int cardValue, String cardType) {
        this.suit = suit;
        this.cardValue = cardValue;
        this.cardType = cardType;
    }

    @Override
    public String toString() {
        return cardType + " of " + this.suit;
    }

    public int getCardValue() {
        return cardValue;
    }
}
