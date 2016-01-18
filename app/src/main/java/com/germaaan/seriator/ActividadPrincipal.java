package com.germaaan.seriator;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class ActividadPrincipal extends Activity implements View.OnClickListener {
    private static int NUM_PREGUNTAS = 4;

    private int puntuacion;

    private DBPref manejadorBaseDatos;

    private Stack listaPreguntas = new Stack();
    private Pregunta pregunta;

    private TextView campoPregunta;
    private ImageView imagen;
    private Button opcion1;
    private Button opcion2;
    private Button opcion3;
    private Button opcion4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_principal);

        this.campoPregunta = (TextView) this.findViewById(R.id.campo_pregunta);
        this.imagen = (ImageView) this.findViewById(R.id.imagen_pregunta);
        this.opcion1 = (Button) this.findViewById(R.id.boton_opcion_1);
        this.opcion2 = (Button) this.findViewById(R.id.boton_opcion_2);
        this.opcion3 = (Button) this.findViewById(R.id.boton_opcion_3);
        this.opcion4 = (Button) this.findViewById(R.id.boton_opcion_4);

        this.manejadorBaseDatos = new DBPref(this);
        Cursor preguntas = this.manejadorBaseDatos.getPreguntas(DBPref.Categoria.SERIES, DBPref.Dificultad.FACIL, ActividadPrincipal.NUM_PREGUNTAS);

        if (preguntas.moveToFirst()) {

            do {
                String pregunta = preguntas.getString(preguntas.getColumnIndex("pregunta"));
                int tipo = preguntas.getInt(preguntas.getColumnIndex("tipo"));
                String imagen = preguntas.getString(preguntas.getColumnIndex("imagen"));

                String respuestaCorrecta = preguntas.getString(preguntas.getColumnIndex("respuestaCorrecta"));
                ArrayList<String> respuestasIncorrectas = new ArrayList();

                respuestasIncorrectas.add(preguntas.getString(preguntas.getColumnIndex("respuestaIncorrecta1")));
                respuestasIncorrectas.add(preguntas.getString(preguntas.getColumnIndex("respuestaIncorrecta2")));
                respuestasIncorrectas.add(preguntas.getString(preguntas.getColumnIndex("respuestaIncorrecta3")));

                this.listaPreguntas.push(new Pregunta(pregunta, tipo, imagen, respuestaCorrecta, respuestasIncorrectas));
            } while (preguntas.moveToNext());
        }

        this.manejadorBaseDatos.close();

        this.setPregunta((Pregunta) this.listaPreguntas.pop());

        this.opcion1.setOnClickListener(this);
        this.opcion2.setOnClickListener(this);
        this.opcion3.setOnClickListener(this);
        this.opcion4.setOnClickListener(this);
    }

    private void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;

        ArrayList<String> respuestas = new ArrayList<String>(pregunta.getRespuestas());

        this.campoPregunta.setText(pregunta.getPregunta());

        this.opcion1.setText(respuestas.get(0));
        this.opcion2.setText(respuestas.get(1));
        this.opcion3.setText(respuestas.get(2));
        this.opcion4.setText(respuestas.get(3));

        switch (this.pregunta.getTipo()) {
            case 2:
                this.imagen.getLayoutParams().height = 300;
                this.imagen.setBackgroundResource(getResources().getIdentifier(this.pregunta.getImagen(), "drawable", getPackageName()));

                this.opcion1.setTextSize(20);
                this.opcion2.setTextSize(20);
                this.opcion3.setTextSize(20);
                this.opcion4.setTextSize(20);

                this.opcion1.setBackgroundColor(Color.parseColor("#845208"));
                this.opcion2.setBackgroundColor(Color.parseColor("#845208"));
                this.opcion3.setBackgroundColor(Color.parseColor("#845208"));
                this.opcion4.setBackgroundColor(Color.parseColor("#845208"));

                break;
            case 3:
                this.imagen.getLayoutParams().height = 1;
                this.imagen.setBackgroundResource(0);

                this.opcion1.setTextSize(0);
                this.opcion2.setTextSize(0);
                this.opcion3.setTextSize(0);
                this.opcion4.setTextSize(0);

                this.opcion1.setBackgroundResource(getResources().getIdentifier(respuestas.get(0), "drawable", getPackageName()));
                this.opcion2.setBackgroundResource(getResources().getIdentifier(respuestas.get(1), "drawable", getPackageName()));
                this.opcion3.setBackgroundResource(getResources().getIdentifier(respuestas.get(2), "drawable", getPackageName()));
                this.opcion4.setBackgroundResource(getResources().getIdentifier(respuestas.get(3), "drawable", getPackageName()));
                break;
            default:
                this.imagen.getLayoutParams().height = 1;
                this.imagen.setBackgroundResource(0);

                this.opcion1.setTextSize(20);
                this.opcion2.setTextSize(20);
                this.opcion3.setTextSize(20);
                this.opcion4.setTextSize(20);

                this.opcion1.setBackgroundColor(Color.parseColor("#845208"));
                this.opcion2.setBackgroundColor(Color.parseColor("#845208"));
                this.opcion3.setBackgroundColor(Color.parseColor("#845208"));
                this.opcion4.setBackgroundColor(Color.parseColor("#845208"));
        }
    }

    @Override
    public void onClick(View view) {
        Button seleccionado = (Button) view;

        if (seleccionado.getText().toString().equals(this.pregunta.getRespuesta())) {
            Iterator itr = this.listaPreguntas.iterator();

            if (itr.hasNext()) {
                this.setPregunta((Pregunta) this.listaPreguntas.pop());
            } else {
                Toast.makeText(this, "HAS GANADO!", Toast.LENGTH_LONG).show();
                this.finish();
            }
        } else {
            Toast.makeText(this, "HAS PERDIDO!", Toast.LENGTH_LONG).show();
            this.finish();
        }
    }
}
