package es.iesnervion.yeray.geolocalizaciones;

public class Location {
    private String _nameLocation;
    private double _latitud;
    private double _longitud;

    public Location(){
        _nameLocation = "DEFAULT";
        _latitud = 0;
        _longitud = 0;
    }

    public Location(String _nameLocation, double _latitud, double _longitud){
        this._nameLocation = _nameLocation;
        this._latitud = _latitud;
        this._longitud = _longitud;
    }

    public String get_nameLocation() {
        return _nameLocation;
    }

    public void set_nameLocation(String _nameLocation) {
        this._nameLocation = _nameLocation;
    }

    public double get_latitud() {
        return _latitud;
    }

    public void set_latitud(int _latitud) {
        this._latitud = _latitud;
    }

    public double get_longitud() {
        return _longitud;
    }

    public void set_longitud(int _longitud) {
        this._longitud = _longitud;
    }
}
