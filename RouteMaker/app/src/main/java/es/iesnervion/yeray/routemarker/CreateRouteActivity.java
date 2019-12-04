package es.iesnervion.yeray.routemarker;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.maps.model.LatLng;

import java.nio.BufferUnderflowException;

import es.iesnervion.yeray.routemarker.Entities.LocalizationPoint;
import es.iesnervion.yeray.routemarker.Fragments.GoogleMapsFragment;
import es.iesnervion.yeray.routemarker.ViewModels.CreateRouteActivityVM;

public class CreateRouteActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, ActivityCompat.OnRequestPermissionsResultCallback {

    SeekBar seekBar;
    CreateRouteActivityVM createRouteActivityVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_route);

        seekBar = findViewById(R.id.seekBar01);
        seekBar.setMax(13);//Le asignamos el valor máximo al SeekBar.
        seekBar.setProgress(1);//Le indicamos el rango de progreso.
        seekBar.setOnSeekBarChangeListener(this);

        //Pedimos los permisos
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }
        //Definimos el ViewModel, para que el siguiente viewModel funcione correctamente debe tener los permisos anteriores
        createRouteActivityVM = ViewModelProviders.of(this).get(CreateRouteActivityVM.class);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
        createRouteActivityVM.set_zoom(progress);
        //Llamamos a una función del fragmento que nos permitirá actualizar el mapa
        FragmentManager fm = getSupportFragmentManager();
        GoogleMapsFragment fragment = (GoogleMapsFragment)fm.findFragmentById(R.id.fragmentGoogleMaps01);
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {//Resultado de repuesta de un permiso
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        String mensaje = "";

        switch(requestCode){
            case 1:
                mensaje = "Coarse Location and Fine Location";
                break;
        }
        // Checking whether user granted the permission or not.
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Showing the toast message
            Toast.makeText(CreateRouteActivity.this, mensaje+" Permission Granted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(CreateRouteActivity.this, mensaje+" Permission Denied", Toast.LENGTH_SHORT).show();
        }
    }

    /*
     * Interfaz
     * Nombre: intentarMarcarLocalizacion
     * Comentario: Este método nos permite marcar la última lozalización pulsada en el mapa.
     * Si no existe una localización marcada anteriormente, no se creará ningún marcador.
     * Cabecera: public boolean intentarMarcarLocalizacion(View view)
     * Entrada:
     *   -View view
     * Salida:
     *   -boolean marcado
     * Postcondiciones: El método devuelve un valor booleano asociado al nombre, verdadero
     * si se ha conseguido realizar una marca o false en caso contrario.
     * */
    public boolean intentarMarcarLocalizacion(View view){
        boolean marcado = false;

        if(createRouteActivityVM.get_lastLocalizationClicked() != null){

            //Llamamos a una función del fragmento
            FragmentManager fm = getSupportFragmentManager();
            GoogleMapsFragment fragment = (GoogleMapsFragment)fm.findFragmentById(R.id.fragmentGoogleMaps01);

            LatLng latLng = new LatLng(createRouteActivityVM.get_lastLocalizationClicked().get_latitude(), createRouteActivityVM.get_lastLocalizationClicked().get_longitude());

            fragment.colocarMarcador(latLng, createRouteActivityVM.get_localizationPoints());
            marcado = true;
        }

        return marcado;
    }

    /*
    * Interfaz
    * Nombre: canelarCreacionRuta
    * Comentario: Este método nos permite volver a la actividad de inicio de la aplicación.
    * Este método es llamado por el boton btnCancelRoute de la actividad CreateRouteActivity.
    * Cabecera: public void canelarCreacionRuta(View view)
    * Entrada:
    *   -View view
    * Postcondiciones: Este método nos permite volver a la actividad principal de la aplicación.
    * */
    public void canelarCreacionRuta(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    /*
    * Interfaz
    * Nombre: goToRouteForm
    * Comentario: Este método nos permite ir al formulario de creación de la ruta.
    * Si se han marcado menos de dos puntos de localización en el mapa, no se redicionará al formulario.
    * EL método mostrará un mensaje de error por pantalla indicando que no hay rutas suficientes para
    * crear una ruta.
    * Cabecera: public void goToRouteForm(View view)
    * Entrada:
    *   -View view
    * Postcondiciones: Si el usuario ha marcado más de dos puntos de localización, comenzará la actividad
    * FormRouteActivity.
    * */
    public void goToRouteForm(View view){
        if (createRouteActivityVM.get_localizationPoints().size() >= 2){
            Intent i = new Intent(CreateRouteActivity.this, FormRouteActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("ArrayListLocalizationPoints", createRouteActivityVM.get_localizationPoints());
            //startActivity(i, bundle);
            i.putExtra("Bundle", bundle);
            startActivity(i);
        }else{
            Toast.makeText(CreateRouteActivity.this,"You need at least two routes.", Toast.LENGTH_SHORT).show();
        }
    }
}
