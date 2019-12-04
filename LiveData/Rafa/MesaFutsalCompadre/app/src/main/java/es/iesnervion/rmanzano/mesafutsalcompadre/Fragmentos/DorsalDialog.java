package es.iesnervion.rmanzano.mesafutsalcompadre.Fragmentos;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import es.iesnervion.rmanzano.mesafutsalcompadre.Fila;
import es.iesnervion.rmanzano.mesafutsalcompadre.R;

/*
Esta clase de momento no se usa, porque no he podido hacer que funcione bien,
aunque seria lo mas correcto sacar fuera el Fragment y usarlo desde una clase externa
 */
public class DorsalDialog extends DialogFragment {
    private TextView dorsal;
    //private JuegoActivity ja;
    private Fila fila;

    public Dialog onCreateDialog(Bundle savedInstanceState, Activity ja) {

        AlertDialog.Builder builder = new AlertDialog.Builder(ja.getBaseContext());
        // Get the layout inflater
        LayoutInflater inflater = ja.getLayoutInflater();
        //builder.

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.alertdialog, null))

                // Add action buttons
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dorsal = getView().findViewById(R.id.etDorsal);
                        fila = new Fila(R.drawable.amarilla, dorsal.getText().toString());
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        return builder.create();
    }
}

