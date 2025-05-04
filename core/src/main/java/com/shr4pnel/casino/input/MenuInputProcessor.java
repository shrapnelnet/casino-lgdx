package com.shr4pnel.casino.input;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.shr4pnel.casino.scene.Menu;
import com.shr4pnel.casino.util.ButtonGroupManager;

public class MenuInputProcessor implements InputProcessor {
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

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
