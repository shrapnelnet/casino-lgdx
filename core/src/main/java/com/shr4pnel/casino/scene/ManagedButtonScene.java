package com.shr4pnel.casino.scene;

import com.shr4pnel.casino.util.ButtonGroupManager;

public abstract class ManagedButtonScene extends Scene {
    protected ButtonGroupManager buttonGroupManager;

    public ButtonGroupManager getButtonGroupManager() {
        return buttonGroupManager;
    }
}
