package es.iesnervion.yeray.examen_lista_deportistas.DatosAplicacion;

import java.util.ArrayList;

import es.iesnervion.yeray.examen_lista_deportistas.Entities.Baloncescista;
import es.iesnervion.yeray.examen_lista_deportistas.Entities.Futbolista;
import es.iesnervion.yeray.examen_lista_deportistas.R;

public class Datos {
    public static ArrayList<Object> rellenarListado(){

        ArrayList<Object> listado = new ArrayList<>();

        ArrayList<String> pos = new ArrayList<String>();

        pos.add("mediapunta");
        pos.add("centrocampista");
        listado.add(new Futbolista("De Bruyne", R.drawable.kevin_de_bruyne_f, pos));
        pos.add("extremoIzquierdo");
        listado.add(new Futbolista("Coutinho", R.drawable.generico, pos));
        listado.add(new Baloncescista("Pau Gasol",R.drawable.pau_gasol_b,10,8,8));
        pos.clear();
        pos.add("delantero");
        listado.add(new Futbolista("Lukaku", R.drawable.romelu_lukaku_f, pos));
        listado.add(new Baloncescista("Marc Gasol",R.drawable.marc_gasol_b,9,10,8));
        listado.add(new Baloncescista("Lebron James",R.drawable.lebron_james_b,14,12,10));
        pos.add("extremoIzquierdo");
        listado.add(new Futbolista("Salah", R.drawable.generico, pos));
        pos.add("mediapunta");
        listado.add(new Futbolista("Messi", R.drawable.generico, pos));
        listado.add(new Baloncescista("Navarro",R.drawable.generico,8,12,6));
        listado.add(new Futbolista("Griezzman", R.drawable.generico, pos));
        pos.clear();
        pos.add("extremoDerecho");
        pos.add("mediapunta");
        listado.add(new Futbolista("Joaquin", R.drawable.generico, pos));

        return listado;
    }
}
