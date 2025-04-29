package com.shr4pnel.casino.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;

import java.util.HashMap;
import java.util.Map;

public class SoundEffectHelper {
    private static final Map<String, Sound> loadedSounds = new HashMap<>();
    /**
     * Plays a sound at full volume
     * @param path Path to audio file, relative to asset directory
     */
    public static void play(String path) {
        Sound sound = loadedSounds.get(path);
        if (sound != null) {
            sound.play();
            return;
        }
        Gdx.app.log("SoundEffectHelper", "Loading audio file " + path + ", which was not preloaded");
        sound = Gdx.audio.newSound(Gdx.files.internal(path));
        loadedSounds.put(path, sound);
        sound.play(1.0f);
    }

    /**
     * Plays a sound at a specified volume
     * @param path Path to audio file, relative to asset directory
     * @param volume Volume to play sound at, from 0-1
     */
    public static void play(String path, float volume) {
        Sound sound = loadedSounds.get(path);
        if (sound != null) {
            sound.play(volume);
            return;
        }
        Gdx.app.log("SoundEffectHelper", "Loading audio file " + path + ", which was not preloaded");
        sound = Gdx.audio.newSound(Gdx.files.internal(path));
        loadedSounds.put(path, sound);
        sound.play(volume);
    }

    /**
     * Plays a sound, retaining the sound ID
     * @param path Path to audio file, relative to asset directory
     * @return Sound ID
     */
    public static long playWithID(String path) {
        Sound sound = loadedSounds.get(path);
        if (sound != null) {
            return sound.play();
        }
        Gdx.app.log("SoundEffectHelper", "Loading audio file " + path + ", which was not preloaded");
        sound = Gdx.audio.newSound(Gdx.files.internal(path));
        loadedSounds.put(path, sound);
        return sound.play(1.0f);
    }

    /**
     * Plays a sound, retaining the sound ID at a specified volume
     * @param path Path to audio file, relative to asset directory
     * @param volume Volume to play sound at, from 0-1
     * @return Sound ID
     */
    public static long playWithID(String path, float volume) {
        Sound sound = loadedSounds.get(path);
        if (sound != null) {
            return sound.play(volume);
        }
        Gdx.app.log("SoundEffectHelper", "Loading audio file " + path + ", which was not preloaded");
        sound = Gdx.audio.newSound(Gdx.files.internal(path));
        loadedSounds.put(path, sound);
        return sound.play(volume);
    }

    /**
     * Builds a sound effect instance
     * @param path Path to audio file, relative to asset directory
     * @return A SoundEffect object, with builtin play and cancel methods to avoid manual management of ID
     */
    public static SoundEffect build(String path) {
        return new SoundEffect(path);
    }

    /**
     * Builds a sound effect instance, specifying a volume
     * @param path Path to audio file, relative to asset directory
     * @return A SoundEffect object, with builtin play and cancel methods to avoid manual management of ID
     */
    public static SoundEffect build(String path, float vol) {
        return new SoundEffect(path, vol);
    }

    public static void dispose() {
        for (Sound s: loadedSounds.values()) {
            s.dispose();
        }
    }

    public static void preload() {
        FileHandle fh = Gdx.files.internal("sound");
        for (FileHandle file: fh.list()) {
            String path = "sound/" + file.name();
            Sound sound = Gdx.audio.newSound(file);
            Gdx.app.log("SoundEffectHelper", "Preloading " + path);
            loadedSounds.put(path, sound);
        }
    }

    public static void stopAll() {
        for (Sound s: loadedSounds.values()) {
            s.stop();
        }
    }
}
