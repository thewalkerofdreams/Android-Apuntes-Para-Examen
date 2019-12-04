package com.iesnervion.apprecibeintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText nombre;
    EditText apellidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        nombre = findViewById(R.id.etNombre);
        apellidos = findViewById(R.id.etApellido);

        if(Intent.ACTION_SEND.equals(action) && "text/plain".equals(type)){
            //2 Formas de identificar los datos que me llegan.
            apellidos.setText(intent.getStringExtra("apellido")); //Nombre exacto
            nombre.setText(intent.getStringExtra(Intent.EXTRA_TEXT)); //Nombre general
        }
    }
}
