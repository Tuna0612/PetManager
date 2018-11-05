package com.anhtu.tuna.petmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

public class Edit_PET extends AppCompatActivity {
    private Toolbar toolbar;
    private ImageView outeditpet;
    private EditText edID;
    private EditText edWeight;
    private Spinner spinHealth;
    private CheckBox cboYes;
    private CheckBox cboNo;
    private EditText edPrice;
    private Button btnSave;
    private Button btnDel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pet);
        initView();

         outeditpet.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 finish();
             }
         });
    }

    public void initView(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        outeditpet = (ImageView) findViewById(R.id.outeditpet);
        edID = (EditText) findViewById(R.id.edID);
        edWeight = (EditText) findViewById(R.id.edWeight);
        spinHealth = (Spinner) findViewById(R.id.spinHealth);
        cboYes = (CheckBox) findViewById(R.id.cboYes);
        cboNo = (CheckBox) findViewById(R.id.cboNo);
        edPrice = (EditText) findViewById(R.id.edPrice);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDel = (Button) findViewById(R.id.btnDel);

    }
}
