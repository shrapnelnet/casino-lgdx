package com.shr4pnel.casino.console;

import com.shr4pnel.casino.scene.SceneManager;
import com.strongjoshua.console.CommandExecutor;
import com.strongjoshua.console.annotation.ConsoleDoc;

public class ConsoleExecutor extends CommandExecutor {
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
}
