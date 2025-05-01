package com.shr4pnel.casino.scene;

import com.badlogic.gdx.Gdx;
import com.shr4pnel.casino.Casino;
import com.shr4pnel.casino.audio.SoundEffectHelper;
import com.shr4pnel.casino.input.MenuInputProcessor;

import java.util.HashMap;
import java.util.Map;

public class SceneManager {
    private static scene activeScene;
    private static final Map<scene, String> sceneToPrettyName;
    private static final Map<String, scene> prettyNameToScene;

    static {
        sceneToPrettyName = new HashMap<>();
        sceneToPrettyName.put(scene.INTRO, "Intro");
        sceneToPrettyName.put(scene.BLACKJACK, "Blackjack");
        sceneToPrettyName.put(scene.POKER, "Poker");
        sceneToPrettyName.put(scene.MENU, "Menu");

        prettyNameToScene = new HashMap<>();
        prettyNameToScene.put("intro", scene.INTRO);
        prettyNameToScene.put("blackjack", scene.BLACKJACK);
        prettyNameToScene.put("poker", scene.POKER);
        prettyNameToScene.put("menu", scene.MENU);
        setActiveScene(scene.MENU);
    }

    public static scene getActiveScene() {
        return activeScene;
    }

    public static void setActiveScene(scene activeScene) {
        switch (activeScene) {
            case MENU -> {
                SoundEffectHelper.stopAll();
                // input processor changes prevent console from receiving input without reset
                Gdx.input.setInputProcessor(new MenuInputProcessor());
                Casino.getActiveConsole().reset();
            }
            case BLACKJACK -> SoundEffectHelper.stopAll();
        }
        SceneManager.activeScene = activeScene;
    }

    public static String getActiveScenePretty() {
        return sceneToPrettyName.get(activeScene);
    }

    public static scene getSceneAsEnum(String prettyName) {
        prettyName = prettyName.toLowerCase();
        return prettyNameToScene.get(prettyName);
    }

    public enum scene {
        INTRO,
        BLACKJACK,
        POKER,
        MENU
    }
}
