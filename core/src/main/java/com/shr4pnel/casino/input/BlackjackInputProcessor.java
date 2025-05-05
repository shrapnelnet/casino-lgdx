package com.shr4pnel.casino.input;

import com.badlogic.gdx.Input.Keys;
import com.shr4pnel.casino.scene.Blackjack;
import com.shr4pnel.casino.util.ButtonGroupManager;

public class BlackjackInputProcessor extends InputProcessor {
    private final ButtonGroupManager buttonGroupManager = Blackjack.getButtonGroupManager();

    @Override
    protected ButtonGroupManager getButtonGroupManager() {
        return Blackjack.getButtonGroupManager();
    }

    @Override
    public boolean keyDown(int keycode) {
        return switch (keycode) {
            case Keys.LEFT -> buttonGroupManager.left();
            case Keys.RIGHT -> buttonGroupManager.right();
            case Keys.Z, Keys.ENTER, Keys.NUMPAD_ENTER -> buttonGroupManager.enter();
            default -> false;
        };
    }
}
