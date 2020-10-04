package com.example.viewpager;

import android.app.Application;

import com.example.viewpager.data.local.PrefUtils;
import com.example.viewpager.data.network.AndroidClient;
import com.example.viewpager.data.network.AuthClient;

public class App extends Application {

   public static AndroidClient androidClient;
   public static AuthClient authClient;


    @Override
    public void onCreate() {
        super.onCreate();
         androidClient = new AndroidClient();
         authClient = new AuthClient();

        PrefUtils.init(this);
    }
}
