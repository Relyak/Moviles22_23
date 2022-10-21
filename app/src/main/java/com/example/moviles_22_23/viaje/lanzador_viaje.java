package com.example.moviles_22_23.viaje;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.moviles_22_23.R;

import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class lanzador_viaje extends AppCompatActivity implements View.OnClickListener , AdapterView.OnItemSelectedListener {
    EditText nombre,dni,recogida,horaIda,horaVuelta,fechaIda,fechaVuelta;
    Button imprimir;
    CheckBox ida,ida_vuelta;
    Spinner origen,destino;
    TextView ejemplos;
    String variable_origen,variable_destino;
    boolean check_origen_destino=false;
    boolean check_fecha_ida =false;
    boolean check_dni=false;
    boolean check_fecha_vuelta =false;





    public static String IDENTIFICADOR_RECEPTOR = "CLAVE_PARA_MANDAR_A_RECEPTOR";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lanzador_viaje);
        //le doy señalo los ID de mi layout, a los que son click les asigno
        // el clickListener con this para tener un solo metodo para varios clicks






        //Edit text
        nombre=findViewById(R.id.nombre);
        dni=findViewById(R.id.dni);
        recogida=findViewById(R.id.recogida);
        horaIda=findViewById(R.id.horaIda);
        horaVuelta=findViewById(R.id.horaVuelta);
        fechaIda=findViewById(R.id.fechaIda);
        fechaVuelta=findViewById(R.id.fechaVuelta);

        //imprimir es mi boton
        imprimir=findViewById(R.id.imprimir);
        imprimir.setOnClickListener(this);


        //ida y vuelta son los checkbox
        ida=findViewById(R.id.ida);
        ida.setOnClickListener(this);
        ida_vuelta=findViewById(R.id.ida_vuelta);
        ida_vuelta.setOnClickListener(this);
        //Spinners
        origen=findViewById(R.id.origen);
        origen.setOnItemSelectedListener(this);
        destino=findViewById(R.id.destino);
        destino.setOnItemSelectedListener(this);

        //paso los datos del spinner a un array
        ArrayAdapter <String> datos_origen = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.CiudadesOrigen));
       datos_origen.setDropDownViewResource(android.R.layout.simple_spinner_item);

       origen.setAdapter(datos_origen);
        ArrayAdapter <String> datos_destino = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.CiudadesDestino));
        datos_destino.setDropDownViewResource(android.R.layout.simple_spinner_item);
        destino.setAdapter(datos_destino);
        ejemplos=findViewById(R.id.ejemplos);
        ejemplos.setText("");
        //patern y matcher
        ida.setChecked(true);

    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {
        pulsado(view.getId());
    }
    private void lanzar(){
        Intent intento =  new Intent(this, receptor_viaje.class);
        intento.putExtra(IDENTIFICADOR_RECEPTOR, ("NOMBRE: "+nombre.getText().toString()+"\n"+
                "DNI: "+dni.getText().toString()+"\n"+
                "PUNTO DE RECOGIDA: "+recogida.getText().toString()+"\n"+
                "FECHA DE IDA: "+fechaIda.getText().toString()+"\n"+
                "HORA DE IDA: "+horaIda.getText().toString())+"\n"+
                "FECHA DE VUELTA: "+fechaVuelta.getText().toString()+"\n"+
                "HORA DE VUELTA: "+horaVuelta.getText().toString()+"\n"+
                "Origen: "+variable_origen+"\n"+
                "Destino: "+variable_destino);
        startActivity(intento);
    }

    @Override
    public void onItemSelected(AdapterView<?> nombre_random, View view, int i, long l) {
        SimpleDateFormat formato1=new SimpleDateFormat("DD-MM-YYYY");

        switch (nombre_random.getId()){
            case R.id.origen:
                variable_origen=nombre_random.getItemAtPosition(i).toString();
                break;
            case R.id.destino:
                variable_destino=nombre_random.getItemAtPosition(i).toString();
                break;
        }

    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void pulsado(int opcion){
        Pattern dni_nie_patron = Pattern.compile("^[XYZ]?([0-9]{7,8})([A-Z])$");
        Matcher comprobar_dni_nie= dni_nie_patron.matcher(dni.getText().toString().toUpperCase());
        Pattern fecha_patron= Pattern.compile("(^0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/(\\d{4}$)");
        Matcher comprobar_fecha_ida=fecha_patron.matcher(fechaIda.getText().toString());
        Matcher comprobar_fecha_vuelta=fecha_patron.matcher(fechaVuelta.getText().toString());

        switch (opcion){
            case R.id.imprimir:

                //comprobar si son iguales el origen y el destino
                if(variable_destino.equals(variable_origen)||variable_destino.equals("Destino")||variable_origen.equals("Origen")){
                ejemplos.setText("Origen y destino no pueden ser iguales");
                }else{
                    check_origen_destino=true;
                }
                //comprobar si el dni funciona
                if(!comprobar_dni_nie.matches()){
                    ejemplos.setText("El dni/nie es incorrecto");
                }else{

                    check_dni=true;
                }
                //comprobar si una fecha es valida
                if(!comprobar_fecha_ida.matches()){
                    ejemplos.setText("El formato de fecha no es correcto DD/MM/YYYY");
                }else{
                    check_fecha_ida =true;
                }
                if(ida_vuelta.isChecked()&&!comprobar_fecha_vuelta.matches()){
                    ejemplos.setText("La fecha de vuelta no es correcta DD/MM/YYYY");
                }else{

                   /* if(fechaIda.getText().toString()<fechaVuelta.getText().toString()){

                    }else{
                    check_fecha_vuelta=true;}*/

                    check_fecha_vuelta=true;
                }

                //si todo es correcto y lanzar
                if(check_origen_destino&&check_dni&& check_fecha_ida&&check_fecha_vuelta){
                    lanzar();
                }



                break;
            case R.id.ida:
                ida_vuelta.setChecked(false);
                fechaVuelta.setText("");
                horaVuelta.setText("");
                fechaVuelta.setVisibility(View.INVISIBLE);
                horaVuelta.setVisibility(View.INVISIBLE);
                break;
            case R.id.ida_vuelta:

                ida.setChecked(false);
                fechaVuelta.setVisibility(View.VISIBLE);
                horaVuelta.setVisibility(View.VISIBLE);
                break;



        }
    }
}