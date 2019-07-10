package service;

import dao.ExpenseDao;
import entity.Expense;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class UpdateExpenseHandler implements BudgetHandler {

    String responseBody = "";
    ExpenseDao expenseDao;

    @Override
    public void execute(HashMap<String, Object> data, HttpServletResponse response) {
        try {
            System.out.println("UpdateExpenseHandler executing...");
            System.out.println("Parsing request body...");

            String pattern = "yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

            Long expenseId = ((Double) data.get("expenseId")).longValue();
            Date expenseDate = simpleDateFormat.parse((String) data.get("expenseDate"));
            double expenseAmount = (double) data.get("expenseAmount");
            Long categoryId = ((Double) data.get("categoryId")).longValue();
            String expenseMerchant = (String) data.get("expenseMerchant");

            expenseDao = new ExpenseDao();

            Expense currExpense = expenseDao.get(expenseId);

            if (currExpense == null) {
                throw new IllegalArgumentException("Expense to update does not exist");
            }

            currExpense.setDate(expenseDate);
            currExpense.setAmount(expenseAmount);
            currExpense.setCategoryId(categoryId);
            currExpense.setMerchant(expenseMerchant);

            System.out.println("Updating expense to: " + currExpense.toString());

            expenseDao.update(currExpense);

            responseBody = "Updated expense: " + currExpense.toString();

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
