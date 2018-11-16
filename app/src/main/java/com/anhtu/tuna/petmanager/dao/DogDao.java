package com.anhtu.tuna.petmanager.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.anhtu.tuna.petmanager.database.DatabaseHelper;
import com.anhtu.tuna.petmanager.model.Dog;


import java.util.ArrayList;
import java.util.List;

public class DogDao {
    private final SQLiteDatabase db;
    public static final String TABLE_NAME_DOG = "DOG";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_LOAI = "loai";
    public static final String COLUMN_WEIGHT = "cannang";
    public static final String COLUMN_HEALTH = "suckhoe";
    public static final String COLUMN_ỊNECTED = "tiem";
    public static final String COLUMN_PRICE = "gia";
    public static final String COLUMN_IMAGE = "image";
    public static final String SQL_DOG= "CREATE TABLE "+TABLE_NAME_DOG+" ("
            +COLUMN_ID+" text primary key, "+COLUMN_LOAI+" text, "+COLUMN_WEIGHT
            +" text, "+COLUMN_HEALTH+" text, "+COLUMN_ỊNECTED+" text, "+COLUMN_PRICE+" text, "+COLUMN_IMAGE+" Blob)";
    public static final String TAG = "CAT_DAO";

    public DogDao(Context context) {
        DatabaseHelper databasemanager = new DatabaseHelper(context);
        db = databasemanager.getWritableDatabase();
    }

    public int insertDog(Dog dog) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, dog.getmID());
        values.put(COLUMN_LOAI, dog.getmLoai());
        values.put(COLUMN_WEIGHT, dog.getmWeight());
        values.put(COLUMN_HEALTH, dog.getmHealth());
        values.put(COLUMN_ỊNECTED,dog.getmInjected());
        values.put(COLUMN_PRICE, dog.getmPrice());
        values.put(COLUMN_IMAGE,dog.getImage());
        try {
            if (db.insert(TABLE_NAME_DOG, null, values) == -1) {
                return -1;
            }
        } catch (Exception ex) {
            Log.e(TAG, ex.toString());
        }
        return 1;
    }

    public int updateDog(Dog dog) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, dog.getmID());
        values.put(COLUMN_LOAI, dog.getmLoai());
        values.put(COLUMN_WEIGHT, dog.getmWeight());
        values.put(COLUMN_HEALTH, dog.getmHealth());
        values.put(COLUMN_ỊNECTED,dog.getmInjected());
        values.put(COLUMN_PRICE, dog.getmPrice());
        values.put(COLUMN_IMAGE,dog.getImage());
        int result = db.update(TABLE_NAME_DOG, values, "id=?", new String[]{dog.getmID()});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    public List<Dog> getAllDog() {
        List<Dog> dsDog = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME_DOG, null, null, null, null, null, null);
        c.moveToFirst();
        if (c!= null && c.getCount()>0)
        while (c.isAfterLast() == false) {
            Dog ee = new Dog();
            ee.setmID(c.getString(0));
            ee.setmLoai(c.getString(1));
            ee.setmWeight(c.getString(2));
            ee.setmHealth(c.getString(3));
            ee.setmInjected(c.getString(4));
            ee.setmPrice(c.getString(5));
            ee.setImage(c.getBlob(6));
            dsDog.add(ee);
            Log.d("//=====", ee.toString());
            c.moveToNext();
        }
        c.close();
        return dsDog;
    }

    public int deleteDogbyID(String dog) {
        int result = db.delete(TABLE_NAME_DOG, "id=?", new String[]{dog});
        if (result == 0)
            return -1;
        return 1;
    }
}
