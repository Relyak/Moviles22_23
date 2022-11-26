package com.example.agendaentregar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class mainAgenda extends AppCompatActivity {
    private ArrayList<Persona> personas;
    private RecyclerView rv1;
    Button anadir,borrar;
    private EditText et1;
    private recyclerAdapter.RecyclerViewClickListener listener;
    private EditText et2;
    private String nom,tel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_agenda);
        rv1=findViewById(R.id.rv1);
        personas=new ArrayList<>();
        setUserInfo();
        setAdapter();
        anadir=findViewById(R.id.anadir);
        borrar=findViewById(R.id.borrar);
        et1=findViewById(R.id.et1);
        et2=findViewById(R.id.et2);

        anadir.setOnClickListener(view -> {
            nom=et1.getText().toString();
            tel=et2.getText().toString();
            if(
                    (!nom.equals(""))&&
                    (!tel.equals(""))
            ){

            personas.add(new Persona(nom,tel));

            et1.setText("");
            et2.setText("");
            Toast.makeText(this,"Persona agregada",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"Datos vacios",Toast.LENGTH_SHORT).show();
            }
        });
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
                startActivity(intent);
            }
        };
    }

    private void setUserInfo() {

    }
    private void cargarImagen(){

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