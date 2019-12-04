package es.iesnervion.yeray.examen_lista_deportistas.Entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Futbolista implements Parcelable {
    private String _nombre;
    private int _foto;
    private ArrayList<String> _posiciones;

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Futbolista createFromParcel(Parcel in) {
            return new Futbolista(in);
        }

        public Futbolista[] newArray(int size) {
            return new Futbolista[size];
        }
    };

    public Futbolista(){
        _nombre = "DEFAULT";
        _foto = 0;
        _posiciones = new ArrayList<String>();
    }

    public Futbolista(String nombre, int foto, ArrayList<String> posiciones){
        _nombre = nombre;
        _foto = foto;
        _posiciones = posiciones;
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

    public ArrayList<String> get_posiciones() {
        return _posiciones;
    }

    public void set_posiciones(ArrayList<String> _posiciones) {
        this._posiciones = _posiciones;
    }

    // Parcelling part
    public Futbolista(Parcel in){
        this._nombre = in.readString();
        this._foto = in.readInt();
        this._posiciones = in.createStringArrayList();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._nombre);
        dest.writeInt(this._foto);
        dest.writeStringList(this._posiciones);
    }
}
