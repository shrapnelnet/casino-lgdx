package com.shr4pnel.casino.base;

public class Card {
    private String suit;
    private int cardValue;
    private String cardType;
    protected AceRules aceRule;
    public enum AceRules {
        ACES_HIGH,
        ACES_LOW,
        ACES_HIGH_OR_LOW
    }

    public Card(String suit, int cardValue, String cardType, AceRules aceRules) {
        this.suit = suit;
        this.cardValue = cardValue;
        this.cardType = cardType;
        this.aceRule = aceRules;
    }

    @Override
    public String toString() {
        return cardType + " of " + this.suit;
    }

    public int getCardValue() {
        if (isAce()) {
            if (aceRule.equals(AceRules.ACES_HIGH_OR_LOW)) {
                // todo
            }
            // if aces are high they're worth 11
            return (aceRule.equals(AceRules.ACES_HIGH)) ? 11 : 1;
        }
        return cardValue;
    }

    public boolean isAce() {
        return cardType.equals("ace");
    }
}
