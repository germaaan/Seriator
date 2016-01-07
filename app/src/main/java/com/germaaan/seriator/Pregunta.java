package com.germaaan.seriator;

public class Pregunta {
    private String pregunta;
    private String respuestaCorrecta;
    private String respuestaIncorrecta1;
    private String respuestaIncorrecta2;
    private String respuestaIncorrecta3;
    private char dificultad;
    private char categoria;
    private String tipo;

    public Pregunta() {
        pregunta = "";
        respuestaCorrecta = "";
        respuestaIncorrecta1 = "";
        respuestaIncorrecta2 = "";
        respuestaIncorrecta3 = "";
        dificultad = '0';
        categoria = 'Z';
    }

    public Pregunta(String pregunta, String respuestaCorrecta, String respuestaIncorrecta1,
                    String respuestaIncorrecta2, String respuestaIncorrecta3, char dificultad,
                    char categoria) {
        this.pregunta = pregunta;
        this.respuestaCorrecta = respuestaCorrecta;
        this.respuestaIncorrecta1 = respuestaIncorrecta1;
        this.respuestaIncorrecta2 = respuestaIncorrecta2;
        this.respuestaIncorrecta3 = respuestaIncorrecta3;
        this.dificultad = dificultad;
        this.categoria = categoria;
    }

    public String getPregunta() {
        return this.pregunta;
    }

    public String getRespuestaCorrecta() {
        return this.respuestaCorrecta;
    }

    public String getRespuestaIncorrecta1() {
        return this.respuestaIncorrecta1;
    }

    public String getRespuestaIncorrecta2() {
        return this.respuestaIncorrecta2;
    }

    public String getRespuestaIncorrecta3() {
        return this.respuestaIncorrecta3;
    }

    public char getDificultad() {
        return this.dificultad;
    }

    public char getCategoria() {
        return this.categoria;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public void setRespuestaCorrecta(String respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public void setRespuestaIncorrecta1(String respuestaIncorrecta1) {
        this.respuestaIncorrecta1 = respuestaIncorrecta1;
    }

    public void setRespuestaIncorrecta2(String respuestaIncorrecta2) {
        this.respuestaIncorrecta2 = respuestaIncorrecta2;
    }

    public void setRespuestaIncorrecta3(String respuestaIncorrecta3) {
        this.respuestaIncorrecta3 = respuestaIncorrecta3;
    }

    public void setDificultad(char dificultad) {
        this.dificultad = dificultad;
    }

    public void setCategoria(char categoria) {
        this.categoria = categoria;
    }
}
