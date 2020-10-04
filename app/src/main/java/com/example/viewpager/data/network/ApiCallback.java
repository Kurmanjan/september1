package com.example.viewpager.data.network;

import com.example.viewpager.data.models.PostModel;

import java.util.List;

public interface ApiCallback {
    void onSuccess(List<PostModel> postModels);

    void onFailure(Exception e);



}
