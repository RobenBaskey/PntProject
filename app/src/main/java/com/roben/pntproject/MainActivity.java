package com.roben.pntproject;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ImageView call , send_mail , editBtn;
    private TextView nameTxt , emailTxt , phoneTxt;
    private String sh_name , sh_mail , sh_mobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        call = findViewById(R.id.send_call);
        send_mail = findViewById(R.id.send_email);
        editBtn = findViewById(R.id.edit_btn);

        nameTxt = findViewById(R.id.your_name);
        emailTxt = findViewById(R.id.your_email);
        phoneTxt = findViewById(R.id.your_mobile);


        setDetails();
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCall();
            }
        });
        send_mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , Mail.class);
                intent.putExtra("mail",sh_mail);
                startActivity(intent);
            }
        });
    }

    private void setDetails() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserDetails",MODE_PRIVATE);
        sh_name = sharedPreferences.getString("username",null);
        sh_mail = sharedPreferences.getString("email",null);
        sh_mobile = sharedPreferences.getString("mobile","017**********");

        nameTxt.setText(sh_name);
        emailTxt.setText(sh_mail);
        phoneTxt.setText(sh_mobile);
    }

    private void sendCall() {
        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
               // Toast.makeText(MainActivity.this, "Granted", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:01717601905"));
                startActivity(intent);
            }
            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(MainActivity.this, "Please allow permission", Toast.LENGTH_SHORT).show();
            }
        };
        TedPermission.with(this)
                .setPermissionListener(permissionListener)
                .setPermissions(Manifest.permission.CALL_PHONE)
                .check();
    }

}