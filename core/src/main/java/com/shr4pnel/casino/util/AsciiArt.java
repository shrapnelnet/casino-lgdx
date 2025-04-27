package com.shr4pnel.casino.util;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.shr4pnel.casino.style.StyleManager;

import java.util.ArrayList;

public class AsciiArt {
    public static final String[] cigarette = {
        "                      ((\\",
        "(              _  ,-_  \\ \\",
        ")             / \\/  \\ \\ \\ \\",
        "(            /)| \\/\\ \\ \\| |",
        "`~()_______)___)\\ \\ \\ \\ \\ |",
        "            |)\\ )  `' | | |",
        "           /  /,          |",
        "           |  |          /",
        "           |  |         /",
        "           \\           /",
        "            \\         /",
        "             )       /",
        "            /       /",
        "           /       /",
        "                  /",
        "     KILL EM ALL 2009",
    };

    public static Label[] artAsTypingLabelArray() {
        ArrayList<Label> labelList = new ArrayList<>();
        for (String s: cigarette) {
            Label label = new Label(s, StyleManager.getSkin());
            labelList.add(label);
        }
        return labelList.toArray(Label[]::new);
    }
}
