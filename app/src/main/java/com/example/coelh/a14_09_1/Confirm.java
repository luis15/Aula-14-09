package com.example.coelh.a14_09_1;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class Confirm extends AppCompatActivity {
    private ContentValues val = new ContentValues();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bundle extra = getIntent().getExtras();
        val.put("nome", extra.getString("nome"));
        val.put("formacao", extra.getInt("grauDeEstudo"));
        val.put("uf", extra.getInt("estado"));
        val.put("xml", extra.getInt("isXML"));
        val.put("java",extra.getInt("isJava"));
        val.put("ui", extra.getInt("isUI"));
        val.put("ux", extra.getInt("isUX"));
        val.put("curioso", extra.getInt("isCurioso"));

}
    public void inserir(View view) {
        try {
            DataBaseHelper dbHelper = new DataBaseHelper(this);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            db.insert("mobile", null, val);
            Log.i("Confirm", "Inserido com sucesso");
            db.close();
        } catch (SQLiteException e) {
            Log.i("Confirm", "Falha na inserção");
            return;
        }
    }

}
