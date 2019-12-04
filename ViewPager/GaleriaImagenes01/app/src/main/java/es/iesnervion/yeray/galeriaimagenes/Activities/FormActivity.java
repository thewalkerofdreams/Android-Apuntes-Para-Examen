package es.iesnervion.yeray.galeriaimagenes.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import es.iesnervion.yeray.galeriaimagenes.R;
import es.iesnervion.yeray.galeriaimagenes.Validates.ValidatesForm;

public class FormActivity extends AppCompatActivity {

    EditText nameGalery, numberElements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        nameGalery = findViewById(R.id.nameGalery);
        numberElements = findViewById(R.id.numberElements);
    }


    /*
    * Interfaz
    * Nombre: sendGaleryInfo
    * Comentario: Este método nos permite devolver los datos
    * del formulario de creación de galería a la actividad
    * MainActivity. Esto solo ocurre si todos los datos son
    * válidos.
    * Cabecera: public void sendGaleryInfo(View v)
    * Entrada:
    *   -View v
    * Postcondiciones: El métode devuelve los datos del formulario al
    * MainActivity si los datos erán correctos, en caso contrario no
    * hace nada.
    * */
    public void sendGaleryInfo(View v){
        ValidatesForm validatesForm = new ValidatesForm();
        if(validatesForm.valideData(this, nameGalery.getText().toString(), Integer.valueOf(numberElements.getText().toString()))){
            Intent intent = new Intent(FormActivity.this, MainActivity.class);
            intent.putExtra("nombreGaleria", nameGalery.getText().toString());
            intent.putExtra("numeroElementos", numberElements.getText().toString());
            setResult(Activity.RESULT_OK, intent);
            finish();
        }

    }

    /*
    * Interfaz
    * Nombre: backToMain
    * Comentario: Este método nos permite volver a la actividad MainActivity.
    * Se ejecutará al presionar el botón BackToMain.
    * Cabecera: public void backToMain(View v)
    * Entrada:
    *   -View v
    * Postcondiciones: El método devuelve un mensaje de error al MainActivity,
    * para notificarle que no se creará un item de la lista de galerías.
    * */
    public void backToMain(View v){
        Intent intent = new Intent();
        setResult(Activity.RESULT_CANCELED, intent);
        finish();
    }
}
