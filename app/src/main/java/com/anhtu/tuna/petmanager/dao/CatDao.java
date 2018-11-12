package com.anhtu.tuna.petmanager.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.anhtu.tuna.petmanager.database.DatabaseHelper;
import com.anhtu.tuna.petmanager.model.Cat;

import java.util.ArrayList;
import java.util.List;

public class CatDao {
    private final SQLiteDatabase db;
    public static final String TABLE_NAME_CAT = "Cat";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_LOAI = "loai";
    public static final String COLUMN_WEIGHT = "cannang";
    public static final String COLUMN_HEALTH = "suckhoe";
    public static final String COLUMN_ỊNECTED = "tiem";
    public static final String COLUMN_PRICE = "gia";
    public static final String SQL_CAT= "CREATE TABLE "+TABLE_NAME_CAT+" ("
            +COLUMN_ID+" text primary key, "+COLUMN_LOAI+" text, "+COLUMN_WEIGHT
            +" text, "+COLUMN_HEALTH+" text, "+COLUMN_ỊNECTED+" text, "+COLUMN_PRICE+" text);";
    public static final String TAG = "CAT_DAO";

    public CatDao(Context context) {
        DatabaseHelper databasemanager = new DatabaseHelper(context);
        db = databasemanager.getWritableDatabase();
    }

    public int insertCat(Cat cat) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, cat.getmID());
        values.put(COLUMN_LOAI, cat.getmLoai());
        values.put(COLUMN_WEIGHT, cat.getmWeight());
        values.put(COLUMN_HEALTH, cat.getmHealth());
        values.put(COLUMN_ỊNECTED,cat.getmInjected());
        values.put(COLUMN_PRICE, cat.getmPrice());
        try {
            if (db.insert(TABLE_NAME_CAT, null, values) == -1) {
                return -1;
            }
        } catch (Exception ex) {
            Log.e(TAG, ex.toString());
        }
        return 1;
    }

    public int updateCat(Cat cat) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, cat.getmID());
        values.put(COLUMN_LOAI, cat.getmLoai());
        values.put(COLUMN_WEIGHT, cat.getmWeight());
        values.put(COLUMN_HEALTH, cat.getmHealth());
        values.put(COLUMN_ỊNECTED,cat.getmInjected());
        values.put(COLUMN_PRICE, cat.getmPrice());
        int result = db.update(TABLE_NAME_CAT, values, "id=?", new String[]{cat.getmID()});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    public List<Cat> getAllCat() {
        List<Cat> dsCat = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME_CAT, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            Cat ee = new Cat();
            ee.setmID(c.getString(0));
            ee.setmLoai(c.getString(1));
            ee.setmWeight(c.getString(2));
            ee.setmHealth(c.getString(3));
            ee.setmInjected(c.getString(4));
            ee.setmPrice(c.getString(5));
            dsCat.add(ee);
            Log.d("//=====", ee.toString());
            c.moveToNext();
        }
        c.close();
        return dsCat;
    }

    public int deleteCatbyID(String cat) {
        int result = db.delete(TABLE_NAME_CAT, "id=?", new String[]{cat});
        if (result == 0)
            return -1;
        return 1;
    }

}
