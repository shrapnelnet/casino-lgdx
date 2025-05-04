package com.shr4pnel.casino.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.shr4pnel.casino.scene.SceneManager;
import com.shr4pnel.casino.scene.Menu;

public class MenuState {
    private static ButtonGroup<TextButton> menuButtonGroup = new ButtonGroup<>();
    private static TextButton activeButton;

    public static TextButton getActiveButton() {
        return activeButton;
    }

    public static void setActiveButton(TextButton activeButton) {
        MenuState.activeButton = activeButton;
    }

    public static void setMenuButtonGroup(ButtonGroup<TextButton> buttonGroup) {
        if (menuButtonGroup != null) {
            menuButtonGroup.clear();
        }

        for (TextButton b: buttonGroup.getButtons()) {
            menuButtonGroup.add(b);
        }

        setActiveButton(menuButtonGroup.getChecked());
    }

    private static TextButton getLeftToggledButton() {
        int nextIndex;
        int menuButtonGroupSize = menuButtonGroup.getButtons().size;

        int index = getToggledButtonIndex();

        if (index == 0)
            nextIndex = menuButtonGroupSize - 1;
        else
            nextIndex = index - 1;

        return menuButtonGroup.getButtons().get(nextIndex);
    }

    private static TextButton getRightToggledButton() {
        int nextIndex;
        int menuButtonGroupSize = menuButtonGroup.getButtons().size;

        int index = getToggledButtonIndex();

        if (index == menuButtonGroupSize - 1)
            nextIndex = 0;
        else
            nextIndex = index + 1;

        return menuButtonGroup.getButtons().get(nextIndex);
    }

    private static int getToggledButtonIndex() {
        return menuButtonGroup.getCheckedIndex();
    }

    public static boolean left() {
        TextButton nextButton = MenuState.getLeftToggledButton();
        MenuState.setActiveButton(nextButton);
        Menu.updateActiveButton();
        return true;
    }

    public static boolean right() {
        TextButton nextButton = MenuState.getRightToggledButton();
        MenuState.setActiveButton(nextButton);
        Menu.updateActiveButton();
        return true;
    }

    public static boolean enter() {
        TextButton activeButton = getActiveButton();
        return switch (activeButton.getName()) {
            case "Play" -> true;
            case "Quit" -> {
                Gdx.app.exit();
                yield false;
            }
            case "Settings" -> true;
            default -> false;
        };
    }
}
