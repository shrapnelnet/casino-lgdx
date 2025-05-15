package com.shr4pnel.casino.scene;

import com.badlogic.gdx.Gdx;
import com.shr4pnel.casino.Casino;
import com.shr4pnel.casino.audio.SoundEffectHelper;
import com.shr4pnel.casino.input.BlackjackInputProcessor;
import com.shr4pnel.casino.input.MenuInputProcessor;
import com.shr4pnel.casino.input.NavigationInputProcessor;
import com.shr4pnel.casino.input.PokerInputProcessor;
import com.shr4pnel.casino.input.SlotsInputProcessor;
import com.shr4pnel.casino.input.RouletteInputProcessor;
import com.shr4pnel.casino.input.LootboxesInputProcessor;


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
        sceneToPrettyName.put(Scene.NAVIGATION, "Navigation");
        sceneToPrettyName.put(Scene.SLOTS, "Slots");
        sceneToPrettyName.put(Scene.ROULETTE, "Roulette");
        sceneToPrettyName.put(Scene.LOOTBOXES, "Lootboxes");

        prettyNameToScene = new HashMap<>();
        prettyNameToScene.put("intro", Scene.INTRO);
        prettyNameToScene.put("blackjack", Scene.BLACKJACK);
        prettyNameToScene.put("poker", Scene.POKER);
        prettyNameToScene.put("menu", Scene.MENU);
        prettyNameToScene.put("navigation", Scene.NAVIGATION);
        prettyNameToScene.put("slots", Scene.SLOTS);
        prettyNameToScene.put("roulette", Scene.ROULETTE);
        prettyNameToScene.put("lootboxes", Scene.LOOTBOXES);
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
            case NAVIGATION -> {
                SoundEffectHelper.stopAll();

                ManagedButtonScene navigationInstance = Casino
                    .getInstance()
                    .getSceneInstance(Scene.NAVIGATION);

                Gdx.input.setInputProcessor(new NavigationInputProcessor(navigationInstance));
                Casino.getActiveConsole().reset();
            }
            case POKER -> {
                SoundEffectHelper.stopAll();

                ManagedButtonScene pokerInstance = Casino
                    .getInstance()
                    .getSceneInstance(Scene.POKER);

                Gdx.input.setInputProcessor(new PokerInputProcessor(pokerInstance));
                Casino.getActiveConsole().reset();
            }
            case SLOTS -> {
                SoundEffectHelper.stopAll();

                ManagedButtonScene slotsInstance = Casino
                    .getInstance()
                    .getSceneInstance(Scene.SLOTS);

                Gdx.input.setInputProcessor(new SlotsInputProcessor(slotsInstance));
                Casino.getActiveConsole().reset();
            }
            case ROULETTE -> {
                SoundEffectHelper.stopAll();

                ManagedButtonScene rouletteInstance = Casino
                    .getInstance()
                    .getSceneInstance(Scene.ROULETTE);

                Gdx.input.setInputProcessor(new RouletteInputProcessor(rouletteInstance));
                Casino.getActiveConsole().reset();
            }
            case LOOTBOXES -> {
                SoundEffectHelper.stopAll();

                ManagedButtonScene lootboxInstance = Casino
                    .getInstance()
                    .getSceneInstance(Scene.LOOTBOXES);

                Gdx.input.setInputProcessor(new LootboxesInputProcessor(lootboxInstance));
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
        MENU,
        NAVIGATION,
        POKER,
        SLOTS,
        ROULETTE,
        LOOTBOXES

    }
}
