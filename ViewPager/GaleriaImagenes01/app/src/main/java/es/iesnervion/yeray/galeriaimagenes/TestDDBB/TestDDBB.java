package es.iesnervion.yeray.galeriaimagenes.TestDDBB;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import es.iesnervion.yeray.galeriaimagenes.DDBB.MetodosDDBB;
import es.iesnervion.yeray.galeriaimagenes.R;
import es.iesnervion.yeray.galeriaimagenes.Utilities.UtilidadConversiones;

public class TestDDBB extends AppCompatActivity {

    TextView textProof01, textProof02;
    ImageView imageProof01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prueba_ddbb);

        textProof01 = findViewById(R.id.textProof01);
        textProof02 = findViewById(R.id.textProof02);
        imageProof01 = findViewById(R.id.imageProof01);

        MetodosDDBB metodosDDBB = new MetodosDDBB();

        //Truncamos la lista de itemLista e imagen
        metodosDDBB.truncateItemLista(this);
        metodosDDBB.truncateImagen(this);

        //Creamos una item gallery en la base de datos
        metodosDDBB.insertList(this, "Prueba01", 22);

        //Obtenemos el item de la lista
        Cursor cursor01 = metodosDDBB.getGalleryItem(this, "Prueba01");
        int idItemLista = cursor01.getInt(1);

        //Insertamos una imagen en ese item
        metodosDDBB.insertImage(this, UtilidadConversiones.intToByteArray(R.drawable.mapache01), idItemLista);
        metodosDDBB.insertImage(this, UtilidadConversiones.intToByteArray(R.drawable.mapache02), idItemLista);
        metodosDDBB.insertImage(this, UtilidadConversiones.intToByteArray(R.drawable.mapache03), idItemLista);

        //Obtenemos las imagenes de la gallery
        Cursor cursor02 = metodosDDBB.getImagesOfAGallery(this, idItemLista);

        //Colocamos los datos
        textProof01.setText(cursor01.getString(0));
        textProof02.setText(cursor02.getBlob(1).toString());

        //Bitmap bitmap = UtilidadConversiones.getBitmap(cursor02.getBlob(1));

        byte[] bytes = cursor02.getBlob(1);
        //Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        /*Bitmap bitmap = null;
        if (cursor02.moveToFirst()){
            byte[] bytes = cursor02.getBlob(1);
            bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        }*/

        Bitmap b = BitmapFactory.decodeResource(getResources(), UtilidadConversiones.byteArrayToInt(bytes));

        Bitmap scaled = Bitmap.createScaledBitmap(b, 150, 100, true);

        imageProof01.setImageBitmap(scaled);
    }
}
