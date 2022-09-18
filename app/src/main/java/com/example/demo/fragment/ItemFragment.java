package com.example.demo.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.demo.Adapter.ItemAdapter;
import com.example.demo.MainActivity;
import com.example.demo.R;
import com.example.demo.databaseHelper.StockModel;
import com.example.demo.databaseHelper.StockViewModal;
import com.example.demo.ui.AddItem.ui.AddItemActivity;
import com.example.demo.ui.Categories;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemFragment extends Fragment implements ItemAdapter.Onclick {
    @BindView(R.id.recycle_item)
    RecyclerView recyclerView;

    @BindView(R.id.checkbox_show_item)
    CheckBox show_item;
    StockModel stockModel;
    StockViewModal stockViewModal;
    ItemAdapter adapter;
    Context context;
    TextView tvAddItem;


    public ItemFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("TAG", "onCreate");
        if (getArguments() != null) {
        }
    }

    List<StockModel> stockModelsNew = new ArrayList<>();
    List<StockModel> stockModelsSecond = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item, container, false);
        recyclerView = view.findViewById(R.id.recycle_item);
        show_item = view.findViewById(R.id.checkbox_show_item);
        tvAddItem = getActivity().findViewById(R.id.tv_add_item);

        stockModel = new StockModel();
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        stockViewModal = ViewModelProviders.of(this).get(StockViewModal.class);


        stockViewModal.getAllStock().observe(getActivity(), new Observer<List<StockModel>>() {
            @Override
            public void onChanged(List<StockModel> stockModels) {
                Log.e("model", stockModels.toString());
                stockModelsNew.addAll(stockModels);
                stockModelsSecond.addAll(stockModels);
                adapter = new ItemAdapter(context, stockModels, ItemFragment.this);
                recyclerView.setAdapter(adapter);
            }
        });


        show_item.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.e("TAG", "onCheckedChanged: " + b);
                if (b) {
                    List<StockModel> updateModel = new ArrayList<>();
                        for (StockModel model : stockModelsNew){
                            if (model.inStock) {
                                updateModel.add(model);
                            }
                    }
                    if (updateModel != null) {
                        adapter.addNewData(updateModel);
                    }
                } else {
                    adapter.addNewData(stockModelsSecond);
                }
            }
        });

        return view;
    }

    @Override
    public void onClickEditItem(StockModel stockModel) {

        Intent intent = new Intent(getActivity(), AddItemActivity.class);
        intent.putExtra("stockList", stockModel);
        startActivity(intent);

    }

    @Override
    public void onClickDeleteItem(StockModel stockModel) {
        Log.e("deleteModel", "" + stockModel);
        stockViewModal.delete(stockModel);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("TAG", "onResume");
        tvAddItem.setVisibility(View.VISIBLE);

    }

    @Override
    public void onPause() {
        super.onPause();

        Log.e("TAG", "onPause");
    }
}