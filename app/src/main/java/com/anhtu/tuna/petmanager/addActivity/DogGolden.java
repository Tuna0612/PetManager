package com.anhtu.tuna.petmanager.addActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.anhtu.tuna.petmanager.ListDogActivity;
import com.anhtu.tuna.petmanager.R;
import com.anhtu.tuna.petmanager.dao.DogDao;
import com.anhtu.tuna.petmanager.dao.DogGoldenDao;
import com.anhtu.tuna.petmanager.model.Dog;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DogGolden extends AppCompatActivity {
    private ImageView imgAnh;
    private EditText edID;
    private Spinner spinLoai;
    private EditText edWeight;
    private Spinner spinHealth;
    private RadioGroup rg;
    private RadioButton rboYes;
    private RadioButton rboNo;
    private EditText edPrice;
    private Button btnSave;
    private Button btnDel;
    private DogGoldenDao dogDao;
    private String injected;
    private final int SELECT_PHOTO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dog);
        initView();
        spinnerDog();
        spinnerHealth();

        imgAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, SELECT_PHOTO);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dogDao = new DogGoldenDao(getApplicationContext());
                String id = edID.getText().toString();
                String loai = (String) spinLoai.getSelectedItem();
                String weight = edWeight.getText().toString();
                String health = (String) spinHealth.getSelectedItem();
                String price = edPrice.getText().toString();
                rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if (rboYes.isChecked()) {
                            injected = "Injected";
                        } else if (rboNo.isChecked()) {
                            injected = "Uninjected";
                        }
                    }
                });
                if (rboYes.isChecked()) {
                    injected = "Injected";
                } else if (rboNo.isChecked()) {
                    injected = "Uninjected";
                }
                Dog dog = null;
                try {
                    dog = new Dog(id,loai,weight,health,injected,price,ImageViewChange(imgAnh));
                }catch (Exception e){
                    e.printStackTrace();
                }

                if (dogDao.insertDog(dog) > 0) {
                    Toast.makeText(getApplicationContext(), "Add successfully", Toast.LENGTH_SHORT).show();

                    finish();
                    startActivity(new Intent(getApplicationContext(), ListDogActivity.class));
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
                rboYes.setSelected(true);
                edPrice.setText("");
            }
        });
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

    private byte[] ImageViewChange(ImageView imageView) {
        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        return stream.toByteArray();
    }

    public void initView(){
        imgAnh = findViewById(R.id.img);
        edID = (EditText) findViewById(R.id.edID);
        spinLoai = (Spinner) findViewById(R.id.spinLoai);
        edWeight = (EditText) findViewById(R.id.edWeight);
        spinHealth = (Spinner) findViewById(R.id.spinHealth);
        rg = findViewById(R.id.radioGroup1);
        rboYes = (RadioButton) findViewById(R.id.rboYes);
        rboNo = (RadioButton) findViewById(R.id.rboNo);
        edPrice = (EditText) findViewById(R.id.edPrice);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDel = (Button) findViewById(R.id.btnDel);
        dogDao = new DogGoldenDao(getApplicationContext());
    }

    public void spinnerDog(){
        List<String> list = new ArrayList<>();
        list.add("Husky");
        list.add("Becgie");
        list.add("Pooldy");
        list.add("Samoyed");
        list.add("Golden");
        list.add("Alaska");

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

    public void out(View view) {
        finish();
    }
}
