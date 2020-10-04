package com.example.viewpager.data.network;

import com.example.viewpager.data.models.AuthModel;
import com.example.viewpager.data.models.PostModel;

import java.util.List;

public interface AuthCallback {
    void onSuccess(AuthModel authModel);

    void onFailure(Exception e);

}
