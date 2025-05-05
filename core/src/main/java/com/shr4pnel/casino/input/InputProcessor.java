package com.shr4pnel.casino.input;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.shr4pnel.casino.util.ButtonGroupManager;

public abstract class InputProcessor extends InputAdapter {
    protected abstract ButtonGroupManager getButtonGroupManager();

    @Override
    public boolean keyDown(int keycode) {
        ButtonGroupManager manager = getButtonGroupManager();
        return switch (keycode) {
            case Keys.LEFT -> manager.left();
            case Keys.RIGHT -> manager.right();
            case Keys.Z, Keys.ENTER, Keys.NUMPAD_ENTER -> manager.enter();
            default -> false;
        };
    }
}
