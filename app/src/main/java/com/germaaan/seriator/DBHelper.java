package com.germaaan.seriator;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Scanner;

public class DBHelper extends SQLiteOpenHelper {
    public static final String NOMBRE_BASE_DATOS = "Seriator";
    public static final int VERSION_BASE_DATOS = 1;
    protected SQLiteDatabase baseDatos;
    protected Context contexto;

    public DBHelper(Context contexto) {
        super(contexto, NOMBRE_BASE_DATOS, null, VERSION_BASE_DATOS);
        this.contexto = contexto;
        this.open();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d("debug", "Creando la base de datos...");

        StringBuilder sb = new StringBuilder();
        Scanner sc = new Scanner(this.contexto.getResources().openRawResource(R.raw.database));

        while (sc.hasNextLine()) {
            sb.append(sc.nextLine());
            sb.append("\n");

            if (sb.indexOf(";") != -1) {
                sqLiteDatabase.execSQL(sb.toString());
                sb.delete(0, sb.capacity());
            }
        }

        String consulta = "SELECT * FROM preguntas";
        Cursor cursor = sqLiteDatabase.rawQuery(consulta, null);
        int numPreguntas = cursor.getCount();
        Log.d("debug", numPreguntas + " preguntas cargadas en la base de datos.");
        cursor.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int version, int nueva) {
        Log.d("debug", "INFO: [Actualización de base de datos no implementada]");
    }

    public void open() {
        Log.d("debug", "Abriendo la base de datos...");
        this.baseDatos = this.getWritableDatabase();
    }

    public void close() {
        Log.d("debug", "Cerrando la base de datos...");
        this.baseDatos.close();
    }
}