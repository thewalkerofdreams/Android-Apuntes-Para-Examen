package es.iesnervion.yeray.routemarker.RouteList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import es.iesnervion.yeray.routemarker.Entities.Route;
import es.iesnervion.yeray.routemarker.R;


public class NewAdapter extends BaseAdapter {
    private Context _context;
    private int _layout;
    private ArrayList<Route> _items;
    private Route _item;

    public NewAdapter(Context context, int layout, ArrayList<Route> textos){
        _context = context;
        _layout = layout;
        _items = textos;
    }

    @Override
    public int getCount(){
        return _items.size();
    }

    @Override
    public Route getItem(int position){
        return _items.get(position);
    }

    @Override
    public long getItemId(int id){
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup){
        View v = convertView;
        ViewHolder holder;
        TextView routeName;
        _item = getItem(position);

        if(v == null){
            //Inflamos la vista con nuestro propio layout
            LayoutInflater layoutInflater = LayoutInflater.from(this._context);
            v = layoutInflater.inflate(_layout, null);

            routeName = v.findViewById(R.id.RouteName);

            //Almacenamos los datos en el holder
            holder = new ViewHolder(routeName);
            //Metemos el objeto en el tag de la vista
            v.setTag(holder);
        }else{
            holder = (ViewHolder) v.getTag();
        }

        holder.get_routeName().setText(_item.get_name());
        return v;
    }

    public class ViewHolder{
        TextView _routeName;

        public ViewHolder(){
            _routeName = null;
        }

        public ViewHolder(TextView nameGallery) {
            this._routeName = nameGallery;
        }

        public TextView get_routeName() {
            return _routeName;
        }
    }

}

