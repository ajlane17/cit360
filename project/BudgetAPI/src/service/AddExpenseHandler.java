package service;

import dao.ExpenseDao;
import entity.Expense;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class AddExpenseHandler implements BudgetHandler {

    String responseBody = "";
    ExpenseDao expenseDao;

    @Override
    public void execute(HashMap<String, Object> data, HttpServletResponse response) {
        try {
            System.out.println("AddExpenseHandler executing...");
            System.out.println("Parsing request body...");

            String pattern = "yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

            Date expenseDate = simpleDateFormat.parse((String) data.get("expenseDate"));
            double expenseAmount = (double) data.get("expenseAmount");
            Long categoryId = ((Double) data.get("categoryId")).longValue();
            String expenseMerchant = (String) data.get("expenseMerchant");

            Expense expense = new Expense();
            expense.setDate(expenseDate);
            expense.setAmount(expenseAmount);
            expense.setCategoryId(categoryId);
            expense.setMerchant(expenseMerchant);

            System.out.println("Adding expense: " + expense.toString());

            expenseDao = new ExpenseDao();

            expenseDao.save(expense);

            responseBody = "Added expense: " + expense.toString();

        } catch (NullPointerException e) {
            System.out.println("Request not properly formatted, received: " + data.toString());
            responseBody = "Request not properly formatted, received: " + data.toString();
            response.setStatus(500);
        } catch (Exception e) {
            System.out.println("Error received while creating expense: " + e.getMessage());
            responseBody = "Error received while creating expense: " + e.getMessage();
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
