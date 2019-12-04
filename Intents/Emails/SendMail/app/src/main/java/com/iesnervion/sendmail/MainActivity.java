package com.iesnervion.sendmail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView email, asunto, mensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email);
        asunto = findViewById(R.id.asunto);
        mensaje = findViewById(R.id.mensaje);
    }

    public void lanzarCorreo(View v){
        //Animaciones
        /*
        * Para hacer esta animacion he creado un nuevo recurso en 'res' de tipo animacion.
        * */
        Button boton = findViewById(R.id.enviar);
        Animation animacion = AnimationUtils.loadAnimation(this, R.anim.bounce);

        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2,20);
        animacion.setInterpolator(interpolator);
        boton.startAnimation(animacion);

        //Indicamos quien debe recibir el intent, en este caso es un intent implicito.
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("plain/text");
        //Metemos en el intent la informacion que queremos transferir.
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email.getText().toString()}); //Por alguna razon este tiene que ser un array de String
        intent.putExtra(Intent.EXTRA_SUBJECT, asunto.getText().toString()); //Esto es el asunto
        intent.putExtra(Intent.EXTRA_TEXT, mensaje.getText().toString()); //Este es el mensaje
        //Es lo que permite elegir con que aplicacion ejecutar.
        startActivity(Intent.createChooser(intent, ""));
    }
}
