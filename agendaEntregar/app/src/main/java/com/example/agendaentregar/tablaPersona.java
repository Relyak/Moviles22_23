package com.example.agendaentregar;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@Entity(tableName="tablaPersonas")
public class tablaPersona {
    @PrimaryKey
    @NotNull
    public String uid;

    @ColumnInfo(name = "nombre")
    public String nombre;
    @ColumnInfo(name = "telefono")
    public String telefono;
    public tablaPersona(){
        uid= UUID.randomUUID().toString();
    }

}