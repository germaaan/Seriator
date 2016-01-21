package com.germaaan.seriator;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Presentacion extends Activity implements View.OnClickListener {
    private Button botonVolver;
    private Button botonJuego;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.presentacion);

        this.botonVolver = (Button) this.findViewById(R.id.boton_volver);
        this.botonJuego = (Button) this.findViewById(R.id.boton_juego);

        this.botonVolver.setOnClickListener(this);
        this.botonJuego.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.boton_volver) {
            this.startActivity(new Intent(Presentacion.this, MenuActividad.class));
        } else if (view.getId() == R.id.boton_juego) {
            Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
            myWebLink.setData(Uri.parse("http://ploppy.igme.es/"));
            startActivity(myWebLink);
        }
    }
}
