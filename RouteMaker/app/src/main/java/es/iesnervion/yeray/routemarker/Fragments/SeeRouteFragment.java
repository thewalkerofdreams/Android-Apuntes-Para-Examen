package es.iesnervion.yeray.routemarker.Fragments;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

import es.iesnervion.yeray.routemarker.DDBB.MetodosDDBB;
import es.iesnervion.yeray.routemarker.Entities.LocalizationPoint;
import es.iesnervion.yeray.routemarker.RouteList.ItemRouteList;
import es.iesnervion.yeray.routemarker.ViewModels.SeeRouteActivityVM;

public class SeeRouteFragment extends SupportMapFragment implements OnMapReadyCallback {

    private GoogleMap map;
    SeeRouteActivityVM seeRouteActivityVM;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        seeRouteActivityVM = ViewModelProviders.of(getActivity()).get(SeeRouteActivityVM.class);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getMapAsync(this);//Inicializa el mapa
    }

    @Override
    public void onMapReady(GoogleMap map) {
        this.map = map;
        LatLng latLng;
        // Posicionar el mapa el primer punto de localización
        if(seeRouteActivityVM.get_localizationPoints().size() > 0){
            latLng = new LatLng(seeRouteActivityVM.get_localizationPoints().get(0).get_latitude(),
                    seeRouteActivityVM.get_localizationPoints().get(0).get_longitude());
        }else{
            latLng = new LatLng(0, 0);
        }
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, seeRouteActivityVM.get_zoom()));

        // Colocar un marcador en cada punto de localización
        ArrayList<LocalizationPoint> localizationPoints = new ArrayList<LocalizationPoint>();
        localizationPoints = seeRouteActivityVM.get_localizationPoints();
        LatLng posicion;

        for(int i = 0; i < localizationPoints.size(); i++){
            posicion = new LatLng(localizationPoints.get(i).get_latitude(), localizationPoints.get(i).get_longitude());
            colocarMarcador(posicion);
            //map.addMarker(new MarkerOptions().position(posicion));
        }

        //Pintamos las lias entre los diferentes marcadores
        pintarLineas(localizationPoints);
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

    /*
     * Interfaz
     * Nombre: colocarMarcador
     * Comentario: Este método nos permite colocar un marcador en el mapa.
     * Cabecera: public void colocarMarcador(LatLng latLng)
     * Entrada:
     *   -LatLng latLng
     * Postcondiciones: El método coloca un marcador en el mapa.
     * */
    public void colocarMarcador(LatLng latLng){
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        map.addMarker(markerOptions);
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