package com.germaaan.seriator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

public class Pregunta {
    private static Random random = new Random();

    private String pregunta;
    private int tipoPregunta;
    private String respuesta;
    private ArrayList<String> respuestasIncorrectas;
    private ArrayList<String> respuestas;

    public Pregunta(String pregunta, int tipoPregunta, String respuesta, ArrayList<String> respuestasIncorrectas) {
        this.pregunta = pregunta;
        this.tipoPregunta = tipoPregunta;
        this.respuesta = respuesta;
        this.respuestasIncorrectas = new ArrayList<String>(respuestasIncorrectas);
        this.respuestas = new ArrayList();

        this.respuestas.add(respuesta);

        Iterator itr = this.respuestasIncorrectas.iterator();

        while (itr.hasNext()) {
            Object elemento = itr.next();

            this.respuestas.add((String) elemento);
        }

        Collections.shuffle(this.respuestas);
    }

    public String getPregunta() {
        return this.pregunta;
    }

    public int getTipoPregunta() {
        return this.tipoPregunta;
    }

    public String getRespuesta() {
        return this.respuesta;
    }

    public ArrayList<String> getRespuestas() {
        return this.respuestas;
    }
}
