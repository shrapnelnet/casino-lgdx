package com.shr4pnel.casino.style;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class StyleManager {
    private static final TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("skin/uiskin.atlas"));
    private static final Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
    private static boolean postProcessingEnabled = true;

    static {
        skin.addRegions(atlas);
    }

    public static Skin getSkin() {
        return StyleManager.skin;
    }

    public static boolean getPostProcessingEnabled() {
        return postProcessingEnabled;
    }

    public static boolean getPostProcessingDisabled() {
        return !getPostProcessingEnabled();
    }

    public static void setPostProcessingEnabled(boolean enabled) {
        postProcessingEnabled = enabled;
    }
}
