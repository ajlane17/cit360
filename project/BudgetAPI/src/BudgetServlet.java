import com.google.gson.Gson;
import controller.*;
import service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "BudgetServlet")
public class BudgetServlet extends HttpServlet {

    private BudgetController budgetController = new BudgetController();
    Gson gson = new Gson();

    public void init() {
        // Command mappings
        budgetController.mapCommand("addCategory", new AddCategoryHandler());
        budgetController.mapCommand("getCategory", new GetCategoryHandler());
        budgetController.mapCommand("deleteCategory", new DeleteCategoryHandler());
        budgetController.mapCommand("getAllCategories", new GetAllCategoryHandler());
        budgetController.mapCommand("updateCategory", new UpdateCategoryHandler());
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

            if ("POST".equalsIgnoreCase(request.getMethod())) {
                String requestBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
                dataMap.putAll(gson.fromJson(requestBody, HashMap.class));
            }

            System.out.println("dataMap to string: " + dataMap.toString());

            List<String> command = (ArrayList<String>) dataMap.get("command");

            budgetController.handleRequest(command.get(0), dataMap, response);
        } catch (Exception e) {
            System.out.printf(e.getMessage());
            e.printStackTrace();
        }
    }
}
