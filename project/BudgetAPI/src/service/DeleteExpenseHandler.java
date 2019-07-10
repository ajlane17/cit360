package service;

import dao.ExpenseDao;
import entity.Expense;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

public class DeleteExpenseHandler implements BudgetHandler {

    String responseBody = "";
    ExpenseDao expenseDao;
    Expense expense;

    @Override
    public void execute(HashMap<String, Object> data, HttpServletResponse response) {
        try {
            System.out.println("DeleteExpenseHandler executing...");
            System.out.println("Parsing request body...");

            Long expenseId = ((Double) data.get("expenseId")).longValue();

            System.out.println("Fetching expense: " + expenseId);

            expenseDao = new ExpenseDao();

            expense = expenseDao.get(expenseId);

            if (expense == null) {
                throw new IllegalArgumentException("Expense not found");
            }

            System.out.println("Deleting: " + expense.toString());

            expenseDao.delete(expense);

            responseBody = "Deleted expense: " + expenseId;

        } catch (NullPointerException e) {
            System.out.println("Request not properly formatted, received: " + data.toString());
            responseBody = "Request not properly formatted, received: " + data.toString();
            response.setStatus(500);
        } catch (Exception e) {
            System.out.println("Error received while deleting expense: " + e.getMessage());
            responseBody = "Error received while deleting expense: " + e.getMessage();
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
