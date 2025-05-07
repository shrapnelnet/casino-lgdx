package com.shr4pnel.casino.console;

import com.badlogic.gdx.Gdx;
import com.shr4pnel.casino.scene.SceneManager;
import com.shr4pnel.casino.style.StyleManager;
import com.strongjoshua.console.CommandExecutor;
import com.strongjoshua.console.annotation.ConsoleDoc;

import java.util.HashMap;
import java.util.Map;

public class ConsoleExecutor extends CommandExecutor {
    private Map<String, Boolean> stateStringToBool = new HashMap<>(2);

    public ConsoleExecutor() {
        stateStringToBool.put("on", true);
        stateStringToBool.put("off", false);
    }

    @ConsoleDoc(description = "Clears the console buffer")
    public void clear() {
        console.clear();
    }

    @ConsoleDoc(description = "GOTO specific scene of ['intro', 'blackjack', 'poker']")
    public void coc(String scene) {
        SceneManager.setActiveScene(SceneManager.getSceneAsEnum(scene));
    }

    @ConsoleDoc(description = "Gets the active scene")
    public void getscene() {
        console.log(SceneManager.getActiveScenePretty());
    }

    @ConsoleDoc(description = "Enable or disable post processing")
    public void pp(String enabled) {
        if (!(enabled.equals("on") || enabled.equals("off"))) {
            console.log("Not one of ['on', 'off']");
            return;
        }
        StyleManager.setPostProcessingEnabled(stateStringToBool.get(enabled));
    }

    @ConsoleDoc(description = "Reinitialize starting state, replay intro")
    public void restart() {
        SceneManager.setActiveScene(SceneManager.Scene.INTRO);
    }

    @ConsoleDoc(description = "Exit the game")
    public void exit() {
        Gdx.app.exit();
    }
}
