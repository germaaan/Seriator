package com.germaaan.seriator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class DBPref extends DBHelper {
    public static enum Categoria {
        HISTORIA('A'),
        GEOGRAFIA('B'),
        LITERATURA('C'),
        ARTE('D'),
        DEPORTES('E'),
        CIENCIA('F'),
        TECNOLOGÍA('G'),
        ASTRONOMÍA('H'),
        OTROS('Z');

        public final char C;

        Categoria(char c) {
            this.C = c;
        }
    }

    public static enum Dificultad {
        FACIL('e'),
        MEDIA('m'),
        DIFICIL('h');

        public final char D;

        Dificultad(char d) {
            this.D = d;
        }
    }

    public DBPref(Context contexto) {
        super(contexto);
    }

    public void addRegistro(Pregunta pregunta) {
        ContentValues valores = new ContentValues();
        valores.put("pregunta", pregunta.getPregunta());
        valores.put("respuesta_correcta", pregunta.getRespuesta());

        String[] respuestas_incorrectas = pregunta.getRespuestasIncorrectas();

        for (int i = 0; i < respuestas_incorrectas.length; i++) {
            valores.put("respuesta_incorrecta_" + i, respuestas_incorrectas[i]);
        }

        db.insert("preguntas", null, valores);
    }

    public void addRegistros(Pregunta[] preguntas) {
        for (int i = 0; i < preguntas.length; i++) {
            this.addRegistro(preguntas[i]);
        }
    }

    public void updateRegistro(Pregunta[] id, Pregunta question) {
        String filtro = "_id=" + id;
        ContentValues valores = new ContentValues();
        valores.put("pregunta", question.getPregunta());
        valores.put("respuesta_correcta", question.getRespuesta());

        String[] respuestas_incorrectas = question.getRespuestasIncorrectas();
        for (int i = 0; i < respuestas_incorrectas.length; i++) {
            valores.put("respuesta_incorrecta_" + i, respuestas_incorrectas[i]);
        }

        db.update("preguntas", valores, filtro, null);
    }

    public Cursor getPreguntas(Categoria c, Dificultad d) {
        return this.db.rawQuery("SELECT `pregunta`, `respuesta_correcta`, `respuesta_incorrecta_1`, " +
                        "`respuesta_incorrecta_2`, `respuesta_incorrecta_3`, `pregunta_tipo` " +
                        "FROM `preguntas` WHERE categoria = ? AND dificultad = ? " +
                        "ORDER BY RANDOM() LIMIT 1",
                new String[]{String.valueOf(c.C), String.valueOf(d.D)});
    }

    public Cursor getPreguntas(Categoria c, Dificultad d, int limit) {
        return this.db.rawQuery("SELECT `pregunta`, `respuesta_correcta`, `respuesta_incorrecta_1`, " +
                        "`respuesta_incorrecta_2`, `respuesta_incorrecta_3`, `pregunta_tipo` " +
                        "FROM `preguntas` WHERE categoria = ? AND dificultad = ? " +
                        "ORDER BY RANDOM() LIMIT ?",
                new String[]{String.valueOf(c.C), String.valueOf(d.D), String.valueOf(limit)});
    }
}