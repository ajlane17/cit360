package com.adrianjlane.expensetracker.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.adrianjlane.expensetracker.R;
import com.adrianjlane.expensetracker.presenter.CategoryDetailPresenter;

public class CategoryDetailActivity extends AppCompatActivity {

    CategoryDetailPresenter categoryDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_detail);

        categoryDetailPresenter = new CategoryDetailPresenter(this);

        Intent intent = getIntent();
        String strCategory = intent.getStringExtra("category");

        if (strCategory != null) {
            categoryDetailPresenter.displayCategoryDetails(strCategory);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Set up the menu
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.item_details_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        boolean isSuccess;

        // Attach actions to the menu items
        switch (item.getItemId()) {
            case R.id.action_save:
                isSuccess = categoryDetailPresenter.saveCategory();
                if (isSuccess) {
                    finish();
                }
                return true;
            case R.id.action_cancel:
                finish();
                return true;
            case R.id.action_delete:
                isSuccess = categoryDetailPresenter.deleteCategory();
                if (isSuccess) {
                    finish();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
