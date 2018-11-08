package com.anhtu.tuna.petmanager.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.anhtu.tuna.petmanager.database.DatabaseHelper;
import com.anhtu.tuna.petmanager.model.Cat;
import com.anhtu.tuna.petmanager.model.Dog;

import java.util.ArrayList;
import java.util.List;

public class DogDao {
    private final SQLiteDatabase db;
    public static final String TABLE_NAME = "Dog";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_LOAI = "loai";
    public static final String COLUMN_WEIGHT = "cannang";
    public static final String COLUMN_HEALTH = "suckhoe";
    public static final String COLUMN_TIEM = "tiem";
    public static final String COLUMN_PRICE = "gia";
    public static final String SQL_DOG= "CREATE TABLE "+TABLE_NAME+" ("+COLUMN_ID+" text primary key, "+COLUMN_LOAI+" text, "+COLUMN_WEIGHT+" text, "+COLUMN_HEALTH+" text, "+COLUMN_PRICE+" text);";
    public static final String TAG = "DOG_DAO";

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
        values.put(COLUMN_PRICE, dog.getmPrice());
        try {
            if (db.insert(TABLE_NAME, null, values) == -1) {
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
        values.put(COLUMN_PRICE, dog.getmPrice());
        int result = db.update(TABLE_NAME, values, "id=?", new String[]{dog.getmID()});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    public List<Dog> getAllDog() {
        List<Dog> dsDog = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            Dog ee = new Dog();
            ee.setmID(c.getString(0));
            ee.setmLoai(c.getString(1));
            ee.setmWeight(c.getString(2));
            ee.setmHealth(c.getString(3));
            ee.setmPrice(c.getString(4));
            dsDog.add(ee);
            Log.d("//=====", ee.toString());
            c.moveToNext();
        }
        c.close();
        return dsDog;
    }

    public int deleteDogbyID(String dog) {
        int result = db.delete(TABLE_NAME, "id=?", new String[]{dog});
        if (result == 0)
            return -1;
        return 1;
    }
}
