package com.shr4pnel.casino.scene;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.rafaskoberg.gdx.typinglabel.TypingLabel;

import com.shr4pnel.casino.builders.LabelBuilder;
import com.shr4pnel.casino.style.StyleManager;
import com.shr4pnel.casino.util.ButtonGroupManager;

public class Menu {
    private static final LabelBuilder labelBuilder = new LabelBuilder();
    private static TextButton play, settings, quit;
    private static TypingLabel label;
    private static ButtonGroup<TextButton> textButtonGroup = new ButtonGroup<>();
    private static ButtonGroupManager buttonGroupManager;


    public static Table get() {
        Table root = new Table(StyleManager.getSkin());
        Table labelRoot = new Table(StyleManager.getSkin());

        label = labelBuilder
            .start("{WIND}Casino-GDX{ENDWIND}")
            .noDelay()
            .build();

        play = new TextButton("Play", StyleManager.getSkin(), "toggle");
        settings = new TextButton("Settings", StyleManager.getSkin(), "toggle");
        quit = new TextButton("Quit", StyleManager.getSkin(), "toggle");

        play.setName("Play");
        settings.setName("Settings");
        quit.setName("Quit");

        textButtonGroup.add(play, settings, quit);
        textButtonGroup.setMaxCheckCount(1);
        textButtonGroup.setMinCheckCount(1);
        buttonGroupManager = new ButtonGroupManager(play, settings, quit);
        buttonGroupManager.setListener(activeButton -> textButtonGroup.setChecked(activeButton.getName()));

        labelRoot.add(label).center();
        root.background("window");
        root.setSize(800, 450);

        // without colspan, i stretch the play button out to 800 width
        root.add(labelRoot).colspan(3).center().row();
        root.add(play).pad(10);
        root.add(settings).pad(10);
        root.add(quit).pad(10).row();


        return root;
    }

    public static ButtonGroupManager getButtonGroupManager() {
        return buttonGroupManager;
    }
}
