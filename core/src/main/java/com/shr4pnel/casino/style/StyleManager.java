package com.shr4pnel.casino.style;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class StyleManager {
    private static final TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("skin/uiskin.atlas"));
    private static final Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
    static {
        skin.addRegions(atlas);
    }

    public static Skin getSkin() {
        return StyleManager.skin;
    }
}
