package com.germaaan.seriator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Pregunta {
    private String pregunta;
    private int tipo;
    private String imagen;
    private String sonido;
    private String respuesta;
    private ArrayList<String> respuestasIncorrectas;
    private ArrayList<String> respuestas;

    public Pregunta(String pregunta, int tipo, String imagen, String sonido, String respuesta, ArrayList<String> respuestasIncorrectas) {
        this.pregunta = pregunta;
        this.tipo = tipo;
        this.imagen = imagen;
        this.sonido = sonido;
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

    public int getTipo() {
        return this.tipo;
    }

    public String getImagen() {
        return this.imagen;
    }

    public String getSonido() {
        return this.sonido;
    }

    public String getRespuesta() {
        return this.respuesta;
    }

    public ArrayList<String> getRespuestas() {
        return this.respuestas;
    }
}
