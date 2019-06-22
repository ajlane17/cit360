package com.adrianjlane.appcontroller;

public class Command {

    public Command(String commandName) {
        System.out.println("A new command of " + commandName + " was created");
    }

    public String doSomething(String command) {
        System.out.println("Processed request with command: " + command);
        return "Success";
    }
}
