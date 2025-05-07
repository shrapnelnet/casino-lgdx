package com.shr4pnel.casino.builders;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Align;
import com.rafaskoberg.gdx.typinglabel.TypingLabel;
import com.shr4pnel.casino.style.StyleManager;

/**
 * Create Scene2D labels on-the-fly
 * @author shrapnelnet
 * @since 0.1.0
 * @see TypingLabel
 * @see com.badlogic.gdx.scenes.scene2d.ui.Label
 */
public class LabelBuilder {
    private TypingLabel label;

    /**
     * Build the label, after setting parameters
     * @return A TypingLabel, which can also be assigned to a normal Scene2D Label
     */
    public TypingLabel build() {
        return label;
    }

    /**
     * Begin building a new label
     * @param text The text inside the label
     * @return The current LabelBuilder instance
     */
    public LabelBuilder start(String text) {
        label = new TypingLabel(text, StyleManager.getSkin());
        return this;
    }

    /**
     * Set the colour of the text
     * @param color The color!!!!!
     * @return The current LabelBuilder instance
     */
    public LabelBuilder setColour(Color color) {
        label.setColor(color);
        return this;
    }

    /**
     * Align the label to the center of its container
     * @return The current LabelBuilder instance
     */
    public LabelBuilder alignCenter() {
        label.setAlignment(Align.center);
        return this;
    }

    /**
     * Instead of typing out the label, display all text immediately
     * @return The current LabelBuilder instance
     */
    public LabelBuilder noDelay() {
        label.skipToTheEnd();
        return this;
    }
}
