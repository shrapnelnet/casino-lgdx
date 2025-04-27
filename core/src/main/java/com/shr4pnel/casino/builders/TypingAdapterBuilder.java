package com.shr4pnel.casino.builders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.rafaskoberg.gdx.typinglabel.TypingAdapter;

import com.shr4pnel.casino.audio.SoundEffect;
import com.shr4pnel.casino.audio.SoundEffectHelper;

public class TypingAdapterBuilder {
    private String path;
    private float vol = 1f;

    public TypingAdapterBuilder setSound(String path) {
        this.path = path;
        return this;
    }

    public TypingAdapterBuilder setSound(String path, float vol) {
        this.path = path;
        this.vol = vol;
        return this;
    }

    public TypingAdapter build() {
        final String localPath = path;
        final float localVol = vol;
        final SoundEffect sound = SoundEffectHelper.build(localPath, localVol);

        path = null;
        vol = 1f;

        // time wasted here so far: 2 hours (fixing stupid scheduled thread executor bullshit)
        return new TypingAdapter() {
            public void end() {
                sound.play();
                Gdx.app.postRunnable(() -> {
                    Actions.delay(5);
                    Actions.run(() -> {
                        endSound();
                        sound.dispose();
                    });
                });
            }

            private void endSound() {
                sound.cancel();
            }
        };
    }
}
