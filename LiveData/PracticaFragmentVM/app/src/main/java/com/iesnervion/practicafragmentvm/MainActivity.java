package com.iesnervion.practicafragmentvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import com.iesnervion.practicafragmentvm.Clases.CorreoVM;
import com.iesnervion.practicafragmentvm.Fragments.FragmentListado;

public class MainActivity extends AppCompatActivity{

    CorreoVM vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vm = ViewModelProviders.of(this).get(CorreoVM.class);

        FragmentListado fragListado = (FragmentListado)getSupportFragmentManager().findFragmentById(R.id.FrgListado); //Para administrar los fragmentos de la actividad.
    }

}
