package com.shr4pnel.casino.console;

import com.badlogic.gdx.Input;
import com.strongjoshua.console.CommandExecutor;
import com.strongjoshua.console.Console;
import com.strongjoshua.console.GUIConsole;

public class ConsoleManager {
    private Console console = new GUIConsole();
    private ConsoleExecutor executor = new ConsoleExecutor();

    public ConsoleManager(boolean enabled) {
        console.setCommandExecutor(executor);
        console.setDisplayKeyID(Input.Keys.GRAVE);
        console.setDisabled(!enabled);
    }

    public ConsoleManager() {
        console.setDisplayKeyID(Input.Keys.GRAVE);
        console.setCommandExecutor(executor);
    }

    public void destroy() {
        console.dispose();
    }

    public Console getConsole() {
        return console;
    }

    public CommandExecutor getExecutor() {
        return executor;
    }
}
