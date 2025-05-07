package com.shr4pnel.casino.console;

import com.badlogic.gdx.Input;
import com.strongjoshua.console.CommandExecutor;
import com.strongjoshua.console.Console;
import com.strongjoshua.console.GUIConsole;
import com.strongjoshua.console.LogLevel;

/**
 * Manages the lifecycle of the console
 */
public class ConsoleManager {
    private final Console console = new GUIConsole();
    private final ConsoleExecutor executor = new ConsoleExecutor();

    public ConsoleManager(boolean enabled) {
        console.setCommandExecutor(executor);
        console.setDisplayKeyID(Input.Keys.GRAVE);
        console.setDisabled(!enabled);
    }

    public ConsoleManager() {
        console.setDisplayKeyID(Input.Keys.GRAVE);
        console.setCommandExecutor(executor);
    }

    /**
     * Disposes of the console instance
     */
    public void destroy() {
        console.dispose();
    }

    /**
     * Draw the console on the screen
     */
    public void draw() {
        console.draw();
    }

    public void log(String msg) {
        console.log(msg);
    }

    public void log(Throwable err) {
        console.log(err);
    }

    public void log(String msg, LogLevel lvl) {
        console.log(msg, lvl);
    }

    public void log(Throwable err, LogLevel lvl) {
        console.log(err, lvl);
    }

    /**
     * Reset console input processing, necessary when changing the active input processor
     */
    public void reset() {
        console.resetInputProcessing();
    }
}
