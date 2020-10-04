package com.example.viewpager.data.network;

import com.example.viewpager.data.models.AuthModel;
import com.example.viewpager.data.models.PostModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface AuthApi {
    @GET( "user")
    Call<AuthModel> getUser(@Header("Authorization")String id);


}
