package com.example.agendaentregar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class perfilPersona extends AppCompatActivity implements View.OnClickListener{
    appDataBase db;
    private PersonaDAO personaDAO;
    private ArrayList<Persona> personas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_persona);
        personas=new ArrayList<>();
        createORM();
        TextView tvNombre= findViewById(R.id.tvNom);
        TextView tvTel= findViewById(R.id.tvTel);
        String nombre="Nombre no encontrado";
        String tel="Telefono no encontrado";
        Bundle extras=getIntent().getExtras();
            if(extras!=null){
                nombre=extras.getString("nombre");
                tel=extras.getString("tel");
            }
            tvNombre.setText(nombre);
            tvTel.setText(tel);
        Button boton =findViewById(R.id.Vuelta);
        boton.setOnClickListener(this);
    }
    public void createORM () {
        //Se crea la base de datos
        db = Room.databaseBuilder(getApplicationContext(),
                appDataBase.class, "tablaPersonas").allowMainThreadQueries().build();
        personaDAO = db.personaDAO();//extrae datos de la bd para darle al dao?

        //extr4e el contenido de la bd
        List<tablaPersona> personaBD= personaDAO.getAll();//le estoy metiendo
        //el objeto, la lista, está extrayendo todas las tuplas
        //y se la meto al objeto uwu

        //Recorre la base e anade al ArrayList utilizado
        //mirar como funciona for each:
        //primer valor tabla, segundo valor objeto bd
        for(tablaPersona a /*objeto vacio de la BD*/: personaBD) {
            personas.add(new Persona(a.nombre,a.telefono));

        }





    }

    @Override
    public void onClick(View view) {
        llamada(view.getId());
    }

    private void llamada(int id) {
        switch (id){
            case R.id.Vuelta:
                Intent intent= new Intent(this,mainAgenda.class);
                startActivity(intent);
                break;
        }
    }
}