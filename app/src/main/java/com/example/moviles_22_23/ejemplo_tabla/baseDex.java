package com.example.moviles_22_23.ejemplo_tabla;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Pokedex.class},version = 1)
abstract class baseDex extends RoomDatabase {
public abstract PokedexDAO pokedexDAO();
}
