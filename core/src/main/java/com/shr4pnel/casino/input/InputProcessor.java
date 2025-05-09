package com.shr4pnel.casino.input;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.shr4pnel.casino.scene.ManagedButtonScene;

/**
 * Base class for custom input processors for scenes that use buttons
 * @author shrapnelnet
 * @since 0.1.0
 * @see ManagedButtonScene
 * @see com.shr4pnel.casino.scene.ManagedButtonGame
 */
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
