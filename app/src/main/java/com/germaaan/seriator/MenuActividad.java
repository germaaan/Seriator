package com.germaaan.seriator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActividad extends Activity implements View.OnClickListener {

    private Button botonJugar;
    private Button botonResultados;
    private Button botonOtroJuego;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_menu);

        this.botonJugar = (Button) this.findViewById(R.id.boton_jugar);
        this.botonResultados = (Button) this.findViewById(R.id.boton_resultados);
        this.botonOtroJuego = (Button) this.findViewById(R.id.boton_otro_juego);

        this.botonJugar.setOnClickListener(this);
        this.botonResultados.setOnClickListener(this);
        this.botonOtroJuego.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.boton_jugar) {
            this.startActivity(new Intent(MenuActividad.this, ActividadPrincipal.class));
        } else if (view.getId() == R.id.boton_resultados) {
            this.startActivity(new Intent(MenuActividad.this, Resultados.class));
        } else if (view.getId() == R.id.boton_otro_juego) {
            this.startActivity(new Intent(MenuActividad.this, Presentacion.class));
        }
    }
}
