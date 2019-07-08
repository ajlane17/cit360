package controller;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddCategoryHandler implements BudgetHandler {

    Gson gson = new Gson();
    String responseBody = "";

    @Override
    public void execute(HashMap<String, Object> data, HttpServletResponse response) {
        try {
            System.out.println("AddCategoryHandler executing...");

            List<String> categoryName = (ArrayList<String>) data.get("categoryName");
            System.out.println("Adding category: " + categoryName.get(0));
            responseBody = "Added category: " + categoryName.get(0);

        } catch (NullPointerException ex) {
            System.out.println("categoryName is missing or not defined properly");
            responseBody = "categoryName is missing or not defined properly";
            response.setStatus(500);
        }

        try {
            response.getWriter().write(responseBody);
            response.getWriter().flush();
            response.getWriter().close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
