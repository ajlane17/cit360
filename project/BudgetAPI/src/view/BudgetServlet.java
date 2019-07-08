package view;

import com.google.gson.Gson;
import controller.AddCategoryHandler;
import controller.BudgetController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "BudgetServlet")
public class BudgetServlet extends HttpServlet {

    private BudgetController budgetController = new BudgetController();
    Gson gson = new Gson();

    public void init() {
        // map commands go here
        budgetController.mapCommand("addCategory", new AddCategoryHandler());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            System.out.println("handleRequest called...");

            String jsonParams = gson.toJson(request.getParameterMap());

            HashMap<String, Object> dataMap = gson.fromJson(jsonParams, HashMap.class);
            System.out.println("dataMap to string: " + dataMap.toString());

            List<String> command = (ArrayList<String>) dataMap.get("command");

            budgetController.handleRequest(command.get(0), dataMap, response);
        } catch (Exception e) {
            System.out.printf(e.getMessage());
            e.printStackTrace();
        }
    }
}
