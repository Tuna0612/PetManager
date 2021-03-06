package com.anhtu.tuna.petmanager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public class EditPET extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView tvID;
    private EditText edWeight;
    private EditText edHealth;
    private EditText edPrice;
    private RadioButton rboYes;
    private ImageView imgAnh;
    private final int SELECT_PHOTO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pet);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();

        Intent in = getIntent();
        final Bundle b = in.getExtras();
        String id = b != null ? b.getString("id") : null;
        String weight = b != null ? b.getString("weight") : null;
        String health = b.getString("health");
        String injected = b.getString("injected");
        String price = b != null ? b.getString("price") : null;
        int[] img =b!=null? b.getIntArray("images"):null;
        Log.e("img", img+"");

        imgAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, SELECT_PHOTO);
            }
        });


    }

    public void initView(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvID = (TextView) findViewById(R.id.tvID);
        edWeight = (EditText) findViewById(R.id.edWeight);
        edHealth = (EditText) findViewById(R.id.edHealth);
        edPrice = (EditText) findViewById(R.id.edPrice);
        imgAnh = findViewById(R.id.imgAnh);
        rboYes = (RadioButton) findViewById(R.id.rboYes);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch(requestCode) {
            case SELECT_PHOTO:
                if(resultCode == RESULT_OK){
                    try {
                        final Uri imageUri = imageReturnedIntent.getData();
                        final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                        imgAnh.setImageBitmap(selectedImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }
        }
    }

    public void out(View view) {
        finish();
    }
}
