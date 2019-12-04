package com.iesnervion.practicafragmentvm.Clases;

import java.util.ArrayList;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CorreoVM extends ViewModel {
    private MutableLiveData<Correo> correoSeleccionado; //Para mantener los datos del correo de la lista seleccionado.
    private MutableLiveData<ArrayList<Correo>> datos; //Obtenemos la lista de correos.


    public CorreoVM(){
        correoSeleccionado = new MutableLiveData<>();
        datos = new MutableLiveData<>();
        cargarlistaCorreo();

    }

    public LiveData<ArrayList<Correo>> getDatos() {
        return datos;
    }

    public void setDatos(ArrayList<Correo> datos) {
        this.datos.setValue(datos);
    }

    public LiveData<Correo> getCorreoSeleccionado() {
        return correoSeleccionado;
    }
    public void setCorreoSeleccionado(Correo correoSeleccionado) {
        this.correoSeleccionado.setValue(correoSeleccionado);
    }

    /**
     * Carga los datos de la lista con datos ya predefinidos.
     */
    public void cargarlistaCorreo(){
        ArrayList<Correo> lista = new ArrayList<>();

        lista.add(new Correo("Amazon","Pedido enviado"));
        lista.add(new Correo("Gearbox","Pedidio pendiente de envio"));
        lista.add(new Correo("Amazon","Pedido en espera"));
        lista.add(new Correo("Aliexpress","Vaya meirda has comprao pare"));
        lista.add(new Correo("Banggood","Pedido en entrega"));
        lista.add(new Correo("Lightake","Cubos de rubik pa ti"));
        lista.add(new Correo("El corte ingles","Sablazo de la muerte"));
        lista.add(new Correo("Amazon","Devolucion aceptada"));
        lista.add(new Correo("Privado","Alicia te quiere conocer."));
        lista.add(new Correo("Denuncia","Cagate lorito."));

        datos.setValue(lista);
    }
}
