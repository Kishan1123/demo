package com.example.demo.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.demo.Adapter.ViewPagerAdapter;
import com.example.demo.R;
import com.example.demo.fragment.CatFragment;
import com.example.demo.fragment.ItemFragment;
import com.example.demo.model.CategoryModel;
import com.example.demo.ui.AddItem.ui.AddItemActivity;
import com.example.demo.webservice.Network;
import com.example.demo.webservice.RetrofitInstance;
import com.google.android.material.tabs.TabLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Categories extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout tabLayout;
    TextView tvAddItem;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tab_layot);
        tvAddItem = findViewById(R.id.tv_add_item);
        tabLayout.setupWithViewPager(viewPager);
        tvAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Categories.this, AddItemActivity.class);
                startActivity(intent);
            }
        });
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), this);
        adapter.addFragment(new CatFragment(), "Categories");
        adapter.addFragment(new ItemFragment(), "Item");
        viewPager.setAdapter(adapter);
    }
}