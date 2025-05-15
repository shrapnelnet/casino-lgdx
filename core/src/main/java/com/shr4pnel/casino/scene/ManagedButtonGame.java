package com.shr4pnel.casino.scene;

import com.shr4pnel.casino.base.Game;

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
}
