package com.adrianjlane.expensetracker.presenter;

import android.app.Activity;
import android.os.AsyncTask;

import com.adrianjlane.expensetracker.dto.CategoryDto;
import com.adrianjlane.expensetracker.model.Category;
import com.adrianjlane.expensetracker.service.BudgetApi;
import com.adrianjlane.expensetracker.view.CategoryDetailView;
import com.adrianjlane.expensetracker.view.NotificationsView;
import com.google.gson.Gson;

import java.util.HashMap;

public class CategoryDetailPresenter {

    private Activity activity;
    private CategoryDetailView categoryDetailView;
    private Category category;

    public CategoryDetailPresenter(Activity activity) {
        this.activity = activity;
        categoryDetailView = new CategoryDetailView(activity);
    }

    public void displayCategoryDetails(String strCategory) {
        Gson gson = new Gson();
        category = gson.fromJson(strCategory, Category.class);
        categoryDetailView.displayCategoryInfo(category);
    }

    public boolean saveCategory() {
        try {
            HashMap<String, String> categoryValues;
            categoryValues = categoryDetailView.getCategoryValues();

            String strName = categoryValues.get("name");
            String strBudget = categoryValues.get("budget");

            Double budget = validateBudgetValue(strBudget);

            if (budget != null) {
                Category newCategory = new Category();
                newCategory.setName(strName);
                newCategory.setBudget(budget);

                if (category != null) {
                    newCategory.setId(category.getId());
                    CategoryDto categoryDto = convertToDto(newCategory);
                    String strCategoryDto = new Gson().toJson(categoryDto, CategoryDto.class);
                    System.out.println(strCategoryDto);
                    new UpdateCategoryTask(strCategoryDto, "updateCategory").execute();
                } else {
                    CategoryDto categoryDto = convertToDto(newCategory);
                    String strCategoryDto = new Gson().toJson(categoryDto, CategoryDto.class);
                    System.out.println(strCategoryDto);
                    new UpdateCategoryTask(strCategoryDto, "addCategory").execute();
                }
            }

            return true;
        } catch (Exception e) {
            NotificationsView.showToast(
                    activity.getApplicationContext(), "Something went wrong");
            return false;
        }

    }

    public boolean deleteCategory() {
        if (category != null) {
            Category newCategory = new Category();
            newCategory.setId(category.getId());

            CategoryDto categoryDto = convertToDto(newCategory);
            String strCategoryDto = new Gson().toJson(categoryDto, CategoryDto.class);
            System.out.println(strCategoryDto);
            new UpdateCategoryTask(strCategoryDto, "deleteCategory").execute();
            return true;
        } else {
            NotificationsView.showToast(
                    activity.getApplicationContext(),"No category to delete");
            return false;
        }
    }

    public Double validateBudgetValue(String strBudgetValue) {
        try {
            double budgetValue = Double.parseDouble(strBudgetValue);
            return budgetValue;
        } catch (Exception e) {
            NotificationsView.showToast(
                    activity.getApplicationContext(),
                    "Error parsing budget: " + e.getMessage()
            );
            return null;
        }
    }

    private CategoryDto convertToDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        if (category.getId() != null) {
            categoryDto.setCategoryId(category.getId());
        }
        categoryDto.setCategoryName(category.getName());
        categoryDto.setCategoryBudget(category.getBudget());
        return categoryDto;
    }

    private class UpdateCategoryTask extends AsyncTask<Void, Void, Boolean> {

        private String strCategory;
        private String command;

        UpdateCategoryTask(String strCategory, String command) {
            this.strCategory = strCategory;
            this.command =  command;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            boolean isSuccess = new BudgetApi().saveCategory(strCategory, command);
            return isSuccess;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            String message = "Action successful";

            if (!aBoolean) {
                message = "Action failed";
            }

            NotificationsView.showToast(activity.getApplicationContext(), message);
        }
    }
}
