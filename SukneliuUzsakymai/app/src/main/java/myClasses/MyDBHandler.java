package myClasses;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class MyDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DUOMENU_BAZE = "uzsakymai.db";

    public static final String TABLE_SUKNELES = "sukneles";
    public static final String STULPELIS_SID = "s_id";
    public static final String STULPELIS_UZSAKYMODATA = "uzsakymo_data";
    public static final String STULPELIS_GALUTINEDATA = "galutine_data";
    public static final String STULPELIS_BUSENA = "busena";
    public static final String STULPELIS_KAINA = "kaina";
    public static final String STULPELIS_KLIENTAS = "fk_klientas";

    public static final String TABLE_KLIENTAI = "sukneles";
    public static final String STULPELIS_KID = "k_id";
    public static final String STULPELIS_VARDAS = "vardas";
    public static final String STULPELIS_PAVARDE = "pavarde";
    public static final String STULPELIS_TELEFONAS = "telefonas";
    public static final String STULPELIS_SUKNELE = "fk_suknele";

    public static final String STULPELIS_NUOTRAUKA = "nuotrauka";

    public MyDBHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DUOMENU_BAZE, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS " + TABLE_SUKNELES + "(" +
                STULPELIS_SID + " INTEGER PRIMARY KEY ASC, " +
                STULPELIS_UZSAKYMODATA + " TEXT, " +
                STULPELIS_GALUTINEDATA + " TEXT, " +
                STULPELIS_KAINA + " REAL, " +
                STULPELIS_BUSENA + " TEXT " +
                ");";
        db.execSQL(query);
        query = "CREATE TABLE IF NOT EXISTS " + TABLE_KLIENTAI + "(" +
                STULPELIS_KID + " INTEGER PRIMARY KEY ASC, " +
                STULPELIS_VARDAS + " TEXT, " +
                STULPELIS_PAVARDE + " TEXT, " +
                STULPELIS_TELEFONAS + " TEXT, " +
                "FOREIGN KEY" + "(" + STULPELIS_SUKNELE + ")" + " REFERENCES " +
                TABLE_SUKNELES + "(" + STULPELIS_SID + "));";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUKNELES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_KLIENTAI);
        onCreate(db);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void pridetSuknele(Suknele suknele)
    {
        ContentValues values = new ContentValues();
        values.put(STULPELIS_SID, suknele.getId());
        values.put(STULPELIS_KAINA, suknele.getKaina());
        values.put(STULPELIS_BUSENA, suknele.getBusena().name());
        values.put(STULPELIS_GALUTINEDATA, suknele.suformatuotaData(suknele.getGalutineData()));
        values.put(STULPELIS_UZSAKYMODATA, suknele.suformatuotaData(suknele.getUZSAKYMO_DATA()));

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_SUKNELES, null, values);
        db.close();
    }

    public void pridetiKlienta(Klientas klientas, int s_id)
    {
        ContentValues values = new ContentValues();
        values.put(STULPELIS_KID, klientas.getId());
        values.put(STULPELIS_VARDAS, klientas.get_vardas());
        values.put(STULPELIS_PAVARDE, klientas.get_pavarde());
        values.put(STULPELIS_TELEFONAS, klientas.get_telNr());
        values.put(STULPELIS_SUKNELE, s_id);

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_KLIENTAI, null, values);
        db.close();
    }

    public String databaseToString()
    {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_SUKNELES + " WHERE 1";

        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast())
        {
            if(cursor.getString(cursor.getColumnIndex("galutine_data")) != null)
            {
                dbString += cursor.getString(cursor.getColumnIndex("galutine_data"));
                dbString += "\n";
            }
            cursor.moveToNext();
        }
        db.close();
        return dbString;
    }
}
