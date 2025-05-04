package com.shr4pnel.casino.scene;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Align;
import com.rafaskoberg.gdx.typinglabel.TypingAdapter;
import com.rafaskoberg.gdx.typinglabel.TypingLabel;

import com.shr4pnel.casino.builders.LabelBuilder;
import com.shr4pnel.casino.builders.TypingAdapterBuilder;
import com.shr4pnel.casino.style.StyleManager;

public class Intro {
    private static TypingLabel topLabel, readyLabel, middleLabel, ellipsisLabel;
    private static Window root;
    private static LabelBuilder labelBuilder = new LabelBuilder();
    private static TypingAdapterBuilder typingAdapterBuilder = new TypingAdapterBuilder();

    public static Window get() {
        Skin skin = StyleManager.getSkin();
        root = new Window("", skin);
        root.setSize(800, 450);
        root.align(Align.top);
        topLabel = labelBuilder
            .start("**** COMMODORE 64 BASIC V2 ****\n64K  RAM  SYSTEM  38911  BASIC  BYTES  FREE")
            .alignCenter()
            .noDelay()
            .build();

        readyLabel = labelBuilder
            .start("READY.")
            .noDelay()
            .build();

        middleLabel = labelBuilder
            .start("{SPEED=0.3}LOAD \"CASINO.PRG\",8{RESET}")
            .build();

        ellipsisLabel = labelBuilder
            .start(".{WAIT}.{WAIT}.{WAIT}{EVENT=start_art}")
            .build();

        TypingAdapter ellipsisLabelTypingAdapter = typingAdapterBuilder
            .addEvent("start_art")
            .delay(125)
            .build();

        ellipsisLabel.addTypingListener(ellipsisLabelTypingAdapter);

        TypingAdapter middleLabelTypingAdapter = typingAdapterBuilder
            .setSound("sound/read_alt.ogg", 0.1f)
            .chainTypingLabel(ellipsisLabel, root)
            .delay(2000)
            .dontStopSound()
            .build();

        middleLabel.addTypingListener(middleLabelTypingAdapter);

        root.add(topLabel).height(50).row();
        root.add(readyLabel).left().row();
        root.add().height(8).row();
        root.add(middleLabel).left().row();
        root.add().height(8).row();
        return root;
    }
}
