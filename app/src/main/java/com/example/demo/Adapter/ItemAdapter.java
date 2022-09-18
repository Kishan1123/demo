package com.example.demo.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.TypeConverter;

import com.bumptech.glide.Glide;
import com.example.demo.R;
import com.example.demo.databaseHelper.StockModel;
import com.example.demo.model.CategoryModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemAdapter  extends RecyclerView.Adapter<ItemAdapter.StockViewHolder> {


    private List<StockModel> dataList;
    Context context;
    Onclick onClick;
    public ItemAdapter(Context context, List<StockModel> dataList,Onclick onClick) {
        this.dataList = dataList;
        this.context = context;
        this.onClick = onClick;
    }

    @Override
    public StockViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item, parent, false);
        return new StockViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StockViewHolder holder, int position) {
        holder.tvPrice.setText(dataList.get(position).price +" $");
        holder.tvTitle.setText(dataList.get(position).title );
        holder.tvCategory.setText(dataList.get(position).category );
        holder.ivImage.setImageBitmap(getBitMapFromStr(dataList.get(position).getImage()));
        holder.tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onClickEditItem(dataList.get(position));
            }
        });
        holder.tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onClickDeleteItem(dataList.get(position));
            }
        });

        if (dataList.get(position).inStock==true){
            holder.tvStock.setText("inStock");
        }else{
            holder.tvStock.setText("Out of Stock");
        }


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }



    class StockViewHolder extends RecyclerView.ViewHolder {

         @BindView(R.id.tv_price)
         TextView tvPrice;
         @BindView(R.id.tv_title)
         TextView tvTitle;
         @BindView(R.id.tv_category)
         TextView tvCategory;
         @BindView(R.id.in_stock)
         TextView tvStock;
         @BindView(R.id.tv_edit)
         TextView tvEdit;
         @BindView(R.id.tv_delete)
         TextView tvDelete;
        @BindView(R.id.iv_image)
        ImageView ivImage;


         public StockViewHolder(View view) {
             super(view);
             tvPrice=view.findViewById(R.id.tv_price);
             tvTitle=view.findViewById(R.id.tv_title);
             tvCategory=view.findViewById(R.id.tv_category);
             tvStock=view.findViewById(R.id.in_stock);
             tvEdit=view.findViewById(R.id.tv_edit);
             tvDelete=view.findViewById(R.id.tv_delete);
             ivImage=view.findViewById(R.id.iv_image);
         }
     }
    public interface Onclick{
        void onClickEditItem(StockModel stockModel);
        void onClickDeleteItem(StockModel stockModel);
    }

    public void addNewData(List<StockModel> stockModels){
        dataList.clear();
        dataList.addAll(stockModels);
        notifyDataSetChanged();
    }

    @TypeConverter
    public static Bitmap getBitMapFromStr(byte[] arry){
        return BitmapFactory.decodeByteArray(arry,0,arry.length);
    }

}
