package es.iesnervion.yeray.listaheterogenea;

import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolderItemLista01 {
    private ImageView _imagen01;
    private ImageView _imagen02;
    private TextView _texto;

    public ViewHolderItemLista01(){
        _imagen01 = null;
        _imagen02 = null;
        _texto = null;
    }

    public ViewHolderItemLista01(ImageView _imagen01, ImageView _imagen02, TextView _texto) {
        this._imagen01 = _imagen01;
        this._imagen02 = _imagen02;
        this._texto = _texto;
    }

    public ImageView get_imagen01() {
        return _imagen01;
    }

    public ImageView get_imagen02() {
        return _imagen02;
    }

    public TextView get_texto() {
        return _texto;
    }
}
