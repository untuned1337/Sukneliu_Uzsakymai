package com.example.sukneliuuzsakymai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import myClasses.Naujas_Uzsakymas;

public class MainActivity extends AppCompatActivity {

    Button btn_naujasUzsakymas;
    Button btn_perziuretiSukneles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_naujasUzsakymas = findViewById(R.id.btn_naujasUzsakymas);
        btn_naujasUzsakymas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Naujas_uzsakymas.class);
                startActivity(intent);
            }
        });

        btn_perziuretiSukneles = findViewById(R.id.btn_perziureti_sukneles);
        btn_perziuretiSukneles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Perziureti_sukneles.class);
                startActivity(intent);
            }
        });



    }

}
