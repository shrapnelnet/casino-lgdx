package com.shr4pnel.casino.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.util.HashMap;
import java.util.Map;

/**
 * Manages loading and fetching of textures
 * @author shrapnelnet
 * @since 0.1.0
 */
public class TextureManager {
    private final Map<String, Texture> loadedTextures = new HashMap<>();

    /**
     * Load all files present in assets/card
     * @see com.shr4pnel.casino.Casino
     */
    public void preload() {
        FileHandle fh = Gdx.files.internal("card");
        for (FileHandle file: fh.list()) {
            String path = file.path();
            Texture t = new Texture(Gdx.files.internal(path));
            Gdx.app.log("AssetManager", "Preloading " + path);
            loadedTextures.put(path, t);
        }
    }

    /**
     * Get a texture after preloading
     * @param path The path to the texture relative to assets/
     * @return The texture as an image, or null if the texture wasn't preloaded
     */
    public Image getImage(String path) {
        Texture t = loadedTextures.get(path);
        if (t == null) {
            Gdx.app.log("TextureManager", "Path " + path + " was not preloaded");
            return null;
        }
        return new Image(t);
    }
}
