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
        db.execSQL("CREATE TABLE `mobile` (\n" +
                "  `idmobile` int(11) NOT NULL AUTO_INCREMENT,\n" +
                "  `nome` varchar(45) DEFAULT NULL,\n" +
                "  `uf` varchar(2) DEFAULT NULL,\n" +
                "  `xml` tinyint(1) DEFAULT NULL,\n" +
                "  `java` tinyint(1) DEFAULT NULL,\n" +
                "  `ux` tinyint(1) DEFAULT NULL,\n" +
                "  `ui` tinyint(1) DEFAULT NULL,\n" +
                "  `curioso` tinyint(1) DEFAULT NULL,\n" +
                "  `formacao` varchar(45) DEFAULT NULL,\n" +
                "  PRIMARY KEY (`idmobile`)\n" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
