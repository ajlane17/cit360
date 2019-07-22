package com.adrianjlane.expensetracker.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.adrianjlane.expensetracker.R;
import com.adrianjlane.expensetracker.model.Category;
import com.adrianjlane.expensetracker.model.Expense;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ViewHolder> {
    private List<Expense> expenses;
    private List<Category> categories;
    private HashMap<Long, Category> categoryMap;

    public ExpenseAdapter(List<Expense> expenses, List <Category> categories) {
        this.expenses = expenses;
        this.categories = categories;

        categoryMap = new HashMap<>();

        if (categories != null || categories.size() <= 0) {
            for (Category category : categories) {
                categoryMap.put(category.getId(), category);
            }
        }
    }

    @Override
    public ExpenseAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.expense_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ExpenseAdapter.ViewHolder viewHolder, int i) {

        final Expense expense = expenses.get(i);

        SimpleDateFormat format = new SimpleDateFormat("M-dd-yyyy");
        String dateString = format.format(expense.getDate());

        String categoryName = "";

        if (categoryMap != null || categoryMap.size() <= 0) {
            Category category = categoryMap.get(expense.getCategoryId());
            categoryName = category.getName();
        }

        viewHolder.expenseDate.setText(dateString);
        viewHolder.expenseMerchant.setText(expense.getMerchant());
        viewHolder.expenseCategory.setText(categoryName);
        viewHolder.expenseAmount.setText(String.format("%1$,.2f", expense.getAmount()));
    }

    @Override
    public int getItemCount() {
        return expenses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView expenseDate;
        private TextView expenseAmount;
        private TextView expenseCategory;
        private TextView expenseMerchant;

        public ViewHolder(View view) {
            super(view);

            expenseDate = view.findViewById(R.id.expense_date);
            expenseAmount = view.findViewById(R.id.expense_amount);
            expenseCategory = view.findViewById(R.id.expense_category);
            expenseMerchant = view.findViewById(R.id.expense_merchant);
        }
    }
}
