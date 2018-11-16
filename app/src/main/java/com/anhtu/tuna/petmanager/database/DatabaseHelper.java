package com.anhtu.tuna.petmanager.database;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import com.anhtu.tuna.petmanager.dao.CatDao;
import com.anhtu.tuna.petmanager.dao.DogDao;

@SuppressWarnings("ALL")
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "petstore1";
    public static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CatDao.SQL_CAT);
        sqLiteDatabase.execSQL(DogDao.SQL_DOG);

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop table if exists " + CatDao.TABLE_NAME_CAT);
        sqLiteDatabase.execSQL("Drop table if exists " + DogDao.TABLE_NAME_DOG);
        onCreate(sqLiteDatabase);
    }
}
