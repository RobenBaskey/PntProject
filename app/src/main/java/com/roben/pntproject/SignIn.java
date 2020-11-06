package com.roben.pntproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignIn extends AppCompatActivity {

    private EditText username_et , email_et ,number_et , password_et;
    private Button registerBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        init();
    }

    private void init() {
        username_et = findViewById(R.id.register_username);
        email_et = findViewById(R.id.register_email);
        number_et = findViewById(R.id.register_mobile);
        password_et = findViewById(R.id.register_password);
        registerBtn = findViewById(R.id.register_btn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerOperation();
            }
        });
    }

    private void registerOperation() {
        String username = username_et.getText().toString();
        String email = email_et.getText().toString();
        String number = number_et.getText().toString();
        String password = password_et.getText().toString();

        if(username.isEmpty()){
            username_et.requestFocus();
            username_et.setError("Username is required!");
        }
        else if(email.isEmpty()){
            email_et.setError("Email is required!");
            email_et.requestFocus();
        }
        else if (number.isEmpty()){
            number_et.requestFocus();
            number_et.setError("Mobile no is required!");
        }
        else if(password.isEmpty()){
            password_et.requestFocus();
            password_et.setError("Password is required!");
        }
        else{
            SharedPreferences sharedPreferences = getSharedPreferences("UserDetails",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("username",username);
            editor.putString("email",email);
            editor.putString("mobile",number);
            editor.putString("password",password);
            editor.apply();

            if(editor.commit()){
                Intent intent = new Intent(SignIn.this , Login.class);
                startActivity(intent);
                finish();
            }
            else{
                Toast.makeText(this, "Check Your connection!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void gotoLogin(View view) {
        Intent intent = new Intent(SignIn.this , Login.class);
        startActivity(intent);
        finish();
    }
}