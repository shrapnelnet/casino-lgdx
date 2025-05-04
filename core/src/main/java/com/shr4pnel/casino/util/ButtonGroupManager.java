package com.shr4pnel.casino.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.shr4pnel.casino.scene.Menu;
import com.shr4pnel.casino.scene.SceneManager;

public class ButtonGroupManager {
    private ButtonGroup<TextButton> menuButtonGroup = new ButtonGroup<>();
    private TextButton activeButton;

    public ButtonGroupManager(TextButton... t) {
        menuButtonGroup.add(t);
    }

    public TextButton getActiveButton() {
        return activeButton;
    }

    public void setActiveButton(TextButton activeButton) {
        this.activeButton = activeButton;
    }

    public void setMenuButtonGroup(ButtonGroup<TextButton> buttonGroup) {
        if (menuButtonGroup != null) {
            menuButtonGroup.clear();
        }

        for (TextButton b : buttonGroup.getButtons()) {
            menuButtonGroup.add(b);
        }

        setActiveButton(menuButtonGroup.getChecked());
    }

    private TextButton getLeftToggledButton() {
        int nextIndex;
        int menuButtonGroupSize = menuButtonGroup.getButtons().size;

        int index = getToggledButtonIndex();

        if (index == 0)
            nextIndex = menuButtonGroupSize - 1;
        else
            nextIndex = index - 1;

        return menuButtonGroup.getButtons().get(nextIndex);
    }

    private TextButton getRightToggledButton() {
        int nextIndex;
        int menuButtonGroupSize = menuButtonGroup.getButtons().size;

        int index = getToggledButtonIndex();

        if (index == menuButtonGroupSize - 1)
            nextIndex = 0;
        else
            nextIndex = index + 1;

        return menuButtonGroup.getButtons().get(nextIndex);
    }

    private int getToggledButtonIndex() {
        return menuButtonGroup.getCheckedIndex();
    }

    public boolean left() {
        TextButton nextButton = getLeftToggledButton();
        setActiveButton(nextButton);
        Menu.updateActiveButton();
        return true;
    }

    public boolean right() {
        TextButton nextButton = getRightToggledButton();
        setActiveButton(nextButton);
        Menu.updateActiveButton();
        return true;
    }

    public boolean enter() {
        TextButton activeButton = getActiveButton();
        return switch (activeButton.getName()) {
            case "Play" -> {
                SceneManager.setActiveScene(SceneManager.Scene.BLACKJACK);
                yield true;
            }
            case "Quit" -> {
                Gdx.app.exit();
                yield false;
            }
            case "Settings" -> true;
            default -> false;
        };
    }

}
