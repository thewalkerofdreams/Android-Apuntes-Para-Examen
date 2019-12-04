package es.iesnervion.rmanzano.mesafutsalcompadre;

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

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
