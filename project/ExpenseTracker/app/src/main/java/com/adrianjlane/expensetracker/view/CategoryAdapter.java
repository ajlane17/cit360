package com.adrianjlane.expensetracker.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.adrianjlane.expensetracker.R;
import com.adrianjlane.expensetracker.activity.CategoryDetailActivity;
import com.adrianjlane.expensetracker.model.Category;
import com.google.gson.Gson;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private List<Category> categories;

    public CategoryAdapter(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_row, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryAdapter.ViewHolder viewHolder, int i) {

        Category category = categories.get(i);
        final int itemPos = i;

        viewHolder.categoryName.setText(category.getName());
        viewHolder.categoryBudget.setText(String.format("%1$,.2f", category.getBudget()));

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(v.getContext(), CategoryDetailActivity.class);

                Gson gson = new Gson();
                String serializedCategory = gson.toJson(categories.get(itemPos), Category.class);
                intent.putExtra("category", serializedCategory);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView categoryName;
        private TextView categoryBudget;
        private final Context context;

        public ViewHolder(View view) {
            super(view);
            context = view.getContext();

            categoryName = view.findViewById(R.id.category_name);
            categoryBudget = view.findViewById(R.id.category_budget);
        }
    }
}
