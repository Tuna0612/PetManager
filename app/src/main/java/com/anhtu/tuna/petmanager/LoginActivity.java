package com.anhtu.tuna.petmanager;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private android.support.v7.widget.Toolbar toolbarLogin;
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
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        signin = (Button) findViewById(R.id.signin);

    }

    public void back(View view) {
        finish();
    }
}

