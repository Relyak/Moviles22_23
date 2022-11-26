package com.example.agendaentregar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class perfilPersona extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_persona);
        TextView nText= findViewById(R.id.nTV);
        String nombre="Nombre no encontrado";
        Bundle extras=getIntent().getExtras();
        if(extras!=null){
            nombre=extras.getString("nombre");
        }
        nText.setText(nombre);
    }
}