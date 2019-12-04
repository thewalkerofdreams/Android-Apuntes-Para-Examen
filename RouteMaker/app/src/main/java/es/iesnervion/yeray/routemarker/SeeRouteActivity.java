package es.iesnervion.yeray.routemarker;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;

import es.iesnervion.yeray.routemarker.DDBB.MetodosDDBB;
import es.iesnervion.yeray.routemarker.Entities.LocalizationPoint;
import es.iesnervion.yeray.routemarker.Fragments.GoogleMapsFragment;
import es.iesnervion.yeray.routemarker.Fragments.SeeRouteFragment;
import es.iesnervion.yeray.routemarker.ViewModels.SeeRouteActivityVM;

public class SeeRouteActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    SeeRouteActivityVM seeRouteActivityVM;
    SeekBar seekBar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_route);
        seeRouteActivityVM = ViewModelProviders.of(this).get(SeeRouteActivityVM.class);

        seekBar = findViewById(R.id.seekBar01);
        seekBar.setMax(13);//Le asignamos el valor máximo al SeekBar.
        seekBar.setProgress(1);//Le indicamos el rango de progreso.
        seekBar.setOnSeekBarChangeListener(this);

        if(getIntent() != null){//Si existe un intent
            int idRoute = Integer.valueOf(getIntent().getExtras().getString("idRoute"));
            storeLocalizationPoints(this, idRoute);
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        seeRouteActivityVM.set_zoom(i);
        //Llamamos a una función del fragmento que nos permitirá actualizar el mapa
        FragmentManager fm = getSupportFragmentManager();
        SeeRouteFragment fragment = (SeeRouteFragment)fm.findFragmentById(R.id.fragmentGoogleMaps02);
        fragment.funcionEngancheMapa();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        //Cuando se carga por primera vez el SeekBar
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        //Cuando paramos de tocar el SeekBar
    }

    /*
     * Interfaz
     * Nombre: storeLocalizationPoints
     * Comentario: Este método nos permite almacenar en el viewmodel de la actividad
     * los puntos de localización de una ruta.
     * Cabecera:
     * */
    public void storeLocalizationPoints(Context context, int idRoute){
        MetodosDDBB metodosDDBB = new MetodosDDBB();
        Cursor cursor = metodosDDBB.getPointsOfARoute(context, idRoute);
        ArrayList<LocalizationPoint> localizationsPoints  = new ArrayList<LocalizationPoint>();
        LocalizationPoint item;
        if(cursor != null && cursor.moveToFirst()){//Si la tabla itemLista contiene filas
            do{
                item = new LocalizationPoint(cursor.getInt(0), cursor.getInt(1), cursor.getDouble(2), cursor.getDouble(3), cursor.getInt(4));
                localizationsPoints.add(item);
            }while (cursor.moveToNext());
        }

        seeRouteActivityVM.set_localizationPoints(localizationsPoints);
    }
}
