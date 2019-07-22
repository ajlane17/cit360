package com.adrianjlane.expensetracker.view;

import android.app.Activity;
import android.widget.EditText;

import com.adrianjlane.expensetracker.R;
import com.adrianjlane.expensetracker.model.Category;

import java.util.HashMap;

public class CategoryDetailView {

    private Activity activity;
    EditText categoryField;
    EditText budgetField;

    public CategoryDetailView(Activity activity) {
        this.activity = activity;
    }

    public void displayCategoryInfo(Category category) {
        categoryField = activity.findViewById(R.id.category_value);
        budgetField = activity.findViewById(R.id.budget_value);

        categoryField.setText(category.getName());
        budgetField.setText(Double.toString(category.getBudget()));
    }

    public HashMap<String, String> getCategoryValues() {
        categoryField = activity.findViewById(R.id.category_value);
        budgetField = activity.findViewById(R.id.budget_value);

        HashMap<String, String> categoryValues = new HashMap<>();

        categoryValues.put("name", categoryField.getText().toString());
        categoryValues.put("budget", budgetField.getText().toString());

        return categoryValues;
    }
}
