package com.adrianjlane.expensetracker.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.adrianjlane.expensetracker.R;
import com.adrianjlane.expensetracker.model.Category;
import com.adrianjlane.expensetracker.model.Expense;
import com.adrianjlane.expensetracker.view.ExpenseAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ManageExpensesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);

        // TODO: Remove static test
        RecyclerView recyclerView = findViewById(R.id.expense_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        List<Category> categories = new ArrayList<>();

        Category category1 = new Category();
        category1.setId(1L);
        category1.setName("Category 1");
        category1.setBudget(50.00);
        categories.add(category1);

        Category category2 = new Category();
        category2.setId(2L);
        category2.setName("Category 2");
        category2.setBudget(50.00);
        categories.add(category2);

        Category category3 = new Category();
        category3.setId(3L);
        category3.setName("Category 3");
        category3.setBudget(50.00);
        categories.add(category3);

        Category category4 = new Category();
        category4.setId(4L);
        category4.setName("Category 4");
        category4.setBudget(50.00);
        categories.add(category4);

        Category category5 = new Category();
        category5.setId(5L);
        category5.setName("Category 5");
        category5.setBudget(50.00);
        categories.add(category5);

        Category category6 = new Category();
        category6.setId(6L);
        category6.setName("Category 6");
        category6.setBudget(50.00);
        categories.add(category6);

        Category category7 = new Category();
        category7.setId(7L);
        category7.setName("Category 7");
        category7.setBudget(50.00);
        categories.add(category7);

        Category category8 = new Category();
        category8.setId(8L);
        category8.setName("Category 8");
        category8.setBudget(50.00);
        categories.add(category8);

        List<Expense> expenses = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

        try {
            Expense expense1 = new Expense();
            expense1.setAmount(23.23);
            expense1.setCategoryId(1);
            expense1.setDate(format.parse("01-01-2019"));
            expense1.setMerchant("Target");
            expenses.add(expense1);

            Expense expense2 = new Expense();
            expense2.setAmount(23.23);
            expense2.setCategoryId(2);
            expense2.setDate(format.parse("01-01-2019"));
            expense2.setMerchant("Target");
            expenses.add(expense2);

            Expense expense3 = new Expense();
            expense3.setAmount(23.23);
            expense3.setCategoryId(3);
            expense3.setDate(format.parse("01-01-2019"));
            expense3.setMerchant("Target");
            expenses.add(expense3);

            Expense expense4 = new Expense();
            expense4.setAmount(23.23);
            expense4.setCategoryId(4);
            expense4.setDate(format.parse("01-01-2019"));
            expense4.setMerchant("Target");
            expenses.add(expense4);

            Expense expense5 = new Expense();
            expense5.setAmount(23.23);
            expense5.setCategoryId(5);
            expense5.setDate(format.parse("01-01-2019"));
            expense5.setMerchant("Target");
            expenses.add(expense5);

            Expense expense6 = new Expense();
            expense6.setAmount(23.23);
            expense6.setCategoryId(6);
            expense6.setDate(format.parse("01-01-2019"));
            expense6.setMerchant("Target");
            expenses.add(expense6);
        } catch (Exception e) {
            e.printStackTrace();
        }

        RecyclerView.Adapter adapter = new ExpenseAdapter(expenses, categories);
        recyclerView.setAdapter(adapter);
    }
}
