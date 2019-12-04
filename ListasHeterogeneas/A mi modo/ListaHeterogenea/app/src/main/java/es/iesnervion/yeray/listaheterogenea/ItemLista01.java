package es.iesnervion.yeray.listaheterogenea;

public class ItemLista01 {
    private int _imagen01;
    private int _imagen02;
    private String _texto;

    public ItemLista01(){
        this._imagen01 = 0;
        this._imagen02 = 0;
        this._texto = "DEFAULT";
    }

    public ItemLista01(int _imagen01, int _imagen02, String _texto) {
        this._imagen01 = _imagen01;
        this._imagen02 = _imagen02;
        this._texto = _texto;
    }

    public int get_imagen01() {
        return _imagen01;
    }

    public void set_imagen01(int _imagen01) {
        this._imagen01 = _imagen01;
    }

    public int get_imagen02() {
        return _imagen02;
    }

    public void set_imagen02(int _imagen02) {
        this._imagen02 = _imagen02;
    }

    public String get_texto() {
        return _texto;
    }

    public void set_texto(String _texto) {
        this._texto = _texto;
    }
}
