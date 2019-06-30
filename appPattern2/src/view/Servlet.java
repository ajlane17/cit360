package view;

import com.google.gson.Gson;
import controller.ApplicationController;
import controller.SpeakHandler;
import controller.ObjectHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "Servlet")
public class Servlet extends HttpServlet {

    private ApplicationController applicationController = new ApplicationController();
    Gson gson = new Gson();

    public void init(){
        applicationController.mapCommand("Speak", new SpeakHandler());
        applicationController.mapCommand("Object", new ObjectHandler());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            System.out.println("Servlet GET called");

            String jsonParams = gson.toJson(request.getParameterMap());

            HashMap<String, Object> dataMap = gson.fromJson(jsonParams, HashMap.class);
            System.out.println("dataMap to string: " + dataMap.toString());

            List<String> command = (ArrayList<String>) dataMap.get("command");

            applicationController.handleRequest(command.get(0), dataMap, response);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
