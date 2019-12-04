package es.iesnervion.rmanzano.listaheterogenea;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends ListActivity {
    private ArrayList<Fila> filas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        filas = new ArrayList<Fila>();
        filas.add(new Fila(R.drawable.par,"Par"));
        filas.add(new Fila(R.drawable.impar,"Impar"));
        filas.add(new Fila(R.drawable.par,"Par"));
        filas.add(new Fila(R.drawable.impar,"Impar"));
        filas.add(new Fila(R.drawable.par,"Par"));
        filas.add(new Fila(R.drawable.impar,"Impar"));
        filas.add(new Fila(R.drawable.par,"Par"));
        filas.add(new Fila(R.drawable.impar,"Impar"));
        filas.add(new Fila(R.drawable.par,"Par"));
        filas.add(new Fila(R.drawable.impar,"Impar"));
        filas.add(new Fila(R.drawable.par,"Par"));
        filas.add(new Fila(R.drawable.impar,"Impar"));
        filas.add(new Fila(R.drawable.par,"Par"));
        filas.add(new Fila(R.drawable.impar,"Impar"));
        filas.add(new Fila(R.drawable.par,"Par"));
        filas.add(new Fila(R.drawable.impar,"Impar"));
        filas.add(new Fila(R.drawable.par,"Par"));
        filas.add(new Fila(R.drawable.impar,"Impar"));
        filas.add(new Fila(R.drawable.par,"Par"));
        filas.add(new Fila(R.drawable.impar,"Impar"));
        filas.add(new Fila(R.drawable.par,"Par"));
        filas.add(new Fila(R.drawable.impar,"Impar"));
        filas.add(new Fila(R.drawable.par,"Par"));
        filas.add(new Fila(R.drawable.impar,"Impar"));
        filas.add(new Fila(R.drawable.par,"Par"));
        filas.add(new Fila(R.drawable.impar,"Impar"));
        filas.add(new Fila(R.drawable.par,"Par"));
        filas.add(new Fila(R.drawable.impar,"Impar"));
        filas.add(new Fila(R.drawable.par,"Par"));
        filas.add(new Fila(R.drawable.impar,"Impar"));
        filas.add(new Fila(R.drawable.par,"Par"));
        filas.add(new Fila(R.drawable.impar,"Impar"));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Adaptador adapter = new Adaptador(filas);
        setListAdapter(adapter);
    }

    public class Adaptador extends BaseAdapter {
        private ArrayList<Fila> filas;
        private Fila fila;

        public Adaptador(ArrayList<Fila> filas) {
            this.filas = filas;
        }

        @Override
        public int getCount() {
            return filas.size();
        }

        @Override
        public Object getItem(int i) {
            return filas.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public int getItemViewType(int position) {
            int lugar = 0;
            if(position % getViewTypeCount() != 0) {
                lugar = 1;
            }
            return lugar;
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public View getView(int i, View view, ViewGroup parent) {
            View row = view;
            ViewHolder holder;
            TextView texto;
            ImageView imagen;
            fila = (Fila) getItem(i);

            if(row == null) {
                if(getItemViewType(i) == 0) {
                    LayoutInflater inflater = getLayoutInflater();
                    row = inflater.inflate(R.layout.primer_row, parent, false);
                    texto = row.findViewById(R.id.textodetras);
                    imagen = row.findViewById(R.id.imagendelante);
                    holder = new ViewHolder(imagen, texto);
                    row.setTag(holder);
                }
                else{
                    LayoutInflater inflater = getLayoutInflater();
                    row = inflater.inflate(R.layout.segundo_row, parent, false);
                    texto = row.findViewById(R.id.textodelante);
                    imagen = row.findViewById(R.id.imagendetras);
                    holder = new ViewHolder(imagen, texto);
                    row.setTag(holder);
                }

            }
            else {
                holder = (ViewHolder) row.getTag();
            }
            holder.getImagen().setBackgroundResource(fila.getFoto());
            holder.getNombre().setText(fila.getTexto());

            return row;
        }
    }

    public class ViewHolder {
        ImageView imagen;
        TextView texto;

        public ViewHolder(ImageView imagen, TextView texto) {
            this.imagen = imagen;
            this.texto = texto;
        }

        public ImageView getImagen() {
            return imagen;
        }

        public TextView getNombre() {
            return texto;
        }
    }
}
