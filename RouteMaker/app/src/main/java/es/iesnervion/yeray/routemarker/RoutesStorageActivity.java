package es.iesnervion.yeray.routemarker;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import es.iesnervion.yeray.routemarker.DDBB.MetodosDDBB;
import es.iesnervion.yeray.routemarker.Entities.Route;
import es.iesnervion.yeray.routemarker.RouteList.NewAdapter;

public class RoutesStorageActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener{

    ListView listView;
    ArrayList<Route> itemRouteLists = new ArrayList<Route>();
    NewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_list);
        listView = findViewById(R.id.ListView01);

        itemRouteLists = getAllItemsList();//Recargamos la lista con los datos de la BBDD.

        adapter = new NewAdapter(this, R.layout.list_item, itemRouteLists);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Route item = (Route) adapterView.getItemAtPosition(i);//Obtenemos el item de la posición clicada

        Intent intent = new Intent(this, SeeRouteActivity.class);
        intent.putExtra("idRoute", String.valueOf(item.get_id()));
        intent.putExtra("nameRoute", item.get_name());
        startActivity(intent);//Comenzamos otra actividad
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        final Route item = (Route) adapterView.getItemAtPosition(i);//Obtenemos el item de la posición clicada

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Confirm Delete");// Setting Alert Dialog Title
        //alertDialogBuilder.setIcon(R.drawable.question);// Icon Of Alert Dialog
        alertDialogBuilder.setMessage("Do you really want delete this route?");// Setting Alert Dialog Message
        alertDialogBuilder.setCancelable(false);//Para que no podamos quitar el dialogo sin contestarlo

        alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(getBaseContext(),"Route deleted",Toast.LENGTH_SHORT).show();
                MetodosDDBB metodosDDBB = new MetodosDDBB();
                metodosDDBB.elimnarRutaConPuntosDeLocalizacion(getBaseContext(), item.get_id()); //Crear método eliminar ruta
                recargarLista();
                //finish();
            }
        });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        return true;//Nos permite no realizar la acción de clicado rápido cuando dejamos pulsado un item.
    }

    /*
    * Interfaz
    * Nombre: recargarLista
    * Comentario: Este método nos permite recargar la lista de tutas.
    * Cabecera: public void recargarLista()
    * Postcondiciones: El método recarga la lista de rutas.
    * */
    public void recargarLista(){
        itemRouteLists = getAllItemsList();//Recargamos la lista con los datos de la BBDD.
        adapter = new NewAdapter(this, R.layout.list_item, itemRouteLists);
        listView.setAdapter(adapter);
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
    public ArrayList<Route> getAllItemsList(){
        MetodosDDBB metodosDDBB = new MetodosDDBB();
        Cursor cursor = metodosDDBB.getAllRoutes(this); //Hacer un método que nos devuelva todas las rutas con sus localizaciones
        ArrayList<Route> listado = new ArrayList<Route>();
        Route item;

        if(cursor != null && cursor.moveToFirst()){//Si la tabla itemLista contiene filas
            do{
                item = new Route(cursor.getInt(0), cursor.getString(1));
                listado.add(item);
            }while (cursor.moveToNext());
        }

        return listado;
    }
}
