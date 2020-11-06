package com.roben.pntproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private EditText emailET , passwordET;
    private Button loginBtn;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
    }

    private void init() {
        emailET = findViewById(R.id.login_email);
        passwordET = findViewById(R.id.login_password);
        loginBtn = findViewById(R.id.login_btn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginOperation();
            }
        });
    }

    private void loginOperation() {
        sharedPreferences = getSharedPreferences("UserDetails",MODE_PRIVATE);
        String sh_email = sharedPreferences.getString("email",null);
        String sh_pass = sharedPreferences.getString("password",null);

        String email = emailET.getText().toString();
        String pass = passwordET.getText().toString();

        if(email.isEmpty()){
            emailET.requestFocus();
            emailET.setError("Email is required!");
        }
        else if(pass.isEmpty()){
            passwordET.setError("Password is required!");
            passwordET.requestFocus();
        }
        else{
            if(email.equals(sh_email)){
                if(pass.equals(sh_pass)){
                    Intent intent = new Intent(Login.this , MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(this, "Password is invalid!", Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(this, "Email is not correct !", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void gotoRegister(View view) {
        Intent intent = new Intent(Login.this , SignIn.class);
        startActivity(intent);
        finish();
    }
}