package com.adrianjlane.appcontroller;

public class CommandProcessor {

    public ResponseContext invoke(Command command, RequestContext requestContext) {
        String result = command.doSomething(requestContext.getCommandName());

        return new ResponseContext(result);
    }
}
