package service;

import entity.Category;
import dao.CategoryDao;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

public class DeleteCategoryHandler implements BudgetHandler {

    String responseBody = "";
    CategoryDao categoryDao;
    Category category;

    @Override
    public void execute(HashMap<String, Object> data, HttpServletResponse response) {
        try {
            System.out.println("DeleteCategoryHandler executing...");
            System.out.println("Parsing request body...");

            Long categoryId = ((Double) data.get("categoryId")).longValue();

            System.out.println("Fetching category: " + categoryId);

            categoryDao = new CategoryDao();

            category = categoryDao.get(categoryId);

            if (category == null) {
                throw new IllegalArgumentException("Category not found");
            }

            System.out.println("Deleting: " + category.toString());

            categoryDao.delete(category);

            responseBody = "Deleted category: " + categoryId;

        } catch (NullPointerException e) {
            System.out.println("Request not properly formatted, received: " + data.toString());
            responseBody = "Request not properly formatted, received: " + data.toString();
            response.setStatus(500);
        } catch (Exception e) {
            System.out.println("Error received while deleting category: " + e.getMessage());
            responseBody = "Error received while deleting category: " + e.getMessage();
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
