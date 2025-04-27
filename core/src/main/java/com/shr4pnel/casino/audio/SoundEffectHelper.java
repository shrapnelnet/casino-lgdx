package com.shr4pnel.casino.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundEffectHelper {
    public static void play(String path) {
        Sound sound = Gdx.audio.newSound(Gdx.files.internal(path));
        sound.play(1.0f);
    }

    public static void play(String path, float volume) {
        Sound sound = Gdx.audio.newSound(Gdx.files.internal(path));
        sound.play(volume);
    }

    public static long playWithID(String path) {
        Sound sound = Gdx.audio.newSound(Gdx.files.internal(path));
        return sound.play(1.0f);
    }

    public static long playWithID(String path, float volume) {
        Sound sound = Gdx.audio.newSound(Gdx.files.internal(path));
        return sound.play(volume);
    }

    public static void cancel(long id) {
        Sound sound = Gdx.audio.newSound(Gdx.files.internal("sound/load.ogg"));
        sound.stop(id);
    }
}
