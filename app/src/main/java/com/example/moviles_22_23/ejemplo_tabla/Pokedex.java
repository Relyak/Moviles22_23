package com.example.moviles_22_23.ejemplo_tabla;


import android.text.Editable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(tableName = "pokedex")
public class Pokedex {
    @PrimaryKey
    @NonNull
    public String id;
    @ColumnInfo(name="numero")
    public String num;
    @ColumnInfo(name="nombre")
    public String nombre;
    public Pokedex(){
        id= UUID.randomUUID().toString();
    }
}
