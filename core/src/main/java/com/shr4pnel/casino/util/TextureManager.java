package com.shr4pnel.casino.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.util.HashMap;
import java.util.Map;

public class TextureManager {
    private final Map<String, Texture> loadedTextures = new HashMap<>();

    public void preload() {
        FileHandle fh = Gdx.files.internal("card");
        for (FileHandle file: fh.list()) {
            String path = file.path();
            Texture t = new Texture(Gdx.files.internal(path));
            Gdx.app.log("AssetManager", "Preloading " + path);
            loadedTextures.put(path, t);
        }
    }

    public Image getImage(String path) {
        Texture t = loadedTextures.get(path);
        if (t == null) {
            Gdx.app.log("TextureManager", "Path " + path + " was not preloaded");
            return null;
        }
        return new Image(t);
    }
}
