package com.anhtu.tuna.petmanager;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
    private Toolbar toolbarLogin;
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
                    final Dialog dialog = new Dialog(LoginActivity.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.activity_dialog);
                    dialog.show();
                    WindowManager.LayoutParams lp = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
                    lp.copyFrom(dialog.getWindow().getAttributes());
                    dialog.getWindow().setAttributes(lp);
                    final VideoView videoview = (VideoView) dialog.findViewById(R.id.video);
                    Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.tiktok);
                    videoview.setVideoURI(uri);
                    videoview.start();
                } else if(mEmail.equals("tuna") && mPass.equals("tuna")){
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                } else {
                    Toast.makeText(LoginActivity.this, "Lêu lêu", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public void initView() {
        toolbarLogin = (Toolbar) findViewById(R.id.toolbarLogin);
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

