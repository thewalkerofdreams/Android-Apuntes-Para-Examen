package com.iesnervion.practicafragmentvm.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iesnervion.practicafragmentvm.Clases.Correo;
import com.iesnervion.practicafragmentvm.Clases.CorreoVM;
import com.iesnervion.practicafragmentvm.R;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class FragmentDetalle extends Fragment {
    //Este fragment muestra un mensaje de descripcion.
    TextView descripcion;
    private CorreoVM vm = new CorreoVM();

    //Constructor por defecto.
    public FragmentDetalle(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        vm = ViewModelProviders.of(getActivity()).get(CorreoVM.class);

        View view = inflater.inflate(R.layout.fragment_detalle, container, false);
        descripcion = view.findViewById(R.id.txvdetalle);

        if(vm.getCorreoSeleccionado().getValue() != null){
            //Actualizo los datos.
            descripcion.setText(vm.getCorreoSeleccionado().getValue().getDescripcion());
        }

        //El observer
        final Observer<Correo> correoObserver = new Observer<Correo>() {
            @Override
            public void onChanged(Correo correo) {
                if(vm.getCorreoSeleccionado().getValue() != null){
                    descripcion.setText(vm.getCorreoSeleccionado().getValue().getDescripcion());
                }
            }
        };

        //Observo el LiveData con el Observer recien creado.
        vm.getCorreoSeleccionado().observe(this,correoObserver);

        return view;
    }
    

}
