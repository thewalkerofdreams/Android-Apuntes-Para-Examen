package es.iesnervion.yeray.routemarker.ViewModels;

import android.app.Application;
import android.content.Context;
import android.database.Cursor;

import androidx.lifecycle.AndroidViewModel;

import java.util.ArrayList;

import es.iesnervion.yeray.routemarker.DDBB.MetodosDDBB;
import es.iesnervion.yeray.routemarker.Entities.LocalizationPoint;

public class FormRouteActivityVM extends AndroidViewModel {
    private String _name;
    private Context _context;

    public FormRouteActivityVM(Application application){
        super(application);
        _context = application.getBaseContext();
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public Context get_context() {
        return _context;
    }

    public void set_context(Context _context) {
        this._context = _context;
    }

    /*
     * Interfaz
     * Nombre: insertarRutaYLocalizaciones
     * Comentario: Este método nos permite insertar un ruta y sus localizaciones en la base de datos.
     * Cabecera: public void insertarRutasYLocalizaciones(Context context, String nombreRuta, ArrayList<LocalizationPoint> localizationPoints)
     * Entrada:
     *   -Context context
     *   -String nombreRuta,
     *   -ArrayList<LocalizationPoint> localizationPoints
     * Postcondiciones: El método inserta la nueva ruta y sus localizaciones en la base de datos.
     * */
    public void insertarRutasYLocalizaciones(Context context, String nombreRuta, ArrayList<LocalizationPoint> localizationPoints){
        MetodosDDBB metodosDDBB = new MetodosDDBB();
        metodosDDBB.insertRoute(context, nombreRuta);//Almacenamos la ruta
        Cursor cursor = metodosDDBB.getRoute(context, nombreRuta);
        cursor.moveToFirst();
        int idRoute = cursor.getInt(0);//Obtenemos el id de la ruta

        for(int i = 0; i < localizationPoints.size(); i++){
            metodosDDBB.insertLocalizationPoint(context, localizationPoints.get(i).get_pointNumber(), localizationPoints.get(i).get_latitude(),
                    localizationPoints.get(i).get_longitude(), idRoute);
        }
    }
}
