package com.shr4pnel.casino.input;

import com.badlogic.gdx.Input.Keys;
import com.shr4pnel.casino.scene.ManagedButtonScene;

/**
 * stub
 */
public class NavigationInputProcessor extends InputProcessor {
    public NavigationInputProcessor(ManagedButtonScene s) {
        super(s);
    }

    @Override
    public boolean keyDown(int keycode) {
        if (manager == null)
            return false;

        boolean success = super.keyDown(keycode);
        if (success)
            return true;

        return switch (keycode) {
            case Keys.UP,Keys.W -> manager.up();
            case Keys.DOWN, Keys.S -> manager.down();
            default -> false;
        };
    }
}
