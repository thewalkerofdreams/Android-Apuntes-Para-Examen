package es.iesnervion.yeray.listaheterogenea;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    NewAdapter adapter;
    ArrayList<ItemLista01> items01 = new ArrayList<ItemLista01>();
    ArrayList<ItemLista02> items02 = new ArrayList<ItemLista02>();
    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = findViewById(R.id.listView01);
        for(int i = 0; i < 50000;i++){
            items01.add(new ItemLista01(R.drawable.mapache, R.drawable.mapache, "Prueba0"+i));
            items02.add(new ItemLista02("Prueba0"+i, R.drawable.mapache));
        }

        adapter = new NewAdapter(this, R.layout.list_item01, R.layout.list_item02, items01, items02);
        lista.setAdapter(adapter);
    }
}
