package es.iesnervion.yeray.routemarker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*
     * Intefaz
     * Nombre: throwMakeRouteActivity
     * Comentario: Este método nos permite lanzar la actividad CreateRouteActivity.
     * Cabecera: public void throwMakeRouteActivity(View v)
     * Entrada:
     *   -View v
     * Postcondiciones: El método lanza la actividad CreateRouteActivity.
     * */
    public void throwMakeRouteActivity(View v){
        Intent i = new Intent(this, CreateRouteActivity.class);
        startActivity(i);
    }

    /*
     * Intefaz
     * Nombre: throwRoutesStorageActivity
     * Comentario: Este método nos permite lanzar la actividad RoutesStorageActivity.
     * Cabecera: public void throwRoutesStorageActivity(View v)
     * Entrada:
     *   -View v
     * Postcondiciones: El método lanza la actividad RoutesStorageActivity.
     * */
    public void throwRoutesStorageActivity(View v){
        Intent i = new Intent(this, RoutesStorageActivity.class);
        startActivity(i);
    }

    /*
     * Intefaz
     * Nombre: throwSettingsActivity
     * Comentario: Este método nos permite lanzar la actividad SettingsActivity.
     * Cabecera: public void throwSettingsActivity(View v)
     * Entrada:
     *   -View v
     * Postcondiciones: El método lanza la actividad SettingsActivity.
     * */
    public void throwSettingsActivity(View v){
        Intent i = new Intent(this, SettingsActivity.class);
        startActivity(i);
    }
}
