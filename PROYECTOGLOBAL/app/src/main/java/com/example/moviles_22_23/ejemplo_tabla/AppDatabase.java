package com.example.moviles_22_23.ejemplo_tabla;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Pokemon.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PokemonDAO pokedexDAO();
}
