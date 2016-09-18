package com.example.coelh.a14_09_1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by coelh on 14/09/2016.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String dbname = "mobile";
    public static final int dbversion = 1;

    DataBaseHelper(Context context){
        super(context, dbname, null, dbversion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE mobile ( \n" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, \n" +
                "nome Text, \n" +
                "uf INTEGER NOT NULL, \n" +
                "xml INTEGER NOT NULL, \n" +
                "java INTEGER NOT NULL, \n" +
                "ux INTEGER NOT NULL, \n" +
                "ui INTEGER NOT NULL, \n" +
                "curioso INTEGER NOT NULL, \n" +
                "formacao INTEGER NOT NULL \n" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
