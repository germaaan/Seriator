package com.germaaan.seriator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Resultados extends Activity implements View.OnClickListener {
    private Utilidad mUtilidad;

    private TextView textoResultados;
    private Button botonVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultados);

        this.botonVolver = (Button) this.findViewById(R.id.boton_volver);

        this.mUtilidad = (Utilidad) getApplicationContext();
        this.textoResultados = (TextView) this.findViewById(R.id.texto_resultados);
        this.textoResultados.setText("Tu última puntuación ha sido " + this.mUtilidad.getPuntuacion() +
                ". \n\nEsto seguro de que puedes mejorarla, ¿te atreves a intentarlo?");

        this.botonVolver.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.boton_volver) {
            this.startActivity(new Intent(Resultados.this, MenuActividad.class));
        }
    }
}
