package es.iesnervion.yeray.listaequiposnba;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.ClipData;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import es.iesnervion.yeray.listaequiposnba.Models.Player;
import es.iesnervion.yeray.listaequiposnba.Models.Team;
import es.iesnervion.yeray.listaequiposnba.ViewModels.MainActivityVM;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView teamsListView, playersListView;
    ArrayAdapter<Team> adapter01;
    ArrayAdapter<Player> adapter02;
    MainActivityVM mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewModel = ViewModelProviders.of(this).get(MainActivityVM.class);

        teamsListView = findViewById(R.id.listView01);
        playersListView = findViewById(R.id.listView02);

        adapter01 = new ArrayAdapter<Team>(this, android.R.layout.simple_list_item_1, mViewModel.get_teamList());
        teamsListView.setAdapter(adapter01);
        adapter02 = new ArrayAdapter<Player>(this, android.R.layout.simple_list_item_1, mViewModel.get_playerList());
        playersListView.setAdapter(adapter02);

        teamsListView.setOnItemClickListener(this);
        playersListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getAdapter() == adapter01){//Si el elemento seleccionadao es de la lista de equipos
            Team item = (Team) parent.getItemAtPosition(position);//Obtenemos el item de la posición clicada
            mViewModel.cargarListaJugadores(item.get_id());
            //Recargamos la lista de jugadores
            adapter02 = new ArrayAdapter<Player>(this, android.R.layout.simple_list_item_1, mViewModel.get_playerList());
            playersListView.setAdapter(adapter02);
        }else{//Si el elemento seleccionadao es de la lista de jugadores
            Player item = (Player) parent.getItemAtPosition(position);//Obtenemos el item de la posición clicada
            Toast.makeText(getApplicationContext(), item.get_firstName()+" "+item.get_lastName(), Toast.LENGTH_LONG).show();
        }
    }

}
