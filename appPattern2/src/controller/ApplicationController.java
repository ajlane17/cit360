package controller;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

public class ApplicationController {

    private HashMap<String, Handler> handlerMap = new HashMap<>();

    public void handleRequest(String command, HashMap<String, Object> data, HttpServletResponse response) {
        Handler commandHandler = handlerMap.get(command);
        if (commandHandler != null) {
            commandHandler.handleIt(data, response);
        }
        else {
            try {
                response.setStatus(500);
                response.getWriter().write("Not a valid command: " + command);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void mapCommand(String command, Handler handler) {
        handlerMap.put(command, handler);
    }
}
