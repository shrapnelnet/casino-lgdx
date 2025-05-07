package com.shr4pnel.casino.style;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Handles the active skin, as well as initialisation and loading of all skin-related assets
 * @author shrapnelnet
 * @since 0.1.0
 */
public class StyleManager {
    private static final TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("skin/uiskin.atlas"));
    private static final Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
    private static boolean postProcessingEnabled = true;

    static {
        skin.addRegions(atlas);
    }

    /**
     * @return The active skin
     */
    public static Skin getSkin() {
        return StyleManager.skin;
    }

    /**
     * @return true/false, depending on if post processing is enabled
     */
    public static boolean getPostProcessingEnabled() {
        return postProcessingEnabled;
    }

    /**
     *
     * @return true/false, depending on if post processing is disabled
     */
    public static boolean getPostProcessingDisabled() {
        return !getPostProcessingEnabled();
    }

    /**
     * Sets post processing to on/off
     * @param enabled Should post processing be enabled?
     */
    public static void setPostProcessingEnabled(boolean enabled) {
        postProcessingEnabled = enabled;
    }
}
