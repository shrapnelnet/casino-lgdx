package com.shr4pnel.casino.builders;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Align;
import com.rafaskoberg.gdx.typinglabel.TypingLabel;
import com.shr4pnel.casino.style.StyleManager;

public class LabelBuilder {
    private TypingLabel label;

    public TypingLabel build() {
        return label;
    }

    public LabelBuilder start(String text) {
        label = new TypingLabel(text, StyleManager.getSkin());
        return this;
    }

    public LabelBuilder setColour(Color color) {
        label.setColor(color);
        return this;
    }

    public LabelBuilder defaultXY() {
        label.setX(25);
        label.setY(25);
        return this;
    }

    public LabelBuilder setSize() {
        label.setFontScale(0.3f);
        return this;
    }

    public LabelBuilder wrap() {
        label.setWrap(true);
        return this;
    }

    public LabelBuilder alignCenter() {
        label.setAlignment(Align.center);
        return this;
    }
}
