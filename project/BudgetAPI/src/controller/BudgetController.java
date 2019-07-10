package controller;

import service.BudgetHandler;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

public class BudgetController {

    private HashMap<String, BudgetHandler> handlerMap = new HashMap<>();

    public void handleRequest(String command, HashMap<String, Object> data, HttpServletResponse response) {
        BudgetHandler commandHandler = handlerMap.get(command);
        if (commandHandler != null) {
            commandHandler.execute(data, response);
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

    public void mapCommand(String command, BudgetHandler handler) {
        handlerMap.put(command, handler);
    }
}
