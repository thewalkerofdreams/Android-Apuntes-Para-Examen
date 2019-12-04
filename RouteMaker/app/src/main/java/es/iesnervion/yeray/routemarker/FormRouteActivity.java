package es.iesnervion.yeray.routemarker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;
import es.iesnervion.yeray.routemarker.Entities.LocalizationPoint;
import es.iesnervion.yeray.routemarker.Validates.ValidatesForm;
import es.iesnervion.yeray.routemarker.ViewModels.FormRouteActivityVM;

public class FormRouteActivity extends AppCompatActivity {

    FormRouteActivityVM formRouteActivityVM;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_route);
        editText = findViewById(R.id.editText01);
        formRouteActivityVM = ViewModelProviders.of(this).get(FormRouteActivityVM.class);
    }

    /*
    * Interfaz
    * Nombre: guardarRuta
    * Comentario: Este método nos permite guardar una ruta en la base de datos.
    * Si el nombre de la ruta se encuentra en blanco o ya existe en la base de datos,
    * el método no guardará nada. Si se consigue almacenar la nueva ruta en la base de datos
    * se lanzará la actividad principal de la aplicación.
    * Cabecera: public void guardarRuta(View view)
    * Entrada:
    *   -View view
    * Postcondiciones: Si el nombre de la ruta es válido, se guardará la nueva ruta en
    * la base de datos y se lanzará la actividad MainActivity, en caso contrario se manda
    * un mensaje de error por pantalla.
    * */
    public void guardarRuta(View view){
        ValidatesForm validatesForm = new ValidatesForm();
        if(editText.getText() != null && !editText.getText().toString().equals("")){

            if(validatesForm.nombreUnico(this, editText.getText().toString())){
                Bundle extras = getIntent().getExtras().getBundle("Bundle");
                ArrayList<LocalizationPoint> localizationsPoints  = extras.getParcelableArrayList("ArrayListLocalizationPoints");
                formRouteActivityVM.insertarRutasYLocalizaciones(this, editText.getText().toString(), localizationsPoints);

                //Volvemos al MainActivity
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
            }else{
                Toast.makeText(FormRouteActivity.this, "The route name already exits in the data base.", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(FormRouteActivity.this, "The route name is empty.", Toast.LENGTH_SHORT).show();
        }
    }

}
