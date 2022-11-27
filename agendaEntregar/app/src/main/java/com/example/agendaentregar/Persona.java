package com.example.agendaentregar;

import android.widget.ImageView;

public class Persona {
    private String nombre;
    private String tel;
    private ImageView img;
    public String getNombre() {
        return nombre;
    }


    public String getTel() {
        return tel;
    }



    public Persona(){}
    public Persona(String nombre,String telefono) {
        this.nombre = nombre;
        this.tel=telefono;
    }



}
