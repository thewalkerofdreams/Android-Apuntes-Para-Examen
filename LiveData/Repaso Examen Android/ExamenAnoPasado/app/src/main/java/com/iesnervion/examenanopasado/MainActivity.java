package com.iesnervion.examenanopasado;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.iesnervion.examenanopasado.Clases.Baloncescista;
import com.iesnervion.examenanopasado.Clases.Futbolista;
import com.iesnervion.examenanopasado.ViewModel.JugadoresVM;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private JugadoresVM vm;
    private ListView lvJugadores;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vm = ViewModelProviders.of(this).get(JugadoresVM.class);

        //Buscamos la vista con el ListView.
        lvJugadores = findViewById(R.id.ListaJugadores);

        //Asiganmos el adaptador customizado.
        final CustomAdapter adaptador = new CustomAdapter(this, vm.getListadoJugadores().getValue());
        lvJugadores.setAdapter(adaptador);
        lvJugadores.setOnItemClickListener(this);

        //Ahora tengo que hacer un observer para la lista.
        final Observer<ArrayList<?>> listContactObserver = new Observer<ArrayList<?>>() {
            @Override
            public void onChanged(ArrayList<?> listContact) {
                lvJugadores.setAdapter(adaptador);
            }
        };

        //Observo el MutableLiveData con el observer creado
        vm.getListadoJugadores().observe(this, listContactObserver);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
        //Aqui le pasamos el intent a cada actividad.
        if(vm.getListadoJugadores().getValue().get(i) instanceof Futbolista){
            Futbolista f = (Futbolista)vm.getListadoJugadores().getValue().get(i);
            Intent intentFutbol = new Intent(this, DetailsFutbol.class);
            intentFutbol.putExtra("jugador", f);
            startActivityForResult(intentFutbol, 1);
        }else{
            Baloncescista baloncesto = (Baloncescista)vm.getListadoJugadores().getValue().get(i);
            Intent intentBaloncesto = new Intent(this,Basket_Details.class);
            intentBaloncesto.putExtra("jugador",baloncesto);
            startActivity(intentBaloncesto);
        }
    }

    //Clase con el adaptador custom
    class CustomAdapter extends BaseAdapter{
        private Context context;
        private ArrayList<Object> jugadores; //Lista con los jugadores

        //Cosntructor con parametros
        public CustomAdapter(Context context, ArrayList<Object> items){
            this.context = context;
            this.jugadores = items;
        }

        //Metodos que me obliga implementar la interfaz
        @Override
        public int getCount(){
            return jugadores.size();
        }

        @Override
        public Object getItem(int position){
            return jugadores.get(position);
        }

        @Override
        public long getItemId(int position){
            return position;
        }

        @Override
        public int getItemViewType(int position){
            int type = 1;

            if(jugadores.get(position) instanceof Futbolista){ //Miramos si es instancia de Futbolista
                type = 0;
            }

            return type;
        }

        @Override
        public  int getViewTypeCount(){
            return 2;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            View row = convertView;
            //Declaramos los ViewHolder
            ViewHolderBaloncescista holderB;
            ViewHolderFutbolista holderF;

            //Propiedades de cada holder
            //rowfutbolistas
            TextView nombreF;
            TextView posicionF;
            ImageView fotoF;
            //rowbaloncestistas
            TextView nombreB;
            TextView puntosB;
            TextView rebotesB;
            TextView asistenciasB;
            ImageView fotoB;

            if(row == null){
                LayoutInflater inflater = getLayoutInflater();

                //Si es Futbolista
                if(getItemViewType(position) == 0){
                    row = inflater.inflate(R.layout.futbol_row, parent , false);

                    nombreF = row.findViewById(R.id.tvNombreFut);
                    posicionF = row.findViewById(R.id.tvPosicionFut);
                    fotoF = row.findViewById(R.id.ivFotoFut);
                    holderF = new ViewHolderFutbolista(nombreF, posicionF, fotoF);

                    row.setTag(holderF); //Establecemos el Tag
                    //Establecemos los elementos de las diferentes vistas del layout
                    Futbolista futbolista = (Futbolista) jugadores.get(position); //El casteo es porque viene un object
                    holderF.getFotoF().setImageResource(futbolista.getFoto());
                    holderF.getNombreF().setText(futbolista.getNombre());
                    holderF.getPosicionesF().setText(futbolista.getPosiciones().get(0));
                }else{ //Si es baloncescista
                    row = inflater.inflate(R.layout.baloncesto_row, parent, false);

                    nombreB = row.findViewById(R.id.tvNombreBal);
                    puntosB = row.findViewById(R.id.tvPuntosBal);
                    rebotesB = row.findViewById(R.id.tvRebotesBal);
                    asistenciasB = row.findViewById(R.id.tvAsistenciasBal);
                    fotoB = row.findViewById(R.id.ivFotoBal);

                    holderB = new ViewHolderBaloncescista(nombreB, puntosB, rebotesB, asistenciasB, fotoB);
                    row.setTag(holderB);

                    //Establecemos los elementos de las diferentes vistas
                    Baloncescista baloncesto = (Baloncescista) jugadores.get(position);
                    holderB.getFotoB().setImageResource(baloncesto.getFoto());
                    holderB.getAsistenciasB().setText(""+baloncesto.getAsistenciasPartido()); //Me cago en mis muertos todos.
                    holderB.getNombreB().setText(baloncesto.getNombre());
                    holderB.getRebotesB().setText(""+baloncesto.getRebotesPartido()); //Esta mierda solo funciona si es un String.
                    holderB.getPuntosB().setText(""+baloncesto.getPuntosPartido()); //1 hora en darme cuenta.
                }
            }else{ //Para el reciclado
                if(getItemViewType(position) == 0){
                    Futbolista futbolista = (Futbolista) jugadores.get(position);
                    holderF = (ViewHolderFutbolista) row.getTag();
                    holderF.getFotoF().setImageResource(futbolista.getFoto());
                    holderF.getNombreF().setText(futbolista.getNombre());
                    holderF.getPosicionesF().setText(futbolista.getPosiciones().get(0));
                }else{
                    Baloncescista baloncesto = (Baloncescista) jugadores.get(position);
                    holderB = (ViewHolderBaloncescista) row.getTag();
                    holderB.getFotoB().setImageResource(baloncesto.getFoto());
                    holderB.getAsistenciasB().setText(""+baloncesto.getAsistenciasPartido()); //Y esto tambien era por lo mismooooo
                    holderB.getNombreB().setText(baloncesto.getNombre());
                    holderB.getRebotesB().setText(""+baloncesto.getRebotesPartido()); //Puta madre ya.
                    holderB.getPuntosB().setText(""+baloncesto.getPuntosPartido());
                }
            }

            return row;
        }

    }

    //Clases ViewHolder
    //ViewHolder Futbolista
    class ViewHolderFutbolista{
        private TextView tvNombre;
        private TextView tvPosiciones;
        private ImageView ivFoto;


        //Constructor con parametros
        public ViewHolderFutbolista(TextView nombre, TextView posiciones, ImageView foto){
            this.tvNombre = nombre;
            this.tvPosiciones = posiciones;
            this.ivFoto = foto;
        }

        //Getters
        public TextView getNombreF() {
            return tvNombre;
        }

        public TextView getPosicionesF() {
            return tvPosiciones;
        }

        public ImageView getFotoF() {
            return ivFoto;
        }
    }

    //ViewHolderBaloncescista
    class ViewHolderBaloncescista{
        TextView nombreB;
        TextView puntosB;
        TextView rebotesB;
        TextView asistenciasB;
        ImageView fotoB;

        //Constructor con parametros
        ViewHolderBaloncescista(TextView tvNombre, TextView tvPtos, TextView tvReb, TextView tvAsis, ImageView ivFoto) {
            this.nombreB = tvNombre;
            this.puntosB = tvPtos;
            this.rebotesB = tvReb;
            this.asistenciasB = tvAsis;
            this.fotoB = ivFoto;
        }

        //Propiedades publicas - getters
        public TextView getNombreB() {
            return this.nombreB;
        }

        public TextView getPuntosB() {
            return this.puntosB;
        }

        public TextView getRebotesB() {
            return this.rebotesB;
        }

        public TextView getAsistenciasB() { return this.asistenciasB; }

        public ImageView getFotoB() { return this.fotoB; }
    }
}
