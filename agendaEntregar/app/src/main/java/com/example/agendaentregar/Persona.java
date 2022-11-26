package com.example.agendaentregar;

import android.widget.ImageView;

public class Persona {
    private String nombre;
    private String tel;
    private ImageView img;
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTel() {
        return tel;
    }

    public void setTelefono(String telefono) {
        this.tel = telefono;
    }


    public Persona(String nombre,String telefono) {
        this.nombre = nombre;
        this.tel=telefono;
    }



}
