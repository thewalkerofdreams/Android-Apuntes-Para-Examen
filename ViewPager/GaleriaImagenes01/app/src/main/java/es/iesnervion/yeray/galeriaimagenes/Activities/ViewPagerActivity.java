package es.iesnervion.yeray.galeriaimagenes.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuWrapperICS;
import androidx.viewpager.widget.ViewPager;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import es.iesnervion.yeray.galeriaimagenes.DDBB.MetodosDDBB;
import es.iesnervion.yeray.galeriaimagenes.ImagesGallery.ImageRecycle;
import es.iesnervion.yeray.galeriaimagenes.ImagesGallery.MyAdminPageAdapter;
import es.iesnervion.yeray.galeriaimagenes.R;
import es.iesnervion.yeray.galeriaimagenes.Utilities.UtilidadConversiones;

public class ViewPagerActivity extends AppCompatActivity {

    ViewPager viewPager;
    ArrayList<ImageRecycle> listadoImagenes;
    MyAdminPageAdapter myAdminPageAdapter;
    int idItemLista = 0;//Nos permite saber el id de la galería a la que le añadimos una imagen.
    int maxNumbersOfImages = 0;//Nos permite saber el número máximo de imagenes de la galería.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);

        viewPager = findViewById(R.id.viewPager);
        //Almacenamos el id de la galería de imagenes
        idItemLista = Integer.valueOf(getIntent().getStringExtra("idItemLista"));
        //Almacenamos el número máximo de imagenes en la galería
        maxNumbersOfImages = Integer.valueOf(getIntent().getStringExtra("maxNumbersOfImages"));

        //función para obtener todas las imagenes de una lista
        listadoImagenes = loadImagesList(idItemLista);

        myAdminPageAdapter = new MyAdminPageAdapter(this, maxNumbersOfImages, listadoImagenes);
        viewPager.setAdapter(myAdminPageAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        MetodosDDBB metodosDDBB = new MetodosDDBB();

        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                byte[] bytes = null;
                try {
                    Uri imageUri = data.getData();//Sacamos la información del intent en formato URI
                    InputStream imageStream = getContentResolver().openInputStream(imageUri);//La pasamos al formato InputStream
                    bytes = UtilidadConversiones.getBytes(imageStream);//La pasamos a un array de bytes

                    metodosDDBB.insertImage(this, bytes, idItemLista);//Almacenamos la imagen en la base de datos

                    reloadGallery();//Recargamos la galería
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
                }
            }
        }else{//Como solo va ha recibir llamadas de dos botones...
            if(resultCode == RESULT_OK){
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");//Sacamos los datos de la imagen en bitmap
                byte[] bytes = UtilidadConversiones.getBytes(bitmap);
                metodosDDBB.insertImage(this, bytes, idItemLista);

                reloadGallery();//Recargamos la galería
            }
        }
    }

    /*
    * Interfaz
    * Nombre: loadImagesList
    * Comentario: Este método nos permite obtener todas las imagenes
    * de un item del listado de galerías del MainActivity.
    * Cabecera: public ArrayList<ImageRecycle> loadImagesList(int idItemList)
    * Entrada:
    *   -int idItemList
    * Salida:
    *   -ArrayList<ImageRecycle> images
    * Postcondiciones: Este método devuelve un listado de imagenes
    * del tipo ImageRecycle de una galería de la lista del MainActivity.
    * */
    public ArrayList<ImageRecycle> loadImagesList(int idItemList){
        ArrayList<ImageRecycle> images = new ArrayList<ImageRecycle>();
        MetodosDDBB metodosDDBB = new MetodosDDBB();
        Cursor cursor = metodosDDBB.getImagesOfAGallery(this, idItemList);
        ImageRecycle imageRecycle;

        if(cursor != null && cursor.moveToFirst()){//Si el cursor no esta vacío devuelve true, además coloca el puntero en el primer elemento de la lista
            do{
                byte[] bytes = cursor.getBlob(1);
                Bitmap b = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                imageRecycle = new ImageRecycle(b);
                images.add(imageRecycle);
            }while (cursor.moveToNext());
        }

        return images;
    }

    /*
    * Interfaz
    * Nombre: addImageToGallery
    * Comentario: Este método nos permite agregar una imagen a la galería.
    * Cabecera: public void addImageToGallery(View v)
    * Entrada:
    *   -View v
    * Postcondiciones: El método agrega una imagen a la galería si el usuario
    * ha seleccionado alguna, en caso contrario el método no hace nada. En el caso
    * de que se haya superado el límite de imagenes de la galería se enviará un
    * mensaje de error a la actividad actual.
    * */
    public void addImageToGallery(View view){
        MetodosDDBB metodosDDBB = new MetodosDDBB();
        if(metodosDDBB.numbersOfImagesOfAGallery(this, idItemLista) < maxNumbersOfImages){
            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
            photoPickerIntent.setType("image/*");
            startActivityForResult(photoPickerIntent, 1);
        }else{
            Toast.makeText(this, "The gallery is full!", Toast.LENGTH_LONG).show();
        }
    }

    /*
     * Interfaz
     * Nombre: addImageToCamera
     * Comentario: Este método nos permite agregar una imagen desde la camara.
     * Cabecera: public void addImageToCamera(View v)
     * Entrada:
     *   -View v
     * Postcondiciones: El método agrega una imagen a la galería si el usuario
     * ha hecho una fotografía, en caso contrario el método no hace nada. En el caso
     * de que se haya superado el límite de imagenes de la galería se enviará un
     * mensaje de error a la actividad actual.
     * */
    public void addImageToCamera(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//Creamos un intent mediante el siguiente MediaStore
        startActivityForResult(intent, 2);//Indicamos el intent y el código del proceso a realizar
    }

    /*
    * Intefaz
    * Nombre: reloadGallery
    * Comentario: Este método nos permite recargar la galería de imagenes.
    * Cabecera: public void reloadGallery()
    * Postcondiciones: El método recarga la galería de imagenes actual.
    * */
    public void reloadGallery(){
        listadoImagenes = loadImagesList(idItemLista);
        myAdminPageAdapter = new MyAdminPageAdapter(this, maxNumbersOfImages, listadoImagenes);
        viewPager.setAdapter(myAdminPageAdapter);
    }
}
