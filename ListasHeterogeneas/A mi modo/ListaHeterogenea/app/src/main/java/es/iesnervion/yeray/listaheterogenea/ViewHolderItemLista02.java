package es.iesnervion.yeray.listaheterogenea;

import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolderItemLista02 {
    private ImageView _imagen;
    private TextView _texto;

    public ViewHolderItemLista02(){
        _imagen = null;
        _texto = null;
    }

    public ViewHolderItemLista02(ImageView _imagen, TextView _texto) {
        this._imagen = _imagen;
        this._texto = _texto;
    }

    public ImageView get_imagen() {
        return _imagen;
    }

    public TextView get_texto() {
        return _texto;
    }
}
