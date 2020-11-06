package com.roben.pntproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sharedPreferences = getSharedPreferences("UserDetails",MODE_PRIVATE);
        email = sharedPreferences.getString("email",null);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                if(email == null){
                    intent = new Intent(Splash.this, Login.class);
                }
                else{
                    intent = new Intent(Splash.this, MainActivity.class);
                }
                startActivity(intent);
                finish();
            }
        },3000);
    }
}