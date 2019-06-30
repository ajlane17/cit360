package controller;

import com.google.gson.Gson;
import model.SomeObject;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ObjectHandler implements Handler {

    Gson gson = new Gson();

    @Override
    public void handleIt(HashMap<String, Object> dataMap, HttpServletResponse response) {
        try {
            System.out.println("Doing \"Object\" stuff");

            List<String> objectProperties = new ArrayList<>();
            objectProperties.add("some");
            objectProperties.add("properties");
            objectProperties.add("have");
            objectProperties.add( "been");
            objectProperties.add("added");

            SomeObject responseObject = new SomeObject(1, "Object", objectProperties);

            String responseBody = gson.toJson(responseObject);

            response.getWriter().write(responseBody);

            response.getWriter().flush();
            response.getWriter().close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
