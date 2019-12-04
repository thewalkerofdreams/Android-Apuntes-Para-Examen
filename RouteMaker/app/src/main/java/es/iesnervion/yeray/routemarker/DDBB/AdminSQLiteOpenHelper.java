package es.iesnervion.yeray.routemarker.DDBB;

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
        db.execSQL("create table localizationPoint(id integer primary key AUTOINCREMENT, pointNumber integer, latitude real, longitude real, idRoute integer)");

        //aquí creamos la tabla de imagen (id, image, id_lista)
        db.execSQL("create table route(id integer primary key AUTOINCREMENT, name varchar(40))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int version1, int version2) {

        db.execSQL("drop table if exists localizationPoint");

        db.execSQL("create table localizationPoint(id integer primary key AUTOINCREMENT, pointNumber integer, latitude real, longitude real, idRoute integer)");

        db.execSQL("drop table if exists route");

        db.execSQL("create table route(id integer primary key AUTOINCREMENT, name varchar(40))");

    }
}
