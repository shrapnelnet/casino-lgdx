package com.shr4pnel.casino.util;

public class GlobalPlayerState {
    private Long chips;

    public Long getChips() {
        return chips;
    }

    public void incrementChips(Long amount) {
        chips += amount;
    }

    public void setChips(Long chips) {
        this.chips = chips;
    }
}
