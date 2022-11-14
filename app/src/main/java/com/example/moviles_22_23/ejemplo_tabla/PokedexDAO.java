package com.example.moviles_22_23.ejemplo_tabla;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PokedexDAO {
    @Query("SELECT * FROM pokedex")
    List<Pokedex> getAll();

    @Insert
    void insertAll(Pokedex... pokedexes);



    @Delete
    void delete(Pokedex user);
}
