package com.germaaan.seriator;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ActividadPrincipal extends Activity implements View.OnClickListener {
    private static int NUM_PREGUNTAS = 4;

    private DBPref db;

    private int puntuacion;
    private Queue<Pregunta> listaPreguntas = new LinkedList<Pregunta>();
    private Pregunta preguntaInicial;

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

        if (preguntas.moveToFirst()) {
            do {
                String pregunta = preguntas.getString(preguntas.getColumnIndex("pregunta"));
                String respuesta = preguntas.getString(preguntas.getColumnIndex("respuesta_correcta"));
                Queue<String> incorrectas = new LinkedList<String>();

                incorrectas.offer(preguntas.getString(preguntas.getColumnIndex("respuesta_incorrecta_1")));
                incorrectas.offer(preguntas.getString(preguntas.getColumnIndex("respuesta_incorrecta_2")));
                incorrectas.offer(preguntas.getString(preguntas.getColumnIndex("respuesta_incorrecta_3")));

                for (int i = 1; i <= 7; i++) {
                    int idx = preguntas.getColumnIndex("incorrectas_respuesta_" + i);
                    if (idx != -1) {
                        incorrectas.offer(preguntas.getString(idx));
                    } else {
                        break;
                    }
                }

                this.listaPreguntas.offer(new Pregunta(pregunta, respuesta, (String[]) incorrectas.toArray(new String[incorrectas.size()])));
            } while (preguntas.moveToNext());
        }

        this.db.close();

        this.setPregunta(this.listaPreguntas.poll());

        this.opcion_1.setOnClickListener(this);
        this.opcion_2.setOnClickListener(this);
        this.opcion_3.setOnClickListener(this);
        this.opcion_4.setOnClickListener(this);
    }

    private void setPregunta(Pregunta pregunta) {
        this.preguntaInicial = pregunta;

        String[] respuestas = pregunta.getRespuestas();

        this.pregunta.setText(pregunta.getPregunta());
        this.opcion_1.setText(respuestas[0]);
        this.opcion_2.setText(respuestas[1]);
        this.opcion_3.setText(respuestas[2]);
        this.opcion_4.setText(respuestas[3]);
    }

    @Override
    public void onClick(View view) {
        Button pulsado = (Button) view;
        if (pulsado.getText().toString() == this.preguntaInicial.getRespuesta()) {
            if (this.listaPreguntas.size() > 0) {
                this.setPregunta(this.listaPreguntas.poll());
            } else {
                Toast t = Toast.makeText(this, "Has ganado!", Toast.LENGTH_LONG);
                t.show();
                this.finish();
            }
        } else {
            Toast t = Toast.makeText(this, "Has perdido!", Toast.LENGTH_LONG);
            t.show();
            this.finish();
        }
    }
}
