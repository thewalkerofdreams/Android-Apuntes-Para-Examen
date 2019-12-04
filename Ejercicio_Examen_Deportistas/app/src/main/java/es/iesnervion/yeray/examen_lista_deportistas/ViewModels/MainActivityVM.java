package es.iesnervion.yeray.examen_lista_deportistas.ViewModels;

import android.widget.ListView;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import es.iesnervion.yeray.examen_lista_deportistas.DatosAplicacion.Datos;
import es.iesnervion.yeray.examen_lista_deportistas.Entities.Baloncescista;
import es.iesnervion.yeray.examen_lista_deportistas.Entities.Futbolista;

public class MainActivityVM extends ViewModel {
    private ArrayList<Object> _deportistas;

    public MainActivityVM(){
        _deportistas = Datos.rellenarListado();
    }

    public ArrayList<Object> get_deportistas() {
        return _deportistas;
    }

    public void set_deportistas(ArrayList<Object> _deportistas) {
        this._deportistas = _deportistas;
    }

    /*
    * Interfaz
    * Nombre: posicionDeportista
    * Comentario: Este método nos permite saber la posición de un deportista según su nombre.
    * Cabecera: public int posicionDeoprtista(String nombre)
    * Entrada:
    *   -String nombre
    * Salida:
    *   -int posicion
    * Postcondiciones: El método devuelve la posición del deportista en la lista, en el caso de
    * haber más de un deportista con el mismo nombre el método devuelve el primero y si no existe
    * en la lista un deportista con ese nombre el método devuelve -1.
    * */
    public int posicionDeoprtista(String nombre){
        int posicion = -1;
        boolean encontrado = false;

        for(int i = 0; i < _deportistas.size() && !encontrado; i++){
            if(_deportistas.get(i) instanceof Futbolista){
                Futbolista futbolista = (Futbolista) _deportistas.get(i);
                if(futbolista.get_nombre().equals(nombre)){
                    posicion = i;
                    encontrado = true;
                }
            }else{//En nuestro caso siempre será un futbolista o un baloncescista
                Baloncescista baloncescista = (Baloncescista) _deportistas.get(i);
                if(baloncescista.get_nombre().equals(nombre)){
                    posicion = i;
                    encontrado = true;
                }
            }
        }

        return posicion;
    }
}
