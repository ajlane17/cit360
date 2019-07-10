package service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dao.ExpenseDao;
import entity.Expense;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

public class GetAllExpenseHandler implements BudgetHandler {

    Gson gson = new Gson();
    String responseBody = "";
    List<Expense> expenses;
    ExpenseDao expenseDao;

    @Override
    public void execute(HashMap<String, Object> data, HttpServletResponse response) {
        try {
            System.out.println("GetAllExpenseHandler executing...");
            System.out.println("Parsing request...");

            System.out.println("Fetching expenses...");

            expenseDao = new ExpenseDao();
            expenses = expenseDao.getAll();

            if (expenses != null) {
                Type listType = new TypeToken<List<Expense>>() {}.getType();
                responseBody = gson.toJson(expenses, listType);
            }

        } catch (Exception e) {
            System.out.println("Error received while fetching expenses: " + e.getMessage());
            responseBody = "Error received while fetching expenses: " + e.getMessage();
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
