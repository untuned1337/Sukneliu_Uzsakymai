package com.example.sukneliuuzsakymai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import myClasses.MyDBHandler;

public class Perziureti_sukneles extends AppCompatActivity {

    ListView lw_sukneliu_perziura;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perziureti_sukneles);

        lw_sukneliu_perziura = findViewById(R.id.lw_sukneliu_perziura);
        ArrayList<String> arrayTest = new ArrayList<>();

        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        String[] visos_sukneles = dbHandler.databaseToString().split("\n");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, visos_sukneles);

        lw_sukneliu_perziura.setAdapter(arrayAdapter);

    }
}
