package com.example.demo.Adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.demo.R;
import com.example.demo.model.CategoryModel;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private List<CategoryModel.Category> dataList;
    Context context;

    public CategoryAdapter(Context context, List<CategoryModel.Category> dataList) {
        this.dataList = dataList;
        this.context = context;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.catgory_item, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        holder.catTxt.setText(dataList.get(position).title );
        Glide.with(context).load(dataList.get(position).image).into(holder.catImage);

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {

        TextView catTxt;
        ImageView catImage;

        CategoryViewHolder(View itemView) {
            super(itemView);
            catImage =  itemView.findViewById(R.id.cat_image);
            catTxt =  itemView.findViewById(R.id.cat_txt);
        }
    }
}