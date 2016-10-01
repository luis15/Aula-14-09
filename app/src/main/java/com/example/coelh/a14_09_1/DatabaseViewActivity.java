package com.example.coelh.a14_09_1;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import static com.example.coelh.a14_09_1.R.styleable.FloatingActionButton;
import static com.example.coelh.a14_09_1.R.styleable.Toolbar;

public class DatabaseViewActivity extends AppCompatActivity {
    private Cursor cursor;
    private CursorAdapter cursorAdapter;
    private SQLiteDatabase db;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> listview,
                                    View view,
                                    int position,
                                    long id){
                    Cursor c = ((PlayersCursorAdapter)listview.getAdapter()).getCursor();
                    c.moveToPosition(position);
                    String clickedName = String.valueOf(c.getString(1));
                    Toast.makeText(DatabaseViewActivity.this, clickedName, Toast.LENGTH_LONG).show();
            }
        };

        listView = (ListView)findViewById(R.id.listView);
        listView.setOnItemClickListener(itemClickListener);
    }

    protected void onStart(){
        super.onStart();
        DataBaseHelper dbHelper = new DataBaseHelper(this);
        db = dbHelper.getReadableDatabase();

        cursor = db.query("mobile", new String[]{"_id", "nome", "uf", "curioso", "formacao"}, null, null, null, null, null);
        cursorAdapter = new PlayersCursorAdapter(this, android.R.layout.simple_list_item_1,
                cursor, new String[]{"nome"}, new int[]{android.R.id.text1},0);

        listView.setAdapter(cursorAdapter);
    }
    protected void onStop(){
        super.onStop();
        cursor.close();
        db.close();
    }
    public void removePlayer(View view){

        DataBaseHelper dbHelper = new DataBaseHelper(this);
        db = dbHelper.getReadableDatabase();
        db.delete("mobile", "_id = ?", new String[]{view.getTag(0x80000001).toString()});
        cursor.close();
        cursor = db.query("mobile", new String[]{"_id", "nome", "uf", "curioso", "formacao"}, null, null, null, null, null);
        cursorAdapter.changeCursor(cursor);
        cursorAdapter.notifyDataSetChanged();
    }

    public void updatePlayer(View view){
        Toast.makeText(DatabaseViewActivity.this, "Botão update = "+ view.getTag(0x80000002), Toast.LENGTH_LONG).show();
    }



}
