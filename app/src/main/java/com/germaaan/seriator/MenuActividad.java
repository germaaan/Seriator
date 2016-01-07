package com.germaaan.seriator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActividad extends Activity implements View.OnClickListener {

    private Button botonPlay;
    private Button botonStats;
    private Button botonSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_menu);

        this.botonPlay = (Button) this.findViewById(R.id.btn_play);
        this.botonStats = (Button) this.findViewById(R.id.btn_stats);
        this.botonSettings = (Button) this.findViewById(R.id.btn_settings);

        this.botonPlay.setOnClickListener(this);
        this.botonStats.setOnClickListener(this);
        this.botonSettings.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_play) {
            this.startActivity(new Intent(MenuActividad.this, ActividadPrincipal.class));
        } else if (view.getId() == R.id.btn_stats) {
            this.startActivity(new Intent(MenuActividad.this, Resultados.class));
        } else if (view.getId() == R.id.btn_settings) {
            this.startActivity(new Intent(MenuActividad.this, Despedida.class));
        }
    }
}
