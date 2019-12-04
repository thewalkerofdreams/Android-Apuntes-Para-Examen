package com.iesnervion.examenanopasado.Clases;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Futbolista implements Parcelable { //Implementamos parcelable para pasar objetos de una activity a otra.
    private  String nombre;
    private int foto;
    private ArrayList<String> posiciones;

    //Constructor por defecto
    public Futbolista(){
        nombre = "";
        foto = 0;
        posiciones = null;
    }

    //Constructor con parametros
    public Futbolista(String nombre, int foto, ArrayList<String> posiciones){
        this.nombre = nombre;
        this.foto = foto;
        this.posiciones = posiciones;
    }

    //getter and setters

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFoto() {
        return foto;
    }
    public void setFoto(int foto) {
        this.foto = foto;
    }

    public ArrayList<String> getPosiciones() {
        return posiciones;
    }
    public void setPosiciones(ArrayList<String> posiciones) {
        this.posiciones = posiciones;
    }

    //Metodos necesarios para Parcelable
    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(this.nombre);
        dest.writeInt(this.foto);
        dest.writeStringList(this.posiciones);
    }

    private Futbolista(Parcel in){
        this.nombre = in.readString();
        this.foto = in.readInt();
        this.posiciones = in.createStringArrayList();
    }

    public static final Creator<Futbolista> CREATOR = new Creator<Futbolista>() { //Los metodos de abajo los ha creado solo.
        @Override
        public Futbolista createFromParcel(Parcel parcel) {
            return null;
        }

        @Override
        public Futbolista[] newArray(int i) {
            return new Futbolista[0];
        }
    };
}
