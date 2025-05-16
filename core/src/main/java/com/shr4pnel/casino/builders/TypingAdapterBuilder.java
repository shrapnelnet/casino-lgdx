package com.shr4pnel.casino.builders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.rafaskoberg.gdx.typinglabel.TypingAdapter;
import com.rafaskoberg.gdx.typinglabel.TypingLabel;
import com.shr4pnel.casino.Casino;
import com.shr4pnel.casino.audio.SoundEffect;
import com.shr4pnel.casino.audio.SoundEffectHelper;
import com.shr4pnel.casino.blackjack.BlackjackGame;
import com.shr4pnel.casino.scene.Blackjack;
import com.shr4pnel.casino.scene.SceneManager;
import com.shr4pnel.casino.util.AsciiArt;

/**
 * Build TypingAdapters on the fly, which can play sounds when text has finished typing, or fire other events relating to a TypingLabel that has finished typing
 */
public class TypingAdapterBuilder {
    private String path;
    private float vol = 1;
    private Window root;
    private TypingLabel labelToAdd;
    private int delayMillis = 2500;
    private int delayPerChar = 125;
    private boolean stopSound = true;
    private int height = 0;
    private String eventName;
    private AsciiArt asciiArt = new AsciiArt();

    /**
     * Set a sound to play at the end of the TypingLabel
     *
     * @param path Path to the audio file relative to assets/
     * @return The current instance
     */
    public TypingAdapterBuilder setSound(String path) {
        this.path = path;
        return this;
    }

    /**
     * Set a sound to play at the end of the TypingLabel
     *
     * @param path Path to the audio file relative to assets/
     * @param vol  The volume of the sound effect from 0-1
     * @return The current instance
     */
    public TypingAdapterBuilder setSound(String path, float vol) {
        this.path = path;
        this.vol = vol;
        return this;
    }

    /**
     * Do not interrupt other sound effects when the typing label has finished
     *
     * @return The current instance
     */
    public TypingAdapterBuilder dontStopSound() {
        stopSound = false;
        return this;
    }

    /**
     * Chain a typing label, start a typing label when another has finished
     *
     * @param tl The new typing label
     * @param w  The window to render the typing label inside
     * @return The current instance
     */
    public TypingAdapterBuilder chainTypingLabel(TypingLabel tl, Window w) {
        root = w;
        labelToAdd = tl;
        return this;
    }

    /**
     * Set a delay, before performing any set actions at the end of a typinglabel
     *
     * @param delayMillis How long to wait in ms
     * @return The current instance
     */
    public TypingAdapterBuilder delay(int delayMillis) {
        this.delayMillis = delayMillis;
        return this;
    }

    /**
     * Add a TypingLabel event to fire
     *
     * @param s The name of the event
     * @return The current instance
     */
    public TypingAdapterBuilder addEvent(String s) {
        eventName = s;
        return this;
    }

    public TypingAdapterBuilder delayPerChar(int delay) {
        delayPerChar = delay;
        return this;
    }

    /**
     * Build the typing adapter, with previously set parameters
     *
     * @return A TypingAdapter
     */
    public TypingAdapter build() {
        final String localPath = path;
        final float localVol = vol;
        final SoundEffect sound = SoundEffectHelper.build(localPath, localVol);

        path = null;
        vol = 1;

        return new TypingAdapter() {
            @Override
            public void end() {
                sound.play();
                // If this isn't on its own thread, it'll block the rendering loop and crash the application
                new Thread(() -> {
                    try {
                        Thread.sleep(delayMillis);
                        if (stopSound)
                            endSound();

                        if (root != null && labelToAdd != null) {
                            root.add(labelToAdd).left().height(height).row();
                        }

                    } catch (InterruptedException e) {
                        Gdx.app.log("TypingAdapterBuilder", "Interrupted while waiting on thread in build()", e);
                    }
                }).start();
            }

            private void endSound() {
                sound.cancel();
            }

            /*
            TODO hacky!!! fix!!!! this only fires once and isn't extendable easily
            note: this is fine if we need to process more events but this is doubtful
             */
            @Override
            public void event(String event) {
                switch (eventName) {
                    case "start_art" -> new Thread(() -> {
                        root.clear();
                        for (Label l : asciiArt.artAsTypingLabelArray()) {
                            try {
                                root.add(l).row();
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                Gdx.app.log("TypingAdapterBuilder", "Interrupted while waiting on thread in event()", e);
                            }
                        }
                        try {
                            Thread.sleep(750);
                        } catch (InterruptedException e) {
                            Gdx.app.log("TypingAdapterBuilder", "Interrupted while waiting on thread in event()", e);
                        }
                        root.clear();
                        SceneManager.setActiveScene(SceneManager.Scene.MENU);
                    }).start();

                    case "bjdeal_complete" -> {
                        Casino c = Casino.getInstance();
                        Blackjack ui = (Blackjack) c.getGameInstance(SceneManager.Scene.BLACKJACK);
                        BlackjackGame g = ui.getGameInstance();
                        g.nextPhase();
                        ui.updatePhase();
                    }
                }
            }
        };
    }
}
