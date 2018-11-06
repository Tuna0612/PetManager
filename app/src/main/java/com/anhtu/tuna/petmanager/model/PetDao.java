package com.anhtu.tuna.petmanager.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.anhtu.tuna.petmanager.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class PetDao {
    private SQLiteDatabase db;
    private DatabaseHelper databaseHelper;
    public static final String TABLE_NAME = "PET";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_LOAI = "loai";
    public static final String COLUMN_WEIGHT = "cannang";
    public static final String COLUMN_HEALTH = "suckhoe";
    public static final String COLUMN_TIEM = "tiem";
    public static final String COLUMN_PRICE = "gia";
    public static final String SQL_PET= "CREATE TABLE "+TABLE_NAME+" ("+COLUMN_ID+" text primary key, "+COLUMN_LOAI+" text, "+COLUMN_WEIGHT+" text, "+COLUMN_HEALTH+" text, "+COLUMN_PRICE+" text);";
    public static final String TAG = "PET_DAO";

    public PetDao(Context context){
        databaseHelper = new DatabaseHelper(context);
        db = databaseHelper.getWritableDatabase();
    }

    public int insertPet(PET pet) {
        ContentValues values = new ContentValues();
        values.put("username", pet.getmID());
        values.put("password", pet.getmLoai());
        values.put("name", pet.getmWeight());
        values.put("phone", pet.getmHealth());
        values.put("phone", pet.getmPrice());
        try {
            if (db.insert(TABLE_NAME, null, values) == -1) {
                return -1;
            }
        } catch (Exception ex) {
            Log.e(TAG, ex.toString());
        }
        return 1;
    }

    public int updatePet(PET pet) {
        ContentValues values = new ContentValues();
        values.put("username", pet.getmID());
        values.put("password", pet.getmLoai());
        values.put("name", pet.getmWeight());
        values.put("phone", pet.getmHealth());
        values.put("phone", pet.getmPrice());
        int result = db.update(TABLE_NAME, values, "_id=?", new String[]{pet.getmID()});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    public List<PET> getAllPet() {
        List<PET> dsPET = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            PET ee = new PET();
            ee.setmID(c.getString(0));
            ee.setmLoai(c.getString(1));
            ee.setmWeight(c.getString(2));
            ee.setmHealth(c.getString(3));
            ee.setmPrice(c.getString(4));
            dsPET.add(ee);
            Log.d("//=====", ee.toString());
            c.moveToNext();
        }
        c.close();
        return dsPET;
    }

    public int deletePET(String pet) {
        int result = db.delete(TABLE_NAME, "_id=?", new String[]{pet});
        if (result == 0)
            return -1;
        return 1;
    }
}
