package es.iesnervion.yeray.routemarker.Fragments;

import android.Manifest;
import android.app.ActivityManager;
import android.app.Application;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationManager;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOverlay;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import es.iesnervion.yeray.routemarker.CreateRouteActivity;
import es.iesnervion.yeray.routemarker.Entities.LocalizationPoint;
import es.iesnervion.yeray.routemarker.R;
import es.iesnervion.yeray.routemarker.ViewModels.CreateRouteActivityVM;


public class GoogleMapsFragment extends SupportMapFragment implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMarkerDragListener {

    private GoogleMap map;
    CreateRouteActivityVM createRouteActivityVM;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        createRouteActivityVM = ViewModelProviders.of(getActivity()).get(CreateRouteActivityVM.class);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getMapAsync(this);//Inicializa el mapa
    }

    /*@Override
    public boolean onMarkerClick(Marker marker) {
        Toast.makeText(getContext(), "Clicked a marker with title..." + marker.getTitle(), Toast.LENGTH_SHORT).show();
        return true;    //No nos interesa modificar la variable booleana
    }*/

    @Override
    public void onMarkerDragStart(Marker marker) {
        createRouteActivityVM.eliminarMarcador(marker);
        marker.remove();
        pintarLineas(createRouteActivityVM.get_localizationPoints());
    }

    @Override
    public void onMarkerDrag(Marker marker) {
        //Mientras se esta moviendo el marcador
    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        //Al finalizar el moviento del marcador
    }

    /*
     * Interfaz
     * Nombre: funcionActualizarMapa
     * Comanterio: Esta función nos permite actualizar el mapa.
     * Cabecera: public void funcionEngancheMapa()
     * Postcondiciones: El método modifica la localización del mapa en el fragmento.
     * */
    public void funcionEngancheMapa() {
       getMapAsync(this);
    }

    @Override
    public void onMapClick(LatLng latLng) {
        //Mostramos las coordenadas con un Toast
        String format = String.format(Locale.getDefault(),
                "Lat/Lng = (%f,%f)", latLng.latitude, latLng.longitude);
        Toast.makeText(getContext(), format, Toast.LENGTH_LONG).show();

        //Almacenamos la última localización clicada
        createRouteActivityVM.set_lastLocalizationClicked(new LocalizationPoint(latLng.latitude, latLng.longitude));
    }

    @Override
    public void onMapReady(GoogleMap map) {
        this.map = map;
        LatLng latLng;
        // Posicionar el mapa en una localización y con un nivel de zoom
        float zoom;
        if(createRouteActivityVM.get_actualLocation() == null){
            latLng = new LatLng(40.4636688, -3.7492199);
            zoom = 13;
        }else{
            latLng = new LatLng(createRouteActivityVM.get_actualLocation().getLatitude(), createRouteActivityVM.get_actualLocation().getLongitude());
            zoom = createRouteActivityVM.get_zoom();
        }

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));

        // Colocar un marcador en la misma posición
        //map.addMarker(new MarkerOptions().position(latLng));

        //Eventos
        map.setOnMapClickListener(this);
        map.setOnMarkerDragListener(this);
    }

    /*
    * Interfaz
    * Nombre: colocarMarcador
    * Comentario: Este método nos permite colocar un marcador en el mapa.
    * Cabecera: public void colocarMarcador(LatLng latLng)
    * Entrada:
    *   -LatLng latLng
    * Postcondiciones: El método coloca un marcador en el mapa.
    * */
    public void colocarMarcador(LatLng latLng, ArrayList<LocalizationPoint> localizaciones){
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.draggable(true);//Permite que podamos mover elmarcador por el mapa, yo lo utilizo para hacer un marcado largo
        map.addMarker(markerOptions);

        createRouteActivityVM.almacenarUltimaLocalizacion();
        pintarLineas(localizaciones);//Intentamos pintar las lineas
    }

    /*
    * Interfaz
    * Nombre: pintarLineas
    * Comentario: Este método nos permite pintar las lineas entre las
    * diferentes localizaciones almacenadas para la ruta.
    * Cabecera: public void pintarLineas(ArrayList<LocalizationPoint> localizaciones)
    * Entrada:
    *   -ArrayList<LocalizationPoint> localizaciones
    * Postcondiciones: El método pinta lineas entre los diferentes puntos de localización.
    * */
    public void pintarLineas(ArrayList<LocalizationPoint> localizaciones) {
        PolylineOptions opcionesPoliLinea = new PolylineOptions();
        for(int i = 0; i < localizaciones.size(); i++){
            opcionesPoliLinea.add(new LatLng(localizaciones.get(i).get_latitude(), localizaciones.get(i).get_longitude()));
        }

        opcionesPoliLinea.color(Color.BLUE);
        Polyline polyline = map.addPolyline(opcionesPoliLinea);
        polyline.setColor(Color.RED);
    }

}
