package es.iesnervion.yeray.galeriaimagenes.Utilities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;

public class UtilidadConversiones {

    // convert from bitmap to byte array
    /*
    * Intefaz
    * Nombre: getBytes
    * Comentario: Este método nos permite convertir un dato bitmap a
    * un array de bytes.
    * Cabecera: public static byte[] getBytes(Bitmap bitmap)
    * Entrada:
    *   -Bitmap bitmap
    * Postcondiciones: El método devuelve un array de bytes asociado al
    * nombre, que es la conversión del tipo bitmap.
    * */
    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    // convert from byte array to bitmap
    /*
     * Intefaz
     * Nombre: getImage
     * Comentario: Este método nos permite convertir un array de bytes
     * a un tipo bitmap.
     * Cabecera: public static Bitmap getImage(byte[] image)
     * Entrada:
     *   -byte[] image
     * Postcondiciones: El método devuelve un tipo bitmap asociado al
     * nombre, que es la conversión del array de bytes.
     * */
    public static Bitmap getBitmap(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);//Esta mierda de función de Java da errores por todas partes y no te lo notifica. 4 horas...
    }

    /*
    * Interfaz
    * Nombre: intToByteArray
    * Comentario: Este método nos permite convertir un entero en un
    * array de bytes.
    * Cabecera: public static byte[] intToByteArray(int entero)
    * Entrada:
    *   -int entero
    * Salida;
    *   -byte[] ret
    * Postcondiciones: El método devuelve un array de bytes asociado
    * al nombre, que es la conversión del entero.
    * */
    public static byte[] intToByteArray(int entero)
    {
        byte[] ret = new byte[4];
        ret[3] = (byte) (entero & 0xFF);
        ret[2] = (byte) ((entero >> 8) & 0xFF);
        ret[1] = (byte) ((entero >> 16) & 0xFF);
        ret[0] = (byte) ((entero >> 24) & 0xFF);
        return ret;

        /*byte[] result = new byte[4];  //También sirve

        result[0] = (byte) (entero >> 24);
        result[1] = (byte) (entero >> 16);
        result[2] = (byte) (entero >> 8);
        result[3] = (byte) (entero >> 0);

        return result;*/

        /*BigInteger bigInt = BigInteger.valueOf(entero);//También sirve
        return bigInt.toByteArray();*/
    }

    /*
    * Interfaz
    * Nombre: byteArrayToInt
    * Comentario: Este método nos permite convertir un array de bytes
    * en un entero.
    * Cabecera: public static int byteArrayToInt(byte[] b)
    * Entrada:
    *   -byte[] b
    * Salida:
    *   -int ret
    * Postcondiciones: El método devuelve un número entero asociado
    * al nombre, que es la conversión del array de bytes.
    * */
    public static int byteArrayToInt(byte[] b)
    {
        return (b[3] & 0xFF) + ((b[2] & 0xFF) << 8) + ((b[1] & 0xFF) << 16) + ((b[0] & 0xFF) << 24);
    }

    /*
    * Interfaz
    * Nombre: getBytes
    * Comentario: Este método nos permite obtener un array de bytes de un
    * inputStream. En caso de error en la lectura del inputstream la función
    * lanzará una excepción del tipo IOException.
    * Cabecera: public byte[] getBytes(InputStream inputStream)
    * */
    public static byte[] getBytes(InputStream inputStream){
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        try{
            while ((len = inputStream.read(buffer)) != -1) {
                byteBuffer.write(buffer, 0, len);
            }
        }catch (IOException error){
            error.printStackTrace();
        }

        return byteBuffer.toByteArray();
    }

}
