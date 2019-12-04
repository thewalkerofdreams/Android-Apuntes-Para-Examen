package com.iesnervion.examenanopasado.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.iesnervion.examenanopasado.Clases.Baloncescista;
import com.iesnervion.examenanopasado.Clases.Futbolista;
import com.iesnervion.examenanopasado.R;
import java.util.ArrayList;

public class JugadoresVM extends ViewModel {
    //Tendremos los datos de la lista de jugadores.
    private MutableLiveData<ArrayList<Object>> listadoJugadores;

    public JugadoresVM(){
        //Rellenamos el listado de los jugadores
        listadoJugadores = new MutableLiveData<>();
        rellenarListado();
    }

    //Getter and setter
    public MutableLiveData<ArrayList<Object>> getListadoJugadores() {
        return listadoJugadores;
    }
    public void setListadoJugadores(ArrayList<Object> listadoJugadores) {
        this.listadoJugadores.setValue(listadoJugadores);

    }

    public void rellenarListado(){

        ArrayList<Object> listado = new ArrayList<>();

        ArrayList<String> pos = new ArrayList<String>();

        //for(int i = 0; i < 10; i++){
            pos.add("mediapunta");
            pos.add("centrocampista");
            listado.add(new Baloncescista("Pau Gasol",10,8,8, R.drawable.pau_gasol_b));
            //String nombre, int puntos, int rebotes, int asist, int foto
            listado.add(new Futbolista("De Bruyne", R.drawable.kevin_de_bruyne_f, pos));
            pos.add("extremoIzquierdo");
            listado.add(new Futbolista("Coutinho", R.drawable.generico, pos));
            pos.clear();
            pos.add("delantero");
            listado.add(new Futbolista("Lukaku", R.drawable.romelu_lukaku_f, pos));
            listado.add(new Baloncescista("Marc Gasol",9,10,8, R.drawable.marc_gasol_b));
            listado.add(new Baloncescista("Lebron James",14,12,10, R.drawable.marc_gasol_b));
            pos.add("extremoIzquierdo");
            listado.add(new Futbolista("Salah", R.drawable.generico, pos));
            pos.add("mediapunta");
            listado.add(new Futbolista("Messi", R.drawable.generico, pos));
            listado.add(new Baloncescista("Navarro",8,12,6, R.drawable.marc_gasol_b));
            listado.add(new Futbolista("Griezzman", R.drawable.generico, pos));
            pos.clear();
            pos.add("extremoDerecho");
            pos.add("mediapunta");
            listado.add(new Futbolista("Joaquin", R.drawable.generico, pos));
        //}

        listadoJugadores.setValue(listado); //Asignamos los datos.
    }

    public void EliminarJugador(int id){
        //Baloncescista baloncesto = (Baloncescista)o;
        listadoJugadores.getValue().remove(0);
    }
}
