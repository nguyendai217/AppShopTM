package com.example.shoptm.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shoptm.R;
import com.example.shoptm.model.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private List<Category>categoryList;


    public CategoryAdapter(List<Category> categoryList) {
        this.categoryList = categoryList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_item, viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String icon= categoryList.get(i).getCategoryIconLink();
        String name= categoryList.get(i).getCategoryName();
        viewHolder.setCategoryName(name);
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imvIcon;
        private TextView tvCategoryName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imvIcon= itemView.findViewById(R.id.category_icon);
            tvCategoryName=itemView.findViewById(R.id.category_name);

        }
        private void setCategoryIcon(){

        }
        private void setCategoryName(String categoryName){
            tvCategoryName.setText(categoryName);
        }

    }
}
