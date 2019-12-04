package es.iesnervion.rmanzano.mesafutsalcompadre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText j1;
    private EditText j2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        j1 = findViewById(R.id.j1ET);
        j2 = findViewById(R.id.j2ET);

    }


    public void play(View view) {
        Intent intentDatos = new Intent(this, JuegoActivity.class);
        intentDatos.putExtra("j1",j1.getText().toString());
        intentDatos.putExtra("j2",j2.getText().toString());
        startActivity(intentDatos);
    }
}
