package com.shr4pnel.casino.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

/**
 * A representation of a sound effect, which can be played or cancelled
 * @author shrapnelnet
 * @since 0.1.0
 * @see SoundEffectHelper
 */
public class SoundEffect {
    private long id;
    private String path;
    private float vol;
    // any sound can be used to cancel other sounds, this sound should NOT be played
    private final Sound soundManager = Gdx.audio.newSound(Gdx.files.internal("sound/load.ogg"));

    /**
     * Build a sound effect with default (1) volume
     * @param path Path to sound file relative to assets directory
     */
    public SoundEffect(String path) {
        this.path = path;
    }

    /**
     * Build a sound effect, with control over the volume
     * @param path Path to sound file relative to assets directory
     * @param vol Volume, from 0-1
     */
    public SoundEffect(String path, float vol) {
        this.path = path;
        this.vol = vol;
    }

    /**
     * Plays the sound effect
     */
    public void play() {
        if (path == null)
            return;
        id = SoundEffectHelper.playWithID(path, vol);
    }

    /**
     * Cancels the sound effect
     */
    public void cancel() {
        soundManager.stop(id);
    }
}
