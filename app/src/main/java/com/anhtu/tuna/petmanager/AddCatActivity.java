package com.anhtu.tuna.petmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.anhtu.tuna.petmanager.dao.CatDao;
import com.anhtu.tuna.petmanager.model.Cat;
import com.anhtu.tuna.petmanager.model.PET;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class AddCatActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ImageView outthempet;
    private EditText edID;
    private Spinner spinLoai;
    private EditText edWeight;
    private Spinner spinHealth;
    private CheckBox cboYes;
    private CheckBox cboNo;
    private EditText edPrice;
    private Button btnSave;
    private Button btnDel;
    private List<Cat> catList;
    private CatDao catDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cat);
        initView();
        spinnerDog();
        spinnerHealth();

        outthempet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                catDao = new CatDao(getApplicationContext());
                String id = edID.getText().toString();
                String loai = (String) spinLoai.getSelectedItem();
                String weight = edWeight.getText().toString();
                String health = (String) spinHealth.getSelectedItem();
                String price = edPrice.getText().toString();
                Cat cat = null;
                try {
                    cat = new Cat(id,loai,weight,health,price);
                }catch (Exception e){
                    e.printStackTrace();
                }

                if (catDao.insertCat(cat) > 0) {
                    Toast.makeText(getApplicationContext(), "Add successfully", Toast.LENGTH_SHORT).show();

                    finish();
                    startActivity(new Intent(getApplicationContext(), ListCatActivity.class));
                } else {
                    edID.setError("Add error");

                }
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edID.setText("");
                spinLoai.setSelection(0);
                edWeight.setText("");
                spinHealth.setSelection(0);
                edPrice.setText("");
            }
        });
    }

    public void initView(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        outthempet = (ImageView) findViewById(R.id.outthempet);
        edID = (EditText) findViewById(R.id.edID);
        spinLoai = (Spinner) findViewById(R.id.spinLoai);
        edWeight = (EditText) findViewById(R.id.edWeight);
        spinHealth = (Spinner) findViewById(R.id.spinHealth);
        cboYes = (CheckBox) findViewById(R.id.cboYes);
        cboNo = (CheckBox) findViewById(R.id.cboNo);
        edPrice = (EditText) findViewById(R.id.edPrice);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDel = (Button) findViewById(R.id.btnDel);
        catDao = new CatDao(getApplicationContext());

    }

    public void spinnerDog(){
        List<String> list = new ArrayList<>();
        list.add("Egyptian Mau");
        list.add("Himalaya");
        list.add("LaPerm");
        list.add("Maine Coon");
        list.add("Peterbald");
        list.add("Scottish Fold");

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinLoai.setAdapter(adapter);
        spinLoai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void spinnerHealth(){
        List<String> list = new ArrayList<>();
        list.add("Healthy");
        list.add("Sick");
        list.add("There are deformities");

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinHealth.setAdapter(adapter);
        spinHealth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
