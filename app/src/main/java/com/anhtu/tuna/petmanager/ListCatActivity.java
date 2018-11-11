package com.anhtu.tuna.petmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.anhtu.tuna.petmanager.adapter.CatAdapter;
import com.anhtu.tuna.petmanager.dao.CatDao;
import com.anhtu.tuna.petmanager.model.Cat;

import java.util.List;

public class ListCatActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ListView lv;
    private CatDao catDao;
    private CatAdapter adapter;
    private List<Cat> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cat);
        toolbar = findViewById(R.id.toolbar);
        lv = findViewById(R.id.lv);
        setSupportActionBar(toolbar);

        catDao = new CatDao(ListCatActivity.this);
        try {
            list = catDao.getAllCat();
            adapter = new CatAdapter(this, list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        lv.setAdapter(adapter);
    }

    public void out(View view) {
        finish();
    }
}
