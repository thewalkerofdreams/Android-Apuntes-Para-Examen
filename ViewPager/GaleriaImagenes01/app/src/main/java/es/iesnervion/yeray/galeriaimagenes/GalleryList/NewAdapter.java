package es.iesnervion.yeray.galeriaimagenes.GalleryList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import es.iesnervion.yeray.galeriaimagenes.R;

public class NewAdapter extends BaseAdapter {
    private Context _context;
    private int _layout;
    private ArrayList<ItemGalleryList> _items;
    private ItemGalleryList _item;

    public NewAdapter(Context context, int layout, ArrayList<ItemGalleryList> textos){
        _context = context;
        _layout = layout;
        _items = textos;
    }

    @Override
    public int getCount(){
        return _items.size();
    }

    @Override
    public ItemGalleryList getItem(int position){
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
        TextView GalleryName, maxNumbersOfImages, idGalleryImages;
        _item = getItem(position);

        if(v == null){
            //Inflamos la vista con nuestro propio layout
            LayoutInflater layoutInflater = LayoutInflater.from(this._context);
            v = layoutInflater.inflate(_layout, null);

            GalleryName = v.findViewById(R.id.galleryName);
            maxNumbersOfImages = v.findViewById(R.id.maxNumbersOfImages);

            //Almacenamos los datos en el holder
            holder = new ViewHolder(GalleryName, maxNumbersOfImages);
            //Metemos el objeto en el tag de la vista
            v.setTag(holder);
        }else{
            holder = (ViewHolder) v.getTag();
        }

        holder.get_GalleryName().setText(_item.get_galleryName());
        holder.get_maxNumbersOfImages().setText(String.valueOf("Max number of images: "+_item.get_maxNumbersOfImages()));
        return v;
    }

    public class ViewHolder{
        TextView _GalleryName;
        TextView _maxNumbersOfImages;

        public ViewHolder(){
            _maxNumbersOfImages = null;
            _GalleryName = null;
        }

        public ViewHolder(TextView nameGallery, TextView maxNumbersOfImages) {
            this._GalleryName = nameGallery;
            this._maxNumbersOfImages = maxNumbersOfImages;
        }

        public TextView get_maxNumbersOfImages() {
            return _maxNumbersOfImages;
        }

        public TextView get_GalleryName() {
            return _GalleryName;
        }
    }

}

