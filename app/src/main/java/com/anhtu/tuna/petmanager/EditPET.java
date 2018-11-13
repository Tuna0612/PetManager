package com.anhtu.tuna.petmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.anhtu.tuna.petmanager.dao.CatDao;
import com.anhtu.tuna.petmanager.dao.DogDao;
import com.anhtu.tuna.petmanager.model.Cat;
import com.bumptech.glide.Glide;

import java.util.List;

public class EditPET extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView tvID;
    private EditText edWeight;
    private EditText edHealth;
    private EditText edPrice;
    private RadioGroup radioGroup1;
    private RadioButton rboYes;
    private RadioButton rboNo;
    private Button btnSave;
    private Button btnDel;
    private List<Cat> catList;
    private CatDao catDao;
    private DogDao dogDao;
    private ImageView imgAnh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pet);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        catDao = new CatDao(getApplicationContext());
        dogDao = new DogDao(getApplicationContext());
        initView();

        Intent in = getIntent();
        final Bundle b = in.getExtras();
        String id = b != null ? b.getString("id") : null;
        String weight = b != null ? b.getString("weight") : null;
        String health = b.getString("health");
        String injected = b.getString("injected");
        String price = b != null ? b.getString("price") : null;
        Glide.with(EditPET.this).load(b.getByteArray("images")).into(imgAnh);
        tvID.setText(id);
        edWeight.setText(weight);
        edHealth.setText(health);
        edPrice.setText(price);
        rboYes.setSelected(Boolean.parseBoolean(injected));


    }

    public void initView(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvID = (TextView) findViewById(R.id.tvID);
        edWeight = (EditText) findViewById(R.id.edWeight);
        edHealth = (EditText) findViewById(R.id.edHealth);
        edPrice = (EditText) findViewById(R.id.edPrice);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDel = (Button) findViewById(R.id.btnDel);
        imgAnh = findViewById(R.id.imgAnh);
        radioGroup1 = (RadioGroup) findViewById(R.id.radioGroup1);
        rboYes = (RadioButton) findViewById(R.id.rboYes);
        rboNo = (RadioButton) findViewById(R.id.rboNo);
    }

    public void out(View view) {
        finish();
    }
}
