package com.anhtu.tuna.petmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.anhtu.tuna.petmanager.dao.CatDao;
import com.anhtu.tuna.petmanager.model.Cat;
import java.util.List;

public class EditPET extends AppCompatActivity {
    private Toolbar toolbar;
    private ImageView outeditpet;
    private TextView tvID;
    private EditText edWeight;
    private EditText edHealth;
    private EditText edPrice;
    private Button btnSave;
    private Button btnDel;
    private List<Cat> catList;
    private CatDao catDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pet);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();

        catDao = new CatDao(this);
        Intent in = getIntent();
        Bundle b = in.getExtras();
        String id = b != null ? b.getString("id") : null;
        String weight = b != null ? b.getString("weight") : null;
        String health = b.getString("health");
        String price = b != null ? b.getString("price") : null;
        tvID.setText(id);
        edWeight.setText(weight);
        edHealth.setText(health);
        edPrice.setText(price);
    }

    public void initView(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        outeditpet = (ImageView) findViewById(R.id.outeditpet);
        tvID = (TextView) findViewById(R.id.tvD);
        edWeight = (EditText) findViewById(R.id.edWeight);
        edHealth = (EditText) findViewById(R.id.edHealth);
        edPrice = (EditText) findViewById(R.id.edPrice);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDel = (Button) findViewById(R.id.btnDel);

    }

    public void out(View view) {
        finish();
    }
}
