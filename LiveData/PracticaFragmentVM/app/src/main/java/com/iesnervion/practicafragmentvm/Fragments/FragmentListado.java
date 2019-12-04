package com.iesnervion.practicafragmentvm.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.iesnervion.practicafragmentvm.Clases.Correo;
import com.iesnervion.practicafragmentvm.Clases.CorreoVM;
import com.iesnervion.practicafragmentvm.R;
import java.util.ArrayList;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class FragmentListado extends Fragment implements AdapterView.OnItemClickListener{
    //Este fragment muestra un listado de los correos. Los obtiene mediante ViewModel.

    CorreoVM vm;
    private ListView listado;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //Se llama cuando se debe diseñar su interfaz por primera vez.
        View view = inflater.inflate(R.layout.fragment_listado, container, false);

        vm = ViewModelProviders.of(getActivity()).get(CorreoVM.class);

        //Antes petaba porque tenia getView() en lugar de view.
        listado = view.findViewById(R.id.Listado); //El ListView del fragment_listado
        final AdaptadorCorreos adaptador = new AdaptadorCorreos(getActivity().getBaseContext(), vm.getDatos().getValue());
        listado.setAdapter(adaptador);

        listado.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //Se llama cuando se crea el fragmento. Se deben inicializar componentes esenciales que queramos conservar cuando se pause o detenga.
        super.onCreate(savedInstanceState);
    }

    /**
     * Permite establecer el texto de descripcion del elemento de la lista seleccionado.
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        //Cuando se selecciona un correo, pongo ese elemento como CorreoSeleccionado en el ViewModel
        Correo correoSeleccionado = null;

        if(position >= 0){
            correoSeleccionado = vm.getDatos().getValue().get(position);
        }

        vm.setCorreoSeleccionado(correoSeleccionado);
    }

    //Clase para el adaptador Custom
    class AdaptadorCorreos extends BaseAdapter {

        private Context context;
        private ArrayList<Correo> datos;

        public AdaptadorCorreos(Context context, ArrayList<Correo> datos){
            this.context = context;
            this.datos = datos;
        }

        @Override
        public int getCount() {
            return datos.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override //No recicla, me he centrado mas en ver como van los fragments.
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.listitem_correo,parent, false);

            TextView lblDe = convertView.findViewById(R.id.descripcion);
            lblDe.setText(datos.get(position).getDescripcion());

            TextView lblAsunto = convertView.findViewById(R.id.asunto);
            lblAsunto.setText(datos.get(position).getAsunto());

            return(convertView);
        }
    }

}
