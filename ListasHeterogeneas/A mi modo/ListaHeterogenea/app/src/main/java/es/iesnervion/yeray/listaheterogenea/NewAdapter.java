package es.iesnervion.yeray.listaheterogenea;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class NewAdapter extends BaseAdapter {
    private Context _context;
    private int _layout01;
    private int _layout02;
    private ArrayList<ItemLista01> _items01 = new ArrayList<ItemLista01>();
    private ArrayList<ItemLista02> _items02 = new ArrayList<ItemLista02>();

    public NewAdapter(Context _context, int _layout01 , int _layout02, ArrayList<ItemLista01> _items01, ArrayList<ItemLista02> _items02){
        this._context = _context;
        this._layout01 = _layout01;
        this._layout02 = _layout02;
        this._items01 = _items01;
        this._items02 = _items02;
    }

    @Override
    public int getCount() {
        return _items01.size() + _items02.size();
    }

    @Override
    public Object getItem(int position) {
        Object object = null;
        boolean turnoLista01 = true;
        int punteroLista01 = 0;
        int punteroLista02 = 0;

        if(position < getCount()){//Si es posible obtener el elemento
            if(_items01.size() == 0){//Si lista _items01 se encuentra vacía
                turnoLista01 = false;
            }

            for(int i = 0; i <= position; i++){
                if(i == position){//Si es la posición exacta
                    if(turnoLista01){
                        object = _items01.get(punteroLista01);
                    }else{
                        object = _items02.get(punteroLista02);
                    }
                }else{
                    if(turnoLista01){
                        punteroLista01++;
                        if(punteroLista02 < _items02.size()){//Si el puntero aún no ha sobrepasado la lista _items02.
                            turnoLista01 = false;
                        }
                    }else{
                        if(!turnoLista01){
                            punteroLista02++;
                            if(punteroLista01 < _items01.size()){//Si el puntero aún no ha sobrepasado la lista _items01.
                                turnoLista01 = true;
                            }
                        }
                    }
                }
            }
        }

        return object;
    }

    @Override
    public long getItemId(int position) {
        long id = 0;
        boolean turnoLista01 = true;
        int punteroLista01 = 0;
        int punteroLista02 = 0;

        if(position < getCount()){//Si es posible obtener el elemento
            if(_items01.size() == 0){//Si lista _items01 se encuentra vacía
                turnoLista01 = false;
            }

            for(int i = 0; i < position; i++){
                if(i == position){//Si es la posición exacta
                    if(turnoLista01){
                        id = punteroLista01;
                    }else{
                        id = punteroLista02;
                    }
                }else{
                    if(turnoLista01){
                        punteroLista01++;
                        if(punteroLista02 < _items02.size()){//Si el puntero aún no ha sobrepasado la lista _items02.
                            turnoLista01 = false;
                        }
                    }else{
                        if(!turnoLista01){
                            punteroLista02++;
                            if(punteroLista01 < _items01.size()){//Si el puntero aún no ha sobrepasado la lista _items01.
                                turnoLista01 = true;
                            }
                        }
                    }
                }
            }
        }

        return id;
    }

    @Override
    public int getViewTypeCount(){
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        int tipoItem = 1;

        if(getItem(position) instanceof ItemLista01){
            tipoItem = 0;
        }

        return tipoItem;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Copiamos la vista
        View v = convertView;
        ViewHolderItemLista01 holderItemLista01;
        ViewHolderItemLista02 holderItemLista02;
        TextView text;
        ImageView image01, image02;
        Object item = getItem(position);
        ItemLista01 item01;
        ItemLista02 item02;

        if(v == null){
            if(getItemViewType(position) == 0){
                LayoutInflater layoutInflater = LayoutInflater.from(this._context);
                v = layoutInflater.inflate(_layout01, parent, false);

                text = v.findViewById(R.id.textView01);
                image01 = v.findViewById(R.id.image01);
                image02 = v.findViewById(R.id.image02);
                holderItemLista01 = new ViewHolderItemLista01(image01, image02, text);

                //Metemos el objeto en el tag de la vista
                v.setTag(R.id.holder01, holderItemLista01);

                item01 = (ItemLista01) item;
                holderItemLista01 = (ViewHolderItemLista01) v.getTag(R.id.holder01);
                holderItemLista01.get_imagen01().setBackgroundResource(item01.get_imagen01());
                holderItemLista01.get_imagen02().setBackgroundResource(item01.get_imagen02());
                holderItemLista01.get_texto().setText(item01.get_texto());
            }else{
                LayoutInflater layoutInflater = LayoutInflater.from(this._context);
                v = layoutInflater.inflate(_layout02, parent, false);

                text = v.findViewById(R.id.textView01);
                image01 = v.findViewById(R.id.image01);
                holderItemLista02 = new ViewHolderItemLista02(image01, text);
                //Metemos el objeto en el tag de la vista
                v.setTag(R.id.holder02, holderItemLista02);

                item02 = (ItemLista02) item;
                holderItemLista02 = (ViewHolderItemLista02) v.getTag(R.id.holder02);
                holderItemLista02.get_imagen().setBackgroundResource(item02.get_imagen());
                holderItemLista02.get_texto().setText(item02.get_texto());
            }
        }else{
            if(getItemViewType(position) == 0){//si item es una instancia de ItemLista01
                item01 = (ItemLista01) item;
                holderItemLista01 = (ViewHolderItemLista01) v.getTag(R.id.holder01);
                holderItemLista01.get_imagen01().setBackgroundResource(item01.get_imagen01());
                holderItemLista01.get_imagen02().setBackgroundResource(item01.get_imagen02());
                holderItemLista01.get_texto().setText(item01.get_texto());
            }else{
                item02 = (ItemLista02) item;
                holderItemLista02 = (ViewHolderItemLista02) v.getTag(R.id.holder02);
                holderItemLista02.get_imagen().setBackgroundResource(item02.get_imagen());
                holderItemLista02.get_texto().setText(item02.get_texto());
            }
        }

        return v;
    }
}
