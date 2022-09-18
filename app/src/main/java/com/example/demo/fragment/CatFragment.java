package com.example.demo.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo.Adapter.CategoryAdapter;
import com.example.demo.R;
import com.example.demo.model.CategoryModel;
import com.example.demo.webservice.Network;
import com.example.demo.webservice.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatFragment extends Fragment {
    RecyclerView recyclerView;
    CategoryAdapter adapter;
    TextView tvAddItem;

    public CatFragment() {
        /*this.tvAddItem = tvAddItem;*/
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cat, container, false);
        recyclerView = view.findViewById(R.id.cat_recycle);
        Network service = RetrofitInstance.getRetrofitInstance().create(Network.class);
        tvAddItem = getActivity().findViewById(R.id.tv_add_item);
        /** Call the method with parameter in the interface to get the notice data*/
        Call<CategoryModel> call = service.catgoryList();

        call.enqueue(new Callback<CategoryModel>() {
            @Override
            public void onResponse(Call<CategoryModel> call, Response<CategoryModel> response) {
                Log.e("", "Response cat" + response.body().categories);
                addData(response.body().categories);
            }

            @Override
            public void onFailure(Call<CategoryModel> call, Throwable t) {

            }
        });

        return view;
    }

    private void addData(List<CategoryModel.Category> categories) {
        adapter = new CategoryAdapter(getContext(), categories);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        tvAddItem.setVisibility(View.GONE);
    }

}