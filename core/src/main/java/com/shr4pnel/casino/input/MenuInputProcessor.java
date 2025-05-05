package com.shr4pnel.casino.input;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.shr4pnel.casino.scene.Menu;
import com.shr4pnel.casino.util.ButtonGroupManager;

public class MenuInputProcessor extends InputAdapter {
    private final ButtonGroupManager buttonGroupManager = Menu.getButtonGroupManager();

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
