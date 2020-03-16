package com.example.sukneliuuzsakymai;
import com.example.sukneliuuzsakymai.R;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;
import java.time.LocalDate;

import myClasses.Locales;
import myClasses.MyDBHandler;
import myClasses.Suknele;

public class Naujas_uzsakymas extends AppCompatActivity {

    CalendarView cw_galutine;

    EditText et_kaina;
    EditText et_pozymiai;
    EditText et_vardas;
    EditText et_pavarde;
    EditText et_telNr;

    TextView rezultatai;

    Button btn_patvirtinti;

    LocalDate ld_parinkta;

    public MyDBHandler dbHandler;

    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Locales.LT_locale(getBaseContext());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_naujas_uzsakymas);

        cw_galutine = findViewById(R.id.calendarView);
        et_kaina = findViewById(R.id.kaina);
        et_pozymiai = findViewById(R.id.pozymiai);
        et_vardas = findViewById(R.id.vardas);
        et_pavarde = findViewById(R.id.pavarde);
        et_telNr = findViewById(R.id.telNr);
        btn_patvirtinti = findViewById(R.id.btn_patvirtinti);
        rezultatai = findViewById(R.id.rezultatai); // for testing

        dbHandler = new MyDBHandler(this, null, null, 1);

        cw_galutine.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                ld_parinkta = LocalDate.of(year, month, dayOfMonth);
            }
        });

        btn_patvirtinti.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                Suknele naujaSuknele = SukurtiSuknele();
                dbHandler.pridetSuknele(naujaSuknele);
                //String dbstring = dbHandler.databaseToString(); // for testing
                //rezultatai.setText(dbstring); // for testing
            }

        });

        et_pozymiai.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                switch(event.getAction() & MotionEvent.ACTION_MASK)
                {
                    case MotionEvent.ACTION_UP:
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }
                return false;
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Suknele SukurtiSuknele(){
        try
        {
            double kaina = Double.parseDouble(et_kaina.getText().toString());
            return new Suknele(kaina, ld_parinkta, "");
        }
        catch(Exception e)
        {
            return new Suknele(0, ld_parinkta, "");
        }
    }
}
