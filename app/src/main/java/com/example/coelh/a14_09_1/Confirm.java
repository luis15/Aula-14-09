package com.example.coelh.a14_09_1;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;

import java.net.HttpURLConnection;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;
import static com.example.coelh.a14_09_1.R.id.pin;


public class Confirm extends AppCompatActivity {
    private ContentValues val = new ContentValues();
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

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
        val.put("java", extra.getInt("isJava"));
        val.put("ui", extra.getInt("isUI"));
        val.put("ux", extra.getInt("isUX"));
        val.put("curioso", extra.getInt("isCurioso"));

        TextView confirmName = (TextView) findViewById(R.id.confirmName);
        confirmName.setText(extra.getString("nome"));

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void inserir(View view) {
        try {
            DataBaseHelper dbHelper = new DataBaseHelper(this);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            db.insert("mobile", null, val);
            Log.i("Confirm", "Inserido com sucesso");
            tweet();
            db.close();
            finish();
        } catch (SQLiteException e) {
            Log.i("Confirm", "Falha na inserção");
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void tweet() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("2ZyAjhefDd0lOlUXImx2L4uJw")
                .setOAuthConsumerSecret("dF1jJOna5W9FkcZ25qeCzVzOgZIGaZRSMCHc6a9uc8CAMj67nj")
                .setOAuthAccessToken("AohyWpYqxfYpQo757zp141z4dle3SGNW1rVBKD2")
                .setOAuthAccessTokenSecret("j5DhetcCFlBUf8qXwRMZ4WRMWt9kbWfogYa3M2NmOrkWy");
        Twitter twitter = new TwitterFactory(cb.build()).getInstance();
        String mensagem = "Usuário "+val.get("nome")+" inserido com sucesso!";
        Log.i("Tweet", mensagem);
        twitter4j.Status status = null;
        try {
            status = twitter.updateStatus(mensagem);
            Log.i("Tweeet", "Deu bom");
        } catch (TwitterException e) {
            e.printStackTrace();
            Log.i("Tweeet", "Deu ruim");
        }
    }


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Confirm Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
