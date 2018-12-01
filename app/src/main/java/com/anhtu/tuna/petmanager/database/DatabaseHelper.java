package com.anhtu.tuna.petmanager.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.anhtu.tuna.petmanager.dao.CatDao;
import com.anhtu.tuna.petmanager.dao.CatEgyptianDao;
import com.anhtu.tuna.petmanager.dao.CatHimalayaDao;
import com.anhtu.tuna.petmanager.dao.CatLapernDao;
import com.anhtu.tuna.petmanager.dao.CatMaineDao;
import com.anhtu.tuna.petmanager.dao.CatPeterbaldDao;
import com.anhtu.tuna.petmanager.dao.CatScootishDao;
import com.anhtu.tuna.petmanager.dao.DogAlaskaDao;
import com.anhtu.tuna.petmanager.dao.DogBecgieDao;
import com.anhtu.tuna.petmanager.dao.DogDao;
import com.anhtu.tuna.petmanager.dao.DogGoldenDao;
import com.anhtu.tuna.petmanager.dao.DogHuskyDao;
import com.anhtu.tuna.petmanager.dao.DogPooldyDao;
import com.anhtu.tuna.petmanager.dao.DogSamoyedDao;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "petstore1";
    public static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Cat
        sqLiteDatabase.execSQL(CatEgyptianDao.SQL_Egyptian_CAT);
        sqLiteDatabase.execSQL(CatHimalayaDao.SQL_Himalaya_CAT);
        sqLiteDatabase.execSQL(CatLapernDao.SQL_Lapern_CAT);
        sqLiteDatabase.execSQL(CatMaineDao.SQL_Maine_CAT);
        sqLiteDatabase.execSQL(CatScootishDao.SQL_Scootish_CAT);
        sqLiteDatabase.execSQL(CatPeterbaldDao.SQL_Peterbald_CAT);

        //Dog
        sqLiteDatabase.execSQL(DogAlaskaDao.SQL_Alaska_DOG);
        sqLiteDatabase.execSQL(DogBecgieDao.SQL_Becgie_DOG);
        sqLiteDatabase.execSQL(DogGoldenDao.SQL_Golden_DOG);
        sqLiteDatabase.execSQL(DogHuskyDao.SQL_Husky_DOG);
        sqLiteDatabase.execSQL(DogPooldyDao.SQL_Pooldy_DOG);
        sqLiteDatabase.execSQL(DogSamoyedDao.SQL_Samoyed_DOG);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop table if exists " + CatDao.TABLE_NAME_CAT);
        sqLiteDatabase.execSQL("Drop table if exists " + DogDao.TABLE_NAME_DOG);
        onCreate(sqLiteDatabase);
    }
}
