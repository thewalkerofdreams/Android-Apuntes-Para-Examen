package es.iesnervion.yeray.routemarker.DDBB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.DateFormat;
import android.icu.text.RelativeDateTimeFormatter;
import android.text.format.DateUtils;

import java.util.Date;
import java.util.GregorianCalendar;

import es.iesnervion.yeray.routemarker.Utilities.Conversiones;

public class MetodosDDBB {
    /*
    * Interfaz
    * Nombre: insertRoute
    * Comentario: Este método nos permite insertar una ruta en la
    * base de datos.
    * Cabecera: public void insertRoute(Context context, String nameRoute)
    * Entrada:
    *   -Context context
    *   -String nameRoute
    * Precondiciones:
    *   -dateCreation debe tener el siguiente formato "yyyy-MM-dd HH:mm:ss".
    * Postcondiciones: El método inserta una nueva ruta en la base de datos.
    * */
    public void insertRoute(Context context, String nameRoute){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "administracion", null, 1);

        SQLiteDatabase bd = admin.getWritableDatabase();

        ContentValues registro = new ContentValues();

        registro.put("name", nameRoute);

        // los inserto en la base de datos
        bd.insert("route", null, registro);

        bd.close();
    }

    /*
    * Interfaz
    * Nombre: getRoute
    * Comentario: Este método nos permite obtener una ruta de la
    * base de datos.
    * Cabecera: public Cursor getRoute(Context context, String nameRoute)
    * Entrada:
    *   -Context context
    *   -String galleryName
    * Salida:
    *   -Cursor cursor
    * Postcondiciones: El método devuelve un tipo Cursor asociado al nombre, que
    * es la ruta con el mismo nombre que el parámetro de entrada, en el caso de
    * que no exista una ruta con ese nombre el método devuelve null.
    * */
    public Cursor getRoute(Context context, String nameRoute){
        Cursor cursor;
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "administracion", null, 1);

        SQLiteDatabase bd = admin.getWritableDatabase();

        cursor = bd.rawQuery("select id, name from route where name= '"+nameRoute+"'", null);
        //Si no se ha encontrado una ruta con ese nombre cursor se encontrará vacío pero no a null
        if(!cursor.moveToFirst()){//Esto nos permite saber si cursor se encuentra vacío, además si no se encuentra, mueve el cursor al primer resultado.
            cursor = null;
        }

        bd.close();
        return cursor;
    }

    /*
    * Interfaz
    * Nombre: elimnarRutaConPuntosDeLocalizacion
    * Comentario: Este método nos permite eliminar una ruta y todos los puntos
    * de localización que le pertenezcan.
    * Cabecera: public boolean elimnarRutaConPuntosDeLocalizacion(Context context, int idRoute)
    * Entrada:
    *   -Context context
    *   -int idRoute
    * Salida:
    *   -boolean eliminado
    * Postcondiciones: El método devuelve un valor booleano asociado al nombre, true si se ha
    * conseguido eliminar la ruta junto con sus puntos de localización o false en caso contrario.
    * */
    public boolean elimnarRutaConPuntosDeLocalizacion(Context context, int idRoute){
        boolean eliminado = false;
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "administracion", null, 1);

        SQLiteDatabase bd = admin.getWritableDatabase();

        //bd.rawQuery("delete from route where id= "+idRoute+"", null);
        //bd.rawQuery("delete from localizationPoint where idRoute= "+idRoute+"", null);
        String where01="id=?";
        String where02="idRoute=?";
        //int numberOFEntriesDeleted= db.delete(TABLE_NAME, where, new String[]{ID}) ;
        int numberOFEntriesDeleted= bd.delete("route", where01, new String[]{String.valueOf(idRoute)});
        if(numberOFEntriesDeleted > 0){
            eliminado = true;
        }
        bd.delete("localizationPoint", where02, new String[]{String.valueOf(idRoute)});

        return eliminado;
    }

    /*
    * Interfaz
    * Nombre: getAllRoutes
    * Comentario: Este método nos permite obtener todas las filas de
    * la tabla route.
    * Cabecera: public Cursor getAllRoutes(Context context)
    * Entrada:
    *   -Context context
    * Postcondiciones: Este método devuelve un tipo Cursor con todas las
    * filas de la tabla route. Si la lista no contiene filas el método
    * devuelve null.
    * */
    public Cursor getAllRoutes(Context context){
        Cursor cursor;
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "administracion", null, 1);

        SQLiteDatabase bd = admin.getWritableDatabase();

        cursor = bd.rawQuery("select id, name from route", null);
        //Si no se ha encontrado una galería con ese nombre cursor se encontrará vacío pero no a null
        if(!cursor.moveToFirst()){//Esto nos permite saber si cursor se encuentra vacío, además si no se encuentra mueve el cursor al primer resultado.
            cursor = null;
        }

        bd.close();
        return cursor;
    }

    /*
    * Interfaz
    * Nombre: insertLocalizationPoint
    * Comentario: Este método nos permite insertar un punto de localización en la base de datos.
    * Cabecera: public void insertLocalizationPoint(Context context, String pointName, double latitude, double longitude, int idRoute)
    * Entrada:
    *   -Context context
    *   -String pointName
    *   -double latitude
    *   -double longitude
    *   -int idRoute
    * Postcondiciones: El método inserta un nuevo punto de localización en la base de datos.
    * */
    public void insertLocalizationPoint(Context context, int pointNumber, double latitude, double longitude, int idRoute){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "administracion", null, 1);

        SQLiteDatabase bd = admin.getWritableDatabase();

        ContentValues registro = new ContentValues();

        registro.put("pointNumber", pointNumber);
        registro.put("latitude", latitude);
        registro.put("longitude", longitude);
        registro.put("idRoute", idRoute);

        // los inserto en la base de datos
        bd.insert("localizationPoint", null, registro);

        bd.close();
    }

    /*
    * Interfaz
    * Nombre: getPointsOfARoute
    * Comentario: Este método nos permite obtener las puntos de localización de
    * una ruta específica de la tabla localizationPoint.
    * Cabecera: public Cursor getPointsOfARoute(Context context, int idRoute)
    * Entrada:
    *   -Context context
    *   -int idRoute
    * Salida:
    *   -Cursor cursor
    * Postcondiciones: El método devuelve cursor que contiene todas los puntos de localización
    * de la ruta, si la ruta no existe o no contiene puntos de localización, se devuelve null.
    * */
    public Cursor getPointsOfARoute(Context context, int idRoute){
        Cursor cursor;

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "administracion", null, 1);

        SQLiteDatabase bd = admin.getWritableDatabase();

        cursor = bd.rawQuery("select id, pointNumber, latitude, longitude, idRoute from localizationPoint where idRoute=" + idRoute, null);
        //Si no se ha encontrado una ruta con ese nombre, cursor se encontrará vacío pero no a null
        if(!cursor.moveToFirst()){//Esto nos permite saber si cursor se encuentra vacío, además si no se encuentra mueve el cursor al primer resultado.
            cursor = null;
        }

        bd.close();
        return cursor;
    }

    /*
    * Interfaz
    * Nombre: truncateRoute
    * Comentario: Este método nos permite truncar los datos de la
    * tabla route.
    * Cabecera: public void truncateRoute(Context context)
    *   -Context context
    * Postcondiciones: El método trunca la tabla route.
    * */
    public void truncateRoute(Context context){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "administracion", null, 1);

        SQLiteDatabase bd = admin.getWritableDatabase();

        bd.execSQL("delete from route");
        bd.close();
    }

    /*
    * Interfaz
    * Nombre: truncateLocalizationPoint
    * Comentario: Este método nos permite truncar los datos de la
    * tabla localizationPoint.
    * Cabecera: public void truncateLocalizationPoint(Context context)
    *   -Context context
    * Postcondiciones: El método trunca la tabla localizationPoint.
    * */
    public void truncateLocalizationPoint(Context context){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "administracion", null, 1);

        SQLiteDatabase bd = admin.getWritableDatabase();

        bd.execSQL("delete from localizationPoint");
        bd.close();
    }

    /*
    * Intefaz
    * Nombre: numbersOfPointsOfARoute
    * Comentario: Este método nos permite saber el número de puntos de localización que
    * contiene una ruta. Es decir cuantas filas existen en la tabla localizationPoint
    * de la base de datos con un atributo idRoute determinado.
    * Cabecera: public int numbersOfPointsOfARoute(Context context, int idRoute)
    * Entrada:
    *   -Context context
    *   -int idRoute
    * Salida:
    *   -int numbersOfLocalizationPoints
    * Postcondiciones: El método devuelve un número entero asociado al nombre,
    * que son el número de puntos de localización de una ruta. Si la ruta no existe el
    * método devolverá -1.
    * */
    public int numbersOfPointsOfARoute(Context context, int idRoute){
        int numbersOfLocalizationPoints = -1;
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "administracion", null, 1);
        SQLiteDatabase bd = admin.getReadableDatabase();
        Cursor validate = bd.rawQuery("select * from route where id = "+idRoute, null);

        if(validate.moveToFirst()){//Si existe el item de la lista
            Cursor cursor = bd.rawQuery("select * from localizationPoint where idRoute = "+idRoute, null);
            numbersOfLocalizationPoints = cursor.getCount();
            cursor.close();
        }

        bd.close();
        return numbersOfLocalizationPoints;
    }
}
