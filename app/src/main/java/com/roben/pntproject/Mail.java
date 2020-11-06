package com.roben.pntproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Mail extends AppCompatActivity {

    private TextView tv_from , tv_to;
    private EditText et_details;
    private Button send_mail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);


        String mail_id = getIntent().getStringExtra("mail");
        tv_from = findViewById(R.id.mail_from);
        tv_to = findViewById(R.id.mail_to);
        et_details = findViewById(R.id.mail_details);
        send_mail = findViewById(R.id.send_mail_button);

        tv_from.setText(mail_id);
        tv_to.setText("baskeyjames10@gmail.com");
        send_mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String details = et_details.getText().toString();
                if(details.isEmpty()){
                    et_details.setError("Details needed");
                    et_details.requestFocus();
                }else{
                    perfromMail();
                }
            }
        });
    }

    private void perfromMail() {
        Intent intent = new Intent(Intent.ACTION_VIEW
        , Uri.parse("mailto:"+tv_to.getText().toString()));
        intent.putExtra(Intent.EXTRA_SUBJECT,"Test");
        intent.putExtra(Intent.EXTRA_TEXT,et_details.getText().toString());
        startActivity(intent);
    }
}