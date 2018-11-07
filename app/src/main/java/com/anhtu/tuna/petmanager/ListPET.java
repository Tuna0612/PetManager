package com.anhtu.tuna.petmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.anhtu.tuna.petmanager.adapter.DogAdapter;
import com.anhtu.tuna.petmanager.model.Dog;

import java.util.ArrayList;
import java.util.List;

public class ListPET extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Dog> dogList;
    private DogAdapter adapter;
    private Toolbar toolbar;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_pet);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recycle);
        img = findViewById(R.id.outlistpet);
        dogList = new ArrayList<>();

        for(int i=0;i<10;i++){
            dogList.add(new Dog("Husky 00"+i,"5.5M","5 Kg"));
            dogList.add(new Dog("Becgie 00"+i,"8M","10 Kg"));
            dogList.add(new Dog("Pooldy 00"+i,"4M","2 Kg"));
            dogList.add(new Dog("Symoyed 00"+i,"9M","5 Kg"));
            dogList.add(new Dog("Golden 00"+i,"3M","7 Kg"));
            dogList.add(new Dog("Alaska 00"+i,"10M","9 Kg"));
        }

        adapter =  new DogAdapter(dogList);
        recyclerView.setAdapter(adapter);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

    }
}
