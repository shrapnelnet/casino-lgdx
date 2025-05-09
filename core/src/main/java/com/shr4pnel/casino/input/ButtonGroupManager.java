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

        int index = getToggledButtonIndex();

        if (index == menuButtonGroupSize - 1)
            nextIndex = 0;
        else
            nextIndex = index + 1;

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

    /**
     * Increment the displayed bet
     * todo MOVE THIS TO SPECIFIC IMPLEMENTATION SO WE DO NOT HAVE TO CAST... MAYBE?
     * @param name The name of the button, assigned when they are created
     * @return True, when incrementing is complete
     * @see Blackjack
     */
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

    /**
     * Assign a listener to the buttongroupmanager
     */
    public void setListener(ButtonGroupListener listener) {
        this.listener = listener;
    }
}
