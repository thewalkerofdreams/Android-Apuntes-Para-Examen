package es.iesnervion.yeray.camarafotos;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnCaptura;
    ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCaptura = findViewById(R.id.btnCapturaImagen);
        imagen = findViewById(R.id.imageView);

        btnCaptura.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//Creamos un intent mediante el siguiente MediaStore
        startActivityForResult(intent, 0);//Indicamos el intent y el código del proceso a realizar
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {//CódigoProceso, resultado (OK/CANCELED), el intent
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap) data.getExtras().get("data");//Sacamos los datos de la imagen en bitmap

        imagen.setImageBitmap(bitmap);//Guardamos la imagen
    }
}
