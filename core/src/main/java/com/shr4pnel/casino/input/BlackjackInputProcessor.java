package com.shr4pnel.casino.input;

import com.badlogic.gdx.Input.Keys;
import com.shr4pnel.casino.scene.ManagedButtonScene;

public class BlackjackInputProcessor extends InputProcessor {
    public BlackjackInputProcessor(ManagedButtonScene s) {
        super(s);
    }

    @Override
    public boolean keyDown(int keycode) {
        return switch (keycode) {
            case Keys.LEFT -> manager.left();
            case Keys.RIGHT -> manager.right();
            case Keys.Z, Keys.ENTER, Keys.NUMPAD_ENTER -> manager.enter();
            default -> false;
        };
    }
}
