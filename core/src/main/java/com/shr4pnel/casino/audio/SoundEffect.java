package com.shr4pnel.casino.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundEffect {
    private long id;
    private String path;
    private float vol;
    private final Sound soundManager = Gdx.audio.newSound(Gdx.files.internal("sound/load.ogg"));

    public SoundEffect(String path) {
        this.path = path;
    }

    public SoundEffect(String path, float vol) {
        this.path = path;
        this.vol = vol;
    }

    public void play() {
        if (path == null)
            return;
        id = SoundEffectHelper.playWithID(path, vol);
    }

    public void cancel() {
        soundManager.stop(id);
    }

    public void dispose() {
        cancel();
        soundManager.dispose();
    }
}
