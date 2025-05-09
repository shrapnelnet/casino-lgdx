package com.shr4pnel.casino.input;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * Button listener interface, used to handle changes between individual buttons
 * @author shrapnelnet
 * @since 0.1.0
 * @see ButtonGroupManager
 */
public interface ButtonGroupListener {
    void onChange(TextButton activeButton);
}
