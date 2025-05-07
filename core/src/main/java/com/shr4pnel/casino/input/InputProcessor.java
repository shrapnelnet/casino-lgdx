package com.shr4pnel.casino.input;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.shr4pnel.casino.scene.ManagedButtonScene;
import com.shr4pnel.casino.util.ButtonGroupManager;

public abstract class InputProcessor extends InputAdapter {
    protected ButtonGroupManager manager;

    public InputProcessor(ManagedButtonScene s) {
        manager = s.getButtonGroupManager();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (manager == null)
            return false;

        return switch (keycode) {
            case Keys.LEFT, Keys.A -> manager.left();
            case Keys.RIGHT, Keys.D -> manager.right();
            case Keys.Z, Keys.ENTER, Keys.NUMPAD_ENTER -> manager.enter();
            default -> false;
        };
    }
}
