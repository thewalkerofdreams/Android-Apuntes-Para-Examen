package es.iesnervion.yeray.routemarker.ViewModels;

import android.app.Application;
import android.content.Context;
import android.location.LocationManager;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import es.iesnervion.yeray.routemarker.Entities.LocalizationPoint;

public class SeeRouteActivityVM extends AndroidViewModel {
    private float _zoom;
    private Context _context;
    private ArrayList<LocalizationPoint> _localizationPoints;
    private int _idRoute;

    public SeeRouteActivityVM(Application application){
        super(application);
        _context = application.getBaseContext();
        _localizationPoints = new ArrayList<LocalizationPoint>();
        _idRoute = -1;
        _zoom = 13;
    }

    public float get_zoom() {
        return _zoom;
    }

    public void set_zoom(float _zoom) {
        this._zoom = _zoom;
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

    public int get_idRoute() {
        return _idRoute;
    }

    public void set_idRoute(int _idRoute) {
        this._idRoute = _idRoute;
    }
}
