package service;

import com.google.gson.Gson;
import entity.Category;
import dao.CategoryDao;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

public class GetCategoryHandler implements BudgetHandler {

    Gson gson = new Gson();
    String responseBody = "";
    Category category;
    CategoryDao categoryDao;

    @Override
    public void execute(HashMap<String, Object> data, HttpServletResponse response) {
        try {
            System.out.println("GetCategoryHandler executing...");
            System.out.println("Parsing request...");

            Long categoryId = Long.valueOf(((List<String>) data.get("categoryId")).get(0));

            System.out.println("Fetching category: " + categoryId);

            categoryDao = new CategoryDao();
            category = categoryDao.get(categoryId);

            if (category == null) {
                throw new IllegalArgumentException("Category not found");
            }

            responseBody = gson.toJson(category, Category.class);

        } catch (NullPointerException e) {
            System.out.println("Request not properly formatted, received: " + data.toString());
            responseBody = "Request not properly formatted, received: " + data.toString();
            response.setStatus(500);
        } catch (Exception e) {
            System.out.println("Error received while fetching category: " + e.getMessage());
            responseBody = "Error received while fetching category: " + e.getMessage();
            response.setStatus(500);
        }

        try {
            response.getWriter().write(responseBody);
            response.getWriter().flush();
            response.getWriter().close();

        } catch (Exception e) {
            System.out.println("Error: "+ e.getMessage());
            e.printStackTrace();
        }
    }
}
