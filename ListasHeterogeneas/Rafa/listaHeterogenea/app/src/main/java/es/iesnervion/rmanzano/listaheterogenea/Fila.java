package es.iesnervion.rmanzano.listaheterogenea;

public class Fila {
    private int foto;
    private String texto;

    public Fila(int foto, String texto) {
        this.foto = foto;
        this.texto = texto;
    }

    public int getFoto() {
        return foto;
    }

    public String getTexto() {
        return texto;
    }
}
