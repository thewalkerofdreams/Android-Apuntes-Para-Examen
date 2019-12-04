package es.iesnervion.yeray.spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner;
    TextView text;
    ArrayList<String> items = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        items.add("Hamburguesa");
        items.add("Chorizo");
        items.add("Lengua");
        items.add("Salsa Rosa");

        text = findViewById(R.id.text01);
        spinner = findViewById(R.id.Spinner01);
        spinner.setOnItemSelectedListener(this);

        //Creamos un adaptador ArrayAdapter
        ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);

        //Especificamos el layout que aparecerá al desplegarse la lista
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Agregamos el adaptador al tipo spinner
        spinner.setAdapter(aa);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        text.setText(items.get(position));//Obtener
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        text.setText("");
    }
}
