package com.iesnervion.examenanopasado.Clases;

import android.os.Parcel;
import android.os.Parcelable;

public class Baloncescista implements Parcelable {
    private String nombre;
    private int foto;
    private int puntosPartido;
    private int rebotesPartido;
    private int asistenciasPartido;

    //Constructores
    //Por defecto
    public Baloncescista(){
        nombre = "";
        foto = 0;
        puntosPartido = 0;
        rebotesPartido = 0;
        asistenciasPartido = 0;
    }

    //Con parametros
    public Baloncescista(String nombre, int puntos, int rebotes, int asist, int foto){
        this.nombre = nombre;
        this.puntosPartido = puntos;
        this.rebotesPartido = rebotes;
        this.asistenciasPartido = asist;
        this.foto = foto;
    }

    //Propiedades publicas
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

    public int getPuntosPartido() {
        return puntosPartido;
    }
    public void setPuntosPartido(int puntosPartido) {
        this.puntosPartido = puntosPartido;
    }

    public int getRebotesPartido() {
        return rebotesPartido;
    }
    public void setRebotesPartido(int rebotesPartido) {
        this.rebotesPartido = rebotesPartido;
    }

    public int getAsistenciasPartido() {
        return asistenciasPartido;
    }
    public void setAsistenciasPartido(int asistenciasPartido) {
        this.asistenciasPartido = asistenciasPartido;
    }

    //Metodos para Parcelable
    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(this.nombre);
        dest.writeInt(this.foto);
        dest.writeInt(this.asistenciasPartido);
        dest.writeInt(this.puntosPartido);
        dest.writeInt(this.rebotesPartido);
    }

    private Baloncescista(Parcel in){
        this.nombre = in.readString();
        this.foto = in.readInt();
        this.asistenciasPartido = in.readInt();
        this.puntosPartido = in.readInt();
        this.rebotesPartido = in.readInt();
    }

    public static final Creator<Baloncescista> CREATOR = new Creator<Baloncescista>() {
        @Override
        public Baloncescista createFromParcel(Parcel parcel) {
            return new Baloncescista(parcel);
        }

        @Override
        public Baloncescista[] newArray(int i) {
            return new Baloncescista[i];
        }
    };
}
