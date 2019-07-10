package service;

import com.google.gson.Gson;
import dao.CategoryDao;
import dao.ExpenseDao;
import entity.Category;
import entity.Expense;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

public class GetExpenseHandler implements BudgetHandler {

    Gson gson = new Gson();
    String responseBody = "";
    Expense expense;
    ExpenseDao expenseDao;

    @Override
    public void execute(HashMap<String, Object> data, HttpServletResponse response) {
        try {
            System.out.println("GetExpenseHandler executing...");
            System.out.println("Parsing request...");

            Long expenseId = Long.valueOf(((List<String>) data.get("expenseId")).get(0));

            System.out.println("Fetching expense: " + expenseId);

            expenseDao = new ExpenseDao();
            expense = expenseDao.get(expenseId);

            if (expense == null) {
                throw new IllegalArgumentException("Expense not found");
            }

            responseBody = gson.toJson(expense, Expense.class);

        } catch (NullPointerException e) {
            System.out.println("Request not properly formatted, received: " + data.toString());
            responseBody = "Request not properly formatted, received: " + data.toString();
            response.setStatus(500);
        } catch (Exception e) {
            System.out.println("Error received while fetching expense: " + e.getMessage());
            responseBody = "Error received while fetching expense: " + e.getMessage();
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
