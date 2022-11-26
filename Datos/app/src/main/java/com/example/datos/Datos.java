package com.example.datos;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class Datos extends AppCompatActivity {


    EditText nombre;
    EditText email;
    EditText telefono;
    TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datos);
        nombre = findViewById(R.id.idut02NombreEdit);
        email = findViewById(R.id.idut02EmailEdit);
        telefono = findViewById(R.id.idut02TelEdit);
        output = findViewById(R.id.idut02Output);

        TextView.OnEditorActionListener manejador = new TextView.OnEditorActionListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_GO) {
                    output.setText(
                            String.format(
                                    "Hola %s\nTus datos:\n%s\n%s",
                                    nombre.getText(),
                                    email.getText(),
                                    telefono.getText()
                            )
                    );
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
                return false;
            }
        };
        nombre.setOnEditorActionListener(manejador);
        email.setOnEditorActionListener(manejador);
        telefono.setOnEditorActionListener(manejador);
    }
}