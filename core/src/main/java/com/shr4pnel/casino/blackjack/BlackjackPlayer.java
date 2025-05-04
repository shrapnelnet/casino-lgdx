package com.shr4pnel.casino.blackjack;

import com.shr4pnel.casino.base.Card;
import com.shr4pnel.casino.base.Player;

import java.util.ArrayList;
import java.util.List;

public class BlackjackPlayer extends Player {
    public BlackjackPlayer(boolean playerControlled) {
        super(playerControlled);
    }

    public boolean isBust() {
        return getHandValue() > 21;
    }

    public int getHandValue() {
        int handValue = 0;
        List<BlackjackCard> aces = new ArrayList<>();
        for (Card c: hand) {
            BlackjackCard card = (BlackjackCard) c;
            if (card.isAce()) {
                aces.add(card);
                continue;
            }
            handValue += c.getCardValue();
        }

        // aces are high or low. high used by default unless it would bust
        for (int i = 0; i < aces.size(); ++i) {
            if ((handValue + 11) > 21) {
                handValue += 1;
            } else {
                handValue += 11;
            }
        }
        return handValue;
    }
}
