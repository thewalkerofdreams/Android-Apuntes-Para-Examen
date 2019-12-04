package es.iesnervion.yeray.examen_lista_deportistas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import es.iesnervion.yeray.examen_lista_deportistas.Entities.Baloncescista;
import es.iesnervion.yeray.examen_lista_deportistas.Entities.Futbolista;
import es.iesnervion.yeray.examen_lista_deportistas.ListaHeterogenea.AdapterListaDeportistas;
import es.iesnervion.yeray.examen_lista_deportistas.ViewModels.MainActivityVM;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView lista;
    ArrayList<Object> itemRouteLists;
    MainActivityVM mainActivityVM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lista = findViewById(R.id.ListView01);

        //Llamamos al viewmodel
        mainActivityVM = ViewModelProviders.of(this).get(MainActivityVM.class);//Alt + Intro

        itemRouteLists = mainActivityVM.get_deportistas();//Obtenemos los deportistas del viewmodel

        lista.setAdapter(new AdapterListaDeportistas(this, itemRouteLists));
        lista.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Object item = adapterView.getItemAtPosition(i);//Obtenemos el item de la posición clicada
        Futbolista futbolista = null;
        Baloncescista baloncescista = null;
        Bundle bundle = new Bundle();
        if(item instanceof Futbolista){//Si es un futbolista
            futbolista = (Futbolista) item;
            bundle.putParcelable("Deportista" , futbolista);
        }else{
            baloncescista = (Baloncescista) item;
            bundle.putParcelable("Deportista" , baloncescista);
        }

        Intent intent = new Intent(this, DetallesActivity.class);
        intent.putExtra("Bundle", bundle);//Agregamos los datos al intent
        startActivityForResult(intent, 1);//Comenzamos otra actividad
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 1) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                int posicionDeportista = mainActivityVM.posicionDeoprtista(data.getStringExtra("NombreDeportista"));
                if(data.getStringExtra("BotonPulsado").equals("Save")){
                    mainActivityVM.get_deportistas().remove(posicionDeportista);
                    Object deportista = null;
                    if(data.getStringExtra("Deporte").equals("Futbol")){
                        deportista = new Futbolista(data.getStringExtra("NombreDeportistaEdit"), Integer.valueOf(data.getStringExtra("Foto")), data.getStringArrayListExtra("Posiciones"));
                    }else{
                        deportista = new Baloncescista(data.getStringExtra("NombreDeportistaEdit"), Integer.valueOf(data.getStringExtra("Foto")),
                                Integer.valueOf(data.getStringExtra("NumeroPuntosPorPartido")), Integer.valueOf(data.getStringExtra("NumeroRebotesPorPartido")), Integer.valueOf(data.getStringExtra("NumeroAsistenciasPorPartido")));
                    }
                    mainActivityVM.get_deportistas().add(deportista);
                }else{
                    mainActivityVM.get_deportistas().remove(posicionDeportista);
                }
                lista.setAdapter(new AdapterListaDeportistas(this, mainActivityVM.get_deportistas()));//Actualizamos la lista
            }
        }
    }

}
