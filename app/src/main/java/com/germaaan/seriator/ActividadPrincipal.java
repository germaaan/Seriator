package com.germaaan.seriator;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class ActividadPrincipal extends Activity implements View.OnClickListener {
    public final static int NUM_PREGUNTAS = 10;
    private final static String COLOR_BOTONES = "#845208";

    private int puntuacion;
    private Utilidad mUtilidad;

    private DBPref manejadorBaseDatos;

    private Stack listaPreguntas = new Stack();
    private Pregunta pregunta;

    private TextView preguntaInicial;
    private ImageView imagen;
    private Button opcion1;
    private Button opcion2;
    private Button opcion3;
    private Button opcion4;

    private LinearLayout botonesAudio;

    private MediaPlayer sonidoPregunta;
    private MediaPlayer sonidoAcierto;
    private MediaPlayer sonidoError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_principal);

        this.mUtilidad = (Utilidad) this.getApplicationContext();

        this.puntuacion = 0;

        this.preguntaInicial = (TextView) this.findViewById(R.id.pregunta_inicial);
        this.imagen = (ImageView) this.findViewById(R.id.imagen_pregunta);
        this.opcion1 = (Button) this.findViewById(R.id.boton_opcion_1);
        this.opcion2 = (Button) this.findViewById(R.id.boton_opcion_2);
        this.opcion3 = (Button) this.findViewById(R.id.boton_opcion_3);
        this.opcion4 = (Button) this.findViewById(R.id.boton_opcion_4);

        this.botonesAudio = (LinearLayout) findViewById(R.id.layout_audio);

        this.manejadorBaseDatos = new DBPref(this);

        this.sonidoAcierto = MediaPlayer.create(this, R.raw.sonido_acierto);
        this.sonidoError = MediaPlayer.create(this, R.raw.sonido_error);

        Cursor preguntas = this.manejadorBaseDatos.getPreguntas(DBPref.Categoria.SERIES, DBPref.Dificultad.FACIL, ActividadPrincipal.NUM_PREGUNTAS);

        if (preguntas.moveToFirst()) {
            do {
                String pregunta = preguntas.getString(preguntas.getColumnIndex("pregunta"));
                int tipo = preguntas.getInt(preguntas.getColumnIndex("tipo"));
                String imagen = preguntas.getString(preguntas.getColumnIndex("imagen"));
                String sonido = preguntas.getString(preguntas.getColumnIndex("sonido"));

                String respuestaCorrecta = preguntas.getString(preguntas.getColumnIndex("respuestaCorrecta"));
                ArrayList<String> respuestasIncorrectas = new ArrayList();

                respuestasIncorrectas.add(preguntas.getString(preguntas.getColumnIndex("respuestaIncorrecta1")));
                respuestasIncorrectas.add(preguntas.getString(preguntas.getColumnIndex("respuestaIncorrecta2")));
                respuestasIncorrectas.add(preguntas.getString(preguntas.getColumnIndex("respuestaIncorrecta3")));

                this.listaPreguntas.push(new Pregunta(pregunta, tipo, imagen, sonido, respuestaCorrecta, respuestasIncorrectas));
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

        this.preguntaInicial.setText(pregunta.getPregunta());

        this.opcion1.setText(respuestas.get(0));
        this.opcion2.setText(respuestas.get(1));
        this.opcion3.setText(respuestas.get(2));
        this.opcion4.setText(respuestas.get(3));

        switch (this.pregunta.getTipo()) {
            case 1:
                this.imagen.setVisibility(View.GONE);
                this.imagen.setBackgroundResource(0);

                this.botonesAudio.setVisibility(View.GONE);

                this.cambiaTamTexto(20);
                this.cambiaColor();

                break;
            case 2:
                this.imagen.setVisibility(View.VISIBLE);
                this.imagen.setBackgroundResource(getResources().getIdentifier(this.pregunta.getImagen(), "drawable", getPackageName()));

                this.botonesAudio.setVisibility(View.GONE);

                this.cambiaTamTexto(20);
                this.cambiaColor();

                break;
            case 3:
                this.imagen.setVisibility(View.GONE);
                this.imagen.setBackgroundResource(0);

                this.botonesAudio.setVisibility(View.GONE);

                this.cambiaTamTexto(0);

                this.opcion1.setBackgroundResource(getResources().getIdentifier(respuestas.get(0), "drawable", getPackageName()));
                this.opcion2.setBackgroundResource(getResources().getIdentifier(respuestas.get(1), "drawable", getPackageName()));
                this.opcion3.setBackgroundResource(getResources().getIdentifier(respuestas.get(2), "drawable", getPackageName()));
                this.opcion4.setBackgroundResource(getResources().getIdentifier(respuestas.get(3), "drawable", getPackageName()));

                break;
            case 4:
                this.imagen.setVisibility(View.GONE);
                this.imagen.setBackgroundResource(0);

                this.botonesAudio.setVisibility(View.VISIBLE);
                this.sonidoPregunta = MediaPlayer.create(this, Uri.parse("android.resource://com.germaaan.seriator/raw/" + this.pregunta.getSonido()));

                this.cambiaTamTexto(20);
                this.cambiaColor();

                break;
        }
    }

    @Override
    public void onClick(View view) {
        Button seleccionado = (Button) view;

        if (this.sonidoPregunta != null) {
            this.pause(view);
        }

        if (seleccionado.getText().toString().equals(this.pregunta.getRespuesta())) {
            this.puntuacion++;
            Iterator itr = this.listaPreguntas.iterator();

            if (itr.hasNext()) {
                Toast.makeText(this, "CORRECTO", Toast.LENGTH_SHORT).show();
                this.sonidoAcierto.start();
                this.setPregunta((Pregunta) this.listaPreguntas.pop());
            } else {
                this.mUtilidad.setPuntuacion(this.puntuacion);
                this.startActivity(new Intent(ActividadPrincipal.this, Despedida.class));
            }
        } else {
            this.sonidoError.start();

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder
                    .setMessage("Has fallado, si quieres volver a intentarlo te quitaré 2 aciertos.")
                    .setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            puntuacion -= 2;
                        }
                    })
                    .setNegativeButton("Reiniciar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            mUtilidad.setPuntuacion(puntuacion);
                            mostrarRespuesta();
                            finish();
                        }
                    })
                    .show();
        }
    }

    public void mostrarRespuesta() {
        Toast.makeText(this, "La respuesta correcta era: " + this.pregunta.getRespuesta(), Toast.LENGTH_SHORT).show();
    }

    public void play(View view) {
        this.sonidoPregunta.start();
    }

    public void pause(View view) {
        this.sonidoPregunta.pause();
    }

    public void cambiaColor() {
        this.opcion1.setBackgroundColor(Color.parseColor(COLOR_BOTONES));
        this.opcion2.setBackgroundColor(Color.parseColor(COLOR_BOTONES));
        this.opcion3.setBackgroundColor(Color.parseColor(COLOR_BOTONES));
        this.opcion4.setBackgroundColor(Color.parseColor(COLOR_BOTONES));
    }

    public void cambiaTamTexto(int tam) {
        this.opcion1.setTextSize(tam);
        this.opcion2.setTextSize(tam);
        this.opcion3.setTextSize(tam);
        this.opcion4.setTextSize(tam);
    }

}
