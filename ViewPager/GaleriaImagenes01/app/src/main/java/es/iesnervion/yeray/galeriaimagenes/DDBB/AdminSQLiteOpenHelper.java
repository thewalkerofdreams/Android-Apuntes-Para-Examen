package es.iesnervion.yeray.galeriaimagenes.DDBB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    public AdminSQLiteOpenHelper(Context context, String nombre, SQLiteDatabase.CursorFactory factory, int version) {

        super(context, nombre, factory, version);

    }

    @Override

    public void onCreate(SQLiteDatabase db) {

        //aquí creamos la tabla de itemLista (id, galleryName, maxNumbersOfImages)
        db.execSQL("create table itemLista(id integer primary key AUTOINCREMENT, galleryName text, maxNumbersOfImages integer)");

        //aquí creamos la tabla de imagen (id, image, id_lista)
        db.execSQL("create table imagen(id integer primary key AUTOINCREMENT, image blob, id_itemLista integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int version1, int version2) {

        db.execSQL("drop table if exists itemLista");

        db.execSQL("create table itemLista(id integer primary key AUTOINCREMENT, galleryName text, maxNumbersOfImages integer)");

        db.execSQL("drop table if exists imagen");

        db.execSQL("create table imagen(id integer primary key AUTOINCREMENT, image blob, id_itemLista integer)");

    }
}
