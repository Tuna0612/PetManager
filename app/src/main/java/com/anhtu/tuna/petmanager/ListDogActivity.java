package com.anhtu.tuna.petmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.anhtu.tuna.petmanager.adapter.CatAdapter;
import com.anhtu.tuna.petmanager.adapter.DogAdapter;
import com.anhtu.tuna.petmanager.dao.CatDao;
import com.anhtu.tuna.petmanager.dao.DogDao;
import com.anhtu.tuna.petmanager.model.Albums;
import com.anhtu.tuna.petmanager.model.Cat;
import com.anhtu.tuna.petmanager.model.Dog;

import java.util.List;

public class ListDogActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ListView lv;

    //Dog
    private DogDao dogDao;
    private DogAdapter Dadapter;
    private List<Dog> Dlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_dog);
        toolbar = findViewById(R.id.toolbar);
        lv = findViewById(R.id.lv);
        setSupportActionBar(toolbar);

        dogDao = new DogDao(ListDogActivity.this);
        try {
            Dlist = dogDao.getAllDog();
            Dadapter = new DogAdapter(this, Dlist);
        } catch (Exception e) {
            e.printStackTrace();
        }
        lv.setAdapter(Dadapter);
    }

    public void out(View view) {
        finish();
    }
}
