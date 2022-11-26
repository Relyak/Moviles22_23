package com.example.moviles_22_23.viaje;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.moviles_22_23.R;
import com.example.moviles_22_23.ejemplo_varias_vistas.lanzadora;

public class receptor_viaje extends AppCompatActivity {
    TextView datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receptor_viaje);

        datos = findViewById(R.id.datos);
        Intent i = getIntent();
        datos.setText(i.getStringExtra(lanzador_viaje.IDENTIFICADOR_RECEPTOR));
    }
}