package com.germaaan.seriator;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ActividadPrincipal extends Activity {
    private static int NUM_PREGUNTAS = 4;

    private DBPref db;

    private TextView pregunta;
    private Button opcion_1;
    private Button opcion_2;
    private Button opcion_3;
    private Button opcion_4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_principal);

        this.pregunta = (TextView) this.findViewById(R.id.pregunta);
        this.opcion_1 = (Button) this.findViewById(R.id.btn_opcion_1);
        this.opcion_2 = (Button) this.findViewById(R.id.btn_opcion_2);
        this.opcion_3 = (Button) this.findViewById(R.id.btn_opcion_3);
        this.opcion_4 = (Button) this.findViewById(R.id.btn_opcion_4);

        this.db = new DBPref(this);
        Cursor preguntas = this.db.getPreguntas(DBPref.Categoria.HISTORIA, DBPref.Dificultad.FACIL, ActividadPrincipal.NUM_PREGUNTAS);
    }
}
