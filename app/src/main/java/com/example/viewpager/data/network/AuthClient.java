package com.example.viewpager.data.network;

import android.util.Log;

import com.example.viewpager.data.models.AuthModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthClient  {
    Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.github.com/")
            .build();


    AuthApi authApi = retrofit.create(AuthApi.class);

    public void getUser(String id,AuthCallback authCallback){
        Call<AuthModel> authModelCall = authApi.getUser(id);
        authModelCall.enqueue(new Callback<AuthModel>() {
            @Override
            public void onResponse(Call<AuthModel> call, Response<AuthModel> response) {
                if (response.isSuccessful() && response !=null){
                    authCallback.onSuccess(response.body());

                }
            }

            @Override
            public void onFailure(Call<AuthModel> call, Throwable t) {
                Log.d("tag", "onFailure: " + t.getMessage());

            }
        });
    }


}


