package com.example.demo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.demo.model.LogInModel;
import com.example.demo.model.Result;
import com.example.demo.ui.Categories;
import com.example.demo.webservice.Network;
import com.example.demo.webservice.RetrofitInstance;

import java.io.IOException;

import butterknife.BindView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.u_name)
    EditText userName;

    @BindView(R.id.pwd)
    EditText pwd;

    @BindView(R.id.login)
    Button login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = findViewById(R.id.login);
        userName = findViewById(R.id.u_name);
        pwd = findViewById(R.id.pwd);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callApi();
            }
        });

    }

    private void callApi() {
        String username = userName.getText().toString();
        String password = pwd.getText().toString();


        Network service = RetrofitInstance.getRetrofitInstance().create(Network.class);
        Call<Result> call = service.loginWithCredentials(username,password);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.isSuccessful()) {

                    try {
                        Log.e("Success", "" + response.body().toString());
                        // get String from response
                        String stringResponse = response.body().toString();
                        if (response.body().result == true) {
                            Log.e("Success", "Response" + response.body().result);
                            dashBord();

                        }else {
                            Toast.makeText(MainActivity.this, "Incorrect Userid and Password", Toast.LENGTH_SHORT).show();
                        }
                        // Do whatever you want with the String
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                // handle error
            }
        });
    }

    private void dashBord() {
        Intent intent=new Intent(MainActivity.this, Categories.class);
        startActivity(intent);
    }
}