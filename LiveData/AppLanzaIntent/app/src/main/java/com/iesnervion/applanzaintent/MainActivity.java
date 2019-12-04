package com.iesnervion.applanzaintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText nombre;
    EditText apellidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nombre = findViewById(R.id.etNombre);
        apellidos = findViewById(R.id.etApellido);
    }

    //Enviar los datos de esta aplicacion a otra aplicacion.
    public void enviarDatos(View v){
        Intent intent = new Intent();
        //Decimos que accion va a hacer el intent, en este caso enviar.
        intent.setAction(Intent.ACTION_SEND);
        //Introducimos los datos que queremos enviar."Name" es el nombre descriptivo para que al recibir el intentn en otra aplicacion sepamos cual poner.
        //intent.putExtra("nombre", nombre.getText().toString());
        intent.putExtra(Intent.EXTRA_TEXT, nombre.getText().toString());
        intent.putExtra("apellido", apellidos.getText().toString());
        //Formato en el que se envian los datos, en este caso texto plano.
        intent.setType("text/plain");
        startActivity(intent);
    }
}
