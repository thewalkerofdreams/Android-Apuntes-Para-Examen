package es.iesnervion.yeray.galeriaimagenes.DDBB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MetodosDDBB {
    /*
    * Interfaz
    * Nombre: insertList
    * Comentario: Este método nos permite insertar una lista en la
    * base de datos.
    * Cabecera: public void insertList(Context context, String galleryName, int maxNumbersOfImages)
    * Entrada:
    *   -Context context
    *   -String galleryName
    *   -int maxNumbersOfImages
    * Postcondiciones: El método inserta una nueva lista en la base de datos.
    * */
    public void insertList(Context context, String galleryName, int maxNumbersOfImages){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "administracion", null, 1);

        SQLiteDatabase bd = admin.getWritableDatabase();

        ContentValues registro = new ContentValues();

        registro.put("galleryName", galleryName);
        registro.put("maxNumbersOfImages", maxNumbersOfImages);

        // los inserto en la base de datos
        bd.insert("itemLista", null, registro);

        bd.close();
    }

    /*
    * Interfaz
    * Nombre: getGalleryItem
    * Comentario: Este método nos permite obtener una galería de imagenes de la
    * base de datos.
    * Cabecera: public Cursor getGalleryItem(String galleryName)
    * Entrada:
    *   -Context context
    *   -String galleryName
    * Salida:
    *   -Cursor cursor
    * Postcondiciones: El método devuelve un tipo Cursor asociado al nombre, que
    * es la galería con el mismo nombre del parámetro de entrada, en el caso de
    * que no exista una galería con ese nombre el método devuelve null.
    * */
    public Cursor getGalleryItem(Context context, String galleryName){
        Cursor cursor;
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "administracion", null, 1);

        SQLiteDatabase bd = admin.getWritableDatabase();

        cursor = bd.rawQuery("select id, galleryName, maxNumbersOfImages from itemLista where galleryName= '"+galleryName+"'", null);
        //Si no se ha encontrado una galería con ese nombre cursor se encontrará vacío pero no a null
        if(!cursor.moveToFirst()){//Esto nos permite saber si cursor se encuentra vacío, además si no se encuentra mueve el cursor al primer resultado.
            cursor = null;
        }

        bd.close();
        return cursor;
    }

    /*
    * Interfaz
    * Nombre: getAllItemsList
    * Comentario: Este método nos permite obtener todas las filas de
    * la tabla itemLista.
    * Cabecera: public Cursor getAllItemsList(Context context)
    * Entrada:
    *   -Context context
    * Postcondiciones: Este método devuelve un tipo Cursor con todas las
    * filas de la tabla itemLista. Si la lista no contiene filas el método
    * devuelve null.
    * */
    public Cursor getAllItemsList(Context context){
        Cursor cursor;
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "administracion", null, 1);

        SQLiteDatabase bd = admin.getWritableDatabase();

        cursor = bd.rawQuery("select id, galleryName, maxNumbersOfImages from itemLista", null);
        //Si no se ha encontrado una galería con ese nombre cursor se encontrará vacío pero no a null
        if(!cursor.moveToFirst()){//Esto nos permite saber si cursor se encuentra vacío, además si no se encuentra mueve el cursor al primer resultado.
            cursor = null;
        }

        bd.close();
        return cursor;
    }

    /*
    * Interfaz
    * Nombre: insertImage
    * Comentario: Este método nos permite insertar una imagen en la base de datos.
    * Cabecera: public void insertImage(Context context, byte[] image, int idItemLista)
    * Entrada:
    *   -Context context
    *   -byte[] image
    *   -int idItemLista
    * Postcondiciones: El método inserta una nueva imagen en la base de datos.
    * */
    public void insertImage(Context context, byte[] image, int idItemLista){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "administracion", null, 1);

        SQLiteDatabase bd = admin.getWritableDatabase();

        ContentValues registro = new ContentValues();

        registro.put("image", image);
        registro.put("id_itemLista", idItemLista);

        // los inserto en la base de datos
        bd.insert("imagen", null, registro);

        bd.close();
    }

    /*
    * Interfaz
    * Nombre: getImagesOfAGallery
    * Comentario: Este método nos permite obtener las imagenes de
    * una galería de la tabla imagen.
    * Cabecera: public Cursor getImagesOfAGallery(Context context, int idGallery)
    * Entrada:
    *   -Context context
    *   -int idGallery
    * Salida:
    *   -Cursor cursor
    * Postcondiciones: El método devuelve cursor que contiene todas las imagenes
    * de la galería, si la galería no existe o no contiene imagenes devuelve null.
    * */
    public Cursor getImagesOfAGallery(Context context, int idGallery){
        Cursor cursor;

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "administracion", null, 1);

        SQLiteDatabase bd = admin.getWritableDatabase();

        cursor = bd.rawQuery("select id, image, id_itemLista from imagen where id_itemLista=" + idGallery, null);
        //Si no se ha encontrado una galería con ese nombre cursor se encontrará vacío pero no a null
        if(!cursor.moveToFirst()){//Esto nos permite saber si cursor se encuentra vacío, además si no se encuentra mueve el cursor al primer resultado.
            cursor = null;
        }

        bd.close();
        return cursor;
    }

    /*
    * Interfaz
    * Nombre: truncateItemLista
    * Comentario: Este método nos permite truncar los datos de la
    * tabla itemLista.
    * Cabecera: public void truncateItemLista(Context context)
    *   -Context context
    * Postcondiciones: El método trunca la tabla itemLista.
    * */
    public void truncateItemLista(Context context){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "administracion", null, 1);

        SQLiteDatabase bd = admin.getWritableDatabase();

        bd.execSQL("delete from itemLista");
        bd.close();
    }

    /*
    * Interfaz
    * Nombre: truncateImagen
    * Comentario: Este método nos permite truncar los datos de la
    * tabla imagen.
    * Cabecera: public void truncateImagen(Context context)
    *   -Context context
    * Postcondiciones: El método trunca la tabla imagen.
    * */
    public void truncateImagen(Context context){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "administracion", null, 1);

        SQLiteDatabase bd = admin.getWritableDatabase();

        bd.execSQL("delete from imagen");
        bd.close();
    }

    /*
    * Intefaz
    * Nombre: numbersOfImagesOfAGallery
    * Comentario: Este método nos permite saber el número de imagenes que
    * contiene una galería. Es decir cuantas filas existen en la tabla imagen
    * de la base de datos con un atributo idItemLista determinado.
    * Cabecera: public int numbersOfImagesOfAGallery(Context context, int idItemLista)
    * Entrada:
    *   -Context context
    *   -int idItemLista
    * Salida:
    *   -int numeroDeImagenes
    * Postcondiciones: El método devuelve un número entero asociado al nombre,
    * que son el número de imagenes de la galería. Si la galería no existe el
    * método devolverá -1.
    * */
    public int numbersOfImagesOfAGallery(Context context, int idItemLista){
        int numeroDeImagenes = -1;
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "administracion", null, 1);
        SQLiteDatabase bd = admin.getReadableDatabase();
        Cursor validate = bd.rawQuery("select * from itemLista where id = "+idItemLista, null);

        if(validate.moveToFirst()){//Si existe el item de la lista
            Cursor cursor = bd.rawQuery("select * from imagen where id_itemLista = "+idItemLista, null);
            numeroDeImagenes = cursor.getCount();
            cursor.close();
        }

        bd.close();
        return numeroDeImagenes;
    }
}
