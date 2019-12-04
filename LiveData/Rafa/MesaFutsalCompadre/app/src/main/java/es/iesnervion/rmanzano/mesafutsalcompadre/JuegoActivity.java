package es.iesnervion.rmanzano.mesafutsalcompadre;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import es.iesnervion.rmanzano.mesafutsalcompadre.Fragmentos.DorsalDialog;
import es.iesnervion.rmanzano.mesafutsalcompadre.ViewModel.PartidoViewModel;


public class JuegoActivity extends AppCompatActivity implements View.OnClickListener {

    //Para la actividad
    private String equipo1;
    private String equipo2;
    private TextView nombreEquipo1;
    private TextView nombreEquipo2;
    private TextView golLocal;
    private TextView golVisitante;
    private TextView faltaL;
    private TextView faltaV;
    private TextView periodo;
    private ListView equipoLocalTarjetas; //ListView de equipo local
    private ListView equipoVisitanteTarjetas; //ListView de equipo visitante
    private Button taLocal;
    private Button trLocal;
    private Button taVisitante;
    private Button trVisitante;

    //ViewModel
    private PartidoViewModel viewModel;

    //Cronometro
    private static final long START_TIME_IN_MILLIS = 1200000;
    private CountDownTimer mCountDownTimer;
    private TextView mTextViewCountDown;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    //Esto es para los fragmentos
    private TextView dorsal;
    private Fila fila;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Esto se comenta, es una prueba para ver si el listado esta correcto
        /*
        ArrayList<Fila> filas = new ArrayList<Fila>();
        fila = new Fila(R.drawable.amarilla, "1");
        filas.add(new Fila(R.drawable.amarilla, "7"));
        filas.add(new Fila(R.drawable.roja, "2"));
        */


        //onCreate de Android
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        //FindViewById para lo necesario
        nombreEquipo1 = findViewById(R.id.equipo1);
        nombreEquipo2 = findViewById(R.id.equipo2);
        golLocal = findViewById(R.id.golLocal);
        golVisitante = findViewById(R.id.golVisitante);
        equipoLocalTarjetas = findViewById(R.id.listaLocal); //ListView de tarjetas locales
        equipoVisitanteTarjetas = findViewById(R.id.listaVisitante); //ListView de tarjetas visitante
        faltaL = findViewById(R.id.numeroFaltaLocal);
        faltaV = findViewById(R.id.numeroFaltaVisitante);
        taLocal = findViewById(R.id.amarillaLocal);
        trLocal = findViewById(R.id.rojaLocal);
        taVisitante = findViewById(R.id.amarillaVisitante);
        trVisitante= findViewById(R.id.rojaVisitante);
        periodo = findViewById(R.id.periodo);


        //Con esto realizamos un Intent Explicito y recogemos los datos de la otra actividad
        Bundle extras = getIntent().getExtras();
        equipo1 = extras.getString("j1");
        equipo2 = extras.getString("j2");
        nombreEquipo1.setText(equipo1);
        nombreEquipo2.setText(equipo2);

        //Adaptador para la lista de tarjetas
        //Adaptador adapter = new Adaptador();
        //equipoLocalTarjetas.setAdapter(adapter);

        //Cronometro
        mTextViewCountDown = findViewById(R.id.mTextViewCountDown);

        //ViewModel
        viewModel = new PartidoViewModel();
        viewModel = ViewModelProviders.of(this).get(PartidoViewModel.class);


        //Con esto se crea la lista de tarjetas del equipo local
        viewModel.getTarjetasLocales().observe(this, new Observer<ArrayList<Fila>>() {
                    @Override
                    //Si entra en este metodo quiere decir que hay un cambio para notificar
                    public void onChanged(ArrayList<Fila> filas) {
                        //Se usa el adaptador
                        Adaptador adapter = new Adaptador(filas);
                        equipoLocalTarjetas.setAdapter(adapter);
                    }
                });

        //Con esto se crea la lista de tarjetas del equipo visitante
        viewModel.getTarjetasVisitantes().observe(this, new Observer<ArrayList<Fila>>() {
            @Override
            //Si entra en este metodo quiere decir que hay un cambio para notificar
            public void onChanged(ArrayList<Fila> filas) {
                //Se usa el adaptador
                Adaptador adapter = new Adaptador(filas);
                equipoVisitanteTarjetas.setAdapter(adapter);
            }
        });






        mostrarNumerosViewModel(); //Con este metodo retornamos lo correcto cuando hay un cambio de configuracion

        //Para los botones de las tarjetas
        taLocal.setOnClickListener(this);
        trLocal.setOnClickListener(this);
        taVisitante.setOnClickListener(this);
        trVisitante.setOnClickListener(this);

    }

    //Para cambiar la parte, por defecto, es la primera
    public void cambioParte(View view) {
        //viewModel = new PartidoViewModel();

        //ViewModel
        //viewModel.setGolLocal(0);
        //viewModel.setGolVisitante(0);
        viewModel.setFaltaLocal(0);
        viewModel.setFaltaVisitante(0);
        viewModel.setCronometro("20:00");
        //viewModel.setTarjetasLocales(new MutableLiveData<ArrayList<Fila>>());
        //viewModel.setTarjetasVisitantes(new MutableLiveData<ArrayList<Fila>>());

        //Visual
        //golLocal.setText("0");
        //golVisitante.setText("0");
        faltaL.setText("0");
        faltaV.setText("0");
        mTextViewCountDown.setText("20:00");
        //equipoLocalTarjetas.setAdapter(null);
        //equipoVisitanteTarjetas.setAdapter(null);

    if(periodo.getText().toString().equalsIgnoreCase("1")) {
        //viewModel.setGolLocal(viewModel.getGolLocal() + 1);
        //golLocal.setText(viewModel.getGolLocal().toString());
        viewModel.setPeriodo("2");
        periodo.setText(viewModel.getPeriodo());
    }
    else {
        viewModel.setPeriodo("1");
        periodo.setText(viewModel.getPeriodo());
    }

    }

    //ESTO ES UN DIALOG FRAGMENT
    //SE QUE NO DEBE IR AQUI PERO ES COMO FUNCIONA
    public class DorsalDialog extends DialogFragment {
        public Dialog onCreateDialog(Bundle savedInstanceState, final Fila fila, final boolean local) {

            final AlertDialog.Builder builder = new AlertDialog.Builder(JuegoActivity.this);
            // Get the layout inflater
            final LayoutInflater inflater = JuegoActivity.this.getLayoutInflater();
            final View texto = inflater.inflate(R.layout.alertdialog, null);
            //dorsal.setText("0");
            //if(Integer.getInteger(dorsal.getText().toString()) < 1 || Integer.getInteger(dorsal.getText().toString()) > 99) {
                //builder.setCancelable(false);
                //builder.setMessage("Solo valen dorsales del 1 al 99");
            //}
            //builder.

            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
                builder.setView(texto)

                        // Add action buttons
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                dorsal = texto.findViewById(R.id.etDorsal);
                                fila.setTexto(dorsal.getText().toString());

                                //Mirar mas validaciones y posibilidad de un metodo
                                if(dorsal.getText().toString().matches(".*\\D.*")) {
                                    Toast.makeText(JuegoActivity.this, "El dorsal solo son numeros", Toast.LENGTH_LONG).show();
                                }

                                else if(Integer.parseInt(fila.getTexto()) < 1 || Integer.parseInt(fila.getTexto()) > 99) {
                                    //dialog.cancel();
                                    Toast.makeText(JuegoActivity.this, "El dorsal tiene que ser de 1 a 99", Toast.LENGTH_LONG).show();

                                }
                                else if(local) {
                                    viewModel.anhadirTarjetaLocal(fila);
                                    }
                                    else {
                                        viewModel.anhadirTarjetaVisitante(fila);
                                    }
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
           // }
            //while(Integer.getInteger(dorsal.getText().toString()) < 1 || Integer.getInteger(dorsal.getText().toString()) > 99);

            return builder.create();

        }
    }

    /*
    Para sumar el gol local con el boton, con ViewModel
     */
    public void sumaGolLocal(View view) {
        viewModel.setGolLocal(viewModel.getGolLocal() + 1);
        golLocal.setText(viewModel.getGolLocal().toString());
    }

    /*
    Para sumar el gol visitante con el boton, con ViewModel
     */
    public void sumaGolVisitante(View view) {
        viewModel.setGolVisitante(viewModel.getGolVisitante() + 1);
        golVisitante.setText(viewModel.getGolVisitante().toString());
    }

    /*
    Para sumar las faltas locales con el boton, Con ViewModel
     */
    public void faltaLocal(View view) {
        viewModel.setFaltaLocal(viewModel.getFaltaLocal() + 1);
        faltaL.setText(viewModel.getFaltaLocal().toString());
    }

    /*
    Para sumar las faltas visitantes con el boton, Con ViewModel
     */
    public void faltaVisitante(View view) {
        viewModel.setFaltaVisitante(viewModel.getFaltaVisitante() + 1);
        faltaV.setText(viewModel.getFaltaVisitante().toString());
    }

    /*
    Aqui hacemos los onClick para las listas y no tener repetidos los dialogs
    */
 @Override
    public void onClick(View view) {
     DorsalDialog dd = new DorsalDialog();
        switch (view.getId()) {
            case R.id.amarillaLocal:
                dd.onCreateDialog(Bundle.EMPTY, new Fila(R.drawable.amarilla, "0"), true).show();
                break;

            case R.id.rojaLocal:
                dd.onCreateDialog(Bundle.EMPTY, new Fila(R.drawable.roja, "0"), true).show();
                break;

            case R.id.amarillaVisitante:
                dd.onCreateDialog(Bundle.EMPTY, new Fila(R.drawable.amarilla, "0"), false).show();
                break;

            case R.id.rojaVisitante:
                dd.onCreateDialog(Bundle.EMPTY, new Fila(R.drawable.roja, "0"), false).show();
                break;
        }
    }
    /*
    public void botonAmarilla(View v) {
        DorsalDialog dd = new DorsalDialog();
        dd.onCreateDialog(Bundle.EMPTY).show();

    }
    */
    /*
    Con este metodo al pulsar en el boton iniciar, empezaria el cronometro
     */
    public void iniciarCronometro(View view) {
        //Este seria el contador hacia atras
        //mTimeLeftInMillis =  20 Min //Esta al principio en el comentario de Cronometro
        //1000 ms = 1s es lo que va a contar
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                //viewModel.setChrono(millisUntilFinished);
                //Con este metodo actualizara el textView cuando pase el tiempo
                updateCountDownText();
            }

            //No haria falta porque no tenemos ningun problema cuando finalice
            @Override
            public void onFinish() {

            }
        }.start();
    }

    /*
    Con este metodo se para el cronometro, es el boton parar
     */
    public void pararCronometro(View view) {
        mCountDownTimer.cancel();

    }

    /*
    Con este metodo actualiza el texto con los segundos pasados
     */
    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        viewModel.setCronometro(String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds));

        mTextViewCountDown.setText(viewModel.getCronometro());
    }

    //Para finalizar el encuentro e irte a otro
    public void finalizarPartido(View view) {
        //ViewModel
        viewModel.setGolLocal(0);
        viewModel.setGolVisitante(0);
        viewModel.setFaltaLocal(0);
        viewModel.setFaltaVisitante(0);
        viewModel.setCronometro("20:00");
        viewModel.setTarjetasLocales(new MutableLiveData<ArrayList<Fila>>());
        viewModel.setTarjetasVisitantes(new MutableLiveData<ArrayList<Fila>>());

        //Visual
        golLocal.setText("0");
        golVisitante.setText("0");
        faltaL.setText("0");
        faltaV.setText("0");
        mTextViewCountDown.setText("20:00");
        equipoLocalTarjetas.setAdapter(null);
        equipoVisitanteTarjetas.setAdapter(null);
        finish();
    }

    /*
        Con este metodo cuando hay un cambio de configuracion, mostraremos los valores guardados en ViewModel
    */
    public void mostrarNumerosViewModel() {
        golLocal.setText(viewModel.getGolLocal().toString());
        golVisitante.setText(viewModel.getGolVisitante().toString());
        faltaL.setText(viewModel.getFaltaLocal().toString());
        faltaV.setText(viewModel.getFaltaVisitante().toString());
        mTextViewCountDown.setText(viewModel.getCronometro());
        periodo.setText(viewModel.getPeriodo());
    }

    /*
        Es la clase Adaptador para las listas
     */

    private class Adaptador extends BaseAdapter {
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
        public Object getItem(int position) {
            return filas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        //GetView con ViewHolder
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            ViewHolder holder;
            TextView texto;
            ImageView imagen;
            fila = (Fila) getItem(position);


            if(row == null) {
                LayoutInflater inflater = getLayoutInflater();
                row = inflater.inflate(R.layout.row, parent, false);
                texto = row.findViewById(R.id.texto);
                imagen = row.findViewById(R.id.imagen);
                holder = new ViewHolder(imagen, texto);
                row.setTag(holder);
            }
            else {
                holder = (ViewHolder) row.getTag();
            }
            holder.getImagen().setBackgroundResource(fila.getFoto());
            holder.getNombre().setText(fila.getTexto());

            return row;

        }
    }

    /*
    La clase ViewHolder, para poder hacer el reciclado
     */
    private class ViewHolder {
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
