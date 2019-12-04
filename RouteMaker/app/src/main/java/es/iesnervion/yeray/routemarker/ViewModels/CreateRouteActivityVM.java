package es.iesnervion.yeray.routemarker.ViewModels;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;

import androidx.core.app.ActivityCompat;
import androidx.lifecycle.AndroidViewModel;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import es.iesnervion.yeray.routemarker.Entities.LocalizationPoint;
import es.iesnervion.yeray.routemarker.Entities.Route;

public class CreateRouteActivityVM extends AndroidViewModel {
    private LocationManager _locManager;
    private Location _actualLocation;
    private float _zoom;
    private Context _context;
    private ArrayList<LocalizationPoint> _localizationPoints;
    private Route _route;
    private LocalizationPoint _lastLocalizationClicked;

    public CreateRouteActivityVM(Application application){
        super(application);
        _context = application.getBaseContext();
        _locManager = (LocationManager)_context.getApplicationContext().getSystemService(_context.LOCATION_SERVICE);
        _actualLocation = getLastKnownLocation();
        _localizationPoints = new ArrayList<LocalizationPoint>();
        _route = new Route();
        _lastLocalizationClicked = null;
        _zoom = 13;
    }

    public LocationManager get_locManager() {
        return _locManager;
    }

    public void set_locManager(LocationManager _locManager) {
        this._locManager = _locManager;
    }

    public Location get_actualLocation() {
        return _actualLocation;
    }

    public void set_actualLocation(Location _actualLocation) {
        this._actualLocation = _actualLocation;
    }

    public Context get_context() {
        return _context;
    }

    public void set_context(Context _context) {
        this._context = _context;
    }

    public ArrayList<LocalizationPoint> get_localizationPoints() {
        return _localizationPoints;
    }

    public void set_localizationPoints(ArrayList<LocalizationPoint> _localizationPoints) {
        this._localizationPoints = _localizationPoints;
    }

    public Route get_route() {
        return _route;
    }

    public void set_route(Route _route) {
        this._route = _route;
    }

    public LocalizationPoint get_lastLocalizationClicked() {
        return _lastLocalizationClicked;
    }

    public void set_lastLocalizationClicked(LocalizationPoint _lastLocalizationClicked) {
        this._lastLocalizationClicked = _lastLocalizationClicked;
    }

    public float get_zoom() {
        return _zoom;
    }

    public void set_zoom(float _zoom) {
        this._zoom = _zoom;
    }

    /*
    * Interfaz
    * Nombre: getLastKnownLocation
    * Comentario: Este método nos permite obtener la última localización
    * conocida del dispositivo.
    * Cabecera: private Location getLastKnownLocation()
    * Postcondiciones: El método modifica el estado del atributo _actualLocalization.
    * */
    private Location getLastKnownLocation() {
    List<String> providers = _locManager.getProviders(true);
        Location bestLocation = null;
        if(ActivityCompat.checkSelfPermission(_context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(_context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            for (String provider : providers) {
                Location l = _locManager.getLastKnownLocation(provider);
                if (l == null) {
                    continue;
                }
                if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                    // Found best last known location: %s", l);
                    bestLocation = l;
                }
            }
        }
        return bestLocation;
    }

    /*
    * Interfaz
    * Nombre: almacenarUltimaLocalizacion
    * Comentario: Este método nos permite almacenar la última localización almacenada en el ViewModel,
    * en la lista de localizaciones.
    * Cabecera: public boolean almacenarUltimaLocalizacion()
    * Salida:
    *   -boolean localizacionAlmacenada
    * */
    public boolean almacenarUltimaLocalizacion(){
        boolean localizacionAlmacenada = false;
        if(_lastLocalizationClicked != null){
            _lastLocalizationClicked.set_pointNumber(getLastPositionNumber()+1);//Modificamos el número del punto de localización
            _localizationPoints.add(_lastLocalizationClicked);//Añadimos el punto de localización
            _lastLocalizationClicked = null;
            localizacionAlmacenada = true;
        }

        return localizacionAlmacenada;
    }

    /*
    * Interfaz
    * Nombre: getLastPositionNumber
    * Comentario: Este método nos permite obtener el número asignado al último
    * punto de localización añadido a la ruta. Si la función devuelve 0 significa que
    * aún no hay puntos de localización en la ruta.
    * Cabecera: public int getLastPositionNumber()
    * Salida:
    *   -int positionNumber
    * Postcondiciones: La función devuelve el número de la ultima posición añadida a la
    * ruta.
    * */
    public int getLastPositionNumber(){
        int positionNumber = 0;
        int numeroDeLocalizaciones = _localizationPoints.size();
        if(numeroDeLocalizaciones > 0){
            positionNumber = _localizationPoints.get(numeroDeLocalizaciones - 1).get_pointNumber();
        }
        return positionNumber;
    }

    /*
    * Interfaz
    * Nombre: eliminarMarcador
    * Comentario: Este método nos permite eliminar de punto de localización
    * según un marcador dado por parámetro, es decir, si pasamos un marcador que tiene
    * la misma localización que uno de los puntos de localización almacenamos en este
    * ViewModel, se eliminará ese punto de localización.
    * Cabecera:public boolean eliminarMarcador(Marker marcador)
    * Entrada:
    *   -Marker marcador
    * Salida:
    *   -boolean eliminado
    * Postcondiciones: El método devuelve un valor booleano asociado al nombre, true si
    * se ha conseguido eliminar el punto de localización o false en caso contrario.
    * */
    public boolean eliminarMarcador(Marker marcador){//Los puntos de localización aún no se han guardado en la base de datos
        boolean eliminado = false;
        int pointNumber = getPointNumber(marcador);

        if(pointNumber >= 0){//Si el punto de localización existe
            get_localizationPoints().remove(pointNumber);
        }

        return eliminado;
    }

    /*
    * Interfaz
    * Nombre: getPointNumber
    * Comentario: Este método nos permite obtener el atributo pointNumber de un punto de
    * localización según un marcador.
    * Cabecera: public int getPointNumber(Marker marcador)
    * Entrada:
    *   -Marker marcador
    * Salida:
    *   -int pointNumber
    * Postcondiciones: El método devuelve un número entero asociado al nombre, que será el
    * pointNumber de una localización si el marcador coincide con ella o -1 si no se ha encontrado
    * ningún punto de localización que coincida con el marcador. En el caso de existir más de un
    * punto de localización que coincida con el marcador solo se devolverá la posición del
    * primero encontrado.
    * */
    private int getPointNumber(Marker marcador){
        boolean encontrado = false;
        int pointNumber = -1;
        DecimalFormat df = new DecimalFormat("#.0");

        for(int i = 0; i < _localizationPoints.size() && !encontrado; i++){
            double latitude = (double)Math.round(marcador.getPosition().latitude * 10d) / 10d;
            double longitude = (double)Math.round(marcador.getPosition().longitude * 10d) / 10d;
            if(_localizationPoints.get(i).get_latitude() == latitude &&
            _localizationPoints.get(i).get_longitude() == longitude){
                pointNumber = i;
                encontrado = true;
            }
        }

        return pointNumber;
    }
}
