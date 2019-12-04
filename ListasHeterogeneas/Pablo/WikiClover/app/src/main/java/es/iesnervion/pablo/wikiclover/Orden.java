package es.iesnervion.pablo.wikiclover;

public class Orden {
    private int escudo;
    private String nombre;
    private int capitan;

    //Constructor
    public Orden(int escudo, String nombre, int capitan){
        this.escudo = escudo;
        this.nombre = nombre;
        this.capitan = capitan;
    }

    //Getter y setter
    public int getEscudo() {
        return escudo;
    }
    public void setEscudo(int escudo) {
        this.escudo = escudo;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCapitan() {
        return capitan;
    }
}
