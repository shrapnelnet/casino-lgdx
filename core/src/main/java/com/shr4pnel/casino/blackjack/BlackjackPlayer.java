package com.shr4pnel.casino.blackjack;

import com.shr4pnel.casino.base.Player;

import java.util.ArrayList;
import java.util.List;

public class BlackjackPlayer extends Player {
    private List<BlackjackCard> hand = new ArrayList<>();

    public BlackjackPlayer(boolean playerControlled) {
        super(playerControlled);
    }

    public boolean isBust() {
        return false;
    }

    public int getHandValue() {
        int handValue = 0;
        List<BlackjackCard> aces = new ArrayList<>();
        for (BlackjackCard c: hand) {
            if (c.isAce()) {
                aces.add(c);
                continue;
            }
            handValue += c.getCardValue();
        }

        // aces are high or low. high used by default unless it would bust
        for (BlackjackCard c: aces) {
            if ((handValue + 11) > 21) {
                handValue += 1;
            } else {
                handValue += 11;
            }
        }
        return handValue;
    }
}
