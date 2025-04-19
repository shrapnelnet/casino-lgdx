package com.shr4pnel.casino.console;

import com.strongjoshua.console.CommandExecutor;
import com.strongjoshua.console.annotation.ConsoleDoc;

public class ConsoleExecutor extends CommandExecutor {
    @ConsoleDoc(description = "Clears the console buffer")
    public void clear() {
        console.clear();
    }
}
