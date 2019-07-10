package service;

import entity.Category;
import dao.CategoryDao;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

public class UpdateCategoryHandler implements BudgetHandler {

    String responseBody = "";
    CategoryDao categoryDao;

    @Override
    public void execute(HashMap<String, Object> data, HttpServletResponse response) {
        try {
            System.out.println("UpdateCategoryHandler executing...");

            System.out.println("Parsing request body...");

            Long categoryId = ((Double) data.get("categoryId")).longValue();
            String categoryName = (String) data.get("categoryName");
            double categoryBudget = (double) data.get("categoryBudget");

            categoryDao = new CategoryDao();

            Category currCategory = categoryDao.get(categoryId);

            if (currCategory == null) {
                throw new IllegalArgumentException("Category to update does not exist");
            }

            currCategory.setName(categoryName);
            currCategory.setBudget(categoryBudget);

            System.out.println("Updating category to: " + currCategory.toString());

            categoryDao.update(currCategory);

            responseBody = "Updated Category: " + currCategory.toString();

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
