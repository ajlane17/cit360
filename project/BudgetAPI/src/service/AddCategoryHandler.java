package service;

import entity.Category;
import dao.CategoryDao;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

public class AddCategoryHandler implements BudgetHandler {

    String responseBody = "";
    CategoryDao categoryDao;

    @Override
    public void execute(HashMap<String, Object> data, HttpServletResponse response) {
        try {
            System.out.println("AddCategoryHandler executing...");

            System.out.println("Parsing request body...");

            String categoryName = (String) data.get("categoryName");
            double categoryBudget = (double) data.get("categoryBudget");

            Category category = new Category();
            category.setName(categoryName);
            category.setBudget(categoryBudget);

            System.out.println("Adding category: " + category.toString());

            categoryDao = new CategoryDao();

            categoryDao.save(category);

            responseBody = "Added category: " + category.toString();

        } catch (NullPointerException e) {
            System.out.println("Request not properly formatted, received: " + data.toString());
            responseBody = "Request not properly formatted, received: " + data.toString();
            response.setStatus(500);
        } catch (Exception e) {
            System.out.println("Error received while creating category: " + e.getMessage());
            responseBody = "Error received while creating category: " + e.getMessage();
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
