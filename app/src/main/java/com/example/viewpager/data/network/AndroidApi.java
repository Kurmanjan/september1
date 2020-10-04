package com.example.viewpager.data.network;

import com.example.viewpager.data.models.PostModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AndroidApi {

    @GET(Config.GET_ALL_POST)
    Call<List<PostModel>> getAllPosts ();

    @POST(Config.POST_DATA)
    Call<List<PostModel>> postData(@Body PostModel postModel);

    @DELETE(Config.GET_ALL_POST+"{id}")
    Call<PostModel> delete(@Path("id")Integer id); //udelenie post

    @GET("posts?users")
    Call<ArrayList<PostModel>> getUsers();
}
