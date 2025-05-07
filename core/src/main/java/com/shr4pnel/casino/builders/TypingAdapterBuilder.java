package com.shr4pnel.casino.builders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.rafaskoberg.gdx.typinglabel.TypingAdapter;
import com.rafaskoberg.gdx.typinglabel.TypingLabel;
import com.shr4pnel.casino.audio.SoundEffect;
import com.shr4pnel.casino.audio.SoundEffectHelper;
import com.shr4pnel.casino.scene.SceneManager;
import com.shr4pnel.casino.util.AsciiArt;

public class TypingAdapterBuilder {
    private String path;
    private float vol = 1;
    private Window root;
    private TypingLabel labelToAdd;
    private int delayMillis = 2500;
    private boolean stopSound = true;
    private int height = 0;
    private String eventName;

    public TypingAdapterBuilder setSound(String path) {
        this.path = path;
        return this;
    }

    public TypingAdapterBuilder setSound(String path, float vol) {
        this.path = path;
        this.vol = vol;
        return this;
    }

    public TypingAdapterBuilder dontStopSound() {
        stopSound = false;
        return this;
    }

    public TypingAdapterBuilder chainTypingLabel(TypingLabel tl, Window w) {
        root = w;
        labelToAdd = tl;
        return this;
    }

    public TypingAdapterBuilder delay(int delayMillis) {
        this.delayMillis = delayMillis;
        return this;
    }

    public TypingAdapterBuilder addEvent(String s) {
        eventName = s;
        return this;
    }

    public TypingAdapterBuilder height(int height) {
        this.height = height;
        return this;
    }

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
                if (eventName.equals("start_art")) {
                    new Thread(() -> {
                        root.clear();
                        for (Label l : AsciiArt.artAsTypingLabelArray()) {
                            try {
                                root.add(l).row();
                                Thread.sleep(125);
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
                }
            }
        };
    }
}
