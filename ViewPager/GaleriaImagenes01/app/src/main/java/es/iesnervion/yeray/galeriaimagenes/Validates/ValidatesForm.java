package es.iesnervion.yeray.galeriaimagenes.Validates;

import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import es.iesnervion.yeray.galeriaimagenes.DDBB.MetodosDDBB;

public class ValidatesForm {

    /*
     * Interfaz
     * Nombre: valideData
     * Comentario: Este método nos permite verificar si los datos
     * del formulario son correctos. Criterio para que los datos sean correctos:
     * -La galería debe tener un nombre único.
     * -Debe tener un tamaño entre 20 y 100.
     * Este método enviará mensajes de error a la actividad en caso de valor no válido.
     * Cabecera: public boolean valideData(Context context, String nombreGaleria)
     * Entrada:
     *   -Context context
     *   -String nombreGaleria
     *   -Int numeroElementosMaximo
     * Salida:
     *   -boolean valido
     * Postcondiciones: La función devuelve un valor booleano asociado al
     * nombre, true si los datos de la galería son correctos o false
     * en caso contrario.
     * */
    public boolean valideData(Context context, String nombreGaleria, int numeroElementosMaximo){
        boolean valido = false;

        if(nombreGaleria == null || nombreGaleria.isEmpty()){
            Toast.makeText(context, "The name of the gallery is empty!", Toast.LENGTH_LONG).show();
        }else{
            if(!nombreUnico(context, nombreGaleria)){
                Toast.makeText(context, "The name of the gallery already exits!", Toast.LENGTH_LONG).show();
            }else{
                if(numeroElementosMaximo < 20 || numeroElementosMaximo > 100){
                    Toast.makeText(context, "The numbers of images must be between 20 and 100.", Toast.LENGTH_LONG).show();
                }else{
                    valido = true;
                }
            }
        }

        return valido;
    }

    /*
     * Interfaz
     * Nombre: nombreUnico
     * Comentario: Este método nos permite verificar si el nombre de la galería
     * del formulario es un nombre único en la lista.
     * Cabecera: public boolean nombreUnico(Context context, String nombreGaleria)
     * Entrada:
     *   -Context context
     *   -String nombreGaleria
     * Salida:
     *  -boolean valido
     * Postcondiciones: El método devuelve un valor booleano asociado al nombre,
     * true si el nombre es único y false en caso contrario.
     * */
    public boolean nombreUnico(Context context, String nombreGaleria){
        boolean valido = true;
        MetodosDDBB metodosDDBB = new MetodosDDBB();
        Cursor cursor = metodosDDBB.getAllItemsList(context);

        if(cursor != null && cursor.moveToFirst()){//Si la lista de galerías no se encuentra vacía
            do{
                if(cursor.getString(1).equals(nombreGaleria)){//Si el nombre coincide
                    valido = false;
                }
            }while (valido && cursor.moveToNext());//Mientras siga siendo válido y aún queden más items de la lista de galerías
        }

        return valido;
    }
}
