package myClasses;

import android.os.Build;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

enum Busena
{
    LAUKIA_EILEJ,
    SIUNAMA,
    PASIUTA
}

public class Suknele {
    private static int counter = 0;

    private final LocalDate UZSAKYMO_DATA;

    private int id;
    private double kaina;
    private Busena busena;
    private LocalDate galutineData;
    private ImageView nuotrauka;
    private String nuotraukosKelias;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Suknele(double kaina, LocalDate galutineData, String nuotraukosKelias)
    {
        this.id = counter++;
        this.UZSAKYMO_DATA = LocalDate.now();

        this.kaina = kaina;
        this.busena = Busena.LAUKIA_EILEJ;
        this.galutineData = galutineData;
        this.nuotraukosKelias = nuotraukosKelias;
    }


    public LocalDate getUZSAKYMO_DATA() {
        return UZSAKYMO_DATA;
    }

    public int getId() {
        return id;
    }

    public double getKaina() {
        return kaina;
    }

    public Busena getBusena() {
        return busena;
    }

    public LocalDate getGalutineData() {
        return galutineData;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String suformatuotaData(LocalDate data){
        String formatuota_data = "";
        try
        {
            DateTimeFormatter formatas = DateTimeFormatter.ofPattern("dd LLLL yyyy");
            formatuota_data = data.format(formatas);
            return formatuota_data;
        }
        catch(Exception e)
        {
            return "";
        }
    }
}
