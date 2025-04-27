package com.shr4pnel.casino.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundEffect {
    public static void play(String path) {
        Sound sound = Gdx.audio.newSound(Gdx.files.internal(path));
        sound.play(1.0f);
    }

    public static void play(String path, float volume) {
        Sound sound = Gdx.audio.newSound(Gdx.files.internal(path));
        sound.play(volume);
    }
}
