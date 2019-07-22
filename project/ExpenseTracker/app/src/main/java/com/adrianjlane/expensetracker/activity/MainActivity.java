package com.adrianjlane.expensetracker.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.adrianjlane.expensetracker.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void navigateToCategories(View view) {
        Intent intent = new Intent(this, ManageCategoriesActivity.class);

        startActivity(intent);
    }

    public void navigateToExpenses(View view) {
        Intent intent = new Intent(this, ManageExpensesActivity.class);

        startActivity(intent);
    }
}
