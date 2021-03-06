package com.example.coelh.a14_09_1;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.Console;

public class MainActivity extends AppCompatActivity {

    private TextView nome;
    private Spinner uf;
    private CheckBox xml;
    private CheckBox java;
    private CheckBox ui;
    private CheckBox ux;
    private Switch curioso;
    private RadioGroup formacao;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nome = (TextView) findViewById(R.id.nome);
        uf = (Spinner) findViewById(R.id.uf);
        xml = (CheckBox) findViewById(R.id.xml);
        java = (CheckBox) findViewById(R.id.java);
        ui = (CheckBox) findViewById(R.id.ui);
        ux = (CheckBox) findViewById(R.id.ux);
        curioso = (Switch) findViewById(R.id.curioso);
        formacao = (RadioGroup) findViewById(R.id.formacao);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void addPlayer(View view) {
        String name = (String) nome.getText().toString();
        int estado = (int)uf.getSelectedItemPosition();
        int grauDeEstudo = formacao.getCheckedRadioButtonId();
        int isXml = (xml.isChecked() ? 0 : 1);
        int isJava = (java.isChecked() ? 0 : 1);
        int isUi = (ui.isChecked() ? 0 : 1);
        int isUx = (ux.isChecked() ? 0 : 1);
        int isCurioso = (curioso.isChecked() ? 0 : 1);

        switch (grauDeEstudo) {
            case R.id.doc:
                grauDeEstudo = 0;
                break;
            case R.id.mes:
                grauDeEstudo = 1;
                break;
            case R.id.grad:
                grauDeEstudo = 2;
                break;
            default:
                grauDeEstudo = 3;
                break;
        }

        Intent in = new Intent(this, Confirm.class);
        in.putExtra("nome", name);
        in.putExtra("estado", estado);
        in.putExtra("grauDeEstudo", grauDeEstudo);
        in.putExtra("isXML", isXml);
        in.putExtra("isJava", isJava);
        in.putExtra("isUI", isUi);
        in.putExtra("isUX", isUx);
        in.putExtra("isCurioso", isCurioso);


        startActivity(in);
        Log.i("Main Activity", "Entrei certo");
    }
    public void databaseView(View view){
        Intent in = new Intent(this, DatabaseViewActivity.class);
        startActivity(in);
    }
    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.coelh.a14_09_1/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.coelh.a14_09_1/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

}
