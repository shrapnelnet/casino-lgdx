package com.shr4pnel.casino.util;

public class GlobalPlayerState {
    private static Long chips;

    public static Long getChips() {
        if (chips == null)
            return 100L;

        return chips;
    }

    public static void setChips(Long chips) {
        GlobalPlayerState.chips = chips;
    }
}
