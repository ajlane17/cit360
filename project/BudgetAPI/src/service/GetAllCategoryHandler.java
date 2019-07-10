package service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entity.Category;
import dao.CategoryDao;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

public class GetAllCategoryHandler implements BudgetHandler {

    Gson gson = new Gson();
    String responseBody = "";
    List<Category> categories;
    CategoryDao categoryDao;

    @Override
    public void execute(HashMap<String, Object> data, HttpServletResponse response) {
        try {
            System.out.println("GetAllCategoryHandler executing...");
            System.out.println("Parsing request...");

            System.out.println("Fetching categories...");

            categoryDao = new CategoryDao();
            categories = categoryDao.getAll();

            if (categories != null) {
                Type listType = new TypeToken<List<Category>>() {}.getType();
                responseBody = gson.toJson(categories, listType);
            }

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
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
