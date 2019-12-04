package es.iesnervion.yeray.galeriaimagenes.ImagesGallery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

import es.iesnervion.yeray.galeriaimagenes.R;

public class MyAdminPageAdapter extends PagerAdapter
{
    private Context context;
    private int numberMaxImages;
    private ArrayList<ImageRecycle> imagenes;
    private LinearLayout pagina1;

    public MyAdminPageAdapter(Context context, int numberMaxImages, ArrayList<ImageRecycle> imagenes){
        this.context = context;
        this.numberMaxImages = numberMaxImages;
        this.imagenes = imagenes;
    }

    public MyAdminPageAdapter(Context context, int numberMaxImages){
        this.context = context;
        this.numberMaxImages = numberMaxImages;
        this.imagenes = new ArrayList<ImageRecycle>();
    }


    @Override
    public int getCount()//Devuelve el número de imágenes que contiene nuestro ViewPage
    {
        return imagenes.size();
    }

    public boolean addImageToList(ImageRecycle image){
        boolean imageAdded = false;
        if(imagenes.size() < numberMaxImages) {
            imagenes.add(image);
            imageAdded = true;
        }
        return imageAdded;
    }

    public boolean deleteImage(int indice){
        boolean imageRemoved = false;
        if(indice >= 0 && indice < imagenes.size()){
            imagenes.remove(indice);
            imageRemoved = true;
        }
        return imageRemoved;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position)//cargará el archivo de la página XML a mostrar
    {
        cargarImagen(position);
        
        ViewPager vp=(ViewPager) collection;//Generamos un ViewPager con el ViewGroup collection
        vp.addView(pagina1, 0);//Le agregamos la página actual con indice 0.
        return pagina1;
    }

    /*
    * Interfaz
    * Nombre: cargarImagen
    * Comentario: Este método nos permite inflar un layout con su imagen.
    * Cebecera: private void cargarImagen(int position)
    * */
    private void cargarImagen(int position){
        pagina1 = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.pagina1, null);
        ImageView image = pagina1.findViewById(R.id.image01);
        image.setImageBitmap(imagenes.get(position).getImage());
    }

    @Override
    public boolean isViewFromObject(View view, Object object)
    {
        return view == object;
    }

    @Override
    public void destroyItem(View collection, int position, Object view)
    {
        ((ViewPager) collection).removeView((View) view);
    }
}