package com.adrianjlane.expensetracker.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.adrianjlane.expensetracker.R;
import com.adrianjlane.expensetracker.presenter.ManageCategoriesPresenter;

public class ManageCategoriesActivity extends AppCompatActivity {

    ManageCategoriesPresenter categoryPresenter = new ManageCategoriesPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        categoryPresenter.displayAllCategories();
    }

    @Override
    protected void onResume() {
        super.onResume();
        categoryPresenter.displayAllCategories();
    }

    public void addCategory(View view) {
        startActivity(new Intent(getApplicationContext(), CategoryDetailActivity.class));
    }
}
