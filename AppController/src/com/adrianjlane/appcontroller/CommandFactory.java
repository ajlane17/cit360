package com.adrianjlane.appcontroller;

public class CommandFactory {
    private static CommandFactory instance = null;

    private CommandFactory() {

    }

    public static CommandFactory getInstance() {
        if (instance == null) {
            instance = new CommandFactory();
        }
        return instance;
    }

    public Command getCommand(String commandName) {
        return new Command(commandName);
    }
}
