package es.iesnervion.pablo.wikiclover;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageView icono;
    TextView descripcion;
    Intent intentDatos;
    int posicionLista=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        descripcion = findViewById(R.id.descripcion);
        icono = findViewById(R.id.imageView);

        //Asi obtengo los datos de otra activity.
        //posicionLista = getIntent().getIntExtra("posicion",0);

        //Mostramos los resultados correspondientes a lo seleccionado en la lista.
       //MostrarLista();
    }

    /*
    * Va a la actividad lista
    * */
    public void goToLista(View v){
        intentDatos = new Intent(this, lista.class);
        //startActivity(intentDatos);
        //Asi esperamos el resultado que nos devuelva la Activity.
        startActivityForResult(intentDatos,1);
    }

    /*
    * Muestra los datos correspondientes a la opcion elegida de la lista.
    * */
    public void MostrarLista(int pos){
        switch(pos){
            case 0:
                icono.setImageResource(R.drawable.blackbull);
                descripcion.setText(R.string.blackBulls);
                break;
            case 1:
                icono.setImageResource(R.drawable.goldendawn);
                descripcion.setText(R.string.goldenDawn);
                break;
            case 2:
                icono.setImageResource(R.drawable.mantisverdes);
                descripcion.setText(R.string.greenMantis);
                break;
            case 3:
                icono.setImageResource(R.drawable.orcasmoradas);
                descripcion.setText(R.string.orcas);
                break;
            case 4:
                icono.setImageResource(R.drawable.ciervosazules);
                descripcion.setText(R.string.ciervos);
                break;
            case 5:
                icono.setImageResource(R.drawable.crimsonlion);
                descripcion.setText(R.string.crimson);
                break;
            case 6:
                icono.setImageResource(R.drawable.bluerose);
                descripcion.setText(R.string.bluerose);
                break;
            case 7:
                icono.setImageResource(R.drawable.aguilas);
                descripcion.setText(R.string.eagles);
                break;
            default:
                icono.setImageResource(R.drawable.willi);
                break;
        }
    }
    //Añadido
    //Este metodo es llamado luego de cerrarse la Activity lista.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        if(requestCode == 1){
            if(resultCode == Activity.RESULT_OK) {
                posicionLista = data.getIntExtra("posicion",0);
                MostrarLista(posicionLista);
            }
        }
    }
}
