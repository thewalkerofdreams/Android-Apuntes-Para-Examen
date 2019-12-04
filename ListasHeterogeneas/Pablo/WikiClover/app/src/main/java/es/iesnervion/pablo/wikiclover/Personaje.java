package es.iesnervion.pablo.wikiclover;

import android.widget.ImageView;
import android.widget.TextView;

public class Personaje {
    String nombrePersonaje;
    int imagenPersonaje;

    public Personaje(String nombrePersonaje, int imagenPersonaje){
        this.nombrePersonaje = nombrePersonaje;
        this.imagenPersonaje = imagenPersonaje;
    }

    public String getNombrePersonaje() {
        return nombrePersonaje;
    }
    public void setNombrePersonaje(String nombrePersonaje) {
        this.nombrePersonaje = nombrePersonaje;
    }

    public int getImagenPersonaje() {
        return imagenPersonaje;
    }
    public void setImagenPersonaje(int imagenPersonaje) {
        this.imagenPersonaje = imagenPersonaje;
    }
}
