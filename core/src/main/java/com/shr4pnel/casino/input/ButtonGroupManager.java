package com.shr4pnel.casino.input;

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

/**
 * Used to handle grouped, related buttons. Handles keyboard navigation, as well as button actions.
 * @author shrapnelnet
 * @since 0.1.0
 */
public class ButtonGroupManager {
    private final ButtonGroup<TextButton> menuButtonGroup = new ButtonGroup<>();
    private TextButton activeButton;
    private ButtonGroupListener listener;
    protected final Map<String, Long> incrementStringToLong = new HashMap<>();

    public ButtonGroupManager(TextButton... t) {
        setMenuButtonGroup(t);
        incrementStringToLong.put("---", -100L);
        incrementStringToLong.put("--", -10L);
        incrementStringToLong.put("-", -1L);
        incrementStringToLong.put("+", 1L);
        incrementStringToLong.put("++", 10L);
        incrementStringToLong.put("+++", 100L);
    }

    /**
     * Set the internal button group to t
     * @param t A variable amount of textbuttons
     */
    public void setMenuButtonGroup(TextButton... t) {
        menuButtonGroup.clear();
        menuButtonGroup.add(t);
        // bugfix: no active button is set if we don't call setActiveButton at construction time (CTD)
        setActiveButton(menuButtonGroup.getChecked());
    }

    /**
     * @return The button that is being hovered over
     */
    public TextButton getActiveButton() {
        return activeButton;
    }

    /**
     * Set the button that is being hovered over
     * @param activeButton The button to be hovered over
     */
    public void setActiveButton(TextButton activeButton) {
        if (activeButton == null)
            return;
        this.activeButton = activeButton;
        menuButtonGroup.setChecked(activeButton.getName());
        if (listener != null)
            listener.onChange(activeButton);
    }

    /**
     * Get the button to the left of the toggled button
     * @return The button to the left of the toggled button
     */
    private TextButton getLeftToggledButton() {
        int nextIndex;
        int menuButtonGroupSize = menuButtonGroup.getButtons().size;

        if (menuButtonGroupSize == 0)
            return null;

        int index = getToggledButtonIndex();

        if (index == 0)
            nextIndex = menuButtonGroupSize - 1;
        else
            nextIndex = index - 1;

        return menuButtonGroup.getButtons().get(nextIndex);
    }

    /**
     * Get the button to the right of the toggled button
     * @return The button to the right of the toggled button
     */
    private TextButton getRightToggledButton() {
        int nextIndex;
        int menuButtonGroupSize = menuButtonGroup.getButtons().size;

        if (menuButtonGroupSize == 0) {
            return null;
        }

        int index = getToggledButtonIndex();

        if (index == menuButtonGroupSize - 1)
            nextIndex = 0;
        else
            nextIndex = index + 1;

        return menuButtonGroup.getButtons().get(nextIndex);
    }

    private TextButton getUpwardToggledButton() {
        int nextIndex;
        int menuButtonGroupSize = menuButtonGroup.getButtons().size;

        if (menuButtonGroupSize == 0) {
            return null;
        }

        int index = getToggledButtonIndex();

        if (index == 0)
            nextIndex = menuButtonGroupSize - 2;
        else if (index == 1)
            nextIndex = menuButtonGroupSize - 1;
        else
            nextIndex = index - 2;

        return menuButtonGroup.getButtons().get(nextIndex);
    }

    private TextButton getDownwardToggledButton() {
        int nextIndex;
        int menuButtonGroupSize = menuButtonGroup.getButtons().size;

        if (menuButtonGroupSize == 0)
            return null;

        int index = getToggledButtonIndex();

        if (index == menuButtonGroupSize - 2)
        nextIndex = 0;
        else if (index == menuButtonGroupSize - 1)
            nextIndex = 1;
        else
        nextIndex = index + 2;

        return menuButtonGroup.getButtons().get(nextIndex);
    }

    /**
     * @return The position of the toggled button, in the button group
     */
    private int getToggledButtonIndex() {
        return menuButtonGroup.getCheckedIndex();
    }

    /**
     * Toggle the button to the left of the toggled button
     * @return True, to mark a successful left shift
     * @see com.shr4pnel.casino.input.InputProcessor
     */
    public boolean left() {
        TextButton nextButton = getLeftToggledButton();
        if (nextButton == null)
            return false;
        setActiveButton(nextButton);
        return true;
    }

    /**
     * Toggle the button to the right of the toggled button
     * @return True, to mark a successful right shift
     * @see com.shr4pnel.casino.input.InputProcessor
     */
    public boolean right() {
        TextButton nextButton = getRightToggledButton();
        if (nextButton == null)
            return false;
        setActiveButton(nextButton);
        return true;
    }


    public boolean up() {
        TextButton nextButton = getUpwardToggledButton();
        if (nextButton == null)
            return false;
        setActiveButton(nextButton);
        return true;
    }

    public boolean down() {
        TextButton nextButton = getDownwardToggledButton();
        if (nextButton == null)
            return false;
        setActiveButton(nextButton);
        return true;
    }

    /**
     * Fired when enter is pressed in a scene that uses a button
     * @return true/false depending on if action goes to default in switch case
     * @see com.shr4pnel.casino.input.InputProcessor
     */
    public boolean enter() {
        TextButton activeButton = getActiveButton();
        return switch (activeButton.getName()) {
            case "Play", "Return" -> {
                SceneManager.setActiveScene(SceneManager.Scene.NAVIGATION);
                yield true;
            }

            case "Blackjack" -> {
                SceneManager.setActiveScene(SceneManager.Scene.BLACKJACK);
                yield true;
            }


            case "Poker" -> {
                SceneManager.setActiveScene(SceneManager.Scene.POKER);
                yield true;
            }

            case "Slots" -> {
                SceneManager.setActiveScene(SceneManager.Scene.SLOTS);
                yield true;
            }
            case "Roulette" -> {
                SceneManager.setActiveScene(SceneManager.Scene.ROULETTE);
                yield true;
            }
            case "Lootboxes" -> {
                SceneManager.setActiveScene(SceneManager.Scene.LOOTBOXES);
                yield true;
            }

            case "Back" -> {
                SceneManager.setActiveScene(SceneManager.Scene.MENU);
                yield true;
            }

            case "Quit" -> {
                Gdx.app.exit();
                yield true;
            }

            case "Settings" -> true;
            default -> false;
        };
    }
    public boolean exit(){
        Gdx.app.exit();
        return false;
    }


    /**
     * Assign a listener to the buttongroupmanager
     */
    public void setListener(ButtonGroupListener listener) {
        this.listener = listener;
    }
}
