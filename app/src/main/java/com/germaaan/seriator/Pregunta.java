package com.germaaan.seriator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

public class Pregunta {
    private static Random random = new Random();

    private String pregunta;
    private String respuesta;
    private ArrayList<String> respuestasIncorrectas;
    private ArrayList<String> respuestas;

    public Pregunta(String pregunta, String respuesta, ArrayList<String> respuestasIncorrectas) {
        this.pregunta = pregunta;
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

    public ArrayList<String> getRespuestasIncorrectas() {
        return this.respuestasIncorrectas;
    }

    public String getRespuesta() {
        return this.respuesta;
    }

    public ArrayList<String> getRespuestas() {
        return this.respuestas;
    }
}
