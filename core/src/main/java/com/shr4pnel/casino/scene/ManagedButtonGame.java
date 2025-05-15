package com.shr4pnel.casino.scene;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.shr4pnel.casino.base.Game;
import com.shr4pnel.casino.style.StyleManager;

/**
 * Used to force all games to have an accessible getGameInstance method regardless of specific implementation
 * @author shrapnelnet
 * @version 0.1.0
 * @see ManagedButtonScene
 */
public abstract class





ManagedButtonGame extends ManagedButtonScene {
    /**
     * @return The current game instance
     */
    public abstract Game getGameInstance();

    /**
     * Create a new text button
     * @param content The content of the button
     * @return A correctly built text button
     */
    protected TextButton newTextButton(String content) {
        TextButton t = new TextButton(content, StyleManager.getSkin(), "toggle");
        t.setName(content);
        return t;
    }
}
