package com.example.viewpager.data.network;

import android.util.Log;

import com.example.viewpager.data.models.PostModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AndroidClient {

    Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://android-3-mocker.herokuapp.com/")
            .build();

    AndroidApi apiCallback = retrofit.create(AndroidApi.class);


    public void deletePost(Integer id,ApiCallback apiCallback){
        Call<PostModel> call = this.apiCallback.delete(id);
        call.enqueue(new Callback<PostModel>() {
            @Override
            public void onResponse(Call<PostModel> call, Response<PostModel> response) {

            }

            @Override
            public void onFailure(Call<PostModel> call, Throwable t) {

            }
        });
    }


    public  void getData(final ApiCallback callback){
        Call<List<PostModel>> call = apiCallback.getAllPosts();
        call.enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
                if(response.isSuccessful() && response.body() != null){
                    callback.onSuccess(response.body());
                    Log.d("tag", response.body().toString());
                }

            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {

            }
        });
    }


}
