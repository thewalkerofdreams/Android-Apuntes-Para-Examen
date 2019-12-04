package es.iesnervion.yeray.routemarker.Validates;

import android.content.Context;
import android.database.Cursor;

import es.iesnervion.yeray.routemarker.DDBB.MetodosDDBB;

public class ValidatesForm {

    /*
     * Interfaz
     * Nombre: nombreUnico
     * Comentario: Este método nos permite verificar si el nombre de la ruta
     * del formulario es un nombre único en la base de datos.
     * Cabecera: public boolean nombreUnico(Context context, String nombreRuta)
     * Entrada:
     *   -Context context
     *   -String nombreRuta
     * Salida:
     *  -boolean valido
     * Postcondiciones: El método devuelve un valor booleano asociado al nombre,
     * true si el nombre es único y false en caso contrario.
     * */
    public boolean nombreUnico(Context context, String nombreRuta){
        boolean valido = true;
        MetodosDDBB metodosDDBB = new MetodosDDBB();
        Cursor cursor = metodosDDBB.getAllRoutes(context);

        if(cursor != null && cursor.moveToFirst()){//Si la lista rutas no se encuentra vacía
            do{
                if(cursor.getString(1).equals(nombreRuta)){//Si el nombre coincide
                    valido = false;
                }
            }while (valido && cursor.moveToNext());//Mientras siga siendo válido y aún queden más items de la lista de galerías
        }

        return valido;
    }
}
