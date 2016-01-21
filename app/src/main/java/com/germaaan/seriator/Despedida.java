package com.germaaan.seriator;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Despedida extends Activity implements View.OnClickListener {
    private Utilidad mUtilidad;

    private MediaPlayer sonidoGanador;

    private TextView textoDespedida;
    private Button botonVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.despedida);

        this.botonVolver = (Button) this.findViewById(R.id.boton_volver);

        this.mUtilidad = (Utilidad) getApplicationContext();
        this.textoDespedida = (TextView) this.findViewById(R.id.texto_despedida);
        this.textoDespedida.setText("A ver, a ver... Tú resultado final es " + this.mUtilidad.getPuntuacion() +
                ". \n\nVaya, ¿crees que podrías hacerlo mejor? Aquí te espero. ¡Hasta la próxima!");

        this.sonidoGanador = MediaPlayer.create(this, R.raw.sonido_ganador);
        this.sonidoGanador.start();

        this.botonVolver.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.boton_volver) {
            this.startActivity(new Intent(Despedida.this, MenuActividad.class));
        }
    }
}
