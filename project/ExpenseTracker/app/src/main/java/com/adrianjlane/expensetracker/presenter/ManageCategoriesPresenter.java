package com.adrianjlane.expensetracker.presenter;

import android.app.Activity;
import android.os.AsyncTask;

import com.adrianjlane.expensetracker.model.Category;
import com.adrianjlane.expensetracker.service.BudgetApi;
import com.adrianjlane.expensetracker.view.ManageCategoriesView;

import java.util.List;

public class ManageCategoriesPresenter {

    private Activity activity;
    private ManageCategoriesView manageCategoriesView;

    public ManageCategoriesPresenter(Activity activity) {
        this.activity = activity;
        manageCategoriesView = new ManageCategoriesView(this.activity);
    }

    public void displayAllCategories() {
        new DisplayAllCategoriesTask().execute();
    }

    private class DisplayAllCategoriesTask extends AsyncTask<Void, Void, List<Category>> {
        @Override
        protected List<Category> doInBackground(Void... voids) {
            return new BudgetApi().getAllCategories();
        }

        @Override
        protected void onPostExecute(List<Category> categories) {
            super.onPostExecute(categories);
            manageCategoriesView.displayAllCategories(categories);
        }
    }
}
