package com.adrianjlane.appcontroller;

public class WebApplicationController implements ApplicationController {

    @Override
    public void init() {

    }

    @Override
    public ResponseContext handleRequest(RequestContext requestContext) {

        ResponseContext responseContext = null;
        try {
            String commandName = requestContext.getCommandName();

            CommandFactory commandFactory = CommandFactory.getInstance();
            Command command = commandFactory.getCommand(commandName);

            CommandProcessor commandProcessor = new CommandProcessor();
            responseContext = commandProcessor.invoke(command, requestContext);
        } catch (Exception e) {
            // needs InstantiationException & IllegalAccessException
        }

        return responseContext;
    }

    @Override
    public void handleResponse(RequestContext requestContext, ResponseContext responseContext) {

    }

    @Override
    public void destroy() {

    }
}
