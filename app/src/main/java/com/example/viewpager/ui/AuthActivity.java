package com.example.viewpager.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.hotspot2.pps.Credential;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.viewpager.App;
import com.example.viewpager.MainActivity;
import com.example.viewpager.R;
import com.example.viewpager.data.local.PrefUtils;
import com.example.viewpager.data.models.AuthModel;
import com.example.viewpager.data.network.AuthCallback;

import okhttp3.Credentials;

public class AuthActivity extends AppCompatActivity {

    EditText title,deck;
    Button save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        title = findViewById(R.id.title);
        deck = findViewById(R.id.deck);
        save = findViewById(R.id.button_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login = title.getText().toString();
                String pass = deck.getText().toString();
                String header = Credentials.basic(login,pass);
                App.authClient.getUser(header, new AuthCallback() {
                    @Override
                    public void onSuccess(AuthModel authModel) {
                        PrefUtils.saveUserName(authModel.getLogin());
                        startActivity(new Intent(AuthActivity.this,MainActivity.class));
                    }

                    @Override
                    public void onFailure(Exception e) {

                    }
                });
            }
        });

    }


    @Override
    public void onBackPressed() {
    }
}