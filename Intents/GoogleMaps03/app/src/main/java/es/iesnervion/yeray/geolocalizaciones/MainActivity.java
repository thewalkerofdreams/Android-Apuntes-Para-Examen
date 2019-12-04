package es.iesnervion.yeray.geolocalizaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ArrayAdapter adaptador;
    ArrayList<Location> listaLocalizaciones = new ArrayList<Location>();
    ArrayList<String> localizaciones = new ArrayList<String>();
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner01);

        listaLocalizaciones.add(new Location("Londres", 51.5085297, -0.12574));
        listaLocalizaciones.add(new Location("Japon", 36.2048225, 138.2529297));
        listaLocalizaciones.add(new Location("España", 40.4636688, -3.7492199));
        listaLocalizaciones.add(new Location("R'lyeh", 47, 126));

        //Nada de arrays paralelos perro utliza el toString()

        localizaciones.add("Elija una localización");
        localizaciones.add("Londres");
        localizaciones.add("Japon");
        localizaciones.add("España");
        localizaciones.add("R'lyeh");

        adaptador = new ArrayAdapter(this, android.R.layout.simple_spinner_item, localizaciones);

        //Especificamos el layout que aparecerá al desplegarse la lista
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Agregamos el adaptador al tipo spinner
        spinner.setAdapter(adaptador);

        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

        boolean encontrado = false;

        if(position != 0) {//El spinner siempre apunta a -1 al iniciar la aplicación
            for (int i = 0; i < listaLocalizaciones.size() && !encontrado; i++) {
                if (localizaciones.get(position).equals(listaLocalizaciones.get(i).get_nameLocation())) {

                    Uri gmmIntentUri = Uri.parse("geo:" + listaLocalizaciones.get(i).get_latitud() + "," + listaLocalizaciones.get(i).get_longitud());
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");

                    if (mapIntent.resolveActivity(getPackageManager()) != null) {
                        startActivity(mapIntent);

                        encontrado = true;
                    }
                }
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
