package com.example.agendaentregar;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PersonaDAO {
    @Query("SELECT * FROM tablaPersonas")
    List<tablaPersona> getAll();

    @Query("SELECT * FROM tablaPersonas WHERE uid IN (:userIds)")
    List<tablaPersona> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM tablaPersonas WHERE nombre LIKE :nombre LIMIT 1")
    tablaPersona findByName(String nombre);

    @Insert
    void insertAll(tablaPersona users);

    @Delete
    void delete(tablaPersona user);
}