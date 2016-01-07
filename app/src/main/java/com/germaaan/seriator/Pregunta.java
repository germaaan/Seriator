package com.germaaan.seriator;

import java.util.Random;

public class Pregunta {
    private static Random random = new Random();

    private String pregunta;
    private String respuesta;
    private String[] respuestas_incorrectas;
    private String[] respuestas = new String[4];

    public Pregunta(String pregunta, String respuesta, String[] respuestas_incorrectas) {
        this.pregunta = pregunta;
        this.respuesta = respuesta;
        this.respuestas_incorrectas = respuestas_incorrectas;

        this.respuestas[0] = respuesta;
        this.respuestas[1] = respuestas_incorrectas[0];
        this.respuestas[2] = respuestas_incorrectas[1];
        this.respuestas[3] = respuestas_incorrectas[2];

        Pregunta.mezclarRespuestas(this.respuestas);
    }

    public String getPregunta() {
        return this.pregunta;
    }

    public String[] getRespuestasIncorrectas() {
        return this.respuestas_incorrectas;
    }

    public String getRespuesta() {
        return this.respuesta;
    }

    public String[] getRespuestas() {
        return this.respuestas;
    }

    private static void mezclarRespuestas(String[] respuestas) {
        for (int i = respuestas.length - 1; i >= 0; i--) {
            int index = Pregunta.random.nextInt(i + 1);

            String a = respuestas[index];
            respuestas[index] = respuestas[i];
            respuestas[i] = a;
        }
    }
}
