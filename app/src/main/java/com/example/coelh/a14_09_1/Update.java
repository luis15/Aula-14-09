package com.example.coelh.a14_09_1;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CursorAdapter;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public class Update extends AppCompatActivity {
    private SQLiteDatabase db;
    private String id;
    private ContentValues val = new ContentValues();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bundle extra = getIntent().getExtras();
        id = extra.getString("id");
        Log.i("Update", id);
        DataBaseHelper dbHelper = new DataBaseHelper(this);
        db = dbHelper.getReadableDatabase();
        Cursor cursor;
        cursor = db.rawQuery("SELECT * FROM mobile WHERE _id ="+id, null);
        cursor.moveToFirst();

        TextView name =  (TextView)findViewById(R.id.nome);
        Spinner uf = (Spinner)findViewById(R.id.uf);
        CheckBox isXML = (CheckBox)findViewById(R.id.xml);
        CheckBox isJava = (CheckBox)findViewById(R.id.java);
        CheckBox isUI = (CheckBox)findViewById(R.id.ui);
        CheckBox isUX = (CheckBox)findViewById(R.id.ux);
        RadioGroup formacao = (RadioGroup)findViewById(R.id.formacao);
        Switch isCurioso = (Switch)findViewById(R.id.curioso);

        String texto = cursor.getString(cursor.getColumnIndex("nome"));
        name.setText(texto);

        uf.setSelection(cursor.getInt(cursor.getColumnIndex("uf")));

        boolean val;
        val = cursor.getInt(cursor.getColumnIndex("xml"))<=0;
        isXML.setChecked(val);

        val = cursor.getInt(cursor.getColumnIndex("java"))<=0;
        isJava.setChecked(val);

        val = cursor.getInt(cursor.getColumnIndex("ui"))<=0;
        isUI.setChecked(val);

        val = cursor.getInt(cursor.getColumnIndex("ux"))<=0;
        isUX.setChecked(val);

        formacao.check(cursor.getInt(cursor.getColumnIndex("formacao")));

        val = cursor.getInt(cursor.getColumnIndex("curioso"))<=0;
        isCurioso.setChecked(val);

    }
    public void updatePlayer(View view){
        TextView name =  (TextView)findViewById(R.id.nome);
        Spinner uf = (Spinner)findViewById(R.id.uf);
        CheckBox isXML = (CheckBox)findViewById(R.id.xml);
        CheckBox isJava = (CheckBox)findViewById(R.id.java);
        CheckBox isUI = (CheckBox)findViewById(R.id.ui);
        CheckBox isUX = (CheckBox)findViewById(R.id.ux);
        RadioGroup formacao = (RadioGroup)findViewById(R.id.formacao);
        Switch isCurioso = (Switch)findViewById(R.id.curioso);

        String nome = (String) name.getText().toString();
        int estado = uf.getSelectedItemPosition();
        int grauDeEstudo = formacao.getCheckedRadioButtonId();
        int xml = (isXML.isChecked() ? 0 : 1);
        int java = (isJava.isChecked() ? 0 : 1);
        int ui = (isUI.isChecked() ? 0 : 1);
        int ux = (isUX.isChecked() ? 0 : 1);
        int curioso = (isCurioso.isChecked() ? 0 : 1);

        val.put("nome", nome);
        val.put("uf", estado);
        val.put("formacao", grauDeEstudo);
        val.put("xml", xml);
        val.put("java", java);
        val.put("ui", ui);
        val.put("ux", ux);
        val.put("curioso", curioso);

        DataBaseHelper dbHelper = new DataBaseHelper(this);
        db = dbHelper.getReadableDatabase();

        db.update("mobile", val, "_id="+id, null);

        finish();
    }
}
