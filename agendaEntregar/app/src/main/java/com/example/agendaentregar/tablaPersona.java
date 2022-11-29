package com.example.agendaentregar;

import android.widget.ImageView;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.UUID;

@Entity(tableName="tablaPersonas")
public class tablaPersona implements Serializable {
    @PrimaryKey
    @NotNull
    public String uid;

    @ColumnInfo(name = "nombre")
    public String nombre;
    @ColumnInfo(name = "telefono")
    public String telefono;



    public String getNombre() {
        return nombre;
    }


    public String getTel() {
        return telefono;
    }



    public tablaPersona(){ uid= UUID.randomUUID().toString();}
    public tablaPersona(String nombre,String telefono) {
        this.nombre = nombre;
        this.telefono=telefono;
    }

}