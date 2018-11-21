package com.anhtu.tuna.petmanager;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;
import android.widget.VideoView;

public class LoginActivity extends AppCompatActivity {
    private android.support.v7.widget.Toolbar toolbarLogin;
    private ProgressBar loginProgress;
    private ScrollView loginForm;
    private EditText password, email;
    private Button signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        setSupportActionBar(toolbarLogin);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mEmail = email.getText().toString();
                String mPass = password.getText().toString();
                if (mEmail.equals("admin") && mPass.equals("admin")) {
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                } else {
                    Toast.makeText(LoginActivity.this, "Lêu lêu", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public void initView() {
        toolbarLogin =  findViewById(R.id.toolbarLogin);
        loginProgress = (ProgressBar) findViewById(R.id.login_progress);
        loginForm = (ScrollView) findViewById(R.id.login_form);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        signin = (Button) findViewById(R.id.signin);

    }

    public void back(View view) {
        finish();
    }
}

