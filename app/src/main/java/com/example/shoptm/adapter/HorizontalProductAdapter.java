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
import com.example.shoptm.model.HorizontalProduct;

import java.util.List;

public class HorizontalProductAdapter extends RecyclerView.Adapter<HorizontalProductAdapter.ViewHolder> {
    private List<HorizontalProduct> listHorizontalProduct;
    Context context;

    public HorizontalProductAdapter(List<HorizontalProduct> listHorizontalProduct, Context context) {
        this.listHorizontalProduct = listHorizontalProduct;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.horizontal_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        int resource = listHorizontalProduct.get(i).getProductImage();
        String title = listHorizontalProduct.get(i).getProductTitle();
        String description = listHorizontalProduct.get(i).getProductDescription();
        String price = listHorizontalProduct.get(i).getProductPrice();

        viewHolder.setProductImage(resource);
        viewHolder.setProductTitle(title);
        viewHolder.setProductDescription(description);
        viewHolder.setProductPrice(price);

    }

    @Override
    public int getItemCount() {
        return listHorizontalProduct.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imvProductImage;
        TextView tvProductTitle, tvProductDescription, tvProductPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imvProductImage = itemView.findViewById(R.id.imv_imageItem);
            tvProductTitle = itemView.findViewById(R.id.tv_nameItem);
            tvProductDescription = itemView.findViewById(R.id.tv_detail);
            tvProductPrice = itemView.findViewById(R.id.tv_price);
        }

        private void setProductImage(int resource) {
            imvProductImage.setImageResource(resource);
        }

        private void setProductTitle(String title) {
            tvProductTitle.setText(title);
        }

        private void setProductDescription(String description) {
            tvProductDescription.setText(description);
        }

        private void setProductPrice(String price) {
            tvProductPrice.setText(price);
        }
    }
}
