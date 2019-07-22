package com.adrianjlane.expensetracker.view;

import android.app.Activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adrianjlane.expensetracker.R;
import com.adrianjlane.expensetracker.model.Category;

import java.util.List;

public class ManageCategoriesView {

    private Activity activity;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    public ManageCategoriesView(Activity activity) {
        this.activity = activity;
    }

    public void displayAllCategories(List<Category> categories) {
        recyclerView = activity.findViewById(R.id.category_recycler_view);
        layoutManager = new LinearLayoutManager(activity.getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new CategoryAdapter(categories);
        recyclerView.setAdapter(adapter);
    }
}
