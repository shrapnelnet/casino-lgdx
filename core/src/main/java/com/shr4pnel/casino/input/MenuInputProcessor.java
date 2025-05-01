package com.shr4pnel.casino.input;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

public class MenuInputProcessor implements InputProcessor {
    @Override
    public boolean keyDown(int keycode) {
        return switch (keycode) {
            case Keys.LEFT -> MenuState.left();
            case Keys.RIGHT -> MenuState.right();
            case Keys.Z, Keys.ENTER, Keys.NUMPAD_ENTER -> MenuState.enter();
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
