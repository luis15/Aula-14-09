package com.example.coelh.a14_09_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView nome;
    private Spinner uf;
    private CheckBox xml;
    private CheckBox java;
    private CheckBox ui;
    private CheckBox ux;
    private Switch curioso;
    private RadioGroup formacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nome = (TextView)findViewById(R.id.nome);
        uf = (Spinner)findViewById(R.id.uf);
        xml = (CheckBox)findViewById(R.id.xml);
        java = (CheckBox)findViewById(R.id.java);
        ui = (CheckBox)findViewById(R.id.ui);
        ux = (CheckBox)findViewById(R.id.ux);
        curioso = (Switch)findViewById(R.id.curioso);
        formacao = (RadioGroup)findViewById(R.id.formacao);
    }
    private void addPlayer(View view){
        String name = nome.getText().toString();
        int estado = uf.getSelectedItemPosition();
        int grauDeEstudo = formacao.getCheckedRadioButtonId();
        int isXml = (xml.isChecked()?0:1);
        int isJava = (java.isChecked()?0:1);
        int isUi = (ui.isChecked()?0:1);
        int isUx = (ux.isChecked()?0:1);
        int isCurioso = (curioso.isChecked()?0:1);

    }
}
