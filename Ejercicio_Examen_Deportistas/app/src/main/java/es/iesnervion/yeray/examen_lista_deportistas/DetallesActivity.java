package es.iesnervion.yeray.examen_lista_deportistas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import es.iesnervion.yeray.examen_lista_deportistas.Entities.Baloncescista;
import es.iesnervion.yeray.examen_lista_deportistas.Entities.Futbolista;
import es.iesnervion.yeray.examen_lista_deportistas.ViewModels.DetallesActivityVM;

public class DetallesActivity extends AppCompatActivity {

    DetallesActivityVM detallesActivityVM;
    ImageView foto;
    TextView textViewNombre, textViewPuntosPorPartido, textViewRebotesPorPartido, textViewAsistenciasPorPartido;
    EditText editTextNombre, editTextPuntosPorPartido, editTextRebotesPorPartido, editTextAsistenciasPorPartido;
    Spinner posiciones;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);

        // Get the ViewModel.
        detallesActivityVM = ViewModelProviders.of(this).get(DetallesActivityVM.class);
        posiciones = findViewById(R.id.spinner01);
        foto = findViewById(R.id.imageView01);
        textViewNombre = findViewById(R.id.textViewNombre);
        textViewPuntosPorPartido = findViewById(R.id.textViewPuntoPorPartido);
        textViewRebotesPorPartido = findViewById(R.id.textViewRebotesPorPartido);
        textViewAsistenciasPorPartido = findViewById(R.id.textViewAsistenciasPorPartido);
        editTextNombre = findViewById(R.id.editTextNombre);
        editTextPuntosPorPartido = findViewById(R.id.editTextPuntosPorPartido);
        editTextRebotesPorPartido = findViewById(R.id.editTextRebotesPorPartido);
        editTextAsistenciasPorPartido = findViewById(R.id.editTextAsistenciasPorPartido);

        Bundle extras = getIntent().getExtras().getBundle("Bundle");
        if(extras.getParcelable("Deportista") instanceof Futbolista){
            textViewPuntosPorPartido.setVisibility(View.INVISIBLE);
            textViewRebotesPorPartido.setVisibility(View.INVISIBLE);
            textViewAsistenciasPorPartido.setVisibility(View.INVISIBLE);
            editTextPuntosPorPartido.setVisibility(View.INVISIBLE);
            editTextRebotesPorPartido.setVisibility(View.INVISIBLE);
            editTextAsistenciasPorPartido.setVisibility(View.INVISIBLE);
        }else{
            posiciones.setVisibility(View.INVISIBLE);

            // Create the observer which updates the UI.
            final Observer<String> nameObserver02 = new Observer<String>() {
                @Override
                public void onChanged(@Nullable final String newName) {
                    // Update the UI, in this case, a TextView.
                    editTextPuntosPorPartido.setText(newName);
                }
            };

            // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
            detallesActivityVM.getNumeroPuntosPorPartido().observe(this, nameObserver02);

            // Create the observer which updates the UI.
            final Observer<String> nameObserver03 = new Observer<String>() {
                @Override
                public void onChanged(@Nullable final String newName) {
                    // Update the UI, in this case, a TextView.
                    editTextRebotesPorPartido.setText(newName);
                }
            };

            // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
            detallesActivityVM.getNumeroRebotesPorPartido().observe(this, nameObserver03);

            // Create the observer which updates the UI.
            final Observer<String> nameObserver04 = new Observer<String>() {
                @Override
                public void onChanged(@Nullable final String newName) {
                    // Update the UI, in this case, a TextView.
                    editTextAsistenciasPorPartido.setText(newName);
                }
            };

            // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
            detallesActivityVM.getNumeroAsistenciasPorPartido().observe(this, nameObserver04);
        }

        // Create the observer which updates the UI.
        final Observer<String> nameObserver01 = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String newName) {
                // Update the UI, in this case, a TextView.
                editTextNombre.setText(newName);
            }
        };

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        detallesActivityVM.getNombreDeportistaEdit().observe(this, nameObserver01);

        insertarDatosIniciales();//Insertamos los datos iniciales en el viewModel
        foto.setImageResource(detallesActivityVM.get_foto());
    }

    /*
     * Intefaz
     * Nombre: guardarDeportista
     * Comentario: Este método nos permite guardar los datos de un deportista.
     * Cabecera: public void guardarDeportista(View v)
     * Entrada:
     *   -View v
     * Postcondiciones: El método guarda los datos del deportista y lanza la aplicación
     * a la lista de inicio, en caso de error de algún dato se mostrará por pantalla el error y
     * no se modificará nada.
     * */
    public void guardarDeportista(View v){

        if(datosValidos()){
            Intent datos = new Intent();
            datos.putExtra("NombreDeportista", detallesActivityVM.get_nombreActualDeportista());
            datos.putExtra("NombreDeportistaEdit", editTextNombre.getText().toString());
            datos.putExtra("NumeroPuntosPorPartido", editTextPuntosPorPartido.getText().toString());
            datos.putExtra("NumeroRebotesPorPartido", editTextRebotesPorPartido.getText().toString());
            datos.putExtra("NumeroAsistenciasPorPartido", editTextAsistenciasPorPartido.getText().toString());
            datos.putExtra("Foto", String.valueOf(detallesActivityVM.get_foto()));
            datos.putExtra("Posiciones", detallesActivityVM.get_posiciones());
            datos.putExtra("BotonPulsado", "Save");
            Bundle extras = getIntent().getExtras().getBundle("Bundle");
            if(extras.getParcelable("Deportista") instanceof Futbolista){
                datos.putExtra("Deporte", "Futbol");
            }else{
                datos.putExtra("Deporte", "Baloncesto");
            }
            setResult(RESULT_OK, datos);
            finish();//Finalizamos el intento y volvemos a onActivityResult
        }
    }

    /*
    * Interfaz
    * Nombre: datosValidos
    * Comentario: Este método nos permite comprobar si los datos del formulario
    * son válidos.
    * Cabecera: private boolean datosValidos()
    * Salida:
    *   -boolean datosValidos
    * Postcondiciones: El método devuelve un valor booleano asociado al nombre, true
    * si los datos son válidos y false en caso contrario.
    * */
    private boolean datosValidos(){
        boolean datosValidos = true;
        Bundle extras = getIntent().getExtras().getBundle("Bundle");

        if(editTextNombre.getText() == null || editTextNombre.getText().toString().equals("")){
            Toast.makeText(DetallesActivity.this, "El nombre no debe estar en blanco.", Toast.LENGTH_SHORT).show();
            datosValidos = false;
        }

        if(datosValidos && extras.getParcelable("Deportista") instanceof Baloncescista){
            if(editTextPuntosPorPartido.getText() == null || editTextPuntosPorPartido.getText().toString().equals("") || Integer.valueOf(editTextPuntosPorPartido.getText().toString()) < 0){
                Toast.makeText(DetallesActivity.this, "Debes declarar un número de puntos por partido no negativo.", Toast.LENGTH_SHORT).show();
                datosValidos = false;
            }else{
                if(editTextRebotesPorPartido.getText() == null || editTextRebotesPorPartido.getText().toString().equals("") || Integer.valueOf(editTextRebotesPorPartido.getText().toString()) < 0){
                    Toast.makeText(DetallesActivity.this, "Debes declarar un número de rebotes por partido no negativo.", Toast.LENGTH_SHORT).show();
                    datosValidos = false;
                }else{
                    if(editTextAsistenciasPorPartido.getText() == null || editTextAsistenciasPorPartido.getText().toString().equals("") || Integer.valueOf(editTextAsistenciasPorPartido.getText().toString()) < 0){
                        Toast.makeText(DetallesActivity.this, "Debes declarar un número de asistencias por partido no negativo.", Toast.LENGTH_SHORT).show();
                        datosValidos = false;
                    }
                }
            }
        }

        return datosValidos;
    }

    /*
    * Interfaz
    * Nombre: insertarDatosIniciales
    * Comentario: Este método nos permite insertar datos iniciales en el viewmodel.
    * Cabecera: private void insertarDatosIniciales()
    * Postcondiciones: El método módifica el estado del viewmodel.
    * */
    private void insertarDatosIniciales(){

        Bundle extras = getIntent().getExtras().getBundle("Bundle");
        if(extras.getParcelable("Deportista") instanceof Futbolista){
            Futbolista futbolista = (Futbolista) extras.getParcelable("Deportista");
            detallesActivityVM.set_nombreActualDeportista(futbolista.get_nombre());
            editTextNombre.setText(futbolista.get_nombre());
            detallesActivityVM.set_posiciones(futbolista.get_posiciones());
            detallesActivityVM.set_foto(futbolista.get_foto());
            //Creamos un adaptador ArrayAdapter
            ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, detallesActivityVM.get_posiciones());

            //Especificamos el layout que aparecerá al desplegarse la lista
            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            //Agregamos el adaptador al tipo spinner
            posiciones.setAdapter(aa);
        }else{
            Baloncescista baloncescista = (Baloncescista) extras.getParcelable("Deportista");
            detallesActivityVM.set_nombreActualDeportista(baloncescista.get_nombre());
            editTextNombre.setText(baloncescista.get_nombre());
            editTextPuntosPorPartido.setText(String.valueOf(baloncescista.get_puntoPorPartido()));
            editTextRebotesPorPartido.setText(String.valueOf(baloncescista.get_rebotesPorPartido()));
            editTextAsistenciasPorPartido.setText(String.valueOf(baloncescista.get_asistenciasPorPartido()));
            detallesActivityVM.set_foto(baloncescista.get_foto());
        }
    }
    /*
     * Intefaz
     * Nombre: eliminarDeportista
     * Comentario: Este método nos permite elimnar un deportista.
     * Cabecera: public void guardarDeportista(View v)
     * Entrada:
     *   -View v
     * Postcondiciones: El método elimina al deportista de la lista.
     * */
    public void eliminarDeportista(View v){
        Intent datos = new Intent();
        datos.putExtra("NombreDeportista", detallesActivityVM.get_nombreActualDeportista());
        datos.putExtra("BotonPulsado", "Delete");
        setResult(RESULT_OK, datos);
        finish();//Finalizamos el intento y volvemos a onActivityResult
    }
}
