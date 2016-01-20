package com.germaaan.seriator;

import android.content.Context;
import android.database.Cursor;

public class DBPref extends DBHelper {
    public DBPref(Context contexto) {
        super(contexto);
    }

    public Cursor getPreguntas(Categoria c, Dificultad d, int limit) {
        return this.baseDatos.rawQuery("SELECT `pregunta`, `respuestaCorrecta`, " +
                        "`respuestaIncorrecta1`, `respuestaIncorrecta2`, `respuestaIncorrecta3`, " +
                        "`tipo`, `imagen`, `sonido` FROM `preguntas` WHERE categoria = ? AND dificultad = ? " +
                        "ORDER BY RANDOM() LIMIT ?",
                new String[]{String.valueOf(c.C), String.valueOf(d.D), String.valueOf(limit)});
    }

    public static enum Categoria {
        SERIES('S');

        public final char C;

        Categoria(char c) {
            this.C = c;
        }
    }

    public static enum Dificultad {
        FACIL('F'),
        MEDIA('M'),
        DIFICIL('D');

        public final char D;

        Dificultad(char d) {
            this.D = d;
        }
    }
}