package es.iesnervion.yeray.examen_lista_deportistas.ListaHeterogenea;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import es.iesnervion.yeray.examen_lista_deportistas.Entities.Baloncescista;
import es.iesnervion.yeray.examen_lista_deportistas.Entities.Futbolista;
import es.iesnervion.yeray.examen_lista_deportistas.R;

public class AdapterListaDeportistas extends BaseAdapter {
    private Context context;
    private ArrayList<Object> deportistas;

    //Constructor con parametros
    public AdapterListaDeportistas(Context context, ArrayList<Object> items){
        this.context = context;
        this.deportistas = items;
    }

    //Metodos que me obliga a implementar
    @Override
    public int getCount() {
        return deportistas.size(); //returns total of items in the list
    }

    @Override
    public Object getItem(int position) {
        return deportistas.get(position); //returns list item at the specified position
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

        if(deportistas.get(position) instanceof Futbolista){
            type = 0;
        }

        return type;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View row = convertView;
        ViewHolder holder;
        ViewHolder2 holder2;
        TextView txtNombre, posicion, puntoPorPartido, rebotesPorPartido, asistenciasPorPartido;
        ImageView foto;

            //Si la vista a reciclar aun no existe
            if(row == null){
                //LayoutInflater inflater = getLayoutInflater();
                LayoutInflater inflater = LayoutInflater.from(this.context);
                //LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );

                if(getItemViewType(position) == 0) {
                    //Inflamos la fila.
                    row = inflater.inflate(R.layout.activity_row_futbolista, parent, false);

                    foto = (ImageView) row.findViewById(R.id.ImageViewFutbolista01);
                    txtNombre = (TextView) row.findViewById(R.id.TextNombreFutbolista);
                    posicion = (TextView) row.findViewById(R.id.TextPosicionFutbolista);
                    holder = new ViewHolder(txtNombre, foto, posicion);

                    row.setTag(holder);
                    //Establecemos los elementos de las diferentes vistas del layout
                    Futbolista futbolista = (Futbolista) deportistas.get(position);
                    holder.getNombre().setText(futbolista.get_nombre());
                    holder.getFoto().setImageResource(futbolista.get_foto());
                    if(futbolista.get_posiciones().size() > 0){//Si tiene al menos una posición
                        holder.getPosicion().setText(String.valueOf(futbolista.get_posiciones().get(0)));
                    }
                }else{
                    row = inflater.inflate(R.layout.activity_row_baloncescista, parent, false);

                    foto = (ImageView) row.findViewById(R.id.ImageViewBaloncescista01);
                    txtNombre = (TextView) row.findViewById(R.id.TextNombreBaloncescista);
                    puntoPorPartido = row.findViewById(R.id.TextPuntoPorPartido);
                    rebotesPorPartido = row.findViewById(R.id.TextRebotesPorPartido);
                    asistenciasPorPartido = row.findViewById(R.id.TextAsistenciasPorPartido);

                    holder2 = new ViewHolder2(txtNombre, foto, puntoPorPartido, rebotesPorPartido, asistenciasPorPartido);

                    row.setTag(holder2);

                    Baloncescista baloncescista = (Baloncescista) deportistas.get(position);
                    holder2.getNombre().setText(baloncescista.get_nombre());
                    holder2.getImagen().setImageResource(baloncescista.get_foto());
                    holder2.getPuntoPorPartido().setText(String.valueOf(baloncescista.get_puntoPorPartido()));
                    holder2.getRebotePorPartido().setText(String.valueOf(baloncescista.get_rebotesPorPartido()));
                    holder2.getAsistenciaPorPartido().setText(String.valueOf(baloncescista.get_asistenciasPorPartido()));
                }
            }else { //Asi recicla
                if (getItemViewType(position) == 0) {
                    Futbolista futbolista = (Futbolista) deportistas.get(position);
                    holder = (ViewHolder) row.getTag();

                    holder.getNombre().setText(futbolista.get_nombre());
                    holder.getFoto().setImageResource(futbolista.get_foto());
                    if(futbolista.get_posiciones().size() > 0){//Si tiene al menos una posición
                        holder.getPosicion().setText(String.valueOf(futbolista.get_posiciones().get(0)));
                    }
                } else {
                    holder2 = (ViewHolder2) row.getTag();
                    Baloncescista baloncescista = (Baloncescista) deportistas.get(position);
                    holder2.getNombre().setText(baloncescista.get_nombre());
                    holder2.getImagen().setImageResource(baloncescista.get_foto());
                    holder2.getPuntoPorPartido().setText(String.valueOf(baloncescista.get_puntoPorPartido()));
                    holder2.getRebotePorPartido().setText(String.valueOf(baloncescista.get_rebotesPorPartido()));
                    holder2.getAsistenciaPorPartido().setText(String.valueOf(baloncescista.get_asistenciasPorPartido()));
                }
            }
        return row;
    }


    //Clase para el primer tipo de lista
    class ViewHolder{
        ImageView foto;
        TextView nombre;
        TextView posicion;

        ViewHolder(TextView nombre, ImageView imagen, TextView posicion){
            this.foto = imagen;
            this.nombre = nombre;
            this.posicion = posicion;
        }

        public ImageView getFoto(){
            return this.foto;
        }
        public TextView getNombre(){
            return this.nombre;
        }
        public TextView getPosicion(){
            return posicion;
        }
    }
    //Clase para el segundo tipo de lista
    class ViewHolder2{
        ImageView imagen;
        TextView nombre;
        TextView puntoPorPartido;
        TextView rebotePorPartido;
        TextView asistenciaPorPartido;

        ViewHolder2(TextView nombre, ImageView imagen, TextView puntoPorPartido, TextView rebotePorPartido, TextView asistenciaPorPartido){
            this.nombre = nombre;
            this.imagen = imagen;
            this.puntoPorPartido = puntoPorPartido;
            this.rebotePorPartido = rebotePorPartido;
            this.asistenciaPorPartido = asistenciaPorPartido;
        }

        public ImageView getImagen(){
            return this.imagen;
        }
        public TextView getNombre(){
            return this.nombre;
        }

        public TextView getPuntoPorPartido() {
            return puntoPorPartido;
        }

        public TextView getRebotePorPartido() {
            return rebotePorPartido;
        }

        public TextView getAsistenciaPorPartido() {
            return asistenciaPorPartido;
        }
    }
}
