package com.example.moviles_22_23.ejemplo_tabla;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.moviles_22_23.R;

import java.util.List;

public class tabla_pokemon extends AppCompatActivity {
    Button anadir;
    Button recargar;
    EditText nombre,numero;
    TextView mostrar;
    baseDex db;
    PokedexDAO pokedexDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabla_pokemon);

        anadir=findViewById(R.id.anadirPoke);
        recargar=findViewById(R.id.recarga);
        nombre=findViewById(R.id.nombrePoke);
        numero=findViewById(R.id.numPoke);
        mostrar=findViewById(R.id.mostrarBD);



        db= Room.databaseBuilder(getApplicationContext(),
                baseDex.class, "pokedex").allowMainThreadQueries().build();
        pokedexDao=db.pokedexDAO();
        recargar.setOnClickListener( v->{
            List<Pokedex> pokemon=pokedexDao.getAll();
            String sDatos="";
            for (Pokedex a: pokemon){
                sDatos+=a.nombre +" num dex: "+a.num+"\n";
            }
            mostrar.setText(sDatos);
        });
        anadir.setOnClickListener(v->{
            Pokedex a= new Pokedex();
            a.nombre=nombre.getText().toString();
            a.num=numero.getText().toString();
            pokedexDao.insertAll(a);
        });



    }
}