package com.shr4pnel.casino.scene;

import com.shr4pnel.casino.util.ButtonGroupManager;

/**
 * Used to force all scenes with buttons to have an accessible buttongroupmanager
 * @author shrapnelnet
 * @since 0.1.0
 * @see Scene
 */
public abstract class ManagedButtonScene extends Scene {
    protected ButtonGroupManager buttonGroupManager;

    public ButtonGroupManager getButtonGroupManager() {
        return buttonGroupManager;
    }
}
