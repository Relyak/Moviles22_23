package com.example.agendaentregar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class mainAgenda extends AppCompatActivity implements View.OnClickListener {
    private ArrayList<Persona> personas;
    private RecyclerView rv1;
    appDataBase db;//ni idea brother
    Button anadir,borrar;
    private EditText et1;
    private PersonaDAO personaDAO;
    private recyclerAdapter.RecyclerViewClickListener listener;
    private EditText et2;
    private String nom,tel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_agenda);
        rv1=findViewById(R.id.rv1);
        personas=new ArrayList<>();
        getSupportActionBar().hide();//oculta barra de arriba
        createORM();

        setUserInfo();

        setAdapter();

        anadir=findViewById(R.id.anadir); anadir.setOnClickListener(this);
        borrar=findViewById(R.id.borrar);
        et1=findViewById(R.id.et1);
        et2=findViewById(R.id.et2);

        borrar.setOnClickListener(view -> {
            int pos=-1;
            for(int i=0;i<personas.size();i++){
                if(personas.get(i).getNombre().equals(et1.getText().toString())){
                    pos=i;
                }
                if(pos!=1){
                    personas.remove(pos);
                    et1.setText("");
                    Toast.makeText(this,"Persona eliminada",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this,"No existe esa persona",Toast.LENGTH_SHORT).show();
                }
            }

        });

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
    private void setAdapter() {
        setOnClickListener();
        recyclerAdapter adapter=new recyclerAdapter(personas,listener);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getApplicationContext());
        rv1.setLayoutManager(layoutManager);
        rv1.setItemAnimator(new DefaultItemAnimator());
        rv1.setAdapter(adapter);
    }

    private void setOnClickListener() {
        listener= new recyclerAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent= new Intent(getApplicationContext(),perfilPersona.class);
                intent.putExtra("nombre",personas.get(position).getNombre());
                intent.putExtra("tel",personas.get(position).getTel());
                startActivity(intent);
            }
        };
    }

    private void setUserInfo() {

    }
    private void cargarImagen(){

    }

    @Override
    public void onClick(View view) {
        nintendo(view.getId());
    }
    public void nintendo(int aaaa){
        switch(aaaa){
            case R.id.anadir:
                nom=et1.getText().toString();
                tel=et2.getText().toString();
                if(
                        (!nom.equals(""))&&
                                (!tel.equals(""))
                ){
                    tablaPersona datoss=new tablaPersona();
                    datoss.nombre=nom;
                    datoss.telefono=tel;
                    personaDAO.insertAll(datoss);

                    et1.setText("");
                    et2.setText("");
                    Toast.makeText(this,"Persona agregada",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this,"Datos vacios",Toast.LENGTH_SHORT).show();
                }
                break;


        }
    }

    /*
    public void agregar(View v){
        Persona nuevaP=new Persona(et1.getText().toString(),et2.getText().toString());
        personas.add(nuevaP);
        et1.setText("");
        et2.setText("");
        ap.notifyDataSetChanged();
        rv1.scrollToPosition(personas.size()-1);
    }
    public void mostrar(int pos){
        et1.setText(personas.get(pos).getNombre());
        et2.setText(personas.get(pos).getTelefono());
    }
    public void eliminar(View v){
        int pos=-1;
        for(int i=0;i<personas.size();i++){
            if(personas.get(i).getNombre().equals(et1.getText().toString())){
                pos=i;
            }
            if(pos!=1){
                personas.remove(pos);
                et1.setText("");
                et2.setText("");
                ap.notifyDataSetChanged();
                Toast.makeText(this,"Persona eliminada",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"No existe esa persona",Toast.LENGTH_SHORT).show();
            }
        }
    }/*/




}