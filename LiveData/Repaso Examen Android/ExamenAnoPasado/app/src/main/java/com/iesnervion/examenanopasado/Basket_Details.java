package com.iesnervion.examenanopasado;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.iesnervion.examenanopasado.Clases.Baloncescista;
import com.iesnervion.examenanopasado.ViewModel.JugadoresVM;

import java.util.ArrayList;

public class Basket_Details extends AppCompatActivity {

    ImageView foto;
    TextView nombre;
    TextView asistencias;
    TextView puntos;
    JugadoresVM vm;
    Object o;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket__details);

        vm = ViewModelProviders.of(this).get(JugadoresVM.class);
        foto = findViewById(R.id.ImagenJugadorBaloncesto);
        nombre = findViewById(R.id.NombreBaloncesto);
        asistencias = findViewById(R.id.AsistBaloncesto);
        puntos = findViewById(R.id.PuntosBaloncesto);

        o = getIntent().getParcelableExtra("jugador"); //Obtenemos el intent

        //Establecemos los datos del jugador de baloncesto
        Baloncescista b = (Baloncescista)o;

        foto.setImageResource(b.getFoto());
        nombre.setText("Nombre: " + b.getNombre());
        asistencias.setText("Asistencias: " + b.getAsistenciasPartido());
        puntos.setText("Puntos: " + b.getPuntosPartido());
    }

    public void onClick(View v){
        Baloncescista baloncesto = (Baloncescista)o;
        vm.EliminarJugador(0); //Eliminamos al jugador
        ArrayList<Object> objectos = vm.getListadoJugadores().getValue();
        Intent intentBaloncesto = new Intent(this, MainActivity.class);
        finish();
        //startActivity(intentBaloncesto);
    }
}
