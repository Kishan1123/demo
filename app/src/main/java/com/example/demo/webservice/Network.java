package com.example.demo.webservice;

import com.example.demo.model.CategoryModel;
import com.example.demo.model.LogInModel;
import com.example.demo.model.Result;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Network {
    @FormUrlEncoded
    @POST("login.php")
    Call<Result> loginWithCredentials(@Field("username")String username,
                                      @Field("password")String password);

    @GET("categories.json")
    Call<CategoryModel> catgoryList();
}
