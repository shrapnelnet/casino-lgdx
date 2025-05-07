package com.shr4pnel.casino.scene;

import com.badlogic.gdx.Gdx;
import com.shr4pnel.casino.Casino;
import com.shr4pnel.casino.audio.SoundEffectHelper;
import com.shr4pnel.casino.input.BlackjackInputProcessor;
import com.shr4pnel.casino.input.MenuInputProcessor;

import java.util.HashMap;
import java.util.Map;

/**
 * Handles the currently active scene, switching between scenes and mapping strings to the Scene enum
 */
public class SceneManager {
    private static Scene activeScene;
    private static final Map<Scene, String> sceneToPrettyName;
    private static final Map<String, Scene> prettyNameToScene;

    static {
        sceneToPrettyName = new HashMap<>();
        sceneToPrettyName.put(Scene.INTRO, "Intro");
        sceneToPrettyName.put(Scene.BLACKJACK, "Blackjack");
        sceneToPrettyName.put(Scene.POKER, "Poker");
        sceneToPrettyName.put(Scene.MENU, "Menu");

        prettyNameToScene = new HashMap<>();
        prettyNameToScene.put("intro", Scene.INTRO);
        prettyNameToScene.put("blackjack", Scene.BLACKJACK);
        prettyNameToScene.put("poker", Scene.POKER);
        prettyNameToScene.put("menu", Scene.MENU);
        setActiveScene(Scene.MENU);
    }

    /**
     * @return The active scene
     */
    public static Scene getActiveScene() {
        return activeScene;
    }

    /**
     * Sets a new active scene
     * @param activeScene The scene to switch to
     */
    public static void setActiveScene(Scene activeScene) {
        switch (activeScene) {
            case MENU -> {
                SoundEffectHelper.stopAll();

                ManagedButtonScene menuInstance = Casino
                    .getInstance()
                    .getSceneInstance(Scene.MENU);

                // input processor changes prevent console from receiving input without reset
                Gdx.input.setInputProcessor(new MenuInputProcessor(menuInstance));
                Casino.getActiveConsole().reset();
            }
            case BLACKJACK -> {
                SoundEffectHelper.stopAll();

                ManagedButtonScene blackjackInstance = Casino
                    .getInstance()
                    .getGameInstance(Scene.BLACKJACK);

                Gdx.input.setInputProcessor(new BlackjackInputProcessor(blackjackInstance));
                Casino.getActiveConsole().reset();
            }
        }
        SceneManager.activeScene = activeScene;
    }

    /**
     * @return The active scenes human-readable name
     */
    public static String getActiveScenePretty() {
        return sceneToPrettyName.get(activeScene);
    }

    /**
     * Gets the active scene as an enum
     * @param prettyName The human-readable name of the scene
     * @return The specified scene, as an emum, or null if the scene does not exist
     */
    public static Scene getSceneAsEnum(String prettyName) {
        prettyName = prettyName.toLowerCase();
        return prettyNameToScene.get(prettyName);
    }

    public enum Scene {
        INTRO,
        BLACKJACK,
        POKER,
        MENU
    }
}
