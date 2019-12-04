package es.iesnervion.yeray.galeriaimagenes.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import es.iesnervion.yeray.galeriaimagenes.DDBB.MetodosDDBB;
import es.iesnervion.yeray.galeriaimagenes.GalleryList.ItemGalleryList;
import es.iesnervion.yeray.galeriaimagenes.GalleryList.NewAdapter;
import es.iesnervion.yeray.galeriaimagenes.R;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    NewAdapter adapter;
    ArrayList<ItemGalleryList> listaGalerias = new ArrayList<ItemGalleryList>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView01);

        listaGalerias = getAllItemsList();//Recargamos la lista con los datos de la BBDD.

        adapter = new NewAdapter(this, R.layout.list_item, listaGalerias);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ItemGalleryList item = (ItemGalleryList) parent.getItemAtPosition(position);//Obtenemos el item de la posición clicada

        Intent intent = new Intent(this, ViewPagerActivity.class);//Creamos el intent
        intent.putExtra("idItemLista", String.valueOf(item.get_idGalleryImages()));//Pasamos los datos necesarios
        intent.putExtra("maxNumbersOfImages", String.valueOf(item.get_maxNumbersOfImages()));
        startActivity(intent);//Comenzamos otra actividad
    }

    /*
    * Interfaz
    * Nombre: addGalery
    * Comentario: Este método nos permite iniciar otra actividad,
    * que es un formulario para crear un galeria en la lista.
    * Cabecera: public void addGalery(View v)
    * Entrada:
    *   -View v
    * Postcondiciones: El método inicia una actividad comenarFormulario.
    * */
    public void addGalery(View v){
        Intent intent = new Intent(MainActivity.this, FormActivity.class);
        startActivityForResult(intent, 1);//RequestCode es el indentificador
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        MetodosDDBB metodosDDBB = new MetodosDDBB();

        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                String galleryName = data.getStringExtra("nombreGaleria");
                int maxNumbersOfImages = Integer.valueOf(data.getStringExtra("numeroElementos"));
                //Así añadimos un nuevo elemento a la lista
                metodosDDBB.insertList(this, galleryName, maxNumbersOfImages);

                //Recargamos la lista con los nuevos elementos  //Como el código de abajo solo son tres líneas y no lo reutilizo más no creo un nuevo método para él.
                listaGalerias = getAllItemsList();
                adapter = new NewAdapter(this, R.layout.list_item, listaGalerias);
                listView.setAdapter(adapter);
            }
        }
    }

    /*
    * Intefaz
    * Nombre: getAllItemsList
    * Comentario: Este método nos permite cargar una lista del
    * tipo ItemGalerryList. Esto nos permite cargar las listas
    * de galerias de images de la base de datos.
    * Cabecera: public ArrayList<ItemGalleryList> getAllItemsList()
    * Salida:
    *   -ArrayList<ItemGalleryList> listado
    * Postcondiciones: Este método devuelve una lista del tipo
    * ItemGalleryList.
    * */
    public ArrayList<ItemGalleryList> getAllItemsList(){
        MetodosDDBB metodosDDBB = new MetodosDDBB();
        Cursor cursor = metodosDDBB.getAllItemsList(this);
        ArrayList<ItemGalleryList> listado = new ArrayList<ItemGalleryList>();
        ItemGalleryList item;

        if(cursor != null && cursor.moveToFirst()){//Si la tabla itemLista contiene filas
            do{
                item = new ItemGalleryList(cursor.getString(1), cursor.getInt(2), cursor.getInt(0));
                listado.add(item);
            }while (cursor.moveToNext());
        }

        return listado;
    }
}
