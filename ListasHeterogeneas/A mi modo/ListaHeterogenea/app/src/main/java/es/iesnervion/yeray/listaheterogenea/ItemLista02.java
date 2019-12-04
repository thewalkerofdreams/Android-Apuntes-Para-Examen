package es.iesnervion.yeray.listaheterogenea;

public class ItemLista02 {
    private String _texto;
    private int _imagen;

    public ItemLista02(){
        _texto = "DEFAULT";
        _imagen = 0;
    }

    public ItemLista02(String _texto, int _imagen) {
        this._texto = _texto;
        this._imagen = _imagen;
    }

    public String get_texto() {
        return _texto;
    }

    public void set_texto(String _texto) {
        this._texto = _texto;
    }

    public int get_imagen() {
        return _imagen;
    }

    public void set_imagen(int _imagen) {
        this._imagen = _imagen;
    }
}
