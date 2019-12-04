package es.iesnervion.yeray.examen_lista_deportistas.Entities;

import android.os.Parcel;
import android.os.Parcelable;

public class Baloncescista implements Parcelable {
    private String _nombre;
    private int _foto;
    private int _puntoPorPartido;
    private int _rebotesPorPartido;
    private int _asistenciasPorPartido;

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Baloncescista createFromParcel(Parcel in) {
            return new Baloncescista(in);
        }

        public Baloncescista[] newArray(int size) {
            return new Baloncescista[size];
        }
    };

    public Baloncescista(){
        _nombre = "DEFAULT";
        _foto = 0;
        _puntoPorPartido = 0;
        _rebotesPorPartido = 0;
        _asistenciasPorPartido = 0;
    }

    public Baloncescista(String nombre, int foto, int puntoPorPartido, int rebotesPorPartido, int asistenciasPorPartido){
        _nombre = nombre;
        _foto = foto;
        _puntoPorPartido = puntoPorPartido;
        _rebotesPorPartido = rebotesPorPartido;
        _asistenciasPorPartido = asistenciasPorPartido;
    }

    public String get_nombre() {
        return _nombre;
    }

    public void set_nombre(String _nombre) {
        this._nombre = _nombre;
    }

    public int get_foto() {
        return _foto;
    }

    public void set_foto(int _foto) {
        this._foto = _foto;
    }

    public int get_puntoPorPartido() {
        return _puntoPorPartido;
    }

    public void set_puntoPorPartido(int _puntoPorPartido) {
        this._puntoPorPartido = _puntoPorPartido;
    }

    public int get_rebotesPorPartido() {
        return _rebotesPorPartido;
    }

    public void set_rebotesPorPartido(int _rebotesPorPartido) {
        this._rebotesPorPartido = _rebotesPorPartido;
    }

    public int get_asistenciasPorPartido() {
        return _asistenciasPorPartido;
    }

    public void set_asistenciasPorPartido(int _asistenciasPorPartido) {
        this._asistenciasPorPartido = _asistenciasPorPartido;
    }

    // Parcelling part
    public Baloncescista(Parcel in){
        this._nombre = in.readString();
        this._foto = in.readInt();
        this._puntoPorPartido = in.readInt();
        this._rebotesPorPartido = in.readInt();
        this._asistenciasPorPartido = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._nombre);
        dest.writeInt(this._foto);
        dest.writeInt(this._puntoPorPartido);
        dest.writeInt(this._rebotesPorPartido);
        dest.writeInt(this._asistenciasPorPartido);
    }

}
