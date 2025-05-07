package com.shr4pnel.casino.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.shr4pnel.casino.Casino;
import com.shr4pnel.casino.base.Game;
import com.shr4pnel.casino.base.Player;
import com.shr4pnel.casino.scene.Blackjack;
import com.shr4pnel.casino.scene.ManagedButtonGame;
import com.shr4pnel.casino.scene.SceneManager;

import java.util.HashMap;
import java.util.Map;

public class ButtonGroupManager {
    private final ButtonGroup<TextButton> menuButtonGroup = new ButtonGroup<>();
    private TextButton activeButton;
    private ButtonGroupListener listener;
    private final Map<String, Long> incrementStringToLong = new HashMap<>();

    public ButtonGroupManager(TextButton... t) {
        setMenuButtonGroup(t);
        incrementStringToLong.put("---", -100L);
        incrementStringToLong.put("--", -10L);
        incrementStringToLong.put("-", -1L);
        incrementStringToLong.put("+", 1L);
        incrementStringToLong.put("++", 10L);
        incrementStringToLong.put("+++", 100L);
    }

    public void setMenuButtonGroup(TextButton... t) {
        menuButtonGroup.clear();
        menuButtonGroup.add(t);
        // bugfix: no active button is set if we don't call setActiveButton at construction time (CTD)
        setActiveButton(menuButtonGroup.getChecked());
    }

    public TextButton getActiveButton() {
        return activeButton;
    }

    public void setActiveButton(TextButton activeButton) {
        this.activeButton = activeButton;
        menuButtonGroup.setChecked(activeButton.getName());
        if (listener != null)
            listener.onChange(activeButton);
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
        return true;
    }

    public boolean right() {
        TextButton nextButton = getRightToggledButton();
        setActiveButton(nextButton);
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
                yield true;
            }

            case "Settings" -> true;

            case "---", "--", "-", "+", "++", "+++" -> incrementBet(activeButton.getName());

            default -> false;
        };
    }

    private boolean incrementBet(String name) {
        Casino c = Casino.getInstance();
        ManagedButtonGame scene = c.getGameInstance(SceneManager.getActiveScene());
        Game g = scene.getGameInstance();
        Player p = g.getPlayer();
        p.incrementBet(incrementStringToLong.get(name));

        if (scene instanceof Blackjack)
            ((Blackjack) scene).updateChipDisplay();
        return true;
    }

    public void setListener(ButtonGroupListener listener) {
        this.listener = listener;
    }
}
