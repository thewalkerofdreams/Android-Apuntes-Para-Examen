package es.iesnervion.pablo.wikiclover;

import android.app.Activity;
import android.app.ListActivity;
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
import java.util.ArrayList;

public class lista extends ListActivity implements AdapterView.OnItemClickListener{

    //Cargo los datos de las ordenes
    static final Orden bulls = new Orden(R.drawable.blackbull,"Black Bulls", R.drawable.yami);
    static final Orden golden = new Orden(R.drawable.goldendawn,"Golden Dawn", R.drawable.willi);
    static final Orden mantis = new Orden(R.drawable.mantisverdes,"Green Mantis",R.drawable.jack);
    static final Orden orcas = new Orden(R.drawable.orcasmoradas,"Purple Orcas",R.drawable.kaiser);
    static final Orden deer = new Orden(R.drawable.ciervosazules,"Azure Deers",R.drawable.bill);
    static final Orden lion = new Orden(R.drawable.crimsonlion,"Crimson Lions",R.drawable.mereo);
    static final Orden roses = new Orden(R.drawable.bluerose,"Blue Roses", R.drawable.charlote);
    static final Orden eagles = new Orden(R.drawable.aguilas,"Silver Eagles", R.drawable.silva);

    //Datos para los personajes
    static final Personaje asta = new Personaje("Asta", R.drawable.asta);
    static final Personaje noelle = new Personaje("Noelle", R.drawable.noelle);
    static final Personaje yami = new Personaje("Yami", R.drawable.yami);
    static final Personaje julius = new Personaje("Julius", R.drawable.julius);


    ArrayList<Object> ordenes = new ArrayList<Object>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        cargarDatos();
        ListView lv = getListView();
        //Establezco mi adaptador
        setListAdapter(new customAdapter(this, ordenes));
        lv.setOnItemClickListener(this);
    }

    /*
    * Para hacer un onItemClick, primero implemento AdapterView.OnItemClickListener, luego digo quien se encarga de gestionar los click, en este caso seria lv (La propia lista) y por ultimo implemento este metodo.
    * */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l){
        /*Intent intent = new Intent(getApplicationContext() ,MainActivity.class);
        //Asi mando datos a una activity, "posicion es un nombre descriptivo y que tiene que coincidir con el del activity que recibe la llamada." position es el parametro que le envio
        intent.putExtra("posicion", position);
        startActivity(intent);
        finish();*/
        //Añadido
        Intent returnIntent = new Intent();
        returnIntent.putExtra("posicion",position);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }

    /*
     * Introduce 1000 datos ya preestablecidos en el array ordenes.
     * */
    public void cargarDatos(){
        for(int i =0; i <1000; i++) {
            ordenes.add(bulls);ordenes.add(golden);ordenes.add(asta);ordenes.add(mantis);
            ordenes.add(orcas);ordenes.add(deer);ordenes.add(julius);ordenes.add(lion);
            ordenes.add(roses);ordenes.add(eagles);ordenes.add(yami);ordenes.add(noelle);
        }
    }

    //Creo la clase customAdapter
    class customAdapter extends BaseAdapter{
        private Context context;
        private ArrayList<Object> ordenes; //tipo de dato del listAdapter

        //Constructor con parametros
        public customAdapter(Context context, ArrayList<Object> items){
            this.context = context;
            this.ordenes = items;
        }

        //Metodos que me obliga a implementar
        @Override
        public int getCount() {
            return ordenes.size(); //returns total of items in the list
        }

        @Override
        public Object getItem(int position) {
            return ordenes.get(position); //returns list item at the specified position
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public int getItemViewType(int position){
            int type = 1;

            if(ordenes.get(position) instanceof Orden){
                type = 0;
            }

            return type;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            View row = convertView;
            ViewHolder holder;
            ViewHolder2 holder2;
            TextView txtOrden;
            ImageView IVescudo;
            ImageView IVcapitan;

            //Si la vista a reciclar aun no existe
            if(row == null){
                LayoutInflater inflater = getLayoutInflater();

                if(getItemViewType(position) == 0) {
                    //Inflamos la fila.
                    row = inflater.inflate(R.layout.row2, parent, false);

                    IVescudo = (ImageView) row.findViewById(R.id.iconoOrden);
                    txtOrden = (TextView) row.findViewById(R.id.nombreOrden);
                    IVcapitan = (ImageView) row.findViewById(R.id.iconoCapitan);
                    holder = new ViewHolder(txtOrden, IVescudo, IVcapitan);

                    row.setTag(holder);
                    //Establecemos los elementos de las diferentes vistas del layout
                    Orden orden = (Orden) ordenes.get(position);
                    holder.getNombreOrden().setText(orden.getNombre());
                    holder.getEscudo().setImageResource(orden.getEscudo());
                    holder.getCapitan().setImageResource(orden.getCapitan());
                }else{
                    row = inflater.inflate(R.layout.row, parent, false);

                    IVescudo = (ImageView) row.findViewById(R.id.foto);
                    txtOrden = (TextView) row.findViewById(R.id.nombre);
                    holder2 = new ViewHolder2(txtOrden, IVescudo);

                    row.setTag(holder2);

                    Personaje personaje = (Personaje) ordenes.get(position);
                    holder2.getNombrePersonaje().setText(personaje.getNombrePersonaje());
                    holder2.getImagenPersonaje().setImageResource(personaje.getImagenPersonaje());
                }
            }else { //Asi recicla
                if (getItemViewType(position) == 0) {
                    Orden orden = (Orden) ordenes.get(position);
                    holder = (ViewHolder) row.getTag();
                    holder.getNombreOrden().setText(orden.getNombre());
                    holder.getEscudo().setImageResource(orden.getEscudo());
                    holder.getCapitan().setImageResource(orden.getCapitan());
                } else {
                    holder2 = (ViewHolder2) row.getTag();
                    Personaje personaje = (Personaje) ordenes.get(position);
                    holder2.getNombrePersonaje().setText(personaje.getNombrePersonaje());
                    holder2.getImagenPersonaje().setImageResource(personaje.getImagenPersonaje());
                }
            }

            return row;
        }

    }

    //Clase para el primer tipo de lista
    class ViewHolder{
        TextView nombreOrden;
        ImageView escudo;
        ImageView capitan;

        ViewHolder(TextView nombreOrden, ImageView escudo, ImageView capitan){
            this.escudo = escudo;
            this.nombreOrden = nombreOrden;
            this.capitan = capitan;
        }

        public ImageView getEscudo(){
            return this.escudo;
        }
        public TextView getNombreOrden(){
            return this.nombreOrden;
        }
        public ImageView getCapitan(){
            return capitan;
        }
    }
    //Clase para el segundo tipo de lista
    class ViewHolder2{
        TextView nombrePersonaje;
        ImageView imagenPersonaje;

        ViewHolder2(TextView nombre, ImageView imagen){
            this.nombrePersonaje = nombre;
            this.imagenPersonaje = imagen;
        }

        public ImageView getImagenPersonaje(){
            return this.imagenPersonaje;
        }
        public TextView getNombrePersonaje(){
            return this.nombrePersonaje;
        }

    }


}
