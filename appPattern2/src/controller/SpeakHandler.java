package controller;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SpeakHandler implements Handler {

    @Override
    public void handleIt(HashMap<String, Object> dataMap, HttpServletResponse response) {
        try {
            List<String> message = (ArrayList<String>) dataMap.get("message");
            System.out.println("Doing \"Speak\" stuff with message: " + message.get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
